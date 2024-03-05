package br.com.mutztech.agenda.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mutztech.agenda.security.controllers.dto.LoginRequest;
import br.com.mutztech.agenda.security.domain.models.User;
import br.com.mutztech.agenda.security.domain.services.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    
    @PostMapping
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequest loginRequest){
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            var authenticate = this.authenticationManager.authenticate(usernamePassword);
        
            //Gerador de Token para o Usuario.
            var token = tokenService.gerandoToken((User) authenticate.getPrincipal());
            return ResponseEntity.ok(token);
        }



}
