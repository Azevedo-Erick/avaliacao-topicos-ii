package br.unitins.topicosii.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Telefone extends DefaultEntity{
	
	@Column(length=2)
	private String ddd;
	@Column(length=9)
	private String numero;
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

}
