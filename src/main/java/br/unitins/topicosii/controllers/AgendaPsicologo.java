package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.hibernate.type.LocalDateTimeType;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.unitins.topicosii.application.Converters;
import br.unitins.topicosii.application.CustomScheduleEvent;
import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Session;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.application.VersionException;
import br.unitins.topicosii.listing.PacienteListing;
import br.unitins.topicosii.models.Agendamento;
import br.unitins.topicosii.models.DiasDaSemana;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.models.RegistroPagavel;
import br.unitins.topicosii.respository.AgendamentoRepository;
import br.unitins.topicosii.respository.RegistroPagavelRepository;

@Named
@ViewScoped
public class AgendaPsicologo implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<CustomScheduleEvent> scheduleEvents;
	private ScheduleModel model;
	private Agendamento agendamento;
	private ScheduleEvent<Agendamento> event;
	private List<DiasDaSemana> dia;
	// Formulario de marcar consulta

	private int quantidadeSemanas;
	private Paciente pacienteSelecionado;
	private DiasDaSemana diaSelecionadoParaConsulta;
	private LocalTime horarioSelecionado;
	private float valor;
	private Psicologo psicologoLogado;
	// Posiveis horarios de acordo com o psicologo
	private List<LocalTime> listaHorarios;

	public Psicologo getPsicologoLogado() {
		if (this.psicologoLogado == null) {
			this.psicologoLogado = (Psicologo) Session.getInstance().get("psicologoLogado");
			System.out.println(this.psicologoLogado.getPessoa().getNome());
		}

		return psicologoLogado;
	}

	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("/pages/login.xhtml");
	}

	public void setPsicologoLogado(Psicologo psicologoLogado) {
		this.psicologoLogado = psicologoLogado;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public boolean verificaDados() {
		boolean retorno = true;
		if(quantidadeSemanas==0) {
			retorno = false;
			Util.addErrorMessage("Há problemas com a quantidade de semanas");
		}
		if(pacienteSelecionado.getId() == 0){
			retorno = false;
			Util.addErrorMessage("Há problemas com o paciente selecionado");
		}
		if(diaSelecionadoParaConsulta==null){
			retorno = false;
			Util.addErrorMessage("Há problemas com o dia selecionado");
		}
		if(horarioSelecionado==null){
			retorno = false;
			Util.addErrorMessage("Há problemas com o horario selecionado");
		}
		if(valor==0){
			retorno = false;
			Util.addErrorMessage("Há problemas com valor selecionado");
		}

		return retorno;
	}
	
	public List<Agendamento> gerarAgendamentos() {
		if(!verificaDados()) {
			return null;
		}
		// Primeira consulta gerada
		LocalDate dataConsulta = null;
		LocalDateTime dataHoraInicioConsulta = null;
		LocalDateTime dataHoraFimConsulta = null;
		List<Agendamento> agendamentosPaciente = new ArrayList<Agendamento>();
		for (int i = 0; i < quantidadeSemanas; i++) {
			Agendamento agendamentoPaciente = new Agendamento();
			if (dataConsulta == null) {
				dataConsulta = diaSelecionadoParaConsulta.getNextWeekDay(LocalDate.now());
			} else {
				dataConsulta = diaSelecionadoParaConsulta.getNextWeekDay(dataConsulta);
			}
			dataHoraInicioConsulta = dataConsulta.atTime(horarioSelecionado);
			dataHoraFimConsulta = dataHoraInicioConsulta.plus(1, ChronoUnit.HOURS);

			agendamentoPaciente.setDiaSemana(diaSelecionadoParaConsulta.getId());
			agendamentoPaciente.setHoraFim(dataHoraFimConsulta);
			agendamentoPaciente.setHoraInicio(dataHoraInicioConsulta);
			agendamentoPaciente.setPaciente(pacienteSelecionado);
			agendamentoPaciente.setValorSessao(valor);
			agendamentoPaciente.setPsicologo(psicologoLogado);
			agendamentosPaciente.add(agendamentoPaciente);
		}
		return agendamentosPaciente;
	}

	public void excluirAgendamento() {
		AgendamentoRepository repository = new AgendamentoRepository();
		try {
			repository.remove(event.getData());

			Util.addInfoMessage("Agendamento excluído");
		} catch (RepositoryException e) {

		}
	}

	public void cancelarAgendamento() {
		AgendamentoRepository repository = new AgendamentoRepository();
		try {
			event.getData().setCancelado(true);
			repository.save(event.getData());
			Util.addInfoMessage("Agendamento cancelado");
		} catch (RepositoryException e) {

		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	public void concluirAgendamento() {
		AgendamentoRepository repository = new AgendamentoRepository();
		RegistroPagavelRepository registrar = new RegistroPagavelRepository();
		RegistroPagavel registro = new RegistroPagavel();
		try {
			repository.save(event.getData());
			registro.setAgendamento(event.getData());
			registrar.save(registro);
			Util.addInfoMessage("Concluído");
		} catch (RepositoryException e) {

		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	public void incluirAgendamentos() {
		AgendamentoRepository repository = new AgendamentoRepository();
		List<Agendamento> agendamentosGerados = this.gerarAgendamentos();
		if(agendamentosGerados==null) {
			Util.addErrorMessage("Não foi possível gerar os agendamentos");
			return;
		}
		try {
			for (int i = 0; i < agendamentosGerados.size(); i++) {
				if (repository.checkInterval(agendamentosGerados.get(i).getHoraInicio().minus(1, ChronoUnit.MINUTES),
						agendamentosGerados.get(i).getHoraFim().plus(1, ChronoUnit.MINUTES))) {
					Util.addErrorMessage("Não foi possível angendar para o dia "
							+ Converters.getDateFormatted(agendamentosGerados.get(i).getHoraInicio()));
				} else {
					repository.save(agendamentosGerados.get(i));
					this.scheduleEvents = new ArrayList<CustomScheduleEvent>();
					this.init();
					Util.addInfoMessage("Agendamentos gerados com sucesso");
				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	public void onEventSelect(SelectEvent<ScheduleEvent<Agendamento>> selectEvent) {
		event = selectEvent.getObject();
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
		if (this.pacienteSelecionado == null)
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
		if (this.dia == null) {
			this.dia = new ArrayList<DiasDaSemana>();
			for (DiasDaSemana diaSemana : DiasDaSemana.values()) {
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
			CustomScheduleEvent evento = new CustomScheduleEvent( "Consulta com " +
					agendamento.getPaciente().getPessoa().getNome() + " "
							+ agendamento.getPaciente().getPessoa().getSobrenome(),
					agendamento.getHoraInicio(), agendamento.getHoraFim(), false, agendamento);
			this.getScheduleEvents().add(evento);
			this.model.addEvent(evento);
		}
	}

	public List<Agendamento> getAgendamentos() {
		AgendamentoRepository repository = new AgendamentoRepository();
		try {
			return repository.findAllForPsicologo(this.getPsicologoLogado().getId());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
			return new ArrayList<Agendamento>();
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

	public List<LocalTime> getListaHorarios() {
		if (listaHorarios == null)
			listaHorarios = this.gerarPossiveisHorariosDeConsulta();
		return listaHorarios;
	}

	public void setListaHorarios(List<LocalTime> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}

	public String getHorarioExpedientePsicologo(Boolean manha) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		if (manha) {
			return dtf.format(psicologoLogado.getInicioExpediente());
		} else {
			return dtf.format(psicologoLogado.getFimExpediente());
		}
	}

	public List<LocalTime> gerarPossiveisHorariosDeConsulta() {
		List<LocalTime> lista = new ArrayList<LocalTime>();
		LocalTime horarioControle = psicologoLogado.getInicioExpediente();
		int inicioExpediente = psicologoLogado.getInicioExpediente().getHour()  ;
		int fimExpediente = psicologoLogado.getFimExpediente().getHour() + inicioExpediente;
		
		for (int i = 0; i < fimExpediente; i++) {
			lista.add(horarioControle);
			
			horarioControle = horarioControle.plusMinutes(30);
		}
		return lista;
	}
	
	public String formatMoneyValue(Double value) {
		return Util.formatMoneyValues(value);
		
	}
	
	public String formatTime(String time) {
		if(!time.equals(""))
			return Util.getTimeFormatted(LocalDateTime.parse(time) );
		return "";
	}
	public String formatDate(String date) {
		if(!date.equals(""))
			return Util.getDateFormatted(LocalDateTime.parse(date));
		return "";
	}
}
