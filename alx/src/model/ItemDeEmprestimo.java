package model;

public class ItemDeEmprestimo {
  
  private int idItemEmp;

	private Emprestimo emprestimo;

	private Exemplar exemplar;
	
	public ItemDeEmprestimo(int idItemEmp, Emprestimo emprestimo, Exemplar exemplar) {
		super();
		this.idItemEmp = idItemEmp;
		this.emprestimo = emprestimo;
		this.exemplar = exemplar;
	}

	public ItemDeEmprestimo(Emprestimo emp, Exemplar exp) {

	}

	public void vincularEmprestimo(ItemDeEmprestimo itEmp, Emprestimo emp) {

	}

	public int getIdItemEmp() {
		return idItemEmp;
	}

	
	public void setIdItemEmp(int idItemEmp) {
		this.idItemEmp = idItemEmp;
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
