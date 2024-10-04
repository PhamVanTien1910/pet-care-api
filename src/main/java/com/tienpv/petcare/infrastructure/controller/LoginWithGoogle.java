package com.tienpv.petcare.infrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginWithGoogle {

    @GetMapping("/home")
    public String home(){
        return "Login with google success";
    }
}
