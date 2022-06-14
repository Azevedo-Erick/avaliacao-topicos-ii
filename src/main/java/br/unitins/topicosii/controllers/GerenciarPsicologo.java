package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.topicosii.listing.PessoaListing;
import br.unitins.topicosii.listing.PsicologoListing;
import br.unitins.topicosii.models.Estado;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.respository.PsicologoRepository;

@Named
@ViewScoped
public class GerenciarPsicologo extends Controller<Psicologo> implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<LocalTime> listaHorarios;

	public GerenciarPsicologo() {
		super(new PsicologoRepository());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Psicologo getEntity() {
		if (entity == null) {
			
			entity = new Psicologo();
			entity.setPessoa(new Pessoa());
		}
		return entity;
	}

	public void abrirPessoaListing() {
		PessoaListing listing = new PessoaListing();
		listing.open();
	}

	public void obterPessoaListing(SelectEvent<Pessoa> event) {
		this.getEntity().setPessoa(event.getObject());
	}
	
	public void abrirPsicologoListing() {
		PsicologoListing listing = new PsicologoListing();
		listing.open();
	}

	public void obterPsicologoListing(SelectEvent<Psicologo> event) {
		this.setEntity(event.getObject());
	}

	public List<LocalTime> getListaHorarios() {
		if (listaHorarios == null)
			listaHorarios = this.gerarPossiveisHorariosDeConsulta();
		return listaHorarios;
	}

	public void setListaHorarios(List<LocalTime> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}

	public List<LocalTime> gerarPossiveisHorariosDeConsulta() {
		List<LocalTime> lista = new ArrayList<LocalTime>();
		LocalTime horarioControle = LocalTime.of(6, 0);
		int inicioExpediente = 6;
		int fimExpediente = 23 + inicioExpediente;

		for (int i = 0; i < fimExpediente; i++) {
			lista.add(horarioControle);
			horarioControle = horarioControle.plusMinutes(30);
		}
		return lista;
	}
}
