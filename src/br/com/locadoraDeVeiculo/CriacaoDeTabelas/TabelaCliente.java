package br.com.locadoraDeVeiculo.CriacaoDeTabelas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.locadoraDeVeiculo.Conexao.FabricaDeConexao;

public class TabelaCliente {
	public void criar(Connection conexao) throws SQLException {
		// SQL para criar a tabela Cliente
        String sql = "CREATE TABLE Cliente (" +
                     "id SERIAL PRIMARY KEY," +
                     "nomeCompleto VARCHAR(100) NOT NULL," +
                     "cpf VARCHAR(14) NOT NULL," +
                     "telefone VARCHAR(14)," +
                     "endereco VARCHAR(255)" +
                     ")";
        Statement stmt = conexao.createStatement();
        stmt.execute(sql);
        System.out.println("Tabela Cliente criada com sucesso!");
        conexao.close();
	}
}
