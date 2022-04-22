package br.unitins.topicosii.respository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Pessoa;
import br.unitins.topicosii.models.Psicologo;

public class PsicologoRepository extends Repository<Psicologo>{
	public List<Psicologo> findAll() throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Psicologo p");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os pacientes");
		}
	}
	
	public List<Psicologo> findByNome(String nome) throws RepositoryException {
		try {
			PessoaRepository repo = new PessoaRepository();
			Pessoa pessoa = repo.findByNome(nome).get(0);
			
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Psicologo p");
			jpsql.append("WHERE ");
			jpsql.append("p.pessoa_id = :id");
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("id",pessoa.getId());
			
			return  query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome");
		}
	}
}
