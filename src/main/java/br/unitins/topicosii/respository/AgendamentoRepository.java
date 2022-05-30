package br.unitins.topicosii.respository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.Agendamento;

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
	public List<Agendamento> findAllForPaciente(int id) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("a ");
			jpsql.append("FROM ");
			jpsql.append("Agendamento a ");
			jpsql.append("WHERE ");
			jpsql.append("a.paciente.id = :id ");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("id", id);
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os pacientes");
		}
	}
	public List<Agendamento> findAllForPsicologo(int id) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("a ");
			jpsql.append("FROM ");
			jpsql.append("Agendamento a ");
			jpsql.append("WHERE ");
			jpsql.append("a.psicologo.id = :id ");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("id", id);
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os pacientes");
		}
	}
	public boolean checkInterval(LocalDateTime inicio,LocalDateTime fim) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("a ");
			jpsql.append("FROM ");
			jpsql.append("Agendamento a ");
			jpsql.append("WHERE ");
			jpsql.append("a.horaInicio BETWEEN :inicioa AND :fima ");
			jpsql.append("OR ");
			jpsql.append("a.horaFim BETWEEN :iniciob AND :fimb ");
			/*SELECT * from Agendamento a WHERE a.horaInicio BETWEEN '2022-05-30 14:17:00' AND '2022-05-30 15:19:00' OR 
a.horaFim BETWEEN '2022-05-30 14:17:00' AND '2022-05-30 15:19:00'*/
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("inicioa", inicio);
			query.setParameter("fima", fim);
			query.setParameter("iniciob", inicio);
			query.setParameter("fimb", fim);
			if(query.getResultList().size()>0) {
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os pacientes");
		}
	}
	
	
}
