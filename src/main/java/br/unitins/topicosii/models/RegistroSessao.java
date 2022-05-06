package br.unitins.topicosii.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.exolab.castor.types.DateTime;

@Entity
public class RegistroSessao extends DefaultEntity{
	private DateTime dataHora;
	private String descricao;
	private boolean cancelado;
	private Float valor;
	@ManyToOne
	private Paciente paciente;
	@ManyToOne
	private Psicologo psicologo;
	public DateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(DateTime dataHora) {
		this.dataHora = dataHora;
	}
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
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
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
