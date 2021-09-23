package com.goolab.services;

import com.goolab.models.Paciente;
import com.goolab.models.PlanoSaude;
import com.goolab.repositories.PacienteRepository;
import com.goolab.services.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<Paciente> listar(){
        return repository.findAll();
    }

    public Paciente buscar(Long id) {
        Optional<Paciente> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
    }

    public Paciente inserir(Paciente paciente){
        return repository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente paciente) {
        Optional<Paciente> pacienteAtual = repository.findById(id);

        if (pacienteAtual.isEmpty()){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + PlanoSaude.class.getName());
        }

        BeanUtils.copyProperties(paciente, pacienteAtual.get(), "id");
        return repository.save(pacienteAtual.get());
    }

    public Optional<Paciente> buscarPrestadorCodigo(Long id) {
        Optional<Paciente> pacienteSalva = repository.findById(id);
        if (pacienteSalva.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return pacienteSalva;
    }

    public void deletar(Long id){
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isEmpty()){
            throw new ObjectNotFoundException("Objeto com o Id: " + id + " Não existe!!");
        }
        repository.deleteById(id);
    }
}
