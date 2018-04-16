package com.example.hemanthkumar.create_event;

/**
 * Created by hemanthkumar on 22/3/18.
 */

public class Data {
    String title,details;
    String date,time;

    public Data(String title, String details, String date, String time) {
        this.title = title;
        this.details = details;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
