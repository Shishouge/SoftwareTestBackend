package com.example.backend.Solution.Charges;

public class Charges {
    public double getCharge(int num, double time) {
        double charge;
        double basic = 25;
        double cost = 0.15;
        if (checkValid(num, time)) {
            charge = basic + cost * time * (1 - getDiscount(num, time));
        } else {
            // 无效输入
            return -1;
        }
        return charge;
    }

    private boolean checkValid(int num, double time) {
        if (num <0 || time < 0) return false;
        return true;
    }

    private double getDiscount(int num, double time) {
        double discount = 0;
        if (time > 300) {
            if (num <= 6) {
                discount = 0.03;
            }
        } else if (time > 180) {
            if (num <= 3) {
                discount = 0.025;
            }
        } else if (time > 120) {
            if (num <= 3) {
                discount = 0.02;
            }
        } else if (time > 60) {
            if (num <= 2) {
                discount = 0.015;
            }
        } else {
            if (num <= 1) {
                discount = 0.01;
            }
        }
        return discount;
    }
}
