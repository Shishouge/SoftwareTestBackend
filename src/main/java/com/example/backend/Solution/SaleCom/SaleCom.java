package com.example.backend.Solution.SaleCom;

import java.math.BigDecimal;

public class SaleCom {

    private int mainUnit;//主机数量
    private int screen;//显示器数量
    private int perpheral;//外设数量
    private double reward;//佣金
    private double sales;//销售额

    private int priceMainUnit = 25;//主机价格
    private int minMainUnit = 1;//主机最小销售量
    private int maxMainUnit = 70;//主机最大销售量

    private int priceScreen = 30;//显示器价格
    private int minScreen = 1;//显示器最小销售量
    private int maxScreen = 80;//显示器最大销售量

    private int pricePerpheral = 45;//外设价格
    private int minPerpheral = 1;//外设最小销售量
    private int maxPerpheral = 90;//外设最大销售量

    public SaleCom(){
        this(1,1,1);
    }

    public SaleCom(int mainUnit, int screen, int perpheral) {
        this.mainUnit = mainUnit;
        this.screen = screen;
        this.perpheral = perpheral;
    }

    private double calculateReward(int mainUnit, int screen, int perpheral){
        sales = mainUnit * priceMainUnit + screen * priceScreen + perpheral * pricePerpheral;

        if (sales<=1000){
            reward = sales * 0.1;
        }
        else if (sales>1000&&sales<=1800){
            reward = sales * 0.15;
        }
        else {
            reward = sales * 0.2;
        }
        return reward;
    }

    public String checkReward(int mainUnit, int screen, int perpheral){
        if (mainUnit<minMainUnit||screen<minScreen||perpheral<minPerpheral){
            System.out.printf("每个销售员每月至少销售一台完整的机器");
            return "Each salesman sells at least one complete machine per month";
        }
        else if (mainUnit>maxMainUnit){
            System.out.printf("主机销量超出限制");
            return "Main unit sales exceeded the limit";
        }
        else if (screen>maxScreen){
            System.out.printf("显示器销量超出限制");
            return "Screen sales exceeded the limit";
        }
        else if (perpheral>maxPerpheral){
            System.out.printf("外设销量超出限制");
            return "Perpheral sales exceeded the limit";
        }
        else {
            BigDecimal temp= new BigDecimal(String.valueOf(calculateReward(mainUnit,screen,perpheral)));
            return temp.stripTrailingZeros().toPlainString();//去掉多余的0
        }

    }

}
