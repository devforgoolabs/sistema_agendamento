package com.goolab.dto;

import com.goolab.models.Unidade;

import java.io.Serializable;

public class UnidadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    public UnidadeDTO() {
    }

    public UnidadeDTO(Unidade obj) {
        id = obj.getId();
        nome = obj.getNome();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
