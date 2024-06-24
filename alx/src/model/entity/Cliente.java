package model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Pessoa {

	private Date dataCadastro;

	private List<Emprestimo> emprestimos = new ArrayList<>();

	public Cliente(String cpf, String nome) {
		super(cpf, nome);
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Emprestimo> getEmprestimos() {
		return new ArrayList<>(emprestimos); // Cria uma cópia da lista
	}

	public boolean podeEmprestar() {

		/*
		 * int emprestimosAtivos = 0;
		 * boolean temEmprestimoAtrasado = false;
		 * boolean temEmprestimoExtraviado = false;
		 * 
		 * for (Emprestimo emp : emprestimos) {
		 * if (emp != null) {
		 * if (emp.getStatus().equals("ATIVO")) {
		 * emprestimosAtivos++;
		 * } else if (emp.getStatus().equals("ATRASADO")) {
		 * temEmprestimoAtrasado = true;
		 * } else if (emp.getStatus().equals("EXTRAVIADO")) {
		 * temEmprestimoExtraviado = true;
		 * }
		 * }
		 * }
		 * 
		 * return emprestimosAtivos < 3 && !temEmprestimoAtrasado &&
		 * !temEmprestimoExtraviado;
		 */

		return true;

	}

	public void associaEmprestimo(Emprestimo emp) {

		if (emp != null) {
			emprestimos.add(emp);
		}

	}
}
