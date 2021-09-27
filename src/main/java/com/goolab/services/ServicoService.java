package com.goolab.services;

import com.goolab.dto.ServicoDTO;
import com.goolab.models.Servico;
import com.goolab.repositories.ServicoRepository;
import com.goolab.services.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public List<Servico> listar(){
        return repository.findAll();
    }

    public Servico buscar(Long id) {
        Optional<Servico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Servico.class.getName()));
    }

    public Servico inserir(Servico servico){
        return repository.save(servico);
    }

    public Servico atualizar(Long id, Servico servico) {
        Optional<Servico> servicoSalva = repository.findById(id);

        if (servicoSalva.isEmpty()){
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Servico.class.getName());
        }

        BeanUtils.copyProperties(servico, servicoSalva.get(), "id");
        return repository.save(servicoSalva.get());
    }

    public Optional<Servico> buscarPessoaCodigo(Long id) {
        Optional<Servico> servicoSalva = repository.findById(id);
        if (servicoSalva.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return servicoSalva;
    }

    public void deletar(Long id){
        Optional<Servico> servico = repository.findById(id);
        if (servico.isEmpty()){
            throw new ObjectNotFoundException("Objeto com o Id: " + id + " Não existe!!");
        }
        repository.deleteById(id);
    }

    public Page<Servico> buscaPorPagina(Integer page, Integer linesPerPages, String direction, String orderBy){
        PageRequest request = PageRequest.of(page, linesPerPages, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(request);
    }

    public Servico fromDTO(ServicoDTO dto){
        return new Servico(dto.getId(), dto.getNome(), dto.getValor(), dto.getDescricao());
    }
}
