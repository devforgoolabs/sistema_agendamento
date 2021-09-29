package com.goolab.dto;

import com.goolab.models.PlanoSaude;

import java.io.Serializable;

public class PlanoSaudeDTO implements Serializable {
    
    private String nome;

    public PlanoSaudeDTO() {
    }

    public PlanoSaudeDTO(PlanoSaude obj) {
        nome = obj.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
