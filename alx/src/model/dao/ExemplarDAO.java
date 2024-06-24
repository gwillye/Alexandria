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
                int id = rs.getInt("id_exemplar");
                String isbn = rs.getString("ISBN");
                int edicao = rs.getInt("edicao");
                String setor = rs.getString("setor");
                int status = rs.getInt("status");

                Livro livro = livroDAO.consultaLivro(isbn);

                exemplar = new Exemplar(idExemplar, livro, edicao, setor, status);

            } else {
                System.out.println("Nenhum exemplar encontrado com o ID: " + idExemplar);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar exemplar: " + e.getMessage());
        }

        return exemplar;
    }

    public Exemplar buscarExemplarLivroPorISBN(String isbn) {
        String sql = "SELECT * FROM Exemplar WHERE ISBN = ? AND status = 1 LIMIT 1";
        Exemplar exemplar = null;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, isbn);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idExemplar = rs.getInt("id_exemplar");
                int edicao = rs.getInt("edicao");
                int setor = rs.getInt("setor");

                // Assume that livroDAO.consultaLivro(String isbn) returns a Livro object
                Livro livro = livroDAO.consultaLivro(isbn);

                exemplar = new Exemplar(idExemplar, livro, edicao, setor);
            } else {
                System.out.println("Nenhum exemplar encontrado com o ISBN: " + isbn + " e status 1");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar exemplar: " + e.getMessage());
        }

        return exemplar;
    }

    public void atualizarExemplar(Exemplar exemplar) {
        String sql = "UPDATE Exemplar SET status = ? WHERE ID_exemplar = ?";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, exemplar.getStatus());
            pstmt.setInt(2, exemplar.getIdExemplar());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Exemplar atualizado com sucesso: " + exemplar.getIdExemplar());
            } else {
                System.out.println("Nenhum exemplar encontrado com o id: " + exemplar.getIdExemplar());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar exemplar: " + e.getMessage());
        }
    }

}
