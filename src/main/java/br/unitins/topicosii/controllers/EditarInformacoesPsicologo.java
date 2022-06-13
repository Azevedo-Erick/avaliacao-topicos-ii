package br.unitins.topicosii.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Session;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.application.VersionException;
import br.unitins.topicosii.listing.CidadeListing;
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Consultorio;
import br.unitins.topicosii.models.Endereco;
import br.unitins.topicosii.models.Psicologo;
import br.unitins.topicosii.models.Telefone;
import br.unitins.topicosii.respository.CidadeRepository;
import br.unitins.topicosii.respository.ConsultorioRepository;
import br.unitins.topicosii.respository.PsicologoRepository;

@Named
@ViewScoped
public class EditarInformacoesPsicologo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Psicologo psicologo;
	private String senha;
	private Telefone telefone;

	private List<LocalTime> listaHorarios;

	private InputStream fotoInputStream = null;

	public Telefone getTelefone() {
		if (this.telefone == null)
			this.telefone = new Telefone();
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public void adicionarTelefone() {
		this.psicologo.getPessoa().getTelefones().add(telefone);
	}

	public void removerTelefone(Telefone tel) {
		this.psicologo.getPessoa().getTelefones().remove(tel);
	}

	public void editarTelefone(Telefone tel) {
		this.telefone = tel;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Psicologo getPsicologo() {
		if (this.psicologo == null) {
			this.psicologo = (Psicologo) Session.getInstance().get("psicologoLogado");
			if (this.psicologo.getPessoa().getEndereco() == null) {
				this.psicologo.getPessoa().setEndereco(new Endereco());
			}
		}
		return psicologo;
	}

	public void setPsicologo(Psicologo psicologo) {
		this.psicologo = psicologo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void onDateSelect(SelectEvent<Date> event) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		this.psicologo.getPessoa().setDataNascimento(LocalDate.parse(format.format(event.getObject())));

	}

	public void abrirCidadeListing() {
		CidadeListing listing = new CidadeListing();
		listing.open();
	}

	public void obterCidadeListing(SelectEvent<Cidade> event) {
		this.getPsicologo().getPessoa().getEndereco().setCidade(event.getObject());
	}

	public void upload(FileUploadEvent event) {
		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				fotoInputStream = uploadFile.getInputStream();
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da image deve ser png.");
		}

	}

	private boolean autenticar() {
		PsicologoRepository repo = new PsicologoRepository();
		System.out.println("Autenticando");
		String senhaOriginal = null;
		try {
			senhaOriginal = repo.findById(this.psicologo.getId()).getPessoa().getSenha();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String SenhaConfirmacaoComHash = Util.hash(this.psicologo.getPessoa().getId().toString(), senha);
		if (senhaOriginal != null) {
			try {
				if (senhaOriginal.equals(SenhaConfirmacaoComHash)) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	public void salvar() {
		PsicologoRepository repository = new PsicologoRepository();

//		if (getFotoInputStream() != null) {
//			if (!Util.saveImageUsuario(getFotoInputStream(), "png", this.getPsicologo().getPessoa().getId())) {
//				Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do usuário.");
//				return;
//			}
//		}

		try {
			System.out.println("Autenticando");
			if (autenticar()) {
				System.out.println("Autenticado");
				System.out.println(this.psicologo.getPessoa().getNome());
				Psicologo psicologoAtualizado = repository.save(this.psicologo);
				Session.getInstance().set("psicologoLogado", psicologoAtualizado);
				this.setPsicologo(null);
				this.getPsicologo();
				Util.addInfoMessage("Dados atualizados com sucesso");

			} else {
				Util.addErrorMessage("Senha incorreta");
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			Util.addErrorMessage("Erro ao salvar");
		}
		this.setSenha("");
	}

	public String getHorarioExpedientePsicologo(Boolean manha) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		if (manha) {
			return dtf.format(psicologo.getInicioExpediente());
		} else {
			return dtf.format(psicologo.getFimExpediente());
		}
	}

	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}

	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}

	public void redirect(String page) {
		Util.redirect(page);
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

	public List<Consultorio> completeConsultorio(String filtro) {
		ConsultorioRepository repo = new ConsultorioRepository();
		try {
			return repo.findByNome(filtro, 4);
		} catch (RepositoryException e) {
			e.printStackTrace();
			return new ArrayList<Consultorio>();
		}
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
	
}
