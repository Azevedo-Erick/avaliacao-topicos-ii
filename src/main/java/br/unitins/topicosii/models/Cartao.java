package br.unitins.topicosii.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cartao extends Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Column(length=200)
	private String nomeTitular;
	@Column(length=40)
	private String numeroDaConta;
	@Column(length=5)
	private String cvv;
	@Column(length=6)
	private String vencimento;
	public String getNomeTitular() {
		return nomeTitular;
	}
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	public String getNumeroDaConta() {
		return numeroDaConta;
	}
	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getVencimento() {
		return vencimento;
	}
	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}
	
	
	
}
