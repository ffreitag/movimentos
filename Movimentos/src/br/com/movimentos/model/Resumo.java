package br.com.movimentos.model;

/**
 * Classe para objetos do tipo Resumo
 * @author Fernando Freitag
 * @version 1.0
 * @since 13/12/2015
 */
public class Resumo {
	private String filial;
	private Double vlrJaneiro;
	private Double vlrFevereiro;
	private Double vlrMarco;
	private Double percJaneiro;
	private Double percFevereiro;
	private Double percMarco;

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public Double getVlrJaneiro() {
		return vlrJaneiro;
	}

	public void setVlrJaneiro(Double vlrJaneiro) {
		this.vlrJaneiro = vlrJaneiro;
	}

	public Double getVlrFevereiro() {
		return vlrFevereiro;
	}

	public void setVlrFevereiro(Double vlrFevereiro) {
		this.vlrFevereiro = vlrFevereiro;
	}

	public Double getVlrMarco() {
		return vlrMarco;
	}

	public void setVlrMarco(Double vlrMarco) {
		this.vlrMarco = vlrMarco;
	}

	public Double getPercJaneiro() {
		return percJaneiro;
	}

	public void setPercJaneiro(Double percJaneiro) {
		this.percJaneiro = percJaneiro;
	}

	public Double getPercFevereiro() {
		return percFevereiro;
	}

	public void setPercFevereiro(Double percFevereiro) {
		this.percFevereiro = percFevereiro;
	}

	public Double getPercMarco() {
		return percMarco;
	}

	public void setPercMarco(Double percMarco) {
		this.percMarco = percMarco;
	}

	@Override
	public String toString() {
		return "Resumo = [ filial= " + filial + ", percJaneiro= " + percJaneiro
				+ ", vlrJaneiro= " + vlrJaneiro + ", percFevereiro= "
				+ percFevereiro + ", vlrFevereiro=  " + vlrFevereiro
				+ ", percMarco =  " + percMarco + ", vlrMarco = " + vlrMarco
				+ " ]";
	}
}
