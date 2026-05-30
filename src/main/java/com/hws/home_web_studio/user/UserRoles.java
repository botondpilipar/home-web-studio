package com.hws.home_web_studio.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRoles {
    ADMINISTRATOR("ADMIN"),
    USER("USER"),
    RESTRICTED_USER("USER");

    @Getter
    private final String name;
}
