package br.unitins.topicosii.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Cidade extends DefaultEntity{
	private String nome;
		
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		if(this.estado==null)
			this.estado=new Estado();
		return estado;
	}

	public void setEstado(Estado estado) {
		
		this.estado = estado;
	}
	
	
	
}
