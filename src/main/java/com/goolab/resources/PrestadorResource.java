package com.goolab.resources;

import com.goolab.dto.PrestadorDTO;
import com.goolab.models.Prestador;
import com.goolab.services.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prestadores")
public class PrestadorResource {

    @Autowired
    private PrestadorService service;

    @GetMapping
    public ResponseEntity<List<PrestadorDTO>> listar(){
        List<Prestador> list = service.listar();
        List<PrestadorDTO> listDto = list.stream().map(obj -> new PrestadorDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestador> buscar(@PathVariable Long id){
        Prestador prestador = service.buscar(id);
        return ResponseEntity.ok().body(prestador);
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Prestador prestador){
        Prestador obj = service.inserir(prestador);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(String.format("Salvo com Sucesso!! \nId: " + obj.getId()
                + " \nNome: " + obj.getNome()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Prestador prestador, @PathVariable Long id){
        Prestador prestadorAtual = service.atualizar(id, prestador);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
