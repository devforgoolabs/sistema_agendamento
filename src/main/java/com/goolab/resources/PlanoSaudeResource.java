package com.goolab.resources;

import com.goolab.dto.PlanoSaudeDTO;
import com.goolab.models.PlanoSaude;
import com.goolab.services.PlanoSaudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/planosaude")
public class PlanoSaudeResource {

    @Autowired
    private PlanoSaudeService service;

    @GetMapping
    public ResponseEntity<List<PlanoSaudeDTO>> listar(){
        List<PlanoSaude> list = service.listar();
        List<PlanoSaudeDTO> listDto = list.stream().map(obj -> new PlanoSaudeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PlanoSaude> buscar(@PathVariable Long id){
        PlanoSaude planoService = service.buscar(id);
        return ResponseEntity.ok().body(planoService);
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody PlanoSaude planoService){
        PlanoSaude obj = service.inserir(planoService);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(String.format("Salvo com Sucesso!! \nId: " + obj.getId()
                + " \nNome: " + obj.getNome()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody PlanoSaude planoSaude, @PathVariable Long id){
        PlanoSaude planoServiceAtual = service.atualizar(id, planoSaude);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
