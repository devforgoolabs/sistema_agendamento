package com.goolab.services;

import com.goolab.dto.UnidadeDTO;
import com.goolab.models.Unidade;
import com.goolab.repositories.UnidadeRepository;
import com.goolab.services.exception.DataInvalid;
import com.goolab.services.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository repository;

    public List<Unidade> listar(){
        return repository.findAll();
    }

    public Unidade buscar(Long id) {
        Optional<Unidade> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Unidade.class.getName()));
    }

    public Unidade inserir(Unidade unidade) {
        return repository.save(unidade);


    }

    public Unidade atualizar(Long id, Unidade planoService) {
        Optional<Unidade> unidadeSalva = repository.findById(id);

        if (unidadeSalva.isEmpty()){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Unidade.class.getName());
        }

        BeanUtils.copyProperties(planoService, unidadeSalva.get(), "id");
        return repository.save(unidadeSalva.get());
    }

    public void deletar(Long id){
        Optional<Unidade> unidade = repository.findById(id);
        if (unidade.isEmpty()){
            throw new ObjectNotFoundException("Objeto com o Id: " + id + " Não existe!!");
        }
        repository.deleteById(id);
    }

    public Page<Unidade> buscarPorPagina(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Unidade fromDTO(UnidadeDTO dto){
        return new Unidade(dto.getId(), dto.getNome());
    }
}
