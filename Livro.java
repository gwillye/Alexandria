public class Livro {
    private final String ISBN;
    private final String titulo;
    private final String edicao;
    private final int setor;
    private int exemplar;
    private final String autor;
    private final String genero;

    public Livro(String ISBN, String titulo, String edicao, int setor, int exemplar, String autor, String genero) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.edicao = edicao;
        this.setor = setor;
        this.exemplar = exemplar;
        this.autor = autor;
        this.genero = genero;
    }

    public void setExemplar(int exemplar) {
        this.exemplar = exemplar;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEdicao() {
        return edicao;
    }

    public int getSetor() {
        return setor;
    }

    public int getExemplar() {
        return exemplar;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getLivro() {
        return "ISBN: " + ISBN + ", Titulo: " + titulo + ", Edicao: " + edicao + ", Setor: " + setor +
               ", Exemplar: " + exemplar + ", Autor: " + autor + ", Genero: " + genero;
    }
}
