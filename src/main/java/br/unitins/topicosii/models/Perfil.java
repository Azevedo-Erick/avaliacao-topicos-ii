package br.unitins.topicosii.models;

import java.util.ArrayList;
import java.util.List;

public enum Perfil {
	ADMINISTRADOR(1, "Administrador"), 
	FUNCIONARIO(2, "Funcionário"),
	PACIENTE(3, "Paciente");
	
	private int id;
	private String label;
	private List<String> paginasComPermissao;

	Perfil(int id, String label) {
		this.id = id;
		this.label = label;
		paginasComPermissao = new ArrayList<String>();
		
		String baseUrl = "/Topicos2-A2/root/";
		
		paginasComPermissao.add(baseUrl+"listings/listing-cidade.xhtml");
		paginasComPermissao.add(baseUrl+"listings/listing-estado.xhtml");
		
		
		if (id == 1 || id == 2) {
			paginasComPermissao.add(baseUrl+"listings/listing-paciente.xhtml");
			paginasComPermissao.add(baseUrl+"listings/listing-psicologo.xhtml");
			paginasComPermissao.add(baseUrl+"listings/listing-pessoa.xhtml");
			baseUrl+="psicologo/";
			paginasComPermissao.add(baseUrl+"agenda-psicologo.xhtml");
			paginasComPermissao.add(baseUrl+"editar-informacoes-psicologo.xhtml");
			paginasComPermissao.add(baseUrl+"relatorio-pacientes-por-cidade.xhtml");
			paginasComPermissao.add(baseUrl+"relatorio-pedencias-pagamentos.xhtml");
		}
		if (id == 1) {
			paginasComPermissao.add(baseUrl+"admin/gerenciar-cidade.xhtml");
			paginasComPermissao.add(baseUrl+"admin/gerenciar-consultorio.xhtml");
			paginasComPermissao.add(baseUrl+"admin/gerenciar-estado.xhtml");
			paginasComPermissao.add(baseUrl+"admin/gerenciar-paciente.xhtml");
			paginasComPermissao.add(baseUrl+"admin/gerenciar-pessoa.xhtml");
			paginasComPermissao.add(baseUrl+"admin/gerenciar-psicologo.xhtml");
		} 
		if(id == 3) {
			baseUrl+="paciente/";
			paginasComPermissao.add(baseUrl+"agenda-paciente.xhtml");
			paginasComPermissao.add(baseUrl+"editar-informacoes-paciente.xhtml");
			paginasComPermissao.add(baseUrl+"historico-de-pagamentos.xhtml");
			paginasComPermissao.add(baseUrl+"pagamentos-pendentes.xhtml");
		}


	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public List<String> getPaginasComPermissao() {
		return paginasComPermissao;
	}

	public static Perfil valueOf(int id) {
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getId() == id)
				return perfil;
		}
		return null;
	}
}
