package com.objectivecoders.android.garvispoolrepair.DataObjects;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jeffr on 3/7/2018.
 */

public class WorkOrderDate extends Date {

    int day;
    int month;
    int year;

    public WorkOrderDate (int day, int month, int year) {

        this.day = day;
        this.month = month;
        this.year = year;

    }
    public WorkOrderDate(Long date){
             super(date);
        }


        public String getString(){
            String s = super.toString();
            month = Integer.valueOf(convertMonth(s.substring(4,7)));
            day = Integer.valueOf(s.substring(8,10));
            year = Integer.valueOf(s.toString().substring(24,28));
            return month+"-"+day+"-"+year;
        }

        public String toString() {
            return "" + month + "-" + day + "-" + year;
        }

        public int getMonth2(){
         return this.month;
        }

    private String convertMonth(String month){
        switch(month){
            case "Jan":
                return "1";
            case "Feb":
                return "2";
            case "Mar":
                return "3";
            case "Apr":
                return "4";
            case "May":
                return "5";
            case "Jun":
                return "6";
            case "Jul":
                return "7";
            case "Aug":
                return "8";
            case "Sep":
                return "9";
            case "Oct":
                return "10";
            case "Nov":
                return "11";
            case "Dec":
                return "12";
            }
        return "" ;
    }
    }



