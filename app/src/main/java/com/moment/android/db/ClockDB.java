package com.moment.android.db;

import org.litepal.crud.DataSupport;

public class ClockDB extends DataSupport{
    private String FruitName;
    private String EventName;
    private int WorkTime;
    private boolean Ready;

    public boolean getReady(){return Ready;}
    public void setReady(boolean Ready){this.Ready=Ready;}

    public String getFruitName(){
        return FruitName;
    }
    public void setFruitName(String FruitName){
        this.FruitName=FruitName;
    }

    public String getEventName(){
        return EventName;
    }
    public void setEventName(String EventName){
        this.EventName=EventName;
    }

    public int getWorkTime(){
        return WorkTime;
    }
    public void setWorkTime(int WorkTime){
        this.WorkTime=WorkTime;
    }

}
