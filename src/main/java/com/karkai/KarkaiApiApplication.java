package com.karkai;

import com.github.alperkurtul.firebaserealtimedatabase.annotation.EnableFirebaseRealtimeDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFirebaseRealtimeDatabase
@SpringBootApplication
@EnableJpaRepositories
public class KarkaiApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KarkaiApiApplication.class, args);
    }

}
