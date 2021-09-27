package com.goolab.dto;

import com.goolab.models.Servico;

import java.io.Serializable;

public class ServicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Double valor;
    private String descricao;

    public ServicoDTO() {
    }

    public ServicoDTO(Servico obj) {
        id = obj.getId();
        nome = obj.getNome();
        valor = obj.getValor();
        descricao = obj.getDescricao();
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
