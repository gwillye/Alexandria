public class Livro {

	private int isbn;

	private String titulo;

	private String autor;

	private String categoria;

	private int qtdExemplares;

	private Funcionario[] funcionario;
	
	public Livro(int isbn, String titulo, String autor, String categoria, int qtdExemplares,
			Funcionario[] funcionario) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.qtdExemplares = qtdExemplares;
		this.funcionario = funcionario;
	}
	
	
	

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getQtdExemplares() {
		return qtdExemplares;
	}

	public void setQtdExemplares(int qtdExemplares) {
		this.qtdExemplares = qtdExemplares;
	}

	public Funcionario[] getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario[] funcionario) {
		this.funcionario = funcionario;
	}




	

}
