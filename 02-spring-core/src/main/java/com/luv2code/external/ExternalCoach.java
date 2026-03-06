package com.luv2code.external;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.stereotype.Component;

//@Component
public class ExternalCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Jog 10 rounds!";
    }
}
