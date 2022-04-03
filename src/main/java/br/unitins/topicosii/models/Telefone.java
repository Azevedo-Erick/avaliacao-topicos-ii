package br.unitins.topicosii.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Telefone extends DefaultEntity{
	
	@Column(length=2)
	private String ddd;
	@Column(length=9)
	private String numero;

}
