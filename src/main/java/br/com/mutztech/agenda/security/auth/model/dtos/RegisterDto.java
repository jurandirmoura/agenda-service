package br.com.mutztech.agenda.security.auth.model.dtos;

import br.com.mutztech.agenda.security.auth.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(@NotNull String email, @NotNull String password, UserRole role) {
}
