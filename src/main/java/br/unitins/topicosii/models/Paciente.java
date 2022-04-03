package br.unitins.topicosii.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Paciente extends DefaultEntity{
	@OneToOne
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
