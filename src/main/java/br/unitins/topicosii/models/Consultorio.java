package br.unitins.topicosii.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Consultorio extends DefaultEntity{
	private String nome;
	
	@OneToOne
	private Endereco endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
