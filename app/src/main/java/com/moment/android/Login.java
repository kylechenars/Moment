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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moment.android.BmobDB.PersonB;
import com.moment.android.db.Person;

import org.litepal.crud.DataSupport;

import java.util.List;

import javax.crypto.interfaces.PBEKey;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SQLQueryListener;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    //boolean p;
    //public String EQ;
    private String LoginID;
    private String LoginPW;
    private EditText IDName;
    private EditText PassW;
    private boolean keyS;
    private String locPw;
    private boolean netkey=false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_registered);
        Toolbar toolbar=(Toolbar)findViewById(R.id.or_toolbar);
        setSupportActionBar(toolbar);
        //按钮
        Button Login=(Button)findViewById(R.id.login);
        Login.setOnClickListener(this);
        Button Reg=(Button)findViewById(R.id.registered);
        Reg.setOnClickListener(this);
        //EditText
        IDName=(EditText)findViewById(R.id.on_Id);
        PassW=(EditText)findViewById(R.id.on_PW);
        //
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context,Intent intent){
            ConnectivityManager connectivityManager=(ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.isAvailable()){
                netkey=true;
            }else {
                netkey=false;
            }
        }
    }

    public void getEditData(){
        LoginID=IDName.getText().toString();
        LoginPW=PassW.getText().toString();
    }
    
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.login:
                    getEditData();
                    if (TextUtils.isEmpty(LoginID)||TextUtils.isEmpty(LoginPW)||netkey){
                        Toast.makeText(this,"登陆失败！",Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent2=new Intent(Login.this,ReadyLogin.class);
                        intent2.putExtra("PW",LoginID);
                        startActivity(intent2);
                    }
                    break;
                case R.id.registered:
                    Intent intent1=new Intent(Login.this,Registered.class);
                    startActivity(intent1);
                    break;
                default:break;
            }
    }
}