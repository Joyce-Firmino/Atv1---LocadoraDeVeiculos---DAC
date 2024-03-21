package br.com.locadoraDeVeiculo.Testes;

import java.sql.SQLException;
import java.util.List;

import br.com.locadoraDeVeiculo.Dao.ClienteDao;
import br.com.locadoraDeVeiculo.Entidades.Cliente;

public class TesteClienteCrud {
    public static void main(String[] args) throws SQLException {
        //Criando um cliente
        Cliente cliente = new Cliente();
        cliente.setNomeCompleto("Luiz Otavio");
        cliente.setCpf("568.587.685-16");
        cliente.setEndereco("Rua Prefeito Miranda de Lacerda...");
        cliente.setTelefone("(83) 99656-7412");

        //Inserindo um cliente no BD
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.inserirCliente(cliente);

        //Listando todos os clientes via console
        List<Cliente> clientes = clienteDao.listarClientes();
        for (Cliente clienteImprime : clientes) {
            System.out.println("ID: " + clienteImprime.getId());
            System.out.println("Nome Completo: " + clienteImprime.getNomeCompleto());
            System.out.println("CPF: " + clienteImprime.getCpf());
            System.out.println("Telefone: " + clienteImprime.getTelefone());
            System.out.println("Endereço: " + clienteImprime.getEndereco());
            System.out.println();
        }

        //Atualizar dados de um cliente
        cliente.setNomeCompleto("Laiz");
        cliente.setCpf("365.241.635-28");
        cliente.setEndereco("Rua Projetada...");
        cliente.setTelefone("(83) 99365-2879");
        clienteDao.atualizarCliente(1, cliente);

//		//Deletar um cliente
		clienteDao.deletarCliente(1);
    }
}