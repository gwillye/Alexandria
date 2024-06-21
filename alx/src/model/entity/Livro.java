package model.entity;


public class Livro {

    private String isbn;
    private String editora;
    private String autor;
    private String titulo;
    private String subtitulo;
    private String genero;
    private int quantidadeExemplar;
    //private Funcionario[] funcionario;

    public Livro(String ISBN, String editora, String autor, String titulo, String subtitulo, String genero, int quantidadeExemplar) {
        this.isbn = ISBN;
        this.editora = editora;
        this.autor = autor;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.genero = genero;
        this.quantidadeExemplar = quantidadeExemplar;
    }

	
	
	
	public String getEditora() {
		return editora;
	}




	public void setEditora(String editora) {
		this.editora = editora;
	}




	public String getSubtitulo() {
		return subtitulo;
	}




	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}




	public String getGenero() {
		return genero;
	}




	public void setGenero(String genero) {
		this.genero = genero;
	}




	public int getQuantidadeExemplar() {
		return quantidadeExemplar;
	}




	public void setQuantidadeExemplar(int quantidadeExemplar) {
		this.quantidadeExemplar = quantidadeExemplar;
	}



	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
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

	
	// public Funcionario[] getFuncionario() {
	// 	return funcionario;
	// }

	// public void setFuncionario(Funcionario[] funcionario) {
	// 	this.funcionario = funcionario;
	// }




	

}
