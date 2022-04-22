package br.unitins.topicosii.respository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Consultorio;
import br.unitins.topicosii.models.Estado;

public class EstadoRepository extends Repository<Estado>{
	public List<Estado> findByNome(String nome) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("e ");
			jpsql.append("FROM ");
			jpsql.append("Estado e ");
			jpsql.append("WHERE ");
			jpsql.append("e.nome like :nome");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");
			
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome");
		}
	}
	
	
	public List<Estado> findAll() throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("e ");
			jpsql.append("FROM ");
			jpsql.append("Estado e");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os consultorios");
		}
	}
}