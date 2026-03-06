package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.external.VolleyballCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportsConfig {

    @Bean
    public Coach volleyballCoach() { // Method name would be the Bean ID/Qualifier name
        return new VolleyballCoach();
    }
}