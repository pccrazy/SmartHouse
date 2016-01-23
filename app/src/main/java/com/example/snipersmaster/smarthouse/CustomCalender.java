package com.example.snipersmaster.smarthouse;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pccrazy on 1/23/16.
 */
public class CustomCalender {
    static Date curDate = new Date();
    static SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat day = new SimpleDateFormat("E");

   static String today(){

        String DateToStr = dateformat.format(curDate);
        String date_fromatter[] = DateToStr.split("/");
        /**
         the defualt format is 23/01/2016
         the code will format the date to this format
         Sat Feb 23 2016 through spliiting the default then adding the current day
         */
        String currentfulldate=day.format(curDate)+" "+
                theMonth(Integer.parseInt(date_fromatter[1])-1) +
                " " + date_fromatter[0] +" "+ date_fromatter[2];
        return currentfulldate;

    }

    static String current_month(){

        String DateToStr = dateformat.format(curDate);
        String date_fromatter[] = DateToStr.split("/");
        /**
         the defualt format is 23/01/2016
         the code will format the date to this format
         Sat Feb 23 2016 through spliiting the default then adding the current day
         */
        String currentfulldate=
                theMonth(Integer.parseInt(date_fromatter[1])-1)+date_fromatter[2];
        return currentfulldate;
    }

    public static String theMonth(int month){
        String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return monthNames[month];
    }
}
