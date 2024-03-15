package br.com.locadoraDeVeiculo.Main;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.locadoraDeVeiculo.Conexao.FabricaDeConexao;
import br.com.locadoraDeVeiculo.CriacaoDeTabelas.TabelaCliente;

public class Executando {
	public static void main(String[] args) throws SQLException{
		FabricaDeConexao conex = new FabricaDeConexao();
		Connection conexao = conex.getConexao();
		TabelaCliente clientTable= new TabelaCliente();
		clientTable.criar(conexao);
		
	}
}
