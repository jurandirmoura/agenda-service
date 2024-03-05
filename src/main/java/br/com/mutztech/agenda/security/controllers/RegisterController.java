package br.com.mutztech.agenda.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mutztech.agenda.security.controllers.dto.RegisterRequest;
import br.com.mutztech.agenda.security.domain.models.User;
import br.com.mutztech.agenda.security.domain.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    
@PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterRequest registerRequest){
    
    if(this.userRepository.findByEmail(registerRequest.getEmail())!= null) return ResponseEntity.badRequest().build();

    String encrytedPassword = new BCryptPasswordEncoder().encode(registerRequest.getPassword());//Codifica a senha
    User newUser = new User(registerRequest.getEmail(), encrytedPassword, registerRequest.getRole()); //Cria o objeto User

    this.userRepository.save(newUser); // Realiza o salvamento no DB.
    return ResponseEntity.ok().build();
    }

}
