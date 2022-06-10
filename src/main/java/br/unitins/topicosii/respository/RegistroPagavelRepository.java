package br.unitins.topicosii.respository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.RegistroPagavel;

public class RegistroPagavelRepository extends Repository<RegistroPagavel>{
	public List<RegistroPagavel> findAll() throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("r ");
			jpsql.append("FROM ");
			jpsql.append("RegistroPagavel r");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os pacientes");
		}
	}
	public List<RegistroPagavel> findAllForPaciente(int id) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("r ");
			jpsql.append("FROM ");
			jpsql.append("RegistroPagavel r ");
			jpsql.append("WHERE ");
			jpsql.append("r.agendamento.paciente.id = :id ");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("id", id);
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os pacientes");
		}
	}
}
