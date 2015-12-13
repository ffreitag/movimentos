package br.com.movimentos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import br.com.movimentos.model.Movimentos;
import br.com.movimentos.model.Resumo;

/**
 * Classe responsavel pela persistencia dos dados da aplicacao
 * @author Fernando Freitag
 * @version 1.0
 * @since 13/12/2015
 */
@Transactional
public class MovimentosDAO {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Movimentos movimentos) {
		em.persist(movimentos);
	}

	public List<Movimentos> getAll() {
		return em.createQuery("SELECT m FROM Movimentos m", Movimentos.class)
				.getResultList();
	}

	public String getVendasPeriodo() {
		String hql = "select filial, sum(total) from Movimentos m group by filial order by sum(total) desc";

		Query query = em.createQuery(hql);
		Object[] results = (Object[]) query.getResultList().get(0);
		return results[0].toString();
	}

	public List<Resumo> getResumo() {
		List<Resumo> resumo = new ArrayList<Resumo>();

		// Carrega os movimentos por mês
		List<Movimentos> mesJan = getMovimentosPorMes("Janeiro");
		List<Movimentos> mesFev = getMovimentosPorMes("Fevereiro");
		List<Movimentos> mesMar = getMovimentosPorMes("Março");
		int x = 0;
		// Efetua os calculos para encontrar o maior crescimento e maior queda
		for (Movimentos movJan : mesJan) {
			Movimentos movFev = (Movimentos) mesFev.get(x);
			Movimentos movMar = (Movimentos) mesMar.get(x);

			Resumo calcTemp = new Resumo();

			calcTemp.setFilial(movJan.getFilial());
			calcTemp.setVlrJaneiro(movJan.getTotal());
			calcTemp.setVlrFevereiro(movFev.getTotal());
			calcTemp.setVlrMarco(movMar.getTotal());
			calcTemp.setPercJaneiro(100.0);	
			calcTemp.setPercFevereiro(((movFev.getTotal() / movJan.getTotal()) - 1) * 100);
			calcTemp.setPercMarco(((movMar.getTotal() / movFev.getTotal()) - 1) * 100);
			resumo.add(calcTemp);
			x++;
		}
		return resumo;
	}

	public List<String> getMaiorCrescimentoMaiorQueda() {

		List<Resumo> lstResumo = getResumo();
		List<String> resultado = new ArrayList<String>();
		Double maiorCrescimento = 0.0;
		Double maiorQueda = 0.0;
		String filialMaiorCrescimento = null;
		String filialMaiorQueda = null;

		// Efetua os calculos para encontrar o maior crescimento e maior queda
		for (Resumo resumo : lstResumo) {
			
			// Verifica se é o maior crescimento nas vendas
			if ((resumo.getPercFevereiro()+resumo.getPercMarco()) >= maiorCrescimento) {
				maiorCrescimento = resumo.getPercFevereiro()+resumo.getPercMarco();
				filialMaiorCrescimento = resumo.getFilial();
			}

			// Verifica se é a maior queda nas vendas
			if ((resumo.getPercFevereiro()+resumo.getPercMarco()) <= maiorQueda) {
				maiorQueda = resumo.getPercFevereiro()+resumo.getPercMarco();
				filialMaiorQueda = resumo.getFilial();
			}
		}

		resultado.add(filialMaiorCrescimento);
		resultado.add(filialMaiorQueda);

		return resultado;

	}

	public List<Movimentos> getMovimentosPorMes(String mes) {
		String hql = "select m from Movimentos m where periodo = :pPeriodo order by filial";

		Query query = em.createQuery(hql);
		query.setParameter("pPeriodo", mes);
		List<Movimentos> movimentos = (List<Movimentos>) query.getResultList();
		return movimentos;

	}
	
	
	public String getMesMaiorFaturamento() {
		String hql = "select periodo, sum(total) from Movimentos m group by periodo order by sum(total) desc";

		Query query = em.createQuery(hql);
		Object[] results = (Object[]) query.getResultList().get(0);
		return results[0].toString();

	}
}
