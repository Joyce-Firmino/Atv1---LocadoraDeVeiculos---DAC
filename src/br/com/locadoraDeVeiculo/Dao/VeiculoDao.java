package br.com.locadoraDeVeiculo.Dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.locadoraDeVeiculo.Conexao.FabricaDeConexao;
import br.com.locadoraDeVeiculo.CriacaoDeTabelas.TabelaVeiculo;
import br.com.locadoraDeVeiculo.Entidades.Locacao;
import br.com.locadoraDeVeiculo.Entidades.Veiculo;

public class VeiculoDao {
    private Connection conexaoVeiculo;
    LocacaoDao locacao = new LocacaoDao();

    public VeiculoDao() {
        this.conexaoVeiculo = new FabricaDeConexao().getConexao();
    }

    // Método para verificar se a tabela Veiculo já existe no banco
    private boolean tabelaVeiculoExiste() throws SQLException {
        DatabaseMetaData metaData = conexaoVeiculo.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "veiculo", null);
        return tables.next();
    }

    // Método para verificar se o cliente possui locações associadas a ele
    private boolean possuiLocacoes(Integer idCliente) {
        List<Locacao> locacoesClien = locacao.listarLocacoesDoCliente(idCliente);
        return !locacoesClien.isEmpty();
    }

    // Método para inserir um veiculo no banco
    public void inserirVeiculo(Veiculo veiculo) throws SQLException {
        if (!tabelaVeiculoExiste()) {
            TabelaVeiculo tabelaVeiculo = new TabelaVeiculo();
            tabelaVeiculo.criar();
        }

        String sql = "INSERT INTO veiculo (chassi, modelo, ano, marca, placa, cor) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexaoVeiculo.prepareStatement(sql);
            stmt.setString(1, veiculo.getChassi());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, (int) veiculo.getAno());
            stmt.setString(4, veiculo.getMarca());
            stmt.setString(5, veiculo.getPlaca());
            stmt.setString(6, veiculo.getCor());
            stmt.execute();
            stmt.close();
            System.out.println("Veículo criado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Método para listar todos os veiculos do banco
    public List<Veiculo> listarVeiculos() {
        try {
            List<Veiculo> veiculos = new ArrayList<Veiculo>();
            String sql = "SELECT * FROM veiculo ORDER BY id";

            PreparedStatement stmt = conexaoVeiculo.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setChassi(rs.getString("chassi"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCor(rs.getString("cor"));

                veiculos.add(veiculo);
            }
            rs.close();
            stmt.close();
            return veiculos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para atualizar um veiculo
    public void atualizarVeiculo(Integer id, Veiculo novosDadosVeiculo) {
        String sql = "UPDATE veiculo SET chassi=?, modelo=?, ano=?, marca=?, placa=?, cor=? WHERE id=?";
        try {
            PreparedStatement stmt = conexaoVeiculo.prepareStatement(sql);
            stmt.setString(1, novosDadosVeiculo.getChassi());
            stmt.setString(2, novosDadosVeiculo.getModelo());
            stmt.setInt(3, (int) novosDadosVeiculo.getAno());
            stmt.setString(4, novosDadosVeiculo.getMarca());
            stmt.setString(5, novosDadosVeiculo.getPlaca());
            stmt.setString(6, novosDadosVeiculo.getCor());
            stmt.setInt(7, id);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Veículo atualizado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para deletar um veiculo
    public void deletarVeiculo(Integer idDeletar) {
        String sql = "DELETE FROM veiculo WHERE id=?";
        try {
            if (possuiLocacoes(idDeletar)) {
                locacao.deletarLocacao(idDeletar);
            }
            PreparedStatement stmt = conexaoVeiculo.prepareStatement(sql);
            stmt.setInt(1, idDeletar);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Veículo excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}