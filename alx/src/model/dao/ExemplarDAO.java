package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Exemplar;
import model.entity.Livro;

public class ExemplarDAO {
    private static final String url = "jdbc:sqlite:library.db";
    private LivroDAO livroDAO;

    public ExemplarDAO() {
        this.livroDAO = new LivroDAO(); // Inicialização de LivroDAO
    }

    public Exemplar buscarExemplar(int idExemplar) {

        String sql = "SELECT * FROM Exemplar WHERE id_exemplar = ?";
        Exemplar exemplar = null;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idExemplar);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String isbn = rs.getString("ISBN");
                int edicao = rs.getInt("edicao");
                int setor = rs.getInt("setor");

                Livro livro = livroDAO.consultaLivro(isbn);

                exemplar = new Exemplar(idExemplar, livro, edicao, setor);

            } else {
                System.out.println("Nenhum exemplar encontrado com o ID: " + idExemplar);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar exemplar: " + e.getMessage());
        }

        return exemplar;
    }

}
