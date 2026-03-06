package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanConfigureController {

    private Coach myCoach;

    @Autowired
    public BeanConfigureController(@Qualifier("volleyballCoach") Coach coach) {
        this.myCoach = coach;
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    @GetMapping("/test-bean-configure")
    public String getWorkOut() {
        return myCoach.getDailyWorkout();
    }
}
