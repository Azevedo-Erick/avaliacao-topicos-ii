package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.respository.PacienteRepository;
@Named
@ViewScoped
public class ControlePacientesController implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private List<Paciente> pacientes;
	private Paciente pacienteForm;
	
	public List<Paciente> getPacientes() {
		PacienteRepository repository = new PacienteRepository();
		if(this.pacientes==null) {
			try {
				this.setPacientes( repository.findAll());
				
			}catch (RepositoryException e) {
				Util.addErrorMessage(e.getMessage());
			}
		}
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		
		this.pacientes = pacientes;
	}
	public Paciente getPacienteForm() {
		
		if(this.pacienteForm==null) {
			this.setPacienteForm(new Paciente());
		}
		return pacienteForm;
	}
	public void setPacienteForm(Paciente pacienteForm) {
		this.pacienteForm = pacienteForm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void selecionarPaciente(Paciente obj) {
		this.setPacienteForm(obj);
	}
	public void salvar() {
		PacienteRepository repository = new PacienteRepository();
		try {
			repository.save(this.getPacienteForm());
			Util.addInfoMessage("Inclusão realizada com sucesso");
			this.limpar();
		}catch(RepositoryException e){
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void remover(Paciente paciente) {
		PacienteRepository repository = new PacienteRepository();
		try {
			repository.remove(this.getPacienteForm());
			Util.addInfoMessage("Remoção realizada com sucesso");
			this.limpar();
		}catch(RepositoryException e){
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	public void limpar() {
		this.setPacienteForm(null);
	}
}
