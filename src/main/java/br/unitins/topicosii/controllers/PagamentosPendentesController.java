package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Session;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.models.Cartao;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Pagamento;
import br.unitins.topicosii.models.Pix;
import br.unitins.topicosii.models.RegistroPagavel;
import br.unitins.topicosii.models.TIPOPIX;
import br.unitins.topicosii.respository.CartaoRepository;
import br.unitins.topicosii.respository.PixRepository;
import br.unitins.topicosii.respository.RegistroPagavelRepository;

@Named
@ViewScoped
public class PagamentosPendentesController implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<RegistroPagavel> registrosPagaveis;
	private List<RegistroPagavel> listaParaPagamento;
	private Paciente paciente;
	private RegistroPagavel registroPago;
	private Pix pixPagamento;
	private Cartao cartaoPagamento;
	private List<TIPOPIX> tiposPix;
	private Pagamento tipoPagamento;

	public List<RegistroPagavel> getRegistrosPagaveis() {
		if (this.registrosPagaveis == null) {
			RegistroPagavelRepository repo = new RegistroPagavelRepository();
			try {
				this.setRegistrosPagaveis(repo.findAllForPaciente(this.getPaciente().getId()));
			} catch (RepositoryException e) {
				Util.addErrorMessage(e.getMessage());
			}
		}
		return registrosPagaveis;
	}

	public void setRegistrosPagaveis(List<RegistroPagavel> registrosPagaveis) {
		this.registrosPagaveis = registrosPagaveis;
	}

	public List<RegistroPagavel> getListaParaPagamento() {
		if (this.listaParaPagamento == null) {
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

	public RegistroPagavel getRegistroPago() {
		if (this.registroPago == null) {
			this.registroPago = new RegistroPagavel();
		}
		return registroPago;
	}

	public Pix getPixPagamento() {
		if (this.pixPagamento == null)
			this.pixPagamento = new Pix();
		return pixPagamento;
	}

	public void setPixPagamento(Pix pixPagamento) {
		this.pixPagamento = pixPagamento;
	}

	public Cartao getCartaoPagamento() {
		if (this.cartaoPagamento == null)
			this.cartaoPagamento = new Cartao();
		return cartaoPagamento;
	}

	public void setCartaoPagamento(Cartao cartaoPagamento) {
		this.cartaoPagamento = cartaoPagamento;
	}

	public List<TIPOPIX> getTiposPix() {
		if (this.tiposPix == null) {
			this.tiposPix = new ArrayList<TIPOPIX>();
			for (TIPOPIX tipo : TIPOPIX.values()) {
				tiposPix.add(tipo);
			}
		}
		return tiposPix;
	}

	public void setTiposPix(List<TIPOPIX> tiposPix) {
		this.tiposPix = tiposPix;
	}

	public void pagar() {
		if (this.cartaoPagamento == null && this.pixPagamento == null) {
			Util.addErrorMessage("Selecione um meio de pagamento");
			return;
		}
		CartaoRepository cartaoRepository = new CartaoRepository();
		PixRepository pixRepository = new PixRepository();
		float total = 0;
		for (RegistroPagavel registroPagavel : this.getListaParaPagamento()) {
			total += registroPagavel.getAgendamento().getValorSessao()
					+ registroPagavel.getAgendamento().getPsicologo().getValorHora();
		}
		try {

			if (tipoPagamento instanceof Cartao) {
				cartaoPagamento.setTotal(total);
				cartaoPagamento.setRegistros(listaParaPagamento);
				cartaoRepository.save(cartaoPagamento);
			}
			if (tipoPagamento instanceof Pix) {
				pixPagamento.setTotal(total);
				pixPagamento.setRegistros(listaParaPagamento);
				pixRepository.save(pixPagamento);
			}
		} catch (Exception e) {
			Util.addErrorMessage(e.getMessage());
		}
	}

	public void adicionarParaPagamento(RegistroPagavel obj) {
		if(!this.getListaParaPagamento().contains(obj)) {
			this.getListaParaPagamento().add(obj);
		}else {
			this.getListaParaPagamento().remove(obj);
		}
	}

	public void setRegistroPago(RegistroPagavel registroPago) {
		this.registroPago = registroPago;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getTotalPagar() {
		double total = 0;
		for (RegistroPagavel registroPagavel : this.getListaParaPagamento()) {
			total += registroPagavel.getAgendamento().getValorSessao()
					+ registroPagavel.getAgendamento().getPsicologo().getValorHora();
		}
		return total;
	}

	public void selecionaPix(Pix obj) {
		this.anulaMeiosDePagamento();
		this.setPixPagamento(obj);
	}

	public void selecionaCartao(Cartao obj) {
		this.anulaMeiosDePagamento();
		this.setCartaoPagamento(obj);
	}

	private void anulaMeiosDePagamento() {
		this.setCartaoPagamento(null);
		this.setPixPagamento(null);
	}

	public Pagamento getTipoPagamento() {
		if (this.tipoPagamento == null) {
			this.tipoPagamento = new Pagamento();
		}
		return tipoPagamento;
	}

	public void setTipoPagamento(Pagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Pagamento getMeioPagamento(int id) {
		System.out.println(this.tipoPagamento);
		if (id == 1) {
			return new Cartao();
		} else {
			return new Pix();
		}
	}

	public boolean instanceOfCartao() {
		return tipoPagamento instanceof Cartao;
	}

	public boolean instanceOfPix() {
		return tipoPagamento instanceof Pix;
	}
	
	public String[] checkIfContainsToStyleButton(RegistroPagavel obj ) {
		if(this.getListaParaPagamento().contains(obj)) {
			return new String[]{"ui-button-danger","pi pi-times"};
		}
		return new String[]{"ui-button-success","pi pi-credit-card"};
	}
	
	public String formatMoneyValue(String value) {
		return Util.formatMoneyValues(Double.parseDouble(value));
	}
}
