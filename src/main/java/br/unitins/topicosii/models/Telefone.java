package br.unitins.topicosii.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Telefone extends DefaultEntity{
	
	@Column(length=2)
	@NotNull
	private String ddd;
	@Column(length=9)
	@NotNull
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
