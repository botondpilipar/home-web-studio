package com.hws.home_web_studio.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile("dev")
@Component
public class EnvCheck {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void check() {
        if(username.isEmpty() || username.isBlank()) {
            log.error("Default username is empty1");
        } else if (password.isEmpty() || password.isBlank()) {
            log.error("Default passwrod is  empty!");
        } else {
            log.info("Default username is {}", username);
            log.info("Default password is {}", password);
        }
    }
}
