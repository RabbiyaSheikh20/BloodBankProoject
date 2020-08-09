package com.example.bloodbankprooject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class EmailActivity extends AppCompatActivity {
EditText e1;
Button b1;
String number;
Button send;
final int SEND_SMS_PERMISSION_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Intent i=getIntent();
       number=i.getStringExtra("contactt");

        send=(Button)findViewById(R.id.btn);
        send.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS))
        {
            send.setEnabled(true);
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_CODE);
        }



    }
    public void msgg(View view)
    {
        e1=(EditText)findViewById(R.id.msg);
        String m=e1.getText().toString();
        if(number==null || number.length()==0 || m==null || m.length()==0)
        {
            Toast.makeText(this,"Please type some thing",Toast.LENGTH_SHORT).show();
            return;
        }
        if(checkPermission(Manifest.permission.SEND_SMS))
        {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(number,null,m,null,null);
            Toast.makeText(this,"Message sent!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();

        }

    }




    public boolean checkPermission(String permission)
    {
        int check= ContextCompat.checkSelfPermission(this,permission);
        return (check== PackageManager.PERMISSION_GRANTED);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.home:
                Intent in=new Intent(this,choice2.class);
                startActivity(in);
                finish();
                return true;
            case R.id.lg:
                Intent inn=new Intent(this,choiceactivity.class);
                startActivity(inn);
                finish();
                Toast.makeText(getApplicationContext(),"Successfully Logged out!", LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


