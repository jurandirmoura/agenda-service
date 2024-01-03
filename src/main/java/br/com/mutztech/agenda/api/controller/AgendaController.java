package br.com.mutztech.agenda.api.controller;

import br.com.mutztech.agenda.api.mapper.AgendaMapper;
import br.com.mutztech.agenda.api.request.AgendaRequest;
import br.com.mutztech.agenda.api.response.AgendaResponse;
import br.com.mutztech.agenda.domain.entity.Agenda;
import br.com.mutztech.agenda.domain.service.AgendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    public final AgendaService service;
    public final AgendaMapper mapper;

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@Valid @RequestBody AgendaRequest request) {
        Agenda agenda = mapper.toAgenda(request);
        Agenda agendaSalva = service.salvar(agenda);
        AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);

    }

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> listarTodos() {
        List<Agenda> agendas = service.listarTodos();
        List<AgendaResponse> agendaResponses = mapper.toAgendaResponseList(agendas);
        return ResponseEntity.status(HttpStatus.OK).body(agendaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(UUID id) {
        Optional<Agenda> optAgenda = service.buscarPorId(id);

        if (optAgenda.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toAgendaResponse(optAgenda.get()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> alterar(@PathVariable UUID id, @RequestBody AgendaRequest request) {
        Agenda agenda = mapper.toAgenda(request);
        Agenda agendaSalva = service.alterar(id, agenda);
        AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);
        return ResponseEntity.status(HttpStatus.OK).body(agendaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
