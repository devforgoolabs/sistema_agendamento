package com.goolab.resources;

import com.goolab.dto.UnidadeDTO;
import com.goolab.models.Unidade;
import com.goolab.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/unidades")
public class UnidadeResource {

    @Autowired
    private UnidadeService service;

    @GetMapping
    public ResponseEntity<List<UnidadeDTO>> listar(){
        List<Unidade> list = service.listar();
        List<UnidadeDTO> listDto = list.stream().map(obj -> new UnidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> buscar(@PathVariable Long id){
        Unidade unidade = service.buscar(id);
        return ResponseEntity.ok().body(unidade);
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody @Valid Unidade unidade){
        Unidade obj = service.inserir(unidade);
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

    @GetMapping("/page")
    public ResponseEntity<Page<UnidadeDTO>> pages(@RequestParam(value = "page", defaultValue = "0")Integer page,
                                                  @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
                                                  @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
                                                  @RequestParam(value = "direction", defaultValue = "ASC")String direction){

        Page<Unidade> list = service.buscarPorPagina(page, linesPerPage, orderBy, direction);
        Page<UnidadeDTO> listDto = list.map(obj -> new UnidadeDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
