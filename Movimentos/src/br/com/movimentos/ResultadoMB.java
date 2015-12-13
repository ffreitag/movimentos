package br.com.movimentos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.movimentos.dao.MovimentosDAO;
import br.com.movimentos.model.Resultado;

/**
 * Classe para objetos do tipo Funcionários, onde serão contidos, valores e métodos para o mesmo.
 * @author Fernando Freitag
 * @version 1.0
 * @since 13/12/2015
 */
@ManagedBean
@RequestScoped
public class ResultadoMB {

	private Resultado resultado;

	public ResultadoMB() {
	}

	public Resultado getResultado() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MovimentosDAO dao = (MovimentosDAO) context.getBean("movimentosDao");
		
		Resultado resultado = new Resultado();
		resultado.setMaiorCrescimento(dao.getMaiorCrescimentoMaiorQueda().get(0));
		resultado.setMaiorQueda(dao.getMaiorCrescimentoMaiorQueda().get(1));
		resultado.setMaisVendeu(dao.getVendasPeriodo());
		resultado.setMesMaiorVenda(dao.getMesMaiorFaturamento());

		this.resultado = resultado;
		
		context.close();
		
		return this.resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	
}
