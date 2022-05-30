package br.unitins.topicosii.respository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Consultorio;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Pessoa;

public class PessoaRepository extends Repository<Pessoa>{
	public List<Pessoa> findByNome(String nome) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Pessoa p ");
			jpsql.append("WHERE ");
			jpsql.append("p.nome like :nome");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");
			
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome");
		}
	}
	
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
	public Pessoa findByEmail(String email) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Pessoa p ");
			jpsql.append("WHERE ");
			jpsql.append("p.email = :email");
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("email", email);
			
			return (Pessoa) query.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByEmail");
		}
	}
	public Pessoa findByEmailESenha(Pessoa pessoa) throws RepositoryException {
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
			query.setParameter("email", pessoa.getEmail());
			query.setParameter("senha", pessoa.getSenha());
			
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
