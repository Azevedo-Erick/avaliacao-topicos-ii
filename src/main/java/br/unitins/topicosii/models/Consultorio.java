package br.unitins.topicosii.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Consultorio extends DefaultEntity{
	@NotNull
	private String nome;
	
	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	private Endereco endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		if(this.endereco==null) {
			this.setEndereco(new Endereco());
		}
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
