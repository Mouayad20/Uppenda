package com.example.demo;

import com.example.demo.Repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class DemoApplication {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }
}


/*

0xFFFB71C1C  Love
0xFF000000   Angry
0xFFF9A825   Sad
0xFF9C27B0   Like
0xFFF06292   Fun

*/
