package controller;

import model.dao.EmprestimoDAO;
import model.entity.Cliente;
import model.entity.Emprestimo;

public class Controladora {
	private EmprestimoDAO emprestimoDAO;

	public Controladora() {
		this.emprestimoDAO = new EmprestimoDAO();
	}

	public void adicionarItem(int idExemplar, int idEmprestimo) {

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
			novoEmprestimo.emprestimo(cliente, apto); // Define o status como "INICIADO"
			cliente.associaEmprestimo(novoEmprestimo); // Associa o empréstimo ao cliente

			System.out.println("Empréstimo iniciado para o cliente: " + cliente.getNome());
		} else {
			System.out.println("Cliente não está apto para iniciar um novo empréstimo.");
		}
	}

}
