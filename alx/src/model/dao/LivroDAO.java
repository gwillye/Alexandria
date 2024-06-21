package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.entity.Exemplar;
import model.entity.Livro;

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

	public ArrayList<Livro> listarLivro() throws Exception {
		ArrayList<Livro> livros = new ArrayList<>();
		
		String sql = " SELECT * FROM LIVRO  ";
		try (Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql)) {

				while (rs.next()) {
						String ISBN = rs.getString("ISBN");
						String editora = rs.getString("editora");
						String autor = rs.getString("autor");
						String titulo = rs.getString("titulo");
						String subtitulo = rs.getString("subtitulo");
						String genero = rs.getString("genero");
						int quantidadeExemplar = rs.getInt("quantidade_exemplar");

						Livro livro = new Livro(ISBN, editora, autor, titulo, subtitulo, genero, quantidadeExemplar);
						livros.add(livro);
				}
		} catch (SQLException e) {
				throw new Exception("Erro ao listar livros: " + e.getMessage());
		} 
		
		if(livros.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não há livros cadastrados ", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não há livros cadastrados ");
		}
		return livros;
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

	public Exemplar buscarExemplar(int idExemplar) {
		return null;
	}

	public void excluirLivro(String isbn) {
		String sql = "DELETE FROM Livro WHERE ISBN = ?";

		try (Connection conn = DriverManager.getConnection(url);
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, isbn);
				int affectedRows = pstmt.executeUpdate();

				if (affectedRows > 0) {
						System.out.println("Livro excluído com sucesso: " + isbn);
				} else {
						System.out.println("Nenhum livro encontrado com o ISBN: " + isbn);
				}

		} catch (SQLException e) {
				System.err.println("Erro ao excluir livro: " + e.getMessage());
		}
}

public void atualizarLivro(Livro livro) {
		String sql = "UPDATE Livro SET editora = ?, autor = ?, titulo = ?, subtitulo = ?, genero = ?, quantidade_exemplar = ? WHERE ISBN = ?";

		try (Connection conn = DriverManager.getConnection(url);
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, livro.getEditora());
				pstmt.setString(2, livro.getAutor());
				pstmt.setString(3, livro.getTitulo());
				pstmt.setString(4, livro.getSubtitulo());
				pstmt.setString(5, livro.getGenero());
				pstmt.setInt(6, livro.getQuantidadeExemplar());
				pstmt.setString(7, livro.getIsbn());

				int affectedRows = pstmt.executeUpdate();

				if (affectedRows > 0) {
						System.out.println("Livro atualizado com sucesso: " + livro.getTitulo());
				} else {
						System.out.println("Nenhum livro encontrado com o ISBN: " + livro.getIsbn());
				}

		} catch (SQLException e) {
				System.err.println("Erro ao atualizar livro: " + e.getMessage());
		}
}

public Livro consultaLivro(String isbn) {
		String sql = "SELECT * FROM Livro WHERE ISBN = ?";

		try (Connection conn = DriverManager.getConnection(url);
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, isbn);
				ResultSet result = pstmt.executeQuery();

				if (result.next()) {
						String editora = result.getString("editora");
						String autor = result.getString("autor");
						String titulo = result.getString("titulo");
						String subtitulo = result.getString("subtitulo");
						String genero = result.getString("genero");
						int quantidadeExemplar = result.getInt("quantidade_exemplar");

						Livro livro = new Livro(isbn, editora, autor, titulo, subtitulo, genero, quantidadeExemplar);
						
						return livro;
				} else {
						System.out.println("Nenhum livro encontrado com o ISBN: " + isbn);
				}

		} catch (SQLException e) {
				System.err.println("Erro ao consultar livro: " + e.getMessage());
		}
		return null;
}


}
