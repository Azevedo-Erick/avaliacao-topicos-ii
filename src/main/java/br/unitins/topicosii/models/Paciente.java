package br.unitins.topicosii.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Paciente extends DefaultEntity{
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(unique = true)
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		if(this.pessoa==null)
			this.pessoa=new Pessoa();
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
