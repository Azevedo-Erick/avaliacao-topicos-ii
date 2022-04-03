package br.unitins.topicosii.models;


import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Agendamento extends DefaultEntity{
	private LocalTime hora;
	private int diaSemana;
	private float valorSessao;
	@ManyToOne
	private Paciente paciente;
	@ManyToOne
	private Psicologo psicologo;
	
}
