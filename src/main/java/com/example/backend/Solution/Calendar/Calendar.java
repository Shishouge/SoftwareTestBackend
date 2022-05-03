package com.example.backend.Solution.Calendar;

import java.util.Scanner;

public class Calendar {
    private int year;
    private int month;
    private int day;

    public Calendar(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String nextDay()
    {
        int []dayOfMonth={31,28,31,30,31,30,31,31,30,31,30,31};
        String result="";
        if(year<=0||month<=0||day<=0||month>12)
        {
            return "Error Date";
        }
        if(((0 == this.year % 4 && year % 100 != 0) || (0 == year % 400))&&month==2&&day==28)
        {
            result=""+year+'/'+month+'/'+29;
        }
        else if(((0 == year % 4 && year % 100 != 0) || (0 == year % 400))&&month==2&&day==29)
        {
            result=""+year+"/3/1";
        }
        else if(((0 == year % 4 && year % 100 != 0) || (0 == year % 400))&&month==2&&day>29)
        {
            return "Error Date";
        }
        else if(!((0 == year % 4 && year % 100 != 0) || (0 == year % 400))&&month==2&&day>28)
        {
            return "Error Date";
        }
        else
        {
            if(this.day <dayOfMonth[month-1])
            {
                int newday= this.day +1;
                result=""+year+'/'+month+'/'+newday;
            }
            else if(this.day ==dayOfMonth[month-1])
            {
                int newmonth=month+1;
                if(newmonth<=12)
                {
                    result=""+year+'/'+newmonth+"/1";
                }
                else
                {
                    int newYear=year+1;
                    result=""+newYear+"/1/1";
                }
            }
            else
            {
                return "Error Date";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int y=input.nextInt();
        int m=input.nextInt();
        int d=input.nextInt();
        System.out.println(new Calendar(y,m,d).nextDay());
    }

}
