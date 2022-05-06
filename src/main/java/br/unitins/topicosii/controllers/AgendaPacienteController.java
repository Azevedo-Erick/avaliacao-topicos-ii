package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Agendamento;
import br.unitins.topicosii.models.DiasDaSemana;
import br.unitins.topicosii.respository.AgendamentoRepository;

@Named
@ViewScoped
public class AgendaPacienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Agendamento> agendamentos;
	public List<Agendamento> getAgendamentos() {
		if(this.agendamentos==null) {
			try {
				AgendamentoRepository repository = new AgendamentoRepository();
				this.agendamentos = repository.findAll();
			}catch (RepositoryException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		}
		return agendamentos;
	}
	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
	
	public String getDiaSemanaString(int id) {
		return DiasDaSemana.valueOf(id).getLabel();
	}
	public String getDateFormatted(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
		return formatter.format(date);
	}
	public String getTimeFormatted(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
		return formatter.format(date);
	}

}
