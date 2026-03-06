package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScopeTestController {

    private Coach myCoach;
    private Coach anotherCoach;

    //Constructor injection
    @Autowired
    public ScopeTestController(@Qualifier("tennisCoach") Coach coach,
                               @Qualifier("tennisCoach") Coach anotherCoach) {
        this.myCoach = coach;
        this.anotherCoach = anotherCoach;
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    @GetMapping("/doubleworkout")
    public String getWorkOut() {
        System.out.println(myCoach == anotherCoach); // true for Singleton and false for
        System.out.println(anotherCoach.getDailyWorkout());
        return myCoach.getDailyWorkout();
    }
}
