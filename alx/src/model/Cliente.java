package model;

import java.util.Date;
public class Cliente extends Pessoa{
  
  private Date dataCadastro;

	private Emprestimo[] emprestimo;
	

	public Cliente(String cpf, String nome, Date dataNasc, int idade, String Endereco, String senha, String endereco2) {
		super(cpf, nome, dataNasc, idade, Endereco, senha, endereco2);
		// TODO Auto-generated constructor stub
	}
	
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
		return false;
	}

	public void associaEmprestimo(Emprestimo emp) {

	}
}
