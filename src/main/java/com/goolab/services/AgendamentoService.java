package com.goolab.services;

import com.goolab.models.Agendamento;
import com.goolab.models.Paciente;
import com.goolab.models.PlanoSaude;
import com.goolab.repositories.AgendamentoRepository;
import com.goolab.services.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public List<Agendamento> listar(){
        return repository.findAll();
    }

    public Agendamento buscar(Long id) {
        Optional<Agendamento> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Agendamento.class.getName()));
    }

    public Agendamento inserir(Agendamento agendamento){
        return repository.save(agendamento);
    }

    public Agendamento atualizar(Long id, Agendamento agendamento) {
        Optional<Agendamento> agendamentoAtual = repository.findById(id);

        if (agendamentoAtual.isEmpty()){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Agendamento.class.getName());
        }

        BeanUtils.copyProperties(agendamento, agendamentoAtual.get(), "id");
        return repository.save(agendamentoAtual.get());
    }

    public Optional<Agendamento> buscarPrestadorCodigo(Long id) {
        Optional<Agendamento> agendamentoSalva = repository.findById(id);
        if (agendamentoSalva.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return agendamentoSalva;
    }

    public void deletar(Long id){
        Optional<Agendamento> agendamento = repository.findById(id);
        if (agendamento.isEmpty()){
            throw new ObjectNotFoundException("Objeto com o Id: " + id + " Não existe!!");
        }
        repository.deleteById(id);
    }
}
