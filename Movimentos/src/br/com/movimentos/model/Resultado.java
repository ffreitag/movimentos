package br.com.movimentos.model;

/**
 * Classe para objetos do tipo Resultado
 * @author Fernando Freitag
 * @version 1.0
 * @since 13/12/2015
 */
public class Resultado {
	private String maiorQueda;
	private String maiorCrescimento;
	private String mesMaiorVenda;
	private String maisVendeu;

	public String getMaiorQueda() {
		return maiorQueda;
	}

	public void setMaiorQueda(String maiorQueda) {
		this.maiorQueda = maiorQueda;
	}

	public String getMaiorCrescimento() {
		return maiorCrescimento;
	}

	public void setMaiorCrescimento(String maiorCrescimento) {
		this.maiorCrescimento = maiorCrescimento;
	}

	public String getMesMaiorVenda() {
		return mesMaiorVenda;
	}

	public void setMesMaiorVenda(String mesMaiorVenda) {
		this.mesMaiorVenda = mesMaiorVenda;
	}

	public String getMaisVendeu() {
		return maisVendeu;
	}

	public void setMaisVendeu(String maisVendeu) {
		this.maisVendeu = maisVendeu;
	}

}
