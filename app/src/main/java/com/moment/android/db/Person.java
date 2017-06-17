package com.moment.android.db;

import org.litepal.crud.DataSupport;

public class Person extends DataSupport{
    private String id;
    private String password;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
}