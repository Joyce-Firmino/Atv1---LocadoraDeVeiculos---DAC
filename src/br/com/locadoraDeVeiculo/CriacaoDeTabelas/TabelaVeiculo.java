package br.com.locadoraDeVeiculo.CriacaoDeTabelas;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.locadoraDeVeiculo.Conexao.FabricaDeConexao;

public class TabelaVeiculo {
    private Connection conexao;

    public TabelaVeiculo() {
        this.conexao = new FabricaDeConexao().getConexao();
    }

    //Criar tabela veiculo
    public void criar() throws SQLException {
        // SQL para criar a tabela Veiculo
        String sql = "CREATE TABLE Veiculo (" +
                "id SERIAL PRIMARY KEY," +
                "chassi VARCHAR(100) NOT NULL," +
                "modelo VARCHAR(100) NOT NULL," +
                "ano INTEGER NOT NULL," +
                "marca VARCHAR(100) NOT NULL," +
                "placa VARCHAR(20) NOT NULL," +
                "cor VARCHAR(50) NOT NULL" +
                ")";
        Statement stmt = conexao.createStatement();
        stmt.execute(sql);
        System.out.println("Tabela Veiculo criada com sucesso!");
        conexao.close();
    }
}