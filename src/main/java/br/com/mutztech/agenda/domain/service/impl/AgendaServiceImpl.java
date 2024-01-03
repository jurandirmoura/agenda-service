package br.com.mutztech.agenda.domain.service.impl;

import br.com.mutztech.agenda.domain.entity.Agenda;
import br.com.mutztech.agenda.domain.entity.Paciente;
import br.com.mutztech.agenda.domain.repository.AgendaRepository;
import br.com.mutztech.agenda.domain.service.AgendaService;
import br.com.mutztech.agenda.domain.service.PacienteService;
import br.com.mutztech.agenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository repository;
    private final PacienteService pacienteService;
    @Override
    public Agenda salvar(Agenda agenda) {
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(agenda.getPaciente().getId());

        if (optPaciente.isEmpty()){
            throw new BusinessException("Paciente não Encontardo");
        }

       Optional<Agenda> optHorario = repository.findByHorario(agenda.getHorario());

        if (optHorario.isPresent()){
            throw new BusinessException("Já existe agendamento para este horário");
        }

        agenda.setPaciente(optPaciente.get());
        agenda.setDataCriacao(LocalDateTime.now());

        return repository.save(agenda);

    }

    public Agenda alterar(UUID id, Agenda agenda) {
        Optional<Agenda> optAgenda = this.buscarPorId(id);

        if (optAgenda.isEmpty()) {
            throw new BusinessException("Paciente não cadastrado!");
        }

        agenda.setId(id);

        return salvar(agenda);
    }

    @Override
    public Optional<Agenda> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Agenda> listarTodos() {
        return repository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        repository.deleteById(id);

    }
}
