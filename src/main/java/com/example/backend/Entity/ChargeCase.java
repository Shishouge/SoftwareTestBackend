package com.example.backend.Entity;

public class ChargeCase {
    public String id;
    public int year;
    public int month;
    public int num;
    public int time;
    public double expectedOutput;

    // 要有否则parse json的时候报错
    public ChargeCase(){

    }

    public ChargeCase(int year, int month, int num, int time, double expectedOutput) {
        this.year = year;
        this.month = month;
        this.num = num;
        this.time = time;
        this.expectedOutput = expectedOutput;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTime() {
        return time;
    }

    public void setC(int time) {
        this.time = time;
    }

    public double getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(double expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
