package br.unitins.topicosii.respository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Paciente;

public class PacienteRepository extends Repository<Paciente>{
	public List<Paciente> findByNome(String nome) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("u ");
			jpsql.append("FROM ");
			jpsql.append("Paciente u ");
			jpsql.append("WHERE ");
			jpsql.append("u.nome like :nome");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");
			
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome");
		}
	}
	
	public List<Paciente> findAll() throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("u ");
			jpsql.append("FROM ");
			jpsql.append("Paciente u");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os pacientes");
		}
	}
}
