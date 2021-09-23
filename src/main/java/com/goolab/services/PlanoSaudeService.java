package com.goolab.services;

import com.goolab.models.PlanoSaude;
import com.goolab.repositories.PlanoSaudeRepository;
import com.goolab.services.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PlanoSaudeService {

    @Autowired
    private PlanoSaudeRepository repository;

    public List<PlanoSaude> listar(){
        return repository.findAll();
    }

    public PlanoSaude buscar(Long id) {
        Optional<PlanoSaude> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + PlanoSaude.class.getName()));
    }

    public PlanoSaude inserir(PlanoSaude planoSaude){
        return repository.save(planoSaude);
    }

    public PlanoSaude atualizar(Long id, PlanoSaude planoSaude) {
        Optional<PlanoSaude> planoSaudeAtual = repository.findById(id);

        if (planoSaudeAtual.isEmpty()){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + PlanoSaude.class.getName());
        }

        BeanUtils.copyProperties(planoSaude, planoSaudeAtual.get(), "id");
        return repository.save(planoSaudeAtual.get());
    }

    public Optional<PlanoSaude> buscarPrestadorCodigo(Long id) {
        Optional<PlanoSaude> prestadorSalva = repository.findById(id);
        if (prestadorSalva.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return prestadorSalva;
    }

    public void deletar(Long id){
        Optional<PlanoSaude> prestador = repository.findById(id);
        if (prestador.isEmpty()){
            throw new ObjectNotFoundException("Objeto com o Id: " + id + " Não existe!!");
        }
        repository.deleteById(id);
    }
}
