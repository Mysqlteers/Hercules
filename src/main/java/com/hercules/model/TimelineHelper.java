package com.hercules.model;

public class TimelineHelper {

    private int number;
    private String startDate;

    public TimelineHelper(int number, String startDate) {
        this.number = number;
        this.startDate = startDate;
    }

    public TimelineHelper() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
