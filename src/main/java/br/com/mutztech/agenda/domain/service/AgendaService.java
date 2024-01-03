package br.com.mutztech.agenda.domain.service;

import br.com.mutztech.agenda.domain.entity.Agenda;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgendaService {

    Agenda salvar(Agenda agenda);

    Agenda alterar(UUID id, Agenda agenda);

    Optional<Agenda> buscarPorId(UUID id);

    List<Agenda> listarTodos();

    void deletar(UUID id);



}
