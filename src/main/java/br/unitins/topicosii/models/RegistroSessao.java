package br.unitins.topicosii.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.exolab.castor.types.DateTime;

@Entity
public class RegistroSessao extends DefaultEntity{
	private DateTime dataHora;
	private String descricao;
	private boolean cancelado;
	private Float valor;
	@ManyToOne
	private Paciente paciente;
	@ManyToOne
	private Psicologo psicologo;
	
}
