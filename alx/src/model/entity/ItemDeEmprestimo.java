package model.entity;

import java.util.Date;

public class ItemDeEmprestimo {

	private int idItemEmp;
	private Date dataEmprestimoItem;
	private Date dataDevolucao;
	private Date dataPrevistaDevolucao;
	private Emprestimo emprestimo;
	private Exemplar exemplar;

	public ItemDeEmprestimo(int idItemEmp, Emprestimo emprestimo, Exemplar exemplar, Date dataDevolucao,
			Date dataPrevistaDevolucao, Date dataEmprestimoItem) {
		this.idItemEmp = idItemEmp;
		this.emprestimo = emprestimo;
		this.exemplar = exemplar;
		this.dataDevolucao = dataDevolucao;
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
		this.dataEmprestimoItem = dataEmprestimoItem;
	}

	public int getIdItemEmp() {
		return idItemEmp;
	}

	public void setIdItemEmp(int idItemEmp) {
		this.idItemEmp = idItemEmp;
	}

	public Date getDataEmprestimoItem() {
		return dataEmprestimoItem;
	}

	public void setDataEmprestimoItem(Date dataEmprestimoItem) {
		this.dataEmprestimoItem = dataEmprestimoItem;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Date getDataPrevistaDevolucao() {
		return dataPrevistaDevolucao;
	}

	public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}
}
