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

    Button stop, start, lap; //btton objects
    int set = 0; //will count sets of running

    TextView txt, laptxt; //counter text
    long store = 0; //will store time

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


            }
        });

        start = (Button) findViewById(R.id.Start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Starting the counting", Toast.LENGTH_SHORT).show();


                new CountDownTimer(60000, 1000) {
                    public void onTick(long time) {
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

        lap = (Button) findViewById(R.id.Lap);
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Starting the counting", Toast.LENGTH_SHORT).show();
                while (store != 10){
                    store++;
                    laptxt.setText("fun");
                    SystemClock.sleep(1000);

                }
            }
        });


    }
}

