package br.unitins.topicosii.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class RegistroPagavel extends DefaultEntity{
	@OneToOne
	@JoinColumn(unique = true)
	private Agendamento agendamento;

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
}
