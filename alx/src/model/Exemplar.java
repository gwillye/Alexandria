package model;

public class Exemplar {
  
  private int idExemplar;

	private String estado;

	private int edicao;

	private int setor;

	private int estante;
	
	private Livro livro;

	public Exemplar(String estado, int edicao, int setor, int estante, Livro livro) {
		this.estado = estado;
		this.edicao = edicao;
		this.setor = setor;
		this.estante = estante;
		this.livro = livro;
}

	public int getIdExemplar() {
		return idExemplar;
	}

	public void setIdExemplar(int idExemplar) {
		this.idExemplar = idExemplar;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getEstado() {
		return estado;
	}

	public int getSetor() {
		return setor;
	}

	public int getEstante() {
		return estante;
	}

	

	// public void vinculaEmprestimo(Emprestimo emp) {

	// }

	public void setEstado(String estado) {

	}

	public void desvinculaExemplar(Exemplar exp) {

	}

	public void setSetor(int setor) {

	}

	public void setEstante(int estante) {

	}

}
