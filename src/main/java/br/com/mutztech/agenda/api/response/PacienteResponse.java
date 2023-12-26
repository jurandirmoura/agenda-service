package br.com.mutztech.agenda.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {

    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
    private String telefone;
}
