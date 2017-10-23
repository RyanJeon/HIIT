package com.example.ryanj.hiit_v1;

import android.icu.util.TimeUnit;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {

    Button stop, start, reset; //btton objects
    int set = 0; //will count sets of running

    TextView txt, laptxt; //counter text
    long store = 0; //will store time

    Boolean p = false; //default check
    long counter_time = 60000; //default counter time start 60 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.textView); //finds the counter text
        laptxt = (TextView) findViewById(R.id.laptxt); //finds the lap text

        stop = (Button) findViewById(R.id.Stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing..", Toast.LENGTH_SHORT).show();

                p = true;
                txt.setText(String.valueOf(store / 1000));
                counter_time = store; //set counter time to store

            }
        });

        start = (Button) findViewById(R.id.Start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Starting the counting", Toast.LENGTH_SHORT).show();

                p = false; //makes the code run again

                new CountDownTimer(counter_time, 1000) {
                    public void onTick(long time) {
                        if(p){
                            cancel(); //pauses count down
                        }


                        store = time; //stores the current time
                        txt.setText(String.valueOf(time / 1000 ));

                        if(time/1000 == 30){
                            Toast.makeText(getApplicationContext(), "Run!", Toast.LENGTH_SHORT).show();
                        }

                        if(time/1000 == 10){
                            Toast.makeText(getApplicationContext(), "Sprint!", Toast.LENGTH_SHORT).show();

                        }


                    }

                    public void onFinish() {
                        set++;
                        laptxt.setText("Lap:" + String.valueOf(set));
                        start(); //restart the timer
                    }

                }.start();
            }
        });

        reset = (Button) findViewById(R.id.Reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Resetting the counter..", Toast.LENGTH_SHORT).show();
                p = true; //pauses the counter
                store = 0; //reset the stored time
                counter_time = 60000; //reset the counter time to 60
                txt.setText(String.valueOf(store)); //reset the value of counter


            }
        });


    }
}

