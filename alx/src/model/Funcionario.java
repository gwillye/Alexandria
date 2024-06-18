package model;

import java.util.Date;

public class Funcionario extends Pessoa {

	private int idFunc;

	private String cargo;

	private Boolean ehAdministrador;

	private Emprestimo[] emprestimo;

	public Funcionario(String cpf, String nome, Date dataNasc, int idade, String Endereco, String senha, int idFunc,
			String cargo, Boolean ehAdministrador, Emprestimo[] emprestimo) {
		super(cpf, nome, dataNasc, Endereco, senha, cargo);
		this.idFunc = idFunc;
		this.cargo = cargo;
		this.ehAdministrador = ehAdministrador;
		this.emprestimo = emprestimo;
	}

	public Funcionario(String cpf, String nome, String cargo, Boolean ehAdministrador) {
		super(cpf, nome);
		this.cargo = cargo;
		this.ehAdministrador = ehAdministrador;
	}

	public Funcionario(String cpf, String nome, Boolean ehAdministrador) {
		super(cpf, nome);
		this.ehAdministrador = ehAdministrador;
	}

	public int getIdFunc() {
		return idFunc;
	}

	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Boolean getEhAdministrador() {
		return ehAdministrador;
	}

	public void setEhAdministrador(Boolean ehAdministrador) {
		this.ehAdministrador = ehAdministrador;
	}

	public Emprestimo[] getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo[] emprestimo) {
		this.emprestimo = emprestimo;
	}
}