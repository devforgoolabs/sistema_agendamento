package com.goolab.resources;

import com.goolab.models.Unidade;
import com.goolab.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeResource {

    @Autowired
    private UnidadeService service;

    @GetMapping
    public List<Unidade> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> buscar(@PathVariable Long id){
        Unidade unidade = service.buscar(id);
        return ResponseEntity.ok().body(unidade);
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Unidade planoService){
        Unidade obj = service.inserir(planoService);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(String.format("Salvo com Sucesso!! \nId: " + obj.getId()
                + " \nNome: " + obj.getNome()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Unidade planoService, @PathVariable Long id){
        Unidade planoServiceAtual = service.atualizar(id, planoService);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
