package com.goolab.dto;

import com.goolab.models.Atributos;
import com.goolab.models.Especialidade;

import java.io.Serializable;

public class EspecialidadeDTO implements Serializable {

    private static final long serialVersionULD = 1L;

    private Long id;
    private String tipo;

    public EspecialidadeDTO() {
    }

    public EspecialidadeDTO(Especialidade obj) {
        id = obj.getId();
        tipo = obj.getTipo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
