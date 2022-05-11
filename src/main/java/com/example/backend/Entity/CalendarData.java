package com.example.backend.Entity;

public class CalendarData {
    public String id;
    public int year;
    public int month;
    public int day;
    public String expectation;

    public CalendarData(String id, int year, int month, int day, String expectation) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.expectation = expectation;
    }

    public CalendarData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }
}
