package br.unitins.topicosii.application;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Util {

	private static void addMessage(String msg, Severity severity) {
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage(severity, msg, null));
	}
	
	public static void addErrorMessage(String msg) {
		addMessage(msg,FacesMessage.SEVERITY_ERROR);
	}
	
	public static void addWarnMessage(String msg) {
		addMessage(msg,FacesMessage.SEVERITY_WARN);
	}
	
	public static void addInfoMessage(String msg) {
		addMessage(msg,FacesMessage.SEVERITY_INFO);
	}
	
	public static void redirect(String page) {
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + page);
		} catch (IOException e) {
			System.out.println("Não foi possível realizar o redirecionamento.");
			e.printStackTrace();
		}
	}
}
