package br.com.locadoraDeVeiculo.Testes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.locadoraDeVeiculo.Dao.LocacaoDao;
import br.com.locadoraDeVeiculo.Entidades.Locacao;

public class TesteLocacaoCrud {
    public static void main(String[] args) throws SQLException {
        //Criando uma locação
        Locacao locacao = new Locacao();
        locacao.setDt_Inicio(new Date());
        locacao.setDt_Fim(new Date());
        locacao.setValor(100.0);
        locacao.setId_Veiculo(1); // Supondo que o veículo com ID 1 já existe no banco de dados
        locacao.setId_Cliente(1); // Supondo que o cliente com ID 1 já existe no banco de dados

        //Inserindo uma locação no BD
        LocacaoDao locacaoDao = new LocacaoDao();
        locacaoDao.inserirLocacao(locacao);

        //Listando todas as locações via console
        List<Locacao> locacoes = locacaoDao.listarLocacoes();
        for (Locacao locacaoImprime : locacoes) {
            System.out.println("ID: " + locacaoImprime.getId());
            System.out.println("Data de Início: " + locacaoImprime.getDt_Inicio());
            System.out.println("Data de Fim: " + locacaoImprime.getDt_Fim());
            System.out.println("Valor: " + locacaoImprime.getValor());
            System.out.println("ID do Veículo: " + locacaoImprime.getId_Veiculo());
            System.out.println("ID do Cliente: " + locacaoImprime.getId_Cliente());
            System.out.println();
        }

        //Atualizar dados de uma locação
        locacao.setValor(150.0);
        locacaoDao.atualizarLocacao(1, locacao);

        //Deletar uma locação
	    locacaoDao.deletarLocacao(1);
    }

}