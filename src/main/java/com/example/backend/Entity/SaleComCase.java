package com.example.backend.Entity;

public class SaleComCase {
    public String id;
    public int mainUnit;
    public int screen;
    public int perpheral;
    public String expectedOutput;

    public SaleComCase(){

    }

    public SaleComCase(String id, int mainUnit, int screen, int perpheral, String expectedOutput) {
        this.id = id;
        this.mainUnit = mainUnit;
        this.screen = screen;
        this.perpheral = perpheral;
        this.expectedOutput = expectedOutput;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(int mainUnit) {
        this.mainUnit = mainUnit;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public int getPerpheral() {
        return perpheral;
    }

    public void setPerpheral(int perpheral) {
        this.perpheral = perpheral;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
