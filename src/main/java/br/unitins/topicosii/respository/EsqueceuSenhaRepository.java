package br.unitins.topicosii.respository;

import javax.persistence.Query;

import br.unitins.topicosii.application.RepositoryException;
import br.unitins.topicosii.models.EsqueceuSenha;
import br.unitins.topicosii.models.Pessoa;

public class EsqueceuSenhaRepository extends Repository<EsqueceuSenha> {
	public EsqueceuSenha findByCodigo(String codigo) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("C ");
			jpsql.append("FROM ");
			jpsql.append("EsqueceuSenha c ");
			jpsql.append("WHERE ");
			jpsql.append("c.codigo = :codigo");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("codigo", codigo );

			return (EsqueceuSenha) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome");
		}

	}
}
