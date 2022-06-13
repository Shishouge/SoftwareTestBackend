package com.example.backend.Solution.Charges;

import java.math.BigDecimal;

public class Charges {

    public String getCharge(int telTime,int delayTimes) {
        Double discount = getDiscount(delayTimes,telTime);
//        String charge = String.format("%.3f", 25 + 0.15 * teleMinutes * discount);//
//        BigDecimal b1 = new BigDecimal(Double.valueOf(0.15));
//        BigDecimal b2 = new BigDecimal(Double.valueOf(teleMinutes));
//        BigDecimal b3 = new BigDecimal(Double.valueOf(discount));
//        Double res = b1.multiply(b2).multiply(b3).doubleValue() + 25;
//        String charge = String.format("%.2f",res);
        String charge = String.format("%.4f", 25 + 0.15 * telTime * (1-discount));
        if(charge.indexOf(".") > 0){
            //正则表达
            charge = charge.replaceAll("0+?$", "");//去掉后面无用的零
            charge = charge.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return charge;
    }

    private boolean checkFirst(int telTime) {
        if (telTime < 0 || telTime >= 44640) return false;
        return true;
    }

    private boolean checkLast(int delayTimes) {
        if (delayTimes < 0 || delayTimes > 11) return false;
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

    public String checkType(int telTime,int delayTimes){
        if(!checkFirst(telTime))
            return "INVALID TELTIME";
        if(!checkLast(delayTimes))
            return "INVALID DELAYTIMES";
        return getCharge(telTime, delayTimes);
    }
}
