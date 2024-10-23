package com.berre.utility;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    //1. token üret
    // id 5 gelince -> authtoken:5
    public String createToken(Long id){
        return "authtoken:"+id;
    }

    //2. üretilen tokendan bilgi çıkarımı yap
    //authtoken:5 gelince -> 5
    public Long getIdFromToken(String token){
        String[] split = token.split(":");
        return Long.parseLong(split[1]);
    }
}
