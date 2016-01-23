package com.example.snipersmaster.smarthouse;
import android.app.Application;
import com.parse.Parse;

/**
 * Created by SnipersMaster on 1/16/16.
 */
public class Starter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this);
    }
}
