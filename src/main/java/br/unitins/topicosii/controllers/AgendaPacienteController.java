package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.models.Agendamento;

@Named
@ViewScoped
public class AgendaPacienteController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Agendamento> agendamentos;

}
