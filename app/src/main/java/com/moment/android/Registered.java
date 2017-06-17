package com.moment.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moment.android.BmobDB.PersonB;
import com.moment.android.db.Person;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Registered extends AppCompatActivity implements View.OnClickListener{
    private EditText R_Id;
    private EditText R_PW;
    private Button Y_Reg;
    private String Id;
    private String PassWord;

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    //传是否有网
    private boolean key=false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_layout);
        Toolbar toolbar=(Toolbar)findViewById(R.id.regi_toolbar);
        setSupportActionBar(toolbar);
        R_Id=(EditText)findViewById(R.id.r_E_Id);
        R_PW=(EditText)findViewById(R.id.r_E_PW);


        Y_Reg=(Button)findViewById(R.id.y_reg);
        Y_Reg.setOnClickListener(this);
        //
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }

    public void addToBmob(String id,String pw){
        PersonB personB=new PersonB();
        personB.setID(id);
        personB.setPassWord(pw);
        personB.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    //成功
                }else {
                    //失败
                }
            }
        });
    }

    public void saveToLocalDB(String id,String pw){
        Person personR=new Person();
        personR.setId(id);
        personR.setPassword(pw);
        personR.save();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.y_reg:
                if (key==true){
                    //先存入云数据库
                    addToBmob(Id=R_Id.getText().toString(),Id=R_Id.getText().toString());
                    Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();
                    saveToLocalDB(Id=R_Id.getText().toString(),Id=R_Id.getText().toString());
                    finish();
                }else if (key==false){
                    Toast.makeText(this,"没有网络不能注册",Toast.LENGTH_SHORT).show();
                }
                break;
            default:break;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            ConnectivityManager connectivityManager=(ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null && networkInfo.isAvailable()){
                key=true;
            }else {
                key=false;
            }
        }
    }
}
