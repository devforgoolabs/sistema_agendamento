package com.goolab.dto;

import com.goolab.models.Prestador;

import java.io.Serializable;

public class PrestadorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    public PrestadorDTO() {
    }

    public PrestadorDTO(Prestador obj) {
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
