package com.goolab.services;

import com.goolab.models.Especialidade;
import com.goolab.repositories.EspecialidadeRepository;
import com.goolab.services.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository repository;

    public List<Especialidade> listar(){
        return repository.findAll();
    }

    public Especialidade buscar(Long id) {
        Optional<Especialidade> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Especialidade.class.getName()));
    }

    public Especialidade inserir(Especialidade especialidade){
        return repository.save(especialidade);
    }

    public Especialidade atualizar(Long id, Especialidade especialidade) {
        Optional<Especialidade> especialidadeAtual = repository.findById(id);

        if (especialidadeAtual.isEmpty()){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Especialidade.class.getName());
        }

        BeanUtils.copyProperties(especialidade, especialidadeAtual.get(), "id");
        return repository.save(especialidadeAtual.get());
    }

    public Optional<Especialidade> buscarPrestadorCodigo(Long id) {
        Optional<Especialidade> especialidadeSalva = repository.findById(id);
        if (especialidadeSalva.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return especialidadeSalva;
    }

    public void deletar(Long id){
        Optional<Especialidade> especialidade = repository.findById(id);
        if (especialidade.isEmpty()){
            throw new ObjectNotFoundException("Objeto com o Id: " + id + " Não existe!!");
        }
        repository.deleteById(id);
    }
}
