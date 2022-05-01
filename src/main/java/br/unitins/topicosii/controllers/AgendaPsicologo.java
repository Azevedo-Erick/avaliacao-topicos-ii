package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.unitins.topicosii.application.CustomScheduleEvent;
import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.listing.PacienteListing;
import br.unitins.topicosii.models.Agendamento;
import br.unitins.topicosii.models.DiasDaSemana;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.respository.AgendamentoRepository;

@Named
@ViewScoped
public class AgendaPsicologo  implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<CustomScheduleEvent> scheduleEvents;
	private ScheduleModel model;
	private Agendamento agendamento;
	private ScheduleEvent<Agendamento> event;
	private List<DiasDaSemana> dia;
	//Formulario de marcar consulta
	private int quantidadeSemanas;
	private Paciente pacienteSelecionado;
	private DiasDaSemana diaSelecionadoParaConsulta;
	private LocalTime horarioSelecionado;

	public List<Agendamento> gerarAgendamentos() {
		//Primeira consulta gerada
		LocalDate dataConsulta=null;
		LocalDateTime dataHoraInicioConsulta = null;
		LocalDateTime dataHoraFimConsulta = null;
		List<Agendamento> agendamentosPaciente = new ArrayList<Agendamento>();
		for(int i = 0;i<quantidadeSemanas;i++) {
			Agendamento agendamentoPaciente = new Agendamento(); 
			if(dataConsulta==null) {
				dataConsulta = diaSelecionadoParaConsulta.getNextWeekDay(LocalDate.now());
			}else {
				dataConsulta = diaSelecionadoParaConsulta.getNextWeekDay(dataConsulta);
			}
			dataHoraInicioConsulta = dataConsulta.atTime(horarioSelecionado);
			dataHoraFimConsulta = dataHoraInicioConsulta.plus(1, ChronoUnit.HOURS);
			
			agendamentoPaciente.setDiaSemana(diaSelecionadoParaConsulta.getId());
			agendamentoPaciente.setHoraFim(dataHoraFimConsulta);
			agendamentoPaciente.setHoraInicio(dataHoraInicioConsulta);
			agendamentoPaciente.setPaciente(pacienteSelecionado);
			agendamento.setValorSessao(200f);
			agendamentosPaciente.add(agendamentoPaciente);
		}
		return agendamentosPaciente;
	}
	public void incluirAgendamentos(){
		AgendamentoRepository repository = new AgendamentoRepository();
		List<Agendamento> agendamentosGerados = this.gerarAgendamentos();
		try {
			for(int i = 0;i<agendamentosGerados.size();i++) {
				repository.save(agendamentosGerados.get(i));
			}
			Util.addInfoMessage("Agendamentos gerados com sucesso");
		}catch(RepositoryException e){
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void selecionarPaciente(Paciente obj) {
		this.setPacienteSelecionado(obj);
	}
	
	public int getQuantidadeSemanas() {
		return quantidadeSemanas;
	}
	public void setQuantidadeSemanas(int quantidadeSemanas) {
		this.quantidadeSemanas = quantidadeSemanas;
	}
	public Paciente getPacienteSelecionado() {
		if(this.pacienteSelecionado==null)
			this.setPacienteSelecionado(new Paciente());
		return pacienteSelecionado;
	}
	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}
	public DiasDaSemana getDiaSelecionadoParaConsulta() {
		return diaSelecionadoParaConsulta;
	}
	public void setDiaSelecionadoParaConsulta(DiasDaSemana diaSelecionadoParaConsulta) {
		this.diaSelecionadoParaConsulta = diaSelecionadoParaConsulta;
	}
	public LocalTime getHorarioSelecionado() {
		return horarioSelecionado;
	}
	public void setHorarioSelecionado(LocalTime horarioSelecionado) {
		this.horarioSelecionado = horarioSelecionado;
	}


	public List<DiasDaSemana> getDia() {
		if(this.dia==null) {
			this.dia= new ArrayList<DiasDaSemana>();
			for(DiasDaSemana diaSemana : DiasDaSemana.values()) {
				dia.add(diaSemana);
			}
		}
		return dia;
	}

	public void init() {
		event = new CustomScheduleEvent();
		model = new DefaultScheduleModel();
		agendamento = new Agendamento();
		for (Agendamento agendamento : this.getAgendamentos()) {
			System.out.println(agendamento.getHoraInicio());
			System.out.println(agendamento.getHoraFim());
			CustomScheduleEvent evento = new CustomScheduleEvent("Titulo aaa", agendamento.getHoraInicio(),
					agendamento.getHoraFim(), false, agendamento);
			this.getScheduleEvents().add(evento);
			this.model.addEvent(evento);
		}
	}
	
	public List<Agendamento> getAgendamentos() {
		AgendamentoRepository repository = new AgendamentoRepository();
		try {
			return repository.findAll();
		}catch(RepositoryException e){
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
			return null;
		}
	}

	public void setDia(List<DiasDaSemana> dia) {
		this.dia = dia;
	}

	public ScheduleModel getModel() {
		if (this.model == null)
			this.init();
		return model;
	}

	public void setModel(ScheduleModel model) {
		this.model = model;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public ScheduleEvent<Agendamento> getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent<Agendamento> event) {
		this.event = event;
	}

	
	public void abrirPacienteListing() {
		PacienteListing listing = new PacienteListing();
		listing.open();
	}
	

	
	public void obterPacienteListing(SelectEvent<Paciente> event) {
		this.setPacienteSelecionado(event.getObject());
	}



	public List<CustomScheduleEvent> getScheduleEvents() {
		if (this.scheduleEvents == null) {
			scheduleEvents = new ArrayList<CustomScheduleEvent>();
		}
			
	
		return scheduleEvents;
	}

	public void redirect(String page) {
		Util.redirect(page);
	}
	
	

	public void setScheduleEvents(List<CustomScheduleEvent> scheduleEvents) {
		this.scheduleEvents = scheduleEvents;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}