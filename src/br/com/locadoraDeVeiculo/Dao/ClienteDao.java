package br.com.locadoraDeVeiculo.Dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.locadoraDeVeiculo.Conexao.FabricaDeConexao;
import br.com.locadoraDeVeiculo.CriacaoDeTabelas.TabelaCliente;
import br.com.locadoraDeVeiculo.Entidades.Cliente;


public class ClienteDao {
	// Atributo que armazenará conexão com o banco de dados
	private Connection conexaoCliente;
	
	//Construtor que faz uma instancia de Fabrica de Conexão e armazena no atributo conexaoCliente 
	public ClienteDao() {
		this.conexaoCliente = new FabricaDeConexao().getConexao();
	}
	
	// Método para buscar um cliente no banco pelo ID e retornar um objeto Cliente
    public Cliente buscarClientePorId(Long idCliente) {
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE id=?";
        try {
            // prepara statement para seleção do cliente pelo ID
            PreparedStatement stmt = conexaoCliente.prepareStatement(sql);
            stmt.setLong(1, idCliente);
            // executa a consulta
            ResultSet rs = stmt.executeQuery();
            // verifica se o cliente foi encontrado
            if (rs.next()) {
                // cria um objeto Cliente com os dados encontrados
                cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNomeCompleto(rs.getString("nome_completo"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
            }
            rs.close();
            stmt.close();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
    
    // Método para verificar se a tabela Cliente já existe no BD
    private boolean tabelaClienteExiste() throws SQLException {
        DatabaseMetaData metaData = conexaoCliente.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "cliente", null);
        return tables.next();
    }
    
    
	//Metodo para inserir um cliente no BD
	public void inserirCliente(Cliente cliente) throws SQLException {
		
		 // Verifica se a tabela Cliente já existe no BD
	    if (!tabelaClienteExiste()) {
	    	// Criando a tabela Cliente no BD
			TabelaCliente clientTable= new TabelaCliente();
			clientTable.criar();
	    }
	
        String sql = "INSERT INTO cliente (nomeCompleto, cpf, telefone, endereco) VALUES (?, ?, ?, ?)";
        try {
            // prepara statement para inserção
            PreparedStatement stmt = conexaoCliente.prepareStatement(sql);
            // seta os valores do cliente
            stmt.setString(1, cliente.getNomeCompleto());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.execute();
            stmt.close();
            System.out.println("Cliente criado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}