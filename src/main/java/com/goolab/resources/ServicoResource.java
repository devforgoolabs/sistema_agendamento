package com.goolab.resources;

import com.goolab.models.Servico;
import com.goolab.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoResource {

    @Autowired
    private ServicoService service;

    @GetMapping
    public List<Servico> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscar(@PathVariable Long id){
        Servico servico = service.buscar(id);
        return ResponseEntity.ok().body(servico);
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Servico servico){
        Servico obj = service.inserir(servico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(String.format("Salvo com Sucesso!! \nId: " + obj.getId()
                + " \nNome: " + obj.getNome()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Servico servico, @PathVariable Long id){
        Servico servicoAtual = service.atualizar(id, servico);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
