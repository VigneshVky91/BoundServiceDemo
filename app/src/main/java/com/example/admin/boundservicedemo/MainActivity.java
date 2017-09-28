package com.example.admin.boundservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.admin.boundservicedemo.MyService.MyBinder;

public class MainActivity extends AppCompatActivity {

    public static int FLAG = 1;
    EditText editText,editText2;
    MyService mservice;
    boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
    }


    public void bindMethod(View view)
    {
        Intent i = new Intent(this,MyService.class);
        bindService(i,sc, Context.BIND_AUTO_CREATE);
        Toast.makeText(this,"Service started",Toast.LENGTH_LONG).show();
        status = true;
    }
    public void unbindMethod(View view)
    {
        if (status==true)
        {
            unbindService(sc);
            Toast.makeText(this,"Unbinded Service",Toast.LENGTH_LONG).show();
            status=false;
        }
    }
    public void addMethod(View view)
    {
        if(status)
        {

            int num1 = Integer.parseInt(editText.getText().toString());
            int num2 = Integer.parseInt(editText2.getText().toString());
            int res = mservice.add(num1,num2);
            Toast.makeText(this,"Sum = "+res,Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"First Bind the service",Toast.LENGTH_LONG).show();
        }
    }

    ServiceConnection sc = new ServiceConnection()
    {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            MyBinder binder = (MyBinder)service;
            mservice = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {

        }
    };

}
