package br.com.locadoraDeVeiculo.Entidades;

import java.util.Date;

public class Locacao {
	private Long id;
	private Date dt_Inicio;
	private Date dt_Fim;
	private Double valor;
	private Long id_Veiculo;
	private Long id_Cliente;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDt_Inicio() {
		return dt_Inicio;
	}
	public void setDt_Inicio(Date dt_Inicio) {
		this.dt_Inicio = dt_Inicio;
	}
	public Date getDt_Fim() {
		return dt_Fim;
	}
	public void setDt_Fim(Date dt_Fim) {
		this.dt_Fim = dt_Fim;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Long getId_Veiculo() {
		return id_Veiculo;
	}
	public void setId_Veiculo(Long id_Veiculo) {
		this.id_Veiculo = id_Veiculo;
	}
	public Long getId_Cliente() {
		return id_Cliente;
	}
	public void setId_Cliente(Long id_Cliente) {
		this.id_Cliente = id_Cliente;
	}
}
