package com.example.ryanj.hiit_v1;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.ryanj.hiit_v1.Timez;


public class MainActivity extends AppCompatActivity {
    Button taptap; //button objects
    int set = 0; //will count sets of running

    TextView txt, laptxt; //counter text
    long store = 0; //will store time

    Boolean running = false; //default if timer is not running
    Boolean reseti = false; //of the timer was reseted

    CountDownTimer timer; //timer object


    Timez store_time = new Timez(); //create time object
   // long counter_time = store_time.getCounter_time(); //get time for the counter time



    protected void timeM(){ //timer control tower
        running = true; //timer is running
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


    }


    public void startTimer(){ //time starting function
        timeM(); //start the timer
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.textView); //finds the counter text
        laptxt = (TextView) findViewById(R.id.laptxt); //finds the lap text



        taptap = (Button) findViewById(R.id.Taptap);
        taptap.setOnClickListener(new View.OnClickListener(){
            int clicks = 0; //number of clicks
            @Override
            public void onClick(View v){
                clicks++; //increment counter

                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        clicks = 0;
                    }
                };


                if (clicks < 3) { //if clicked once
                    //Single click

                    if(!running && !reseti) { //if not running
                        Toast.makeText(getApplicationContext(), "Single Click", Toast.LENGTH_SHORT).show();
                        startTimer(); //start the timer
                    }
                    else if(running && !reseti){ //if the app is running
                        Toast.makeText(getApplicationContext(), "Pausing..", Toast.LENGTH_SHORT).show();
                        timer.cancel(); //cancel s timer
                        txt.setText(String.valueOf(store / 1000));
                        store_time.setCounter_time(store); //set counter time to store
                        running = false;
                    }
                    else if(reseti){ //if the timer was on the reset mode
                        reseti = false; //tells the program that the timer is not reset
                    }


                    handler.postDelayed(r, 250); //delay component
                } else if (clicks == 100) { //if clicked twice //ignore for now
                    //Double click
                    clicks = 0; //reset the click counter

                }

            }

        });

        //path needed: timer keeps running when resetted.
        taptap.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) { //long click is reset
                Toast.makeText(getApplicationContext(), "Resetting the counter..", Toast.LENGTH_SHORT).show();
                if(running){ //if the timer was running
                    timer.cancel(); //cancel the timer
                }

                store = 60; //reset the stored time
                store_time.setCounter_time(60000); //reset the counter time to 60
                txt.setText(String.valueOf(store)); //reset the value of counter
                running = false; //tells the program that timer is stopped
                reseti = true; //tells the program that timer is resetted
                return false;
            }



        });



    }
}

