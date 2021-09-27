package com.goolab.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Especialidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "atributos_id")
	private Atributos atributos;

	@JsonIgnore
	@OneToMany(mappedBy = "especialidade")
	private List<Agendamento> agendamentos;
	
	public Especialidade() {
	}

	public Especialidade(Long id, String tipo) {
		this.id = id;
		this.tipo = tipo;
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


	public Atributos getAtributos() {
		return atributos;
	}

	public void setAtributos(Atributos atributos) {
		this.atributos = atributos;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidade other = (Especialidade) obj;
		return Objects.equals(id, other.id);
	}
	
}
