package br.unitins.topicosii.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Psicologo;

@WebFilter(filterName = "SecurityFilter", urlPatterns = { "/root/*" })
public class SecurityFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String endereco = servletRequest.getRequestURI();

		// retorna uma sessao corrente (false - nao cria uma nova estrutura de sessao)
		HttpSession session = servletRequest.getSession(false);
		Psicologo psicologoLogado = null;
		Paciente pacienteLogado = null;
		if (session != null) {
			psicologoLogado = (Psicologo) session.getAttribute("psicologoLogado");
			pacienteLogado = (Paciente) session.getAttribute("pacienteLogado");
		}
		;
		// Se não houver sessão redireciona para a pagina de login
		if (psicologoLogado == null && pacienteLogado == null) {
			((HttpServletResponse) response).sendRedirect("/Topicos2-A2/login.xhtml");

		}
		boolean hasPermissionToAcess = false;
		if (psicologoLogado != null) {
			if (psicologoLogado.getPerfil().getPaginasComPermissao().contains(endereco)) {
				hasPermissionToAcess = true;
			}

		}
		if (pacienteLogado != null) {
			if (pacienteLogado.getPerfil().getPaginasComPermissao().contains(endereco)) {
				hasPermissionToAcess = true;
			
			}
		}
		if (hasPermissionToAcess) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("/Topicos2-A2/sem-acesso.xhtml");
		}


	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		System.out.println("Filtro SecurityFilter iniciailzado.");
	}
}
