package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Session;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.models.RegistroPagavel;
import br.unitins.topicosii.respository.RegistroPagavelRepository;

@Named
@ViewScoped
public class PagamentosPendentesController implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<RegistroPagavel> registrosPagaveis;
	private List<RegistroPagavel> listaParaPagamento;
	private Paciente paciente;
	
	
	
	
	public List<RegistroPagavel> getRegistrosPagaveis() {
		if(this.registrosPagaveis==null) {
			RegistroPagavelRepository repo = new RegistroPagavelRepository();
			try {
				this.setRegistrosPagaveis(repo.findAllForPaciente(this.getPaciente().getId()));
			}catch (RepositoryException e) {
				Util.addErrorMessage(e.getMessage());
			}
		}
		return registrosPagaveis;
	}
	public void setRegistrosPagaveis(List<RegistroPagavel> registrosPagaveis) {
		this.registrosPagaveis = registrosPagaveis;
	}
	public List<RegistroPagavel> getListaParaPagamento() {
		if(this.listaParaPagamento==null) {
			this.setListaParaPagamento(new ArrayList<RegistroPagavel>());
		}
		return listaParaPagamento;
	}
	public void setListaParaPagamento(List<RegistroPagavel> listaParaPagamento) {
		this.listaParaPagamento = listaParaPagamento;
	}
	public Paciente getPaciente() {
		if (this.paciente == null) 
			this.paciente = (Paciente) Session.getInstance().get("pacienteLogado");
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
