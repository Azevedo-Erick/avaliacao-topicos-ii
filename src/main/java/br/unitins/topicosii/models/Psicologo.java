package br.unitins.topicosii.models;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Psicologo extends DefaultEntity{
	@NotNull
	private String crp;
	@NotNull
	private float valorHora;
	@NotNull
	private String informacoesFormacao;
	@NotNull
	private LocalTime inicioExpediente;
	@NotNull
	private LocalTime fimExpediente;
	private Perfil perfil=Perfil.FUNCIONARIO;
	@OneToOne
	@JoinColumn(unique = true)
	@Cascade(CascadeType.ALL)
	private Pessoa pessoa;
	@ManyToMany
	private List<Consultorio> consultorios;
	
	
	
	

	public LocalTime getInicioExpediente() {
		return inicioExpediente;
	}

	public void setInicioExpediente(LocalTime inicioExpediente) {
		this.inicioExpediente = inicioExpediente;
	}

	public LocalTime getFimExpediente() {
		return fimExpediente;
	}

	public void setFimExpediente(LocalTime fimExpediente) {
		this.fimExpediente = fimExpediente;
	}

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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	
	
}
