package com.berre.controller;

import static com.berre.constant.EndPoints.*;

import com.berre.dto.request.DoLoginRequestDto;
import com.berre.dto.request.RegisterRequestDto;
import com.berre.exception.AuthServiceException;
import com.berre.exception.ErrorType;
import com.berre.repository.entity.Auth;
import com.berre.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ROOT+AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //1. register işlemi
    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@RequestBody @Valid RegisterRequestDto dto){
       if (!dto.getPassword().equals(dto.getRepassword())){
           throw new AuthServiceException(ErrorType.REGISTER_PASSWORD_MISMATCH);
       }
        return ResponseEntity.ok(authService.register(dto));

    }

    //2. login işlemi
    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDto dto){

        return ResponseEntity.ok(authService.doLogin(dto));

    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Auth>> findAll(String token){
        return ResponseEntity.ok(authService.findAll(token));
    }

    /*
    ApiGateway test entpointi
     */
    @GetMapping("/message")
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Auth Service getMessage erişim sağlandı");
    }

}
