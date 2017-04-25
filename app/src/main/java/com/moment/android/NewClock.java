package com.moment.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//新建一个番茄时钟
public class NewClock extends AppCompatActivity implements View.OnClickListener{

    private EditText editName;
    private EditText editWork;
    private EditText editNoWork;
    private EditText editHowMTimes;
    private EditText editLNoWork;
    private Button buttonNew;

    public String inputTital;
    public String WorkTime;
    public String NoWorkTime;
    public String Times;
    public String LNoWork;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_new);
        buttonNew=(Button)findViewById(R.id.buttonMakeNew);
        buttonNew.setOnClickListener(this);
        editName=(EditText)findViewById(R.id.editNameOfEvent);
        editWork=(EditText)findViewById(R.id.editWorkRange);
        editNoWork=(EditText)findViewById(R.id.editNoWorkRange);
        editHowMTimes=(EditText)findViewById(R.id.editHowManyTimes);
        editLNoWork=(EditText)findViewById(R.id.editLongTimeNoWork);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonMakeNew:
                Intent intent=new Intent(NewClock.this,Clock.class);
                inputTital=editName.getText().toString();
                WorkTime=editWork.getText().toString();
                intent.putExtra("tital_data",inputTital);
                intent.putExtra("time_work",WorkTime);
                startActivity(intent);
                break;
            default:break;
        }
    }
}
