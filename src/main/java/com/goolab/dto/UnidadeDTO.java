package com.goolab.dto;

import com.goolab.models.Unidade;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UnidadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo Obrigatório")
    @Length(min = 3, max = 50)
    private String nome;

    public UnidadeDTO() {
    }

    public UnidadeDTO(@NotNull Unidade obj) {
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
