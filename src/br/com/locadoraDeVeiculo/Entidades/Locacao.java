package br.com.locadoraDeVeiculo.Entidades;

import java.util.Date;

public class Locacao {
	private int id;
	private Date dt_Inicio;
	private Date dt_Fim;
	private Double valor;
	private int id_Veiculo;
	private int id_Cliente;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getId_Veiculo() {
		return id_Veiculo;
	}
	public void setId_Veiculo(int id_Veiculo) {
		this.id_Veiculo = id_Veiculo;
	}
	public int getId_Cliente() {
		return id_Cliente;
	}
	public void setId_Cliente(int id_Cliente) {
		this.id_Cliente = id_Cliente;
	}
}
