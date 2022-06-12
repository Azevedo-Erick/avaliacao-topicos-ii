package br.unitins.topicosii.report;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import br.unitins.topicosii.application.JDBCUtil;

@WebServlet("/root/psicologo/relatorio-pacientes-por-cidade.xhtml")
public class PacientesPorEstado extends ReportServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getArquivoJasper() {
		return "pacientes.jasper";
	}

	@Override
	public HashMap<String, Class<?>> getParametros() {
		HashMap<String, Class<?>> param = new HashMap<String, Class<?>>();
		param.put("ESTADO", String.class);
		return param;
	}

	@Override
	public Connection getConnection() {
		return JDBCUtil.getConnection();
	}
}
