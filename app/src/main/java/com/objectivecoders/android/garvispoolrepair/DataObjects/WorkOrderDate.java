package com.objectivecoders.android.garvispoolrepair.DataObjects;

import java.util.Date;

/**
 * Created by jeffr on 3/7/2018.
 */

public class WorkOrderDate extends Date {
    public WorkOrderDate(Long date){
        super(date);
    }

    @Override
    public String toString() {
        String s = super.toString();
        String month = convertMonth(s.substring(4,7));
        String day = s.substring(8,10);
        String year = s.toString().substring(24,28);
        return month +"-"+day+"-"+year;
    }

    private String convertMonth(String month){
        switch(month){
            case "Jan":
                return "01";
            case "Feb":
                return "02";
            case "Mar":
                return "03";
            case "Apr":
                return "04";
            case "May":
                return "05";
            case "Jun":
                return "06";
            case "Jul":
                return "07";
            case "Aug":
                return "08";
            case "Sep":
                return "09";
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
