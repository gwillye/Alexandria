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
	}

	public void adicionarItem(int idExemplar, int idEmprestimo) throws SQLException {
		Exemplar exp = exemplarDAO.buscarExemplar(idExemplar);
		Emprestimo emp = emprestimoDAO.buscaEmprestimo(idEmprestimo);
		ItemDeEmprestimo item = itemDeEmprestimoDAO.buscaItemDeEmprestimo(idEmprestimo);
		// exp.vinculaEmprestimo(); // campo nulo por enquanto...

		/// adicionarItem
		itemDeEmprestimoDAO.salvarItemDeEmprestimo(item);

	}

	public void registrarEmprestimo(int idEmprestimo, Cliente cliente) {

		Emprestimo emprestimo = emprestimoDAO.buscaEmprestimo(idEmprestimo);

		if (emprestimo != null && emprestimo.getStatus().equals("INICIADO")) {
			emprestimo.registrarEmprestimo(cliente);
			emprestimoDAO.salvaEmprestimo(emprestimo);
		} else {
			System.out
					.println("Não é possível registrar o empréstimo. O empréstimo não foi iniciado ou não encontrado.");
		}
	}

	public void devolverEmprestimo(int idEmprestimo) {

	}

	public Cliente iniciarEmprestimo(String cpf) {
		Cliente cli = clienteDAO.buscaCliente(cpf);
		boolean apto = cli.podeEmprestar();

		if (apto) {
			Date dataHoraAtual = new Date();
			int idEmprestimo = emprestimoDAO.buscaProximoID();
			Emprestimo novoEmprestimo = new Emprestimo(idEmprestimo, dataHoraAtual, cli);
			novoEmprestimo.setStatus("Iniciado");
			cli.associaEmprestimo(novoEmprestimo);
			clienteDAO.salvaCliente(cli);
			emprestimoDAO.salvaEmprestimo(novoEmprestimo);

			System.out.println("Empréstimo iniciado para o cliente: " + cli.getNome());
		} else {
			System.out.println("Cliente não está apto para iniciar um novo empréstimo.");
		}
		return cli;
	}

}
