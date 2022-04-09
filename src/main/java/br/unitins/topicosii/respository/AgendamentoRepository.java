package br.unitins.topicosii.respository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Agendamento;
import br.unitins.topicosii.models.Paciente;

public class AgendamentoRepository extends Repository<Agendamento>{
	public List<Agendamento> findAll() throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("a ");
			jpsql.append("FROM ");
			jpsql.append("Agendamento a");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os pacientes");
		}
	}
	
	
}
