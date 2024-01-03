package br.com.mutztech.agenda.domain.service.impl;

import br.com.mutztech.agenda.domain.entity.Paciente;
import br.com.mutztech.agenda.domain.repository.PacienteRepository;
import br.com.mutztech.agenda.domain.service.PacienteService;
import br.com.mutztech.agenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {

        boolean existeCpf = false;

        Optional<Paciente> optPaciente = repository.findByCpf(paciente.getCpf());

        if (optPaciente.isPresent()) {
            if (!optPaciente.get().getId().equals(paciente.getId())) {
                existeCpf = true;
            }
        }

        if (existeCpf) {
            throw new BusinessException("Cpf já cadastrado!");
        }
        return repository.save(paciente);
    }

    public Paciente alterar(UUID id, Paciente paciente) {
        Optional<Paciente> optPaciente = this.buscarPorId(id);

        if (optPaciente.isEmpty()) {
            throw new BusinessException("Paciente não cadastrado!");
        }

        paciente.setId(id);

        return salvar(paciente);
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(UUID id) {
        repository.deleteById(id);

    }

}
