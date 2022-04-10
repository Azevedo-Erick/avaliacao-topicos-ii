package br.unitins.topicosii.respository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Pessoa;

public class PessoaRepository extends Repository<Pessoa>{
	public List<Pessoa> findAll() throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Pessoa p");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os pacientes");
		}
	}
	
	public Pessoa findByEmailESenha(Paciente paciente) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Pessoa p ");
			jpsql.append("WHERE ");
			jpsql.append("p.email = :email ");
			jpsql.append("AND ");
			jpsql.append("senha = :senha");
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("email", paciente.getPessoa().getEmail());
			query.setParameter("senha", paciente.getPessoa().getSenha());
			
			return (Pessoa) query.getSingleResult();
		}catch (NoResultException e){
			e.printStackTrace();
			throw new RepositoryException("Email e senha não encontrados");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar email e senha");
		}
	} 
}
