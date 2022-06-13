package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.listing.PacienteListing;
import br.unitins.topicosii.listing.PessoaListing;
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.models.Telefone;
import br.unitins.topicosii.respository.CidadeRepository;
import br.unitins.topicosii.respository.PacienteRepository;

@Named
@ViewScoped
public class GerenciarPaciente extends Controller<Paciente> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private Psicologo psicologo;
	private Telefone telefone;
	public GerenciarPaciente() {
		super(new PacienteRepository());
		
	}

	@Override
	public Paciente getEntity() {
		if (entity == null)
			entity = new Paciente();
		return null;
	}
	
	public void abrirPessoaListing() {
		PessoaListing listing = new PessoaListing();
		listing.open();
	}

	public void obterPessoaListing(SelectEvent<Pessoa> event) {
		this.getEntity().setPessoa(event.getObject());
	}
	
	public void abrirPacienteListing() {
		PacienteListing listing = new PacienteListing();
		listing.open();
	}

	public void obterPacienteListing(SelectEvent<Paciente> event) {
		this.setEntity(event.getObject());
	}
	
	public List<Cidade> completeCidade(String filtro) {
		CidadeRepository repo = new CidadeRepository();
		try {
			return repo.findByNome(filtro, 4);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Cidade>();
		}
	}
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

	public Paciente getPaciente() {
		if(this.paciente==null)
			this.setPaciente(new Paciente());
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		
		this.paciente = paciente;
	}

	public Psicologo getPsicologo() {
		return psicologo;
	}

	public void setPsicologo(Psicologo psicologo) {
		this.psicologo = psicologo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
