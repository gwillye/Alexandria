package model.entity;

import java.util.Date;

public class ItemDeEmprestimo {

	private Date dataEmprestimoItem;
	private Date dataDevolucao;
	private Date dataPrevistaDevolucao;
	private Emprestimo emprestimo;
	private Exemplar exemplar;
	private int status;

	public ItemDeEmprestimo(Emprestimo emprestimo, Exemplar exemplar, Date dataDevolucao,
			Date dataPrevistaDevolucao, Date dataEmprestimoItem, int status) {

		this.emprestimo = emprestimo;
		this.exemplar = exemplar;
		this.dataDevolucao = dataDevolucao;
		this.dataPrevistaDevolucao = dataPrevistaDevolucao;
		this.dataEmprestimoItem = dataEmprestimoItem;
	}

	public void itemDeEmprestimo(Emprestimo emp, Exemplar exp) {

	}

	public void vincularEmprestimo(Emprestimo emp) {
		this.emprestimo = emp;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String toString() {
		return "ItemDeEmprestimo{" +
				"dataEmprestimoItem=" + dataEmprestimoItem +
				", dataDevolucao=" + dataDevolucao +
				", dataPrevistaDevolucao=" + dataPrevistaDevolucao +
				", emprestimo=" + emprestimo +
				", exemplar=" + exemplar +
				'}';
	}
}
