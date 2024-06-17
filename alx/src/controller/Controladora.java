package controller;

import model.Cliente;
import model.Emprestimo;

public class Controladora {
  
  public void adicionarItem(int idExemplar, int idEmprestimo) {

	}

	public void registrarEmprestimo(int idEmprestimo) {

	}

	public void devolverEmprestimo(int idEmprestimo) {

	}

	public void iniciarEmprestimo(Cliente cliente) {
		Emprestimo emprestimo = new Emprestimo();
		boolean apto = cliente.podeEmprestar(); // Verifica se o cliente está apto para empréstimo

		emprestimo.emprestimo(cliente, apto); // Inicia o empréstimo com base na aptidão do cliente
	}


}
