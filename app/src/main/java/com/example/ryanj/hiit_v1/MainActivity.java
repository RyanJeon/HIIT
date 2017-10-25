package com.example.ryanj.hiit_v1;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.ryanj.hiit_v1.Timez;


public class MainActivity extends AppCompatActivity {
    Button stop, start, reset; //button objects
    int set = 0; //will count sets of running

    TextView txt, laptxt; //counter text
    long store = 0; //will store time

    Boolean running = false; //default if timer is not running

    CountDownTimer timer; //timer object


    Timez store_time = new Timez(); //create time object
   // long counter_time = store_time.getCounter_time(); //get time for the counter time



    protected void timeM(){ //timer control tower
        running = true;
        timer = new CountDownTimer(store_time.getCounter_time() , 1000) { //Timer object
            public void onTick(long time) {

                    store = time; //stores the current time
                    txt.setText(String.valueOf(time / 1000));

                    if (time / 1000 == 30) {
                        Toast.makeText(getApplicationContext(), "Run!", Toast.LENGTH_SHORT).show();
                    }

                    if (time / 1000 == 10) {
                        Toast.makeText(getApplicationContext(), "Sprint!", Toast.LENGTH_SHORT).show();

                    }

                }

                public void onFinish() {
                    set++; //adds the lap
                    laptxt.setText("Lap:" + String.valueOf(set)); //update lap text
                    start(); //restart the timer
                }

        }.start();

        Toast.makeText(getApplicationContext(), "Testing is successful", Toast.LENGTH_SHORT).show();


    }

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

                if(running) {

                    Toast.makeText(getApplicationContext(), "Pausing..", Toast.LENGTH_SHORT).show();
                    timer.cancel(); //cancel s timer
                    txt.setText(String.valueOf(store / 1000));
                    store_time.setCounter_time(store); //set counter time to store
                }
                else{

                }

            }
        });


        start = (Button) findViewById(R.id.Start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), String.valueOf(store), Toast.LENGTH_SHORT).show();
                timeM(); // start the timer
            }
        });

        reset = (Button) findViewById(R.id.Reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Resetting the counter..", Toast.LENGTH_SHORT).show();
                store = 0; //reset the stored time
                store_time.setCounter_time(60000); //reset the counter time to 60
                txt.setText(String.valueOf(store)); //reset the value of counter


            }
        });


    }
}

