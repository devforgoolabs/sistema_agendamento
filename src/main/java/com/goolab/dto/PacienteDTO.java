package com.goolab.dto;

import com.goolab.models.Paciente;

import java.io.Serializable;

public class PacienteDTO implements Serializable {

    private Long id;

    private String nome;
    private String telefone;
    private String email;
    private String dataNascimento;
    private String sexo;


    public PacienteDTO() {
    }

    public PacienteDTO(Paciente obj) {
        id = obj.getId();
        nome = obj.getNome();
        telefone = obj.getTelefone();
        email = obj.getEmail();
        dataNascimento = obj.getDataNascimento();
        sexo = obj.getSexo();
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
