package com.berre.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/auth")
    public ResponseEntity<String> fallbackAuth(){

        return ResponseEntity.ok("Auth servis şu an yanıt veremiyor.Daha sonra yneiden deneyiniz");
    }

    @GetMapping("/user")
    public ResponseEntity<String> fallbackUser(){
        return ResponseEntity.ok("User servis şu an yanıt veremiyor.Daha sonra yneiden deneyiniz");
    }
}
