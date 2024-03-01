package br.com.mutztech.agenda.security.auth.controllers;

import br.com.mutztech.agenda.security.auth.model.dtos.AuthenticationDto;
import br.com.mutztech.agenda.security.auth.model.dtos.RegisterDto;
import br.com.mutztech.agenda.security.auth.service.AuthorizationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authenticationDto){
        return authorizationService.login(authenticationDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody RegisterDto registerDto){
        return authorizationService.register(registerDto);
    }
}

