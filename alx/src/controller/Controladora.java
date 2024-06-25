package controller;

import java.sql.SQLException;
import java.util.Date;

import model.dao.ClienteDAO;
import model.dao.EmprestimoDAO;
import model.dao.ExemplarDAO;
import model.dao.ItemDeEmprestimoDAO;
import model.entity.Cliente;
import model.entity.Emprestimo;
import model.entity.Exemplar;
import model.entity.ItemDeEmprestimo;

public class Controladora {
	private EmprestimoDAO emprestimoDAO;
	private ExemplarDAO exemplarDAO;
	private ItemDeEmprestimoDAO itemDeEmprestimoDAO;
	private ClienteDAO clienteDAO;

	public Controladora() {
		this.emprestimoDAO = new EmprestimoDAO();
		this.exemplarDAO = new ExemplarDAO();
		this.itemDeEmprestimoDAO = new ItemDeEmprestimoDAO();
		this.clienteDAO = new ClienteDAO();
	}

	public ItemDeEmprestimo adicionarItem(int idExemplar, int idEmprestimo) throws Exception {
    Exemplar exemplar = exemplarDAO.buscarExemplar(idExemplar);
    if (exemplar == null) {
        throw new Exception("Exemplar não encontrado com ID: " + idExemplar);
    }

    Emprestimo emprestimo = emprestimoDAO.buscaEmprestimo(idEmprestimo);
    if (emprestimo == null) {
        throw new Exception("Empréstimo não encontrado com ID: " + idEmprestimo);
    }

    ItemDeEmprestimo item = emprestimo.adicionarItem(exemplar, emprestimo);
    if (item == null) {
        throw new Exception("Falha ao adicionar item de empréstimo.");
    }

    exemplar.setStatus(0);
    itemDeEmprestimoDAO.salvarItemDeEmprestimo(item);
    exemplarDAO.atualizarExemplar(exemplar);

    return item;
}



	public void devolverEmprestimo(int idEmprestimo) {

	}

	public int buscaIdEmprestimo(){
		return emprestimoDAO.buscaProximoID() - 1;
	}

	public Cliente iniciarEmprestimo(String cpf) {
    Cliente cli = null;
    try {
        cli = clienteDAO.buscaCliente(cpf);
        if (cli == null) {
            System.err.println("Cliente não encontrado para o CPF: " + cpf);
            return null;
        }
        
        boolean apto = cli.podeEmprestar();

        if (apto) {
            Date dataHoraAtual = new Date();
            int idEmprestimo = emprestimoDAO.buscaProximoID();

            Emprestimo novoEmprestimo = new Emprestimo(idEmprestimo, dataHoraAtual, cli);
            novoEmprestimo.setStatus("Iniciado");
            
            emprestimoDAO.salvaEmprestimo(novoEmprestimo);
            System.out.println("Empréstimo iniciado para o cliente: " + cli.getNome());
        } else {
            System.out.println("Cliente não está apto para iniciar um novo empréstimo.");
        }
    } catch (Exception e) {
        System.err.println("Erro inesperado: " + e.getMessage());
    }
    return cli;
}

	public String imprimirResultado() {
		int idEmprestimo = buscaIdEmprestimo();
		Emprestimo emprestimo = emprestimoDAO.buscaEmprestimo(idEmprestimo);
		return "cu";
	}

}
