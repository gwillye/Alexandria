package model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Pessoa {

	private Date dataCadastro;

	private int codCliente;

	private List<Emprestimo> emprestimos = new ArrayList<>();

	public Cliente(String cpf, String nome, int codCliente) {
		super(cpf, nome);
		this.codCliente = codCliente;
	}

	public Cliente() {

	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public List<Emprestimo> getEmprestimos() {
		return new ArrayList<>(emprestimos);
	}

	public boolean podeEmprestar() {

		return true;

	}

	public void associaEmprestimo(Emprestimo emp) {

		if (emp != null) {
			emprestimos.add(emp);
		}

	}
}
