package com.objectivecoders.android.garvispoolrepair.DataObjects;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jeffr on 3/7/2018.
 */

public class WorkOrderDate {

    int day;
    int month;
    int year;

    public WorkOrderDate (int day, int month, int year) {

        this.day = day;
        this.month = month;
        this.year = year;

    }
        public String toString() {

            return "" + month + "-" + day + "-" + year;
        }
    }



