package model.entity;

public class Exemplar {

	private int idExemplar;

	private int edicao;

	private String setor;

	private Livro livro;

	private int status;

	public Exemplar(int idExemplar, Livro livro, int edicao, String setor, int status) {
		this.idExemplar = idExemplar;
		this.edicao = edicao;
		this.setor = setor;
		this.livro = livro;
		this.status = status;
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

	public String getSetor() {
		return setor;
	}

	public int getStatus() {
		return status;
	}

	public void vinculaEmprestimo(Emprestimo emp) {

	}

	public void desvinculaExemplar(Exemplar exp) {

	}

	public void setSetor(String setor) {

	}

	public void setStatus(int status) {
		this.status = status;
	}

}
