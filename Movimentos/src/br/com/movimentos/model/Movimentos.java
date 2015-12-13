package br.com.movimentos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Classe para objetos do tipo Movimentos
 * @author Fernando Freitag
 * @version 1.0
 * @since 13/12/2015
 */
@Entity
@Table(name = "tbmovimentos")
public class Movimentos {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "filial", length = 50, nullable = false)
	private String filial;

	@Column(name = "periodo", length = 20, nullable = false)
	private String periodo;

	@Column(name = "total", nullable = false)
	private Double total;

	public Movimentos() {
	}

	public Movimentos(String filial, String periodo, Double total) {
		this.filial = filial;
		this.periodo = periodo;
		this.total = total;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Movimentos [id= " + id + ", filial=" + filial + ", periodo=" + periodo
				+ ", total=" + total + "]";
	}
}
