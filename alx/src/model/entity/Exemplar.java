package model.entity;

public class Exemplar {

	private int idExemplar;

	private int edicao;

	private int setor;

	private Livro livro;

	public Exemplar(int edicao, int setor, int estante, Livro livro) {

		this.edicao = edicao;
		this.setor = setor;
		this.livro = livro;
	}

	public Exemplar(int idExemplar2, Livro livro2, int edicao2, int setor2) {
		// TODO Auto-generated constructor stub
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

	public int getSetor() {
		return setor;
	}

	public void vinculaEmprestimo(Emprestimo emp) {

	}

	public void setEstado(String estado) {

	}

	public void desvinculaExemplar(Exemplar exp) {

	}

	public void setSetor(int setor) {

	}

	public void setEstante(int estante) {

	}

}
