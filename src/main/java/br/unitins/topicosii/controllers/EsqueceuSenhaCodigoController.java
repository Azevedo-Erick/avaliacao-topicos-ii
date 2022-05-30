package br.unitins.topicosii.controllers;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;

import br.unitins.topicosii.application.Email;
import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.application.Util;
import br.unitins.topicosii.application.VersionException;
import br.unitins.topicosii.models.EsqueceuSenha;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.respository.EsqueceuSenhaRepository;
import br.unitins.topicosii.respository.PessoaRepository;
import net.bytebuddy.asm.Advice.This;

@Named
@ViewScoped
public class EsqueceuSenhaCodigoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String emailParaRecuperarSenha;
	private String codigo;
	private String novaSenha;
	private String confirmacaoNovaSenha;

	private Pessoa buscarUsuario() {
		
		PessoaRepository repo = new PessoaRepository();
		Pessoa pessoa = null;
		try {
			pessoa = repo.findByEmail(this.getEmailParaRecuperarSenha());
			return pessoa;
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao encontrar o email.");
			return pessoa;
		} catch (NoResultException e) {
			Util.addErrorMessage("Email não encontrado em nosso banco de dados.");
			return pessoa;
		}
	}

	private String gerarCodigo() {
		Random r = new Random();
		String codigo = new DecimalFormat("T-000000").format(r.nextInt(1000000));
		return codigo;
	}

	private void esqueceuSenha(Pessoa pessoa, String codigo) {
		EsqueceuSenha esqueceu = new EsqueceuSenha();
		esqueceu.setPessoa(pessoa);
		esqueceu.setCodigo(codigo);
		esqueceu.setDataHoraLimite(LocalDateTime.now().plusDays(1));
		esqueceu.setUtilizado(false);
		EsqueceuSenhaRepository repoEsqueceu = new EsqueceuSenhaRepository();
		try {
			repoEsqueceu.save(esqueceu);
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao gerar o código, tente novamente.");
			e.printStackTrace();
		} catch (VersionException e) {
			Util.addErrorMessage("Problema ao gerar o código, tente novamente.");
			e.printStackTrace();
		}
	}
	
	public void enviarEmail() {
		Pessoa pessoa = this.buscarUsuario();
		if (pessoa != null) {

			String codigo = this.gerarCodigo();
			Email email = new Email(pessoa.getEmail(), "Esqueceu a senha",
					this.getHtmlEmail(codigo) + codigo);
			this.esqueceuSenha(pessoa, codigo);
			if (!email.enviar()) {
				Util.addErrorMessage("Problema ao enviar o email.");
			} else
				Util.addInfoMessage("Código enviado para seu email.");
		}
	}

	private boolean senhasIguais() {
		if (!this.getNovaSenha().equals(this.getConfirmacaoNovaSenha())) {
			Util.addErrorMessage("A senhas não coincidem");
			return false;
		}
		return true;
	}

	private EsqueceuSenha buscaCodigo() {
		System.out.println("O codigo:" +this.codigo);
		EsqueceuSenhaRepository repo = new EsqueceuSenhaRepository();
		EsqueceuSenha esqueceuSenha = null;
		try {
			esqueceuSenha = repo.findByCodigo(codigo);

		} catch (Exception e) {
			Util.addErrorMessage("Erro ao buscar a pessoa");
		}
		return esqueceuSenha;
	}

	public void alterarSenha() {
		EsqueceuSenha esqueceuSenha = null;
		PessoaRepository repo = new PessoaRepository();
		EsqueceuSenhaRepository repository = new EsqueceuSenhaRepository();
		if (this.senhasIguais()) {
			esqueceuSenha = buscaCodigo();
			Pessoa pessoa = esqueceuSenha.getPessoa();
			
			pessoa.setSenha(Util.hash(pessoa.getId().toString(), novaSenha));
			try {
				repo.save(pessoa);
				esqueceuSenha.setUtilizado(true);
				repository.save(esqueceuSenha);
				Util.addInfoMessage("Senha atualizada com sucesso");
			} catch (RepositoryException e) {
				e.printStackTrace();
				Util.addErrorMessage(e.getMessage());
			} catch (VersionException e) {
				e.printStackTrace();
				Util.addErrorMessage("Erro ao alterar a senha");
			}
		}
	}

	public String getEmailParaRecuperarSenha() {
		return emailParaRecuperarSenha;
	}

	public void setEmailParaRecuperarSenha(String emailParaRecuperarSenha) {
		this.emailParaRecuperarSenha = emailParaRecuperarSenha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoNovaSenha() {
		return confirmacaoNovaSenha;
	}

	public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {
		this.confirmacaoNovaSenha = confirmacaoNovaSenha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String getHtmlEmail(String codigo) {
		StringBuffer email = new StringBuffer();
		email.append("<table border='0' cellspacing='0' cellpading='0' style='padding-bottom: 20px; max-width: 516px; min-width: 220px'>");
			email.append("<tbody>");
				email.append("<tr>");
					email.append("<td>");
						email.append("<div style='border-style: solid; border-width: thin; border-color: #dadce0; border-radius: 8px; padding: 40px 20px' align='center' class='body'>");
							email.append("<div style='font-family: Google Sans, Roboto, RobotoDraft, Helvetica, Arial, sans-serif; border-bottom: thin solid #dadce0; color: rgba(0, 0, 0, 0.87); line-height: 32px; padding-bottom: 24px; text-align: center; word-break: break-word'>");
								email.append("<div style='font-size: 24px'>Seu código é "); email.append(codigo + "</div>");
							email.append("</div>");
						email.append("</div>");
					email.append("</td>");
				email.append("</tr>");
			email.append("</tbody>");
		email.append("</table>");
		
					
		return email.toString();
	}
}
