package br.com.locadoraDeVeiculo.CriacaoDeTabelas;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.locadoraDeVeiculo.Conexao.FabricaDeConexao;

public class TabelaLocacao {
    private Connection conexao;

    public TabelaLocacao() {
        this.conexao = new FabricaDeConexao().getConexao();
    }

    //Criar tabela locacao
    public void criar() throws SQLException {
        String sql = "CREATE TABLE Locacao (" +
                "id SERIAL PRIMARY KEY," +
                "dt_inicio DATE NOT NULL," +
                "dt_fim DATE NOT NULL," +
                "valor DOUBLE PRECISION NOT NULL," +
                "id_veiculo INTEGER NOT NULL," +
                "id_cliente INTEGER NOT NULL," +
                "FOREIGN KEY (id_veiculo) REFERENCES Veiculo(id)," +
                "FOREIGN KEY (id_cliente) REFERENCES Cliente(id)" +
                ")";
        Statement stmt = conexao.createStatement();
        stmt.execute(sql);
        System.out.println("Tabela Locacao criada com sucesso!");
        conexao.close();
    }

}