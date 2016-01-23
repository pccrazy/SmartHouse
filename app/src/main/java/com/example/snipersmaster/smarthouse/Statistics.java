package com.example.snipersmaster.smarthouse;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentTemp();
            }
        });
    }

    void currentTemp()
    {
        ParseQueryModel.currentTemp(CustomCalender.today(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject temp, ParseException e) {
                if(e==null&&temp!=null){
                    Toast.makeText(Statistics.this,temp.getString("cTemp"),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Statistics.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    void avgTempDay(){
        ParseQueryModel.DayAvg(CustomCalender.today(), new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> temps, ParseException e) {
                if(e==null&&temps!=null){
                    double avg=0;
                    for(ParseObject temp:temps){
                        try {
                            avg=avg+Double.parseDouble(temp.getString("cTemp"));
                        }catch (Exception ex){
                            Toast.makeText(Statistics.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                    Toast.makeText(Statistics.this,"average is "+(avg/temps.size()),Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Statistics.this,"Somthing went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void avgTempWeek(){

    }
}
