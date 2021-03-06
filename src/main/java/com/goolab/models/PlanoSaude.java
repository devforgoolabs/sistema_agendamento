package com.goolab.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "planosaude")
public class  PlanoSaude implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@JsonIgnore
	@OneToOne(mappedBy = "planosaude")
	private Agendamento agendamento;
	
	public PlanoSaude() {
	}
	
	public PlanoSaude(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public PlanoSaude(String nome) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PlanoSaude that = (PlanoSaude) o;
		return id.equals(that.id);
	}
}
