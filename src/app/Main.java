package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import util.DatabaseSetup;

public class Main {
    
    // URL de conexão com o banco de dados SQLite
    static String url = "jdbc:sqlite:library.db";

    public static void main(String[] args) {
        
    
        DatabaseSetup.setupDatabase();

        cadastrarLivro("2222222", "JANE", "Livia", "Trabalho FInal", "Dor", "Terror", 10);
    }

        public static void cadastrarLivro(String isbn, String editora, String autor, String titulo, String subtitulo, String genero, int quantidadeExemplar) {
        String sql = "INSERT INTO Livro (ISBN, editora, autor, titulo, subtitulo, genero, quantidade_exemplar) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, isbn);
            pstmt.setString(2, editora);
            pstmt.setString(3, autor);
            pstmt.setString(4, titulo);
            pstmt.setString(5, subtitulo);
            pstmt.setString(6, genero);
            pstmt.setInt(7, quantidadeExemplar);

            pstmt.executeUpdate();
            System.out.println("Livro cadastrado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }
}
