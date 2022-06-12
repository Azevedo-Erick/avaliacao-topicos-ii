package br.unitins.topicosii.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;



@Entity
public class Estado extends DefaultEntity{
	@NotNull
	private String nome;
	@NotNull
	private String sigla;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	@Override
	public String toString() {
		return nome  + " - " + sigla;
	}
	
}
