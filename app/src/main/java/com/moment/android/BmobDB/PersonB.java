package com.moment.android.BmobDB;

import cn.bmob.v3.BmobObject;

public class PersonB extends BmobObject{
    private String ID;
    private String PassWord;

    public String getID(){
        return ID;
    }
    public void setID(String ID){
        this.ID=ID;
    }

    public String getPassWord(){
        return PassWord;
    }
    public void setPassWord(String PassWord){
        this.PassWord=PassWord;
    }
}