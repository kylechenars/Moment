package com.moment.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class ReadyLogin extends AppCompatActivity {
    private TextView PerID;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readylogin);
        Toolbar toolbar=(Toolbar)findViewById(R.id.ready_toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        String ID=intent.getStringExtra("PW");
        PerID=(TextView)findViewById(R.id.wel_Id);
        PerID.setText(ID);
    }
}
