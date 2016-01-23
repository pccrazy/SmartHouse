package com.example.snipersmaster.smarthouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {
    Switch s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = (Switch) findViewById(R.id.switch1);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
//Check the Device's Status from the database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Devices");
        query.whereEqualTo("Dev_Name", s.getText().toString());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, com.parse.ParseException e) {
                if (object != null) {
                    if (object.getNumber("Dev_Status") == 1) {
                        s.setChecked(true);
                    } else {
                        s.setChecked(false);
                    }
                }}});


//Switch control method
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //if the switch is turned on
                if (isChecked) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Devices");
                    query.whereEqualTo("Dev_Name", s.getText().toString());
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(final ParseObject object, com.parse.ParseException e) {
                            if (object == null) {
                                Toast.makeText(MainActivity.this, "This device is not connected", Toast.LENGTH_SHORT).show();
                            } else {
                                if (!s.getText().toString().isEmpty()) {
                                    object.put("Dev_Status", 1);
                                    object.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(com.parse.ParseException e) {
                                            if (e == null) {
                                                if (object.getNumber("Dev_Status") == 1) {
                                                    Toast.makeText(MainActivity.this, s.getText().toString() + " status is On", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(MainActivity.this, s.getText().toString() + " status is Off", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(MainActivity.this, "NOT SAVED", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        }

                    });
                }
                //if the Switch is turned off
                else if (!isChecked) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Devices");
                    query.whereEqualTo("Dev_Name", s.getText().toString());
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(final ParseObject object, com.parse.ParseException e) {
                            if (object == null) {
                                Toast.makeText(MainActivity.this, "This device is not connected", Toast.LENGTH_SHORT).show();
                            } else {
                                if (!s.getText().toString().isEmpty()) {
                                    object.put("Dev_Status", 0);
                                    object.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(com.parse.ParseException e) {
                                            if (e == null) {
                                                if (object.getNumber("Dev_Status") == 1) {
                                                    Toast.makeText(MainActivity.this, s.getText().toString() + " status is On", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(MainActivity.this, s.getText().toString() + " status is Off", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(MainActivity.this, "NOT SAVED", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        }

                    });

                }
                    }
                });

            }
        }
