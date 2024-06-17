package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Exemplar;
import model.Livro;

//import model.Exemplar;



public class LivroDAO {

	private static final String url = "jdbc:sqlite:library.db";
	

	public void salvarLivro(Livro livro) {
		String sql = "INSERT INTO Livro (ISBN, editora, autor, titulo, subtitulo, genero, quantidade_exemplar) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

	       pstmt.setString(1, livro.getIsbn());
	       pstmt.setString(2, livro.getEditora());
	       pstmt.setString(3, livro.getAutor());
	       pstmt.setString(4, livro.getTitulo());
	       pstmt.setString(5, livro.getSubtitulo());
	       pstmt.setString(6, livro.getGenero());
	       pstmt.setInt(7, livro.getQuantidadeExemplar());
	       
		   pstmt.executeUpdate();
		   System.out.println("Livro cadastrado com sucesso: " + livro.getTitulo());

        } catch (SQLException e) {
           System.err.println("Erro ao cadastrar livro: " + e.getMessage());
        }
	}

	public void salvarExemplar(Exemplar exemp) {
		String sql = "INSERT INTO Exemplar (ISBN, edicao, setor) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, exemp.getLivro().getIsbn());
            pstmt.setInt(2, exemp.getEdicao());
            pstmt.setInt(3, exemp.getSetor());

            pstmt.executeUpdate();
            System.out.println("Exemplar cadastrado com sucesso: " + exemp.getLivro().getTitulo());

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar exemplar: " + e.getMessage());
        }
    }
	

	// public Exemplar buscarExemplar(int idExemplar) {
	// 	return null;
	// }

}
