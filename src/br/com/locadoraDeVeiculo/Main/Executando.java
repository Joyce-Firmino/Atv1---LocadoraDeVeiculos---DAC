package br.com.locadoraDeVeiculo.Main;

import java.sql.SQLException;
import java.util.List;

import br.com.locadoraDeVeiculo.Dao.ClienteDao;
import br.com.locadoraDeVeiculo.Entidades.Cliente;

public class Executando {
	public static void main(String[] args) throws SQLException{
		
		//Criando um cliente
		Cliente cliente1 = new Cliente();
		cliente1.setNomeCompleto("Luiz Otavio");
		cliente1.setCpf("568.587.685-16");
		cliente1.setEndereco("Rua Prefeito Miranda de Lacerda...");
		cliente1.setTelefone("(83) 99656-7412");
		
		//Inserindo um cliente no BD
		ClienteDao clienteDao = new ClienteDao();
		clienteDao.inserirCliente(cliente1);
		
		//Listando todos os clientes via console
        List<Cliente> clientes = clienteDao.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome Completo: " + cliente.getNomeCompleto());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println();
        }
		
		//Atualizar dados de um cliente
		cliente1.setNomeCompleto("Laiz");
		cliente1.setCpf("365.241.635-28");
		cliente1.setEndereco("Rua Projetada...");
		cliente1.setTelefone("(83) 99365-2879");
		clienteDao.atualizarCliente(2L, cliente1);
        
        //Deletar um cliente
        clienteDao.deletarCliente(2L);
	}
}
