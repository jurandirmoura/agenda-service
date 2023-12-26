package br.com.mutztech.agenda.domain.repository;

import br.com.mutztech.agenda.domain.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgendaRepository extends JpaRepository<Agenda, UUID> {
}
