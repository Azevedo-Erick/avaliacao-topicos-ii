package br.unitins.topicosii.controllers;

import java.io.Serializable;

import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Telefone;

public class PacienteEditarInformacoesController implements Serializable{

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private Telefone telefone;
	
	public Paciente getPaciente() {
		if(this.paciente==null) {
			this.paciente=new Paciente();
		}
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public void salvar() {}
	public Telefone getTelefone() {
		if(this.telefone==null) {
			this.telefone = new Telefone();
		}
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public void adicionarTelefone() {
		this.paciente.getPessoa().getTelefones().add(telefone);
	}
	public void removerTelefone(Telefone tel) {
		this.getPaciente().getPessoa().getTelefones().remove(tel);
	}
}
