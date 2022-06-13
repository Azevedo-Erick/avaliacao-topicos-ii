package br.unitins.topicosii.respository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Paciente;
import br.unitins.topicosii.models.Pessoa;

public class PacienteRepository extends Repository<Paciente>{
	public List<Paciente> findByNome(String nome) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Paciente p ");
			jpsql.append("WHERE ");
			jpsql.append("p.pessoa.nome like :nome");
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", '%' + nome + '%');
			
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome");
		}
	}
	
	
	public Paciente findByEmailESenha(Paciente paciente) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("u ");
			jpsql.append("FROM ");
			jpsql.append("Paciente u ");
			jpsql.append("WHERE ");
			jpsql.append("u.pessoa.email = :email ");
			jpsql.append("AND ");
			jpsql.append("u.pessoa.senha = :senha");
			Query query = getEntityManager().createQuery(jpsql.toString());
			
			query.setParameter("email", paciente.getPessoa().getEmail());
			query.setParameter("senha", paciente.getPessoa().getSenha());
			return (Paciente)query.getSingleResult();
		}catch (NoResultException e){
			System.out.println("Erro ao fazer o login");
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar email e senha");
		}
	}
	public Paciente findByEmail(Paciente paciente) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("u ");
			jpsql.append("FROM ");
			jpsql.append("Paciente u ");
			jpsql.append("WHERE ");
			jpsql.append("u.pessoa.email = :email ");
			Query query = getEntityManager().createQuery(jpsql.toString());
			
			query.setParameter("email", paciente.getPessoa().getEmail());
			return (Paciente)query.getSingleResult();
		}catch (NoResultException e){
			System.out.println("Erro ao fazer o login");
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar email e senha");
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
