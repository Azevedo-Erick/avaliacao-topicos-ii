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
			PessoaRepository repo = new PessoaRepository();
			Pessoa pessoa = repo.findByNome(nome).get(0);
			
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Paciente p, ");
			jpsql.append("Pessoa pes ");
			jpsql.append("WHERE ");
			jpsql.append("p.pessoa_id = 1 ");
			jpsql.append("AND ");
			jpsql.append("p.pessoa_id = pes.id");
			Query query = getEntityManager().createQuery(jpsql.toString());
			//query.setParameter("id", pessoa.getId());
			
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome");
		}
	}
	
	public Paciente findPessoaPaciente(Paciente paciente) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("u ");
			jpsql.append("FROM ");
			jpsql.append("Paciente u ");
			jpsql.append("WHERE ");
			jpsql.append("u.pessoa.id = :id");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("id", paciente.getPessoa().getId());
			
			return (Paciente)query.getSingleResult();
		}catch (NoResultException e){
			e.printStackTrace();
			throw new RepositoryException("Email e senha não encontrados");
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
