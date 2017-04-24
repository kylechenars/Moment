package com.moment.android.db;

/**
 * Created by kyle on 2017/4/19.
 */

public class Person {
    private String id;
    private String password;
    private String city;
    private String email;
    private String school;

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

    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city=city;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getSchool(){
        return school;
    }
    public void setSchool(String school){
        this.school=school;
    }
}
