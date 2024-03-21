package br.com.mutztech.agenda.domain.repository;

import br.com.mutztech.agenda.domain.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, UUID> {
    Optional<Agenda> findByHorario(LocalDateTime horario);
}
