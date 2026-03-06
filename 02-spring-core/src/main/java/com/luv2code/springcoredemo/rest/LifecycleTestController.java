package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LifecycleTestController {

    private Coach myCoach;

    @Autowired
    public LifecycleTestController(@Qualifier("cricketCoach") Coach coach) {
        this.myCoach = coach;
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("Entering method of Constructor: " + getClass().getSimpleName());
    }

    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("Exiting method of Constructor: " + getClass().getSimpleName());
    }

    //2026-03-06T21:36:42.543+05:30  INFO 15352 --- [springcoredemo] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.18]
    //2026-03-06T21:36:42.593+05:30  INFO 15352 --- [springcoredemo] [  restartedMain] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 1202 ms
    //In Constructor: CricketCoach
    //In Constructor: FootballCoach
    //In Constructor: DemoController
    //In Constructor: LifecycleTestController
    //Entering method of Constructor: LifecycleTestController
    //In Constructor: TennisCoach
    //In Constructor: TennisCoach
    //In Constructor: ScopeTestController
    //2026-03-06T21:36:43.083+05:30  INFO 15352 --- [springcoredemo] [  restartedMain] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 8080 (http) with context path '/'
    //2026-03-06T21:36:43.090+05:30  INFO 15352 --- [springcoredemo] [  restartedMain] c.l.s.SpringcoredemoApplication          : Started SpringcoredemoApplication in 2.306 seconds (process running for 2.983)
    //2026-03-06T21:36:47.653+05:30  INFO 15352 --- [springcoredemo] [ionShutdownHook] o.s.boot.tomcat.GracefulShutdown         : Commencing graceful shutdown. Waiting for active requests to complete
    //2026-03-06T21:36:47.660+05:30  INFO 15352 --- [springcoredemo] [tomcat-shutdown] o.s.boot.tomcat.GracefulShutdown         : Graceful shutdown complete
    //Exiting method of Constructor: LifecycleTestController

    @GetMapping("/test-lifecycle")
    public String getWorkOut() {
        return myCoach.getDailyWorkout();
    }
}
