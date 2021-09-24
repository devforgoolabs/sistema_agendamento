package com.goolab.resources;

import com.goolab.models.Agendamento;
import com.goolab.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AgendamentoResource {

    @Autowired
    private AgendamentoService service;

    @GetMapping
    public List<Agendamento> listar(){
        return service.listar();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscar(@PathVariable Long id){
        Agendamento agendamentoService = service.buscar(id);
        return ResponseEntity.ok().body(agendamentoService);
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Agendamento agendamento){
        Agendamento obj = service.inserir(agendamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(String.format("Salvo com Sucesso!! \nId: " + obj.getId()
                + " \nNome: " + obj.getEspecialidade().getTipo()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Agendamento agendamento, @PathVariable Long id){
        Agendamento agendamentoAtual = service.atualizar(id, agendamento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
