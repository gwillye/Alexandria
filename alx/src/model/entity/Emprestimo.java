package model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

public class Emprestimo {

	private int idEmprestimo;

	private Date dataEmprestimo;

	private String status;

	private Cliente cliente;

	private Funcionario funcionario;
	private List<ItemDeEmprestimo> listaItens;

	public Emprestimo(int idEmprestimo, Date dataEmprestimo, String status,
			Cliente cliente, Funcionario funcionario) {
		this.idEmprestimo = idEmprestimo;
		this.dataEmprestimo = dataEmprestimo;
		this.status = status;
		this.cliente = cliente;
		this.funcionario = funcionario;
		listaItens = new ArrayList<>(); // listaItens
	}

	public Emprestimo() {
		this.status = "INICIADO";
	}

	public Emprestimo(int idEmprestimo, Date dataEmprestimo, Cliente cliente) {
		this.idEmprestimo = idEmprestimo;
		this.dataEmprestimo = dataEmprestimo;
		this.cliente = cliente;
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

		if (this.dataEmprestimo == null) {
			System.out.println("Data de empréstimo não inicializada corretamente.");
			return;
		}

		// this.dataVencimento = calcularDataVencimento(this.dataEmprestimo);
		this.status = "ATIVO";
		System.out.println("Empréstimo registrado para o cliente: " + cliente.getNome());
		System.out.println("Data de Empréstimo: " + this.dataEmprestimo);
		// System.out.println("Data de Vencimento: " + this.dataVencimento);
		System.out.println("Status " + this.status);
	}

	public ItemDeEmprestimo adicionarItem(Exemplar exemplar, Emprestimo emprestimo) {

		Date hoje = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(hoje);
		calendar.add(Calendar.DAY_OF_MONTH, 14);

		Date dataLimite = calendar.getTime();

		ItemDeEmprestimo item = new ItemDeEmprestimo(emprestimo, exemplar, null, dataLimite, hoje, 0);

		// listaItens.add(item);

		return item;
	}

	public void devolverEmprestimo(Emprestimo emp) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
