package br.com.mutztech.agenda.security.controllers.dto;

import br.com.mutztech.agenda.security.domain.models.roles.UserRole;
import jakarta.validation.constraints.NotNull;

public class RegisterRequest {
    
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private UserRole role;
    
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }


    
}

