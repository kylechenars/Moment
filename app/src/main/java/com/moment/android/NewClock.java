package com.moment.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moment.android.db.ClockDB;

import org.litepal.tablemanager.Connector;

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

        Toolbar toolbar=(Toolbar)findViewById(R.id.clocknew_toolbar);
        setSupportActionBar(toolbar);
        //返回箭头toolbar上的
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        buttonNew=(Button)findViewById(R.id.buttonMakeNew);
        buttonNew.setOnClickListener(this);
        editName=(EditText)findViewById(R.id.editNameOfEvent);
        editWork=(EditText)findViewById(R.id.editWorkRange);
        editNoWork=(EditText)findViewById(R.id.editNoWorkRange);
        editHowMTimes=(EditText)findViewById(R.id.editHowManyTimes);
        editLNoWork=(EditText)findViewById(R.id.editLongTimeNoWork);
    }
    //HomeAsUp点击事件，返回上一个活动
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonMakeNew:
                int n=0;
                if (n<1){
                inputTital=editName.getText().toString();
                WorkTime=editWork.getText().toString();
                NoWorkTime=editNoWork.getText().toString();
                Times=editHowMTimes.getText().toString();
                LNoWork=editLNoWork.getText().toString();
                n++;}
                if (n==1){
                ClockDB clockDB=new ClockDB();
                clockDB.setEventName(inputTital);
                clockDB.setWorkTime(Integer.parseInt(WorkTime));
                clockDB.setRelxTime(Integer.parseInt(NoWorkTime));
                clockDB.setNTimes(Integer.parseInt(Times));
                clockDB.setLongRelxTime(Integer.parseInt(LNoWork));
                clockDB.save();
                n++;}
                if (n==2){
                Intent intent=new Intent(NewClock.this,Clock.class);
                intent.putExtra("tital_data",inputTital);
                startActivity(intent);
                n=0;}
                break;
            default:break;
        }
    }
}
