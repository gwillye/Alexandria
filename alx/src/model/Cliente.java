package model;

import java.util.Date;
public class Cliente extends Pessoa{
  
  private Date dataCadastro;

	private Emprestimo[] emprestimo;
	
	
	public Cliente(String cpf, String nome) {
		super(cpf, nome);
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Emprestimo[] getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo[] emprestimo) {
		this.emprestimo = emprestimo;
	}

	public boolean podeEmprestar() {

		int emprestimosAtivos = 0;
		boolean temEmprestimoAtrasado = false;
		boolean temEmprestimoExtraviado = false;

		for (Emprestimo emp : emprestimo) {
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

		// Condição para permitir ou não novo empréstimo
		return emprestimosAtivos < 3 && !temEmprestimoAtrasado && !temEmprestimoExtraviado;

	}

	public void associaEmprestimo(Emprestimo emp) {

	}
}
