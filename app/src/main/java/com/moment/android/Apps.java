package com.moment.android;

public class Apps {
    private String name;
    private String ForegroundTime;

    public Apps(String name, String ForegroundTime){
        this.name=name;
        this.ForegroundTime=ForegroundTime;
    }

    public String getName(){
        return name;
    }

    public String getForegroundTime(){
        return ForegroundTime;
    }
}
