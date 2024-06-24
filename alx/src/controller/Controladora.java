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

	public ItemDeEmprestimo adicionarItem(int idExemplar, int idEmprestimo) throws SQLException {

		Exemplar exemplar = exemplarDAO.buscarExemplar(idExemplar);
		Emprestimo emprestimo = emprestimoDAO.buscaEmprestimo(idEmprestimo);

		ItemDeEmprestimo item = emprestimo.adicionarItem(exemplar, emprestimo);

		exemplar.setStatus(0);

		itemDeEmprestimoDAO.salvarItemDeEmprestimo(item);

		exemplarDAO.atualizarExemplar(exemplar);

		return item;
	}

	public void devolverEmprestimo(int idEmprestimo) {

	}

	public Cliente iniciarEmprestimo(String cpf) {

		Cliente cli = clienteDAO.buscaCliente(cpf);
		System.out.println("BUSCA CLIENTE " + cli.getNome());
		boolean apto = cli.podeEmprestar();
		System.out.println("ESTÁ APTO? " + apto);

		if (apto) {
			Date dataHoraAtual = new Date();
			int idEmprestimo = emprestimoDAO.buscaProximoID();
			System.out.println("proximo id? " + idEmprestimo);
			Emprestimo novoEmprestimo = new Emprestimo(idEmprestimo, dataHoraAtual, cli);
			novoEmprestimo.setStatus("Iniciado");
			// novoEmprestimo.associaCliente(novoEmprestimo);
			// lienteDAO.salvaCliente(cli);
			emprestimoDAO.salvaEmprestimo(novoEmprestimo);

			System.out.println("Empréstimo iniciado para o cliente: " + cli.getNome());
		} else {
			System.out.println("Cliente não está apto para iniciar um novo empréstimo.");
		}
		return cli;
	}

}
