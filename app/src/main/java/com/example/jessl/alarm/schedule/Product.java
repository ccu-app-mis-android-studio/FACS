package com.example.jessl.alarm.schedule;

public class Product {
    private	int	id;
    private String name;
    private	String desc;
    private	String date;
    private	String time;
    private boolean reminder;

    public Product(int id, String name, String desc, String date, String time) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    public Product(String name, String desc, String dueDate, String dueTime) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }
}
