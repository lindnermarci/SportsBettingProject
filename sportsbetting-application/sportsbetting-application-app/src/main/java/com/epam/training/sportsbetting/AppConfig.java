package com.epam.training.sportsbetting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.training.sportsbetting.service.Service;
import com.epam.training.sportsbetting.service.SportsBettingService;
import com.epam.training.sportsbetting.view.SportsBettingView;
import com.epam.training.sportsbetting.view.View;

@Configuration
public class AppConfig { 
    
    @Bean
    public App app() {
        return new App(sportsBettingService(), sportsBettingView());
    }
    
    @Bean
    public Service sportsBettingService() {
        return new SportsBettingService();
    }
    
    @Bean
    public View sportsBettingView() {
        return new SportsBettingView();
    }
}
