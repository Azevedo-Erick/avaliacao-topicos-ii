package br.unitins.topicosii.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.models.Endereco;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Telefone;
import br.unitins.topicosii.respository.CidadeRepository;
import br.unitins.topicosii.respository.PacienteRepository;

@Named
@ViewScoped
public class EditarInformacoesPaciente implements Serializable{

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private Telefone telefone;
	private String senha;
	private InputStream fotoInputStream = null;
	private boolean autenticar() {
		if(senha==null || senha =="") {
			Util.addErrorMessage("Insira uma senha");
			return false;
		}
		PacienteRepository repo = new PacienteRepository();
		String senhaOriginal = null;
		try {
			senhaOriginal = repo.findByEmail(this.paciente).getPessoa().getSenha();
		} catch (Exception e) {
			Util.addErrorMessage("Não foi possível autenticar a senha no banco de dados");
		}
		String SenhaConfirmacaoComHash = Util.hash(this.paciente.getPessoa().getId().toString(), this.getSenha());
		if (senhaOriginal != null) {
			try {
				System.out.println(this.getSenha());
				System.out.println(senhaOriginal);
				System.out.println(SenhaConfirmacaoComHash);
				System.out.println(senhaOriginal.equals(SenhaConfirmacaoComHash));
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
		PacienteRepository repository = new PacienteRepository();

		if (getFotoInputStream() != null) {
			if (!Util.saveImageUsuario(getFotoInputStream(), "png", this.getPaciente().getPessoa().getId())) {
				Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do usuário.");
				return;
			}
		}
		try {
			if (autenticar()) {
				Paciente pacienteAtualizado = repository.save(this.paciente);
				Session.getInstance().set("pacienteLogado", pacienteAtualizado);
				this.setPaciente(null);
				this.getPaciente();
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
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}
	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void onDateSelect(SelectEvent<Date> event) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.paciente.getPessoa().setDataNascimento(LocalDate.parse(format.format(event.getObject())));
        
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
	public Paciente getPaciente() {
		if(this.paciente==null) {
			this.paciente = (Paciente) Session.getInstance().get("pacienteLogado");
			System.out.println(this.paciente.getPessoa().getNome());
			if (this.paciente.getPessoa().getEndereco() == null) {
				this.paciente.getPessoa().setEndereco(new Endereco());
			}
		}
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
