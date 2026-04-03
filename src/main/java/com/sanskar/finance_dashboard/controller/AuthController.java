package com.sanskar.finance_dashboard.controller;


import com.sanskar.finance_dashboard.entity.User;
import com.sanskar.finance_dashboard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return authService.login(user.getEmail(), user.getPassword());
    }
}
