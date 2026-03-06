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

    //2026-03-06T21:37:34.543+05:30  INFO 36836 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.18]
    //2026-03-06T21:37:34.591+05:30  INFO 36836 --- [springcoredemo] [  restartedMain] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 1252 ms
    //In Constructor: CricketCoach
    //In Constructor: FootballCoach
    //In Constructor: DemoController
    //In Constructor: TennisCoach
    //In Constructor: TennisCoach
    //In Constructor: ScopeTestController

    @GetMapping("/doubleworkout")
    public String getWorkOut() {
        System.out.println(myCoach == anotherCoach); // true for Singleton and false for Prototype.
        System.out.println(anotherCoach.getDailyWorkout());
        return myCoach.getDailyWorkout();
    }
}
