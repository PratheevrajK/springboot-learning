package com.luv2code.external;

import com.luv2code.springcoredemo.common.Coach;

// This is some external class from some jar or dependency
public class VolleyballCoach implements Coach {

    public VolleyballCoach(){
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practise setting for 10mins!";
    }
}
