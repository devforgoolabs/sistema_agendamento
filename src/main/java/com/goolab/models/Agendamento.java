package com.goolab.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Agendamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "prestador_id")
	private Prestador prestador;

	@ManyToOne
	@JoinColumn(name = "unidade_id")
	private Unidade unidade;

	@ManyToOne
	@JoinColumn(name = "especialidade_id")
	private Especialidade especialidade;

	@ManyToOne
	private Servico servico;

	@ManyToOne
	@JoinColumn(name = "horario_id")
	private Horario horario;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@OneToOne
	@JoinColumn(name = "planosaude_id")
	private PlanoSaude planosaude;

	public PlanoSaude getPlanosaude() {
		return planosaude;
	}

	public void setPlanosaude(PlanoSaude planosaude) {
		this.planosaude = planosaude;
	}

	public Agendamento() {

	}

	public Agendamento(Long id, Prestador prestador, Unidade unidade, Especialidade especialidade, Servico servico, Horario horario, Paciente paciente, PlanoSaude planosaude) {
		this.id = id;
		this.prestador = prestador;
		this.unidade = unidade;
		this.especialidade = especialidade;
		this.servico = servico;
		this.horario = horario;
		this.paciente = paciente;
		this.planosaude = planosaude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Agendamento that = (Agendamento) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
