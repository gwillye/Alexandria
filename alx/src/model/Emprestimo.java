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
		this.idEmprestimo = idEmprestimo;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.dataVencimento = dataVencimento;
		this.status = status;
		this.cliente = cliente;
		this.funcionario = funcionario;
	}

	public Emprestimo() {
		this.status = "INICIADO"; // Define um valor padrão para o status
	}

	public void emprestimo(Cliente cliente, boolean apto) {
		this.cliente = cliente;

		if (apto) {
			this.status = "INICIADO";
			this.dataEmprestimo = new Date();
			System.out.println("Empréstimo iniciado para o cliente: " + cliente.getNome());
		} else {
			System.out.println("Cliente não está apto para iniciar um novo empréstimo.");
		}
	}

	public void registrarEmprestimo(Cliente cliente) {
		if (!"INICIADO".equals(this.status)) {
			System.out.println("Não é possível registrar o empréstimo. O empréstimo não foi iniciado.");
			return;
		}

		this.cliente = cliente;

		// Verifica se dataEmprestimo foi inicializada antes de calcular a data de
		// vencimento
		if (this.dataEmprestimo == null) {
			System.out.println("Data de empréstimo não inicializada corretamente.");
			return;
		}

		this.dataVencimento = calcularDataVencimento(this.dataEmprestimo);
		this.status = "ATIVO";
		System.out.println("Empréstimo registrado para o cliente: " + cliente.getNome());
		System.out.println("Data de Empréstimo: " + this.dataEmprestimo);
		System.out.println("Data de Vencimento: " + this.dataVencimento);
		System.out.println("Status " + this.status);
	}

	private Date calcularDataVencimento(Date dataEmprestimo) {
		long prazoMilissegundos = 14 * 24 * 60 * 60 * 1000L; // 14 dias em milissegundos
		return new Date(dataEmprestimo.getTime() + prazoMilissegundos);
	}

	public void adicionarItem(Exemplar exp) {
		// Implementar lógica para adicionar itens de empréstimo, se necessário
	}

	public void devolverEmprestimo(Emprestimo emp) {
		// Implementar lógica para devolver empréstimo, se necessário
	}

	// Getters e Setters

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

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
