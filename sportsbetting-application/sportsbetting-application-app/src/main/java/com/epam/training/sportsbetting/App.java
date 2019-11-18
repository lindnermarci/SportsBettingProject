package com.epam.training.sportsbetting;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.service.Service;
import com.epam.training.sportsbetting.view.View;

public class App {
    
    private static Service service;
    private static View view;
    
    public static void main(String[] args) {
       try(ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, JpaConfig.class)) {
           //testJpa(ctx);
           //testSpringData(ctx);
           App app = ctx.getBean(App.class);
           app.createPLayer();
           app.play();
           app.calculateResult();
           app.printResult();
       } catch (Exception e) {
        e.printStackTrace();
       }
    }
    
    private static void testSpringData(ApplicationContext context) {
        PlayerRepository pr = context.getBean(PlayerRepository.class);
        System.out.println("The id of player is: " + pr.findByName("Marci").get(0).getId());
    }
    
    private static void testJpa(ApplicationContext context) {

        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();
    
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        Player player = new Player();
        player.setBirth(LocalDate.now());
        player.setBalance(new BigDecimal(10));
        player.setName("Marci");
        player.setParticipan1("prt 1");
        player.setParticipan1("prt 2");
        em.merge(player);
        tr.commit();
        System.out.println("Payer id is " + player.getId() );
    }
    public App(Service sportsBettingSevice, View view) {
        App.service = sportsBettingSevice;
        App.view = view;
    }
    
    public void play() {
        Player player = service.findPlayer();
        
        view.PrintWelcomeMessage(player);
        
        while(true) {
            view.PrintBalanace(player);
            view.PrintOutcomeOdds(service.findAllSportEvents());
            Optional<OutcomeOdd> outcomeOdd = view.selectOutcomeOdd(service.findAllSportEvents());
            if (outcomeOdd.isEmpty()) break;
            
            BigDecimal wagerAmount = view.readWagerAmount();
            Wager wager = null;
            if(service.sufficientBalance(wagerAmount)) {
                wager = new Wager(wagerAmount, outcomeOdd.get(), player);
                service.saveWager(wager);
            }else {
                do {
                    view.printNotEnoughBalance(player);
                    wagerAmount = view.readWagerAmount();
                }while(!service.sufficientBalance(wagerAmount));
                wager = new Wager(wagerAmount, outcomeOdd.get(), player);
                service.saveWager(wager);
            }
            view.printWagerSaved(wager);
        }
    }
    
    private void createPLayer() {
        Player player = view.readPlayerData();
        player.setEmail("a@a");
        player.setPassword("root");
        service.savePlayer(player);
    }
    
    private void calculateResult() {
        service.calculateResult();
    }
    
    private void printResult() {
        view.printResult(service.findPlayer(), service.findAllWagers());
    }
}
