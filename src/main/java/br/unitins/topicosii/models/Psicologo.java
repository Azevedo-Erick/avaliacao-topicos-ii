package br.unitins.topicosii.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Psicologo extends DefaultEntity{
	private String crp;
	private float valorHora;
	private String informacoesFormacao;
	@OneToOne
	private Pessoa pessoa;
	@ManyToMany
	private List<Consultorio> consultorios;
	public String getCrp() {
		return crp;
	}
	public void setCrp(String crp) {
		this.crp = crp;
	}
	public float getValorHora() {
		return valorHora;
	}
	public void setValorHora(float valorHora) {
		this.valorHora = valorHora;
	}
	public String getInformacoesFormacao() {
		return informacoesFormacao;
	}
	public void setInformacoesFormacao(String informacoesFormacao) {
		this.informacoesFormacao = informacoesFormacao;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public List<Consultorio> getConsultorios() {
		return consultorios;
	}
	public void setConsultorios(List<Consultorio> consultorios) {
		this.consultorios = consultorios;
	}
	
	
	
}
