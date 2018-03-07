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
        String newString = "";
        newString +=s.substring(4,10);
        newString += s.substring(23);
        return newString;
    }
}
