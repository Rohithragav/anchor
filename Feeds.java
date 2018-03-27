package com.developer.rohithragav.anchor;

/**
 * Created by ROHITH RAGAV on 4/19/2017.
 */

public class Feeds  {

    public String date;
    public String time;
    public String title;
    public String desc;
    public String place;
    public Feeds(String date,String time,String title,String desc,String place ){
        this.date = date;
        this.time = time;
        this.title = title;
        this.desc = desc;
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }
}
