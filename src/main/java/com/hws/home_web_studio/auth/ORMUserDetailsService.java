package com.hws.home_web_studio.auth;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hws.home_web_studio.user.UserModel;
import com.hws.home_web_studio.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ORMUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserModel user = userRepository.findByUserName(username).stream().findFirst().orElseThrow();

            return User.builder()
                        .username(username)
                        .password(user.getPasswordHash())
                        .roles(user.getRole().getName())
                        .build();
        } catch(NoSuchElementException e) {
            throw new UsernameNotFoundException("Username " + username + " does not exist!", e);
        }
    }
}
