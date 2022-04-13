package com.example.agentboard2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication
public class AgentBoard2022Application {

    public static void main(String[] args) {
        SpringApplication.run(AgentBoard2022Application.class, args);
    }

}
