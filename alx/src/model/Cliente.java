package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Pessoa {

	private Date dataCadastro;

	private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();	
	
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
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public boolean podeEmprestar() {

		int emprestimosAtivos = 0;
		boolean temEmprestimoAtrasado = false;
		boolean temEmprestimoExtraviado = false;

		for (Emprestimo emp : emprestimos) {
			if (emp != null) {
				if (emp.getStatus().equals("ATIVO")) {
					emprestimosAtivos++;
				} else if (emp.getStatus().equals("ATRASADO")) {
					temEmprestimoAtrasado = true;
				} else if (emp.getStatus().equals("EXTRAVIADO")) {
					temEmprestimoExtraviado = true;
				}
			}
		}

		return emprestimosAtivos < 3 && !temEmprestimoAtrasado && !temEmprestimoExtraviado;

	}

	public void associaEmprestimo(Emprestimo emp) {
		emprestimos.add(emp);

	}
}
