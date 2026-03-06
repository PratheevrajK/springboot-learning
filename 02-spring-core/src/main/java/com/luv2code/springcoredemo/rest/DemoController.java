package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;

    //Constructor injection
    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach coach) {
        this.myCoach = coach;
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    //Setter injection
//    @Autowired
//    public void setCoach(Coach coach) {
//        this.myCoach = coach;
//    }

    @GetMapping("/dailyworkout")
    public String getWorkOut() {
        return myCoach.getDailyWorkout();
    }
}
