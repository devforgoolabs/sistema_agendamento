package com.goolab.services;

import com.goolab.models.Prestador;
import com.goolab.repositories.PrestadorRepository;
import com.goolab.services.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestadorService {

    @Autowired
    private PrestadorRepository repository;

    public List<Prestador> listar(){
        return repository.findAll();
    }

    public Prestador buscar(Long id) {
        Optional<Prestador> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Prestador.class.getName()));
    }

    public Prestador inserir(Prestador prestador){
        return repository.save(prestador);
    }

    public Prestador atualizar(Long id, Prestador prestador) {
        Optional<Prestador> prestadorSalva = repository.findById(id);

        if (prestadorSalva.isEmpty()){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Prestador.class.getName());
        }

        BeanUtils.copyProperties(prestador, prestadorSalva.get(), "id");
        return repository.save(prestadorSalva.get());
    }

    public Optional<Prestador> buscarPrestadorCodigo(Long codigo) {
        Optional<Prestador> prestadorSalva = repository.findById(codigo);
        if (prestadorSalva.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return prestadorSalva;
    }

    public void deletar(Long id){
        Optional<Prestador> prestador = repository.findById(id);
        if (prestador.isEmpty()){
            throw new ObjectNotFoundException("Objeto com o Id: " + id + " Não existe!!");
        }
        repository.deleteById(id);
    }
}
