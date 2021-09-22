package com.goolab.resources;

import com.goolab.models.Unidade;
import com.goolab.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/unidades")
public class UnidadeResourceInsert {

    @Autowired
    private UnidadeService service;

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Unidade unidade){
        Unidade obj = service.inserir(unidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(String.format("Salvo com Sucesso!! \nId: " + obj.getId()
                + " \nNome: " + obj.getNome()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Unidade unidade, @PathVariable Long id){
        Unidade unidadeAtual = service.atualizar(id, unidade);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
