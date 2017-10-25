package com.example.ryanj.hiit_v1;

import android.os.CountDownTimer;
import android.widget.Toast;

/**
 * Created by RyanJ on 10/24/2017.
 */

public class Timez {

    long counter_time; //variable for counter time

    Timez(){ //constructor
        counter_time = 60000;
    }

    public long getCounter_time(){
        return counter_time; //returns the current counter time

    }

    public void setCounter_time(long n){
        counter_time = n; //set counter time as n
    }

}
