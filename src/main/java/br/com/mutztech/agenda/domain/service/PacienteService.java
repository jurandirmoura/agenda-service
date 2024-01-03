package br.com.mutztech.agenda.domain.service;

import br.com.mutztech.agenda.domain.entity.Paciente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PacienteService {

    Paciente salvar(Paciente paciente);

    Paciente alterar(UUID id, Paciente paciente);

    List<Paciente> listarTodos();

    Optional<Paciente> buscarPorId(UUID id);

    void deletar(UUID id);
}
