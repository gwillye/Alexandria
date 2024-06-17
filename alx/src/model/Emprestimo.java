package model;

import java.util.Date;

public class Emprestimo {
  
  private int idEmprestimo;

	private Date dataEmprestimo;

	private Date dataDevolucao;

	private Date dataVencimento;

	private String status;

	private Cliente cliente;

	private Funcionario funcionario;
	
	public Emprestimo(int idEmprestimo, Date dataEmprestimo, Date dataDevolucao, Date dataVencimento, String status,
			Cliente cliente, Funcionario funcionario) {
		super();
		this.idEmprestimo = idEmprestimo;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.dataVencimento = dataVencimento;
		this.status = status;
		this.cliente = cliente;
		this.funcionario = funcionario;
	}
	
	

	public Emprestimo() {
		super();
	}


	public int getIdEmprestimo() {
		return idEmprestimo;
	}


	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}


	public Date getDataDevolucao() {
		return dataDevolucao;
	}


	public Date getDataVencimento() {
		return dataVencimento;
	}


	public String getStatus() {
		return status;
	}


	public void adicionarItem(Exemplar exp) {

	}

	public void registrarEmprestimo(Cliente cli) {

	}

	public void setStatus(String status) {

	}

	public void setDataEmprestimo(Date dataAtual) {

	}

	public void setDataVencimento(Date dataAtual) {
			//+14
	}

	public void devolverEmprestimo(Emprestimo emp) {

	}

	public void setDataDevolucao(Date dataAtual) {

	}

	public void emprestimo(Cliente cli) {

	}

	public void associaCliente(Cliente cli) {

	}


}
