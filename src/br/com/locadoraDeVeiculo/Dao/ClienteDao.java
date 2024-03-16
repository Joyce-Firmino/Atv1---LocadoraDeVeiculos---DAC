package br.com.locadoraDeVeiculo.Dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	//Método para listar todos os clientes do banco
    public List<Cliente> listarClientes() {
        try {
        	List<Cliente> clientes = new ArrayList<Cliente>();
            String sql = "SELECT * FROM cliente ORDER BY id";

            // prepara statement para seleção
            PreparedStatement stmt = conexaoCliente.prepareStatement(sql);
            
            // executa a consulta
            ResultSet rs = stmt.executeQuery();
            
            // itera sobre os resultados
            while (rs.next()) {
            	
                // cria um novo cliente com os dados do resultado
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNomeCompleto(rs.getString("nomeCompleto"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                
                //adiciona o cliente à lista
                clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para atualizar um cliente no BD
    public void atualizarCliente(Long id_Atualizar,  Cliente novosDadosCliente) {
    	String sql = "UPDATE cliente SET nomeCompleto=?, cpf=?, telefone=?, endereco=? WHERE id=?";
        try {
            // prepara statement para atualização
            PreparedStatement stmt = conexaoCliente.prepareStatement(sql);
            // seta os novos valores do cliente
            stmt.setString(1, novosDadosCliente.getNomeCompleto());
            stmt.setString(2, novosDadosCliente.getCpf());
            stmt.setString(3, novosDadosCliente.getTelefone());
            stmt.setString(4, novosDadosCliente.getEndereco());
            // definindo que o cliente que sera atualizado será o q possui o ID correspondente ao valor de id_Atualizar.
            stmt.setLong(5, id_Atualizar); 
            stmt.executeUpdate(); // executa a atualização
            stmt.close();
            System.out.println("Cliente atualizado com sucesso!");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Método para deletar cliente do BD
    public void deletarCliente(Long idCliente) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            // prepara statement para exclusão
            PreparedStatement stmt = conexaoCliente.prepareStatement(sql);
            // define o ID do cliente a ser deletado
            stmt.setLong(1, idCliente);
            // executa a exclusão
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Cliente excluido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}