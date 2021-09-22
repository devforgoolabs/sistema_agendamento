package com.goolab.resources;

import com.goolab.models.Unidade;
import com.goolab.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeResourceFind {

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
}
