package com.ads.gynvagas.jobboard.controller;

import config.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @GetMapping("/user-details")
    public UserDetails getUserDetails(Authentication authentication) {
        // Obtém o nome de usuário do usuário autenticado
        String username = authentication.getName();
        // Carrega os detalhes do usuário com base no nome de usuário usando o UserDetailsService
        return customUserDetailService.loadUserByUsername(username);

    }
}
