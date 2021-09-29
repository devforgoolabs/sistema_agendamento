package com.goolab.resources;

import com.goolab.dto.EspecialidadeDTO;
import com.goolab.models.Especialidade;
import com.goolab.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeResource {

    @Autowired
    private EspecialidadeService service;

    @GetMapping
    public ResponseEntity<List<EspecialidadeDTO>> listar(){
        List<Especialidade> list = service.listar();
        List<EspecialidadeDTO> listDto = list.stream().map(EspecialidadeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscar(@PathVariable Long id){
        Especialidade especialidade = service.buscar(id);
        return ResponseEntity.ok().body(especialidade);
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Especialidade especialidade){
        Especialidade obj = service.inserir(especialidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(String.format("Salvo com Sucesso!! \nId: " + obj.getId()
                + " \nNome: " + obj.getTipo()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Especialidade especialidade, @PathVariable Long id){
        Especialidade especialidadeAtual = service.atualizar(id, especialidade);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
