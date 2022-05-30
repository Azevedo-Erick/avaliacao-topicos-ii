package br.unitins.topicosii.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.topicosii.application.Session;
import br.unitins.topicosii.application.Util;

@Named
@ViewScoped
public class TemplateController implements Serializable{

	private static final long serialVersionUID = 1L;

	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("/pages/login.xhtml");
	}
	
	public void redirect(String page) {
		Util.redirect(page);
	}	
}
