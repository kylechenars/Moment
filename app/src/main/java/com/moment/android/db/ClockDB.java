package com.moment.android.db;

import org.litepal.crud.DataSupport;

public class ClockDB extends DataSupport{
    //private int NumberId;
    private String EventName;
    private int WorkTime;
    private int RelxTime;
    private int NTimes;
    private int LongRelxTime;

    /*public int getNumberId(){return NumberId;}
    public void setNumberId(int NumberId){this.NumberId=NumberId;}*/

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

    public int getRelxTime(){
        return RelxTime;
    }
    public void setRelxTime(int RelxTime){
        this.RelxTime=RelxTime;
    }

    public int getNTimes(){
        return NTimes;
    }
    public void setNTimes(int NTimes){
        this.NTimes=NTimes;
    }

    public int getLongRelxTime(){
        return LongRelxTime;
    }
    public void setLongRelxTime(int LongRelxTime){
        this.LongRelxTime=LongRelxTime;
    }
}
