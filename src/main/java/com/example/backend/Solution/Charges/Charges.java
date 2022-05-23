package com.example.backend.Solution.Charges;

public class Charges {

    public double getCharge(int num, double time, int year, int month) {
        double charge;
        double basic = 25;
        double cost = 0.15;
        if (checkValid(num, time, year, month)) {
            charge = basic + cost * time * (1 - getDiscount(num, time));
        } else {
            // 无效输入
            return -1;
        }
        double a =Math.round(charge * 100);
        double b = a * 0.01;
        return Math.round(charge * 100) * 0.01;
    }

    private boolean checkValid(int num, double time, int year, int month) {
        if (time < 0 || time > getTimeBoundary(year, month)) return false;
        if (num < 0 || num >= month) return false;
        return true;
    }

    private int getTimeBoundary(int year, int month) {
        if (month != 2 ) {
            if (month == 4 || month == 6 || month == 9 || month ==11) {
                return 30 * 24 * 60;
            }
            return 31 * 24 * 60;
        } else {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                return 29 * 24 * 60;
            }
            return 28 * 24 * 60;
        }
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
