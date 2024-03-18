package br.com.locadoraDeVeiculo.Testes;

import java.sql.SQLException;
import java.util.List;

import br.com.locadoraDeVeiculo.Dao.VeiculoDao;
import br.com.locadoraDeVeiculo.Entidades.Veiculo;

public class TesteVeiculoCrud {
    public static void main(String[] args) throws SQLException {

        //Criando um veiculo
        Veiculo veiculo = new Veiculo();
        veiculo.setChassi("s5f2f4d5s6e556r2");
        veiculo.setAno(2015);
        veiculo.setCor("Prata");
        veiculo.setMarca("Chevrolet");
        veiculo.setModelo("Classic");
        veiculo.setPlaca("JDF2453");

        //Inserir um veiculo no banco
        VeiculoDao veiculoDao = new VeiculoDao();
        veiculoDao.inserirVeiculo(veiculo);

        //Listar todos os veiculos do banco
        List<Veiculo> veiculos = veiculoDao.listarVeiculos();

        for( Veiculo imprimirVeiculos : veiculos) {
            System.out.println("Id: " + imprimirVeiculos.getId());
            System.out.println("Chassi: " + imprimirVeiculos.getChassi());
            System.out.println("Ano: " + imprimirVeiculos.getAno());
            System.out.println("Cor: " + imprimirVeiculos.getCor());
            System.out.println("Marca: " + imprimirVeiculos.getMarca());
            System.out.println("Modelo: " + imprimirVeiculos.getModelo());
            System.out.println("Placa: " + imprimirVeiculos.getPlaca());
        }

        //Atualizar dados de um veiculo
        veiculo.setChassi("d5f54gv125sw14");
        veiculo.setAno(2016);
        veiculo.setCor("Branco");
        veiculo.setMarca("Chevrolet");
        veiculo.setModelo("Onix");
        veiculo.setPlaca("LKKL3647");
        veiculoDao.atualizarVeiculo(1, veiculo);

        //Deletar um veiculo
        veiculoDao.deletarVeiculo(1);
    }
}