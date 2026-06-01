package com.hws.home_web_studio.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LoginController {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        Context context = new Context();
        // Add CSRF token if present
        Object csrfToken = request.getAttribute("_csrf");
        if (csrfToken != null) {
            context.setVariable("_csrf", csrfToken);
        }
        // Optionally add any other variables your template expects
        return templateEngine.process("login", context);
    }
}
