package br.unitins.topicosii.models;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;



@Entity
public class Agendamento extends DefaultEntity{
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;
	private int diaSemana;
	private float valorSessao;
	private String descricao;
	private boolean cancelado;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isCancelado() {
		return cancelado;
	}
	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
	@ManyToOne
	private Paciente paciente;
	@ManyToOne
	private Psicologo psicologo;
	public Agendamento(){}
	public Agendamento(LocalDateTime horaInicio, LocalDateTime horaFim, int diaSemana, float valorSessao) {
		super();
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.diaSemana = diaSemana;
		this.valorSessao = valorSessao;
	}
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalDateTime getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(LocalDateTime horaFim) {
		this.horaFim = horaFim;
	}
	public int getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	public float getValorSessao() {
		return valorSessao;
	}
	public void setValorSessao(float valorSessao) {
		this.valorSessao = valorSessao;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Psicologo getPsicologo() {
		return psicologo;
	}
	public void setPsicologo(Psicologo psicologo) {
		this.psicologo = psicologo;
	}
	
	
}
