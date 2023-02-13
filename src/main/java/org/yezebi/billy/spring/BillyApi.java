package org.yezebi.billy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.yezebi.billy.spring.config.ApiProperties;

@EnableJpaRepositories
@EnableJpaAuditing
@EnableConfigurationProperties({ApiProperties.class})
@SpringBootApplication
public class BillyApi {
    public static void main(String[] args) {
        SpringApplication.run(BillyApi.class, args);
    }
}
