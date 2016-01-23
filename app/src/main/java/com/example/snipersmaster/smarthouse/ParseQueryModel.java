package com.example.snipersmaster.smarthouse;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by pccrazy on 1/23/16.
 */
public class ParseQueryModel {

    static void currentTemp(String date,GetCallback<ParseObject> callback){
        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("Temp");
        parseQuery.whereEqualTo("date",date);
        parseQuery.getFirstInBackground(callback);
    }
    static void DayAvg(String date,FindCallback<ParseObject> callback){
        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("Temp");
        parseQuery.whereContains("date",date);
        parseQuery.findInBackground(callback);
    }

}
