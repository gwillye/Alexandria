package controller;

import model.dao.EmprestimoDAO;
import model.dao.ExemplarDAO;
import model.entity.Cliente;
import model.entity.Emprestimo;
import model.entity.Exemplar;

public class Controladora {
	private EmprestimoDAO emprestimoDAO;
	private ExemplarDAO exemplarDAO;

	public Controladora() {
		this.emprestimoDAO = new EmprestimoDAO();
		this.exemplarDAO = new ExemplarDAO();
	}

	public void adicionarItem(int idExemplar, int idEmprestimo) {
		Exemplar exp = exemplarDAO.buscarExemplar(idExemplar);
		Emprestimo emp = emprestimoDAO.buscaEmprestimo(idEmprestimo);

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

	public void iniciarEmprestimo(Cliente cliente) {
		boolean apto = cliente.podeEmprestar();

		if (apto) {
			Emprestimo novoEmprestimo = new Emprestimo();
			novoEmprestimo.emprestimo(cliente, apto);
			cliente.associaEmprestimo(novoEmprestimo);

			System.out.println("Empréstimo iniciado para o cliente: " + cliente.getNome());
		} else {
			System.out.println("Cliente não está apto para iniciar um novo empréstimo.");
		}
	}

}
