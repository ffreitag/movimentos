package br.com.movimentos;

import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.movimentos.dao.MovimentosDAO;
import br.com.movimentos.model.Movimentos;
import br.com.movimentos.model.Resumo;

/**
 * Classe para objetos do tipo Funcionários, onde serão contidos, valores e métodos para o mesmo.
 * @author Fernando Freitag
 * @version 1.0
 * @since 13/12/2015
 */
@ManagedBean
public class GraficosMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7681117328950692229L;
	
	private BarChartModel animatedModel2;

	@PostConstruct
	public void init() {
		createAnimatedModels();
	}

	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}

	private void createAnimatedModels() {
		animatedModel2 = initBarModel();
		animatedModel2.setTitle("Percentual de Crescimento / Queda");
		animatedModel2.setAnimate(true);
		animatedModel2.setLegendPosition("ne");
		Axis yAxis = animatedModel2.getAxis(AxisType.Y);
		yAxis.setMin(-200);
		yAxis.setMax(200);
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MovimentosDAO dao = (MovimentosDAO) context.getBean("movimentosDao");

		List<Resumo> lstResumo = dao.getResumo();
		
		for (Resumo resumo : lstResumo) {
			ChartSeries filial = new ChartSeries();
			filial.setLabel(resumo.getFilial());
			filial.set("Fevereiro", resumo.getPercFevereiro());
			filial.set("Março", resumo.getPercMarco());
			model.addSeries(filial);
		}
		
		context.close();
		
		return model;
	}

}