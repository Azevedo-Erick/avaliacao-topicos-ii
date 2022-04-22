package br.unitins.topicosii.respository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Consultorio;
import br.unitins.topicosii.models.Paciente;

public class ConsultorioRepository extends Repository<Consultorio>{
	public List<Consultorio> findByNome(String nome) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Consultorio c ");
			jpsql.append("WHERE ");
			jpsql.append("c.nome like :nome");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");
			
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome");
		}
	}
	
	
	public List<Consultorio> findAll() throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Consultorio c");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os consultorios");
		}
	}
}
