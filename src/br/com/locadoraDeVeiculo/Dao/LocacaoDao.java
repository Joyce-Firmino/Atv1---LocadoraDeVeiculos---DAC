package br.com.locadoraDeVeiculo.Dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.locadoraDeVeiculo.Conexao.FabricaDeConexao;
import br.com.locadoraDeVeiculo.CriacaoDeTabelas.TabelaLocacao;
import br.com.locadoraDeVeiculo.Entidades.Locacao;

public class LocacaoDao {
    private Connection conexaoLocacao;

    public LocacaoDao() {
        this.conexaoLocacao = new FabricaDeConexao().getConexao();
    }


    // Método para verificar se a tabela Locação já existe no banco
    private boolean tabelaLocacaoExiste() throws SQLException {
        DatabaseMetaData metaData = conexaoLocacao.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "locacao", null);
        return tables.next();
    }


    // Método para inserir uma locação no banco
    public void inserirLocacao(Locacao locacao) throws SQLException {
        if (!tabelaLocacaoExiste()) {
            TabelaLocacao tabelaLocacao = new TabelaLocacao();
            tabelaLocacao.criar();
        }

        String sql = "INSERT INTO locacao (dt_inicio, dt_fim, valor, id_veiculo, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexaoLocacao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(locacao.getDt_Inicio().getTime()));
            stmt.setDate(2, new java.sql.Date(locacao.getDt_Fim().getTime()));
            stmt.setDouble(3, locacao.getValor());
            stmt.setInt(4, locacao.getId_Veiculo());
            stmt.setInt(5, locacao.getId_Cliente());
            stmt.execute();
            stmt.close();
            System.out.println("Locação criada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Método para listar todos as locações do banco
    public List<Locacao> listarLocacoes() {
        try {
            List<Locacao> locacoes = new ArrayList<>();
            String sql = "SELECT * FROM locacao ORDER BY id";

            PreparedStatement stmt = conexaoLocacao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Locacao locacao = new Locacao();
                locacao.setId(rs.getInt("id"));
                locacao.setDt_Inicio(rs.getDate("dt_inicio"));
                locacao.setDt_Fim(rs.getDate("dt_fim"));
                locacao.setValor(rs.getDouble("valor"));
                locacao.setId_Veiculo(rs.getInt("id_veiculo"));
                locacao.setId_Cliente(rs.getInt("id_cliente"));

                locacoes.add(locacao);
            }

            rs.close();
            stmt.close();
            return locacoes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Método para atualizar uma locação
    public void atualizarLocacao(Integer id, Locacao novosDadosLocacao) {
        String sql = "UPDATE locacao SET dt_inicio=?, dt_fim=?, valor=?, id_veiculo=?, id_cliente=? WHERE id=?";
        try {
            PreparedStatement stmt = conexaoLocacao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(novosDadosLocacao.getDt_Inicio().getTime()));
            stmt.setDate(2, new java.sql.Date(novosDadosLocacao.getDt_Fim().getTime()));
            stmt.setDouble(3, novosDadosLocacao.getValor());
            stmt.setInt(4, novosDadosLocacao.getId_Veiculo());
            stmt.setInt(5, novosDadosLocacao.getId_Cliente());
            stmt.setInt(6, id);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Locação atualizada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Método para deletar uma locação
    public void deletarLocacao(Integer id) {
        String sql = "DELETE FROM locacao WHERE id=?";
        try {
            PreparedStatement stmt = conexaoLocacao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Locação excluída com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Buscar todas as locações de um cliente
    public List<Locacao> listarLocacoesDoCliente(Integer idCliente) {
        try {
            List<Locacao> locacoes = new ArrayList<>();
            String sql = "SELECT * FROM locacao WHERE id_cliente = ?";
            PreparedStatement stmt = conexaoLocacao.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Locacao locacao = new Locacao();
                locacao.setId(rs.getInt("id"));
                locacao.setDt_Inicio(rs.getDate("dt_inicio"));
                locacao.setDt_Fim(rs.getDate("dt_fim"));
                locacao.setValor(rs.getDouble("valor"));
                locacao.setId_Veiculo(rs.getInt("id_veiculo"));
                locacao.setId_Cliente(rs.getInt("id_cliente"));
                locacoes.add(locacao);
            }
            rs.close();
            stmt.close();
            return locacoes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}