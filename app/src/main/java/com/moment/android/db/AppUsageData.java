package com.moment.android.db;

import org.litepal.crud.DataSupport;

public class AppUsageData extends DataSupport{
    private String AppsOneN;
    private String AppsTwoN;
    private String AppsThreeN;
    private String AppsFourN;
    private String AppsFiveN;
    private String  TimeA;
    private long AppsOneT;
    private long AppsTwoT;
    private long AppsThreeT;
    private long AppsFourT;
    private long AppsFiveT;

    public String setTimeA(){
        return TimeA;
    }
    public void setTimeA(String TimeA){
        this.TimeA=TimeA;
    }

    public String getAppsOneN(){
        return AppsOneN;
    }
    public void setAppsOneN(String AppsOneN){
        this.AppsOneN=AppsOneN;
    }

    public String getAppsTwoN(){
        return AppsTwoN;
    }
    public void setAppsTwoN(String AppsTwoN){
        this.AppsTwoN=AppsTwoN;
    }
    public String getAppsThreeN(){
        return AppsThreeN;
    }
    public void setAppsThreeN(String AppsThreeN){
        this.AppsThreeN=AppsThreeN;
    }
    public String getAppsFourN(){
        return AppsFourN;
    }
    public void setAppsFourN(String AppsFourN){
        this.AppsFourN=AppsFourN;
    }
    public String getAppsFiveN(){
        return AppsFiveN;
    }
    public void setAppsFiveN(String AppsFiveN){
        this.AppsFiveN=AppsFiveN;
    }
    public long getAppsOneT(){
        return AppsOneT;
    }
    public void setAppsOneT(long AppsOneT){
        this.AppsOneT=AppsOneT;
    }

    public long getAppsTwoT(){
        return AppsTwoT;
    }
    public void setAppsTwoT(long AppsTwoT){
        this.AppsTwoT=AppsTwoT;
    }
    public long getAppsThreeT(){
        return AppsThreeT;
    }
    public void setAppsThreeT(long AppsThreeT){
        this.AppsThreeT=AppsThreeT;
    }
    public long getAppsFourT(){
        return AppsFourT;
    }
    public void setAppsFourT(long AppsFourT){
        this.AppsFourT=AppsFourT;
    }
    public long getAppsFiveT(){
        return AppsFiveT;
    }
    public void setAppsFiveT(long AppsFiveT){
        this.AppsFiveT=AppsFiveT;
    }

}