package com.example.backend.Entity;

public class TriangleCase {
    public String id;
    public int a;
    public int b;
    public int c;
    public String expectedOutput;

    // 要有否则parse json的时候报错
    public TriangleCase(){

    }

    public TriangleCase(String id, int a, int b, int c, String expectedOutput) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
        this.expectedOutput = expectedOutput;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}

