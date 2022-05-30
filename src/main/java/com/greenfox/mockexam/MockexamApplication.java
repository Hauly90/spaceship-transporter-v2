package com.greenfox.mockexam;

import com.greenfox.mockexam.repositories.PlanetRepository;
import com.greenfox.mockexam.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockexamApplication.class, args);
    }

}
