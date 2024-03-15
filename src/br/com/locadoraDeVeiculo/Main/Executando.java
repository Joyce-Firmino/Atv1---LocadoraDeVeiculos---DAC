package br.com.locadoraDeVeiculo.Main;

import java.sql.SQLException;
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
		
		
	}
}
