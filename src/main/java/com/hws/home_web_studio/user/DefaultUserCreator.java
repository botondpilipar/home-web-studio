package com.hws.home_web_studio.user;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DefaultUserCreator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment environment;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    private String getDefaultPasswordPropertyName() throws Exception {
        switch (activeProfile) {
            case "default":
                return "pg_password";
            case "dev":
                return "HWS_DEFAULT_PWD";
            default:
                throw new Exception("Unrecognized profile: " + activeProfile);
        }
    }

    private String getDefaultUsernamePropertyName() throws Exception {
        switch (activeProfile) {
            case "default":
                return "pg_username";
            case "dev":
                return "HWS_DEFAULT_USER";
            default:
                throw new Exception("Unrecognized profile: " + activeProfile);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDefaultAdminUser() {
        try {
            final String defaultUserName = environment.getRequiredProperty(getDefaultUsernamePropertyName());
            final String defaltPwdEncoded = passwordEncoder.encode(environment.getRequiredProperty(getDefaultPasswordPropertyName()));

            if(!userRepository.existsByUserName(defaultUserName)) {
                final UserModel defaultUser = UserModel.builder()
                                            .userName(defaultUserName)
                                            .passwordHash(defaltPwdEncoded)
                                            .fullName("Default User")
                                            .role(UserRoles.ADMINISTRATOR)
                                            .profilePicture(null)
                                            .backgroundImage(null)
                                            .build();
                userRepository.save(defaultUser);
            }
        } catch(Exception e) {
            log.error("Cannot create default user: " + e.toString());
        }

    }

}