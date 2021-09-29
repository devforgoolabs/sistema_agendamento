package com.goolab.resources;

import com.goolab.dto.PacienteDTO;
import com.goolab.models.Paciente;
import com.goolab.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
public class PacienteResource {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar(){
        List<Paciente> list = service.listar();
        List<PacienteDTO> listDto = list.stream().map(obj -> new PacienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id){
        Paciente paciente = service.buscar(id);
        return ResponseEntity.ok().body(paciente);
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Paciente paciente){
        Paciente obj = service.inserir(paciente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(String.format("Salvo com Sucesso!! \nId: " + obj.getId()
                + " \nNome: " + obj.getNome()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Paciente paciente, @PathVariable Long id){
        Paciente pacienteAtual = service.atualizar(id, paciente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
