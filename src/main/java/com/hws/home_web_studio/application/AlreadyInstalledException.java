package com.hws.home_web_studio.application;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AlreadyInstalledException extends Exception {
    private final String appName;
    private final String userName;

    @Override
    public String toString() {
        return new StringBuilder()
                        .append("Application ")
                        .append(appName)
                        .append(" already installed for userId: ")
                        .append(userName).toString();
    }
}
