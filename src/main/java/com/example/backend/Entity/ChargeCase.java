package com.example.backend.Entity;

public class ChargeCase {

    public String id;
    public int telTime;
    public int delayPayTimes;
    public String expectedResult;

    // 要有否则parse json的时候报错
    public ChargeCase(){

    }

    public ChargeCase(int telTime, int delayPayTimes, String expectedOutput) {
        this.telTime = telTime;
        this.delayPayTimes = delayPayTimes;
        this.expectedResult = expectedOutput;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTelTime() {
        return telTime;
    }

    public void setTelTime(int telTime) {
        this.telTime = telTime;
    }

    public int getDelayPayTimes() {
        return delayPayTimes;
    }

    public void setDelayPayTimes(int delayPayTimes) {
        this.delayPayTimes = delayPayTimes;
    }

    public String getExpectedOutput() {
        return expectedResult;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedResult = expectedOutput;
    }


}
