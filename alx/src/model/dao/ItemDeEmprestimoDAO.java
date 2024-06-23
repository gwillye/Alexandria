package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.entity.Emprestimo;
import model.entity.Exemplar;
import model.entity.ItemDeEmprestimo;

public class ItemDeEmprestimoDAO {

    private static final String url = "jdbc:sqlite:library.db";

    // Método para buscar um item de empréstimo por ID
    public ItemDeEmprestimo buscaItemDeEmprestimo(int idItemEmprestimo) throws SQLException {
        String sql = "SELECT * FROM ItemEmprestimo WHERE ID_exemplar = ?";
        ItemDeEmprestimo item = null;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idItemEmprestimo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idExemplar = rs.getInt("id_exemplar");
                int idEmprestimo = rs.getInt("id_emprestimo");
                Date dataEmprestimoItem = rs.getDate("data_emprestimo");
                Date dataDevolucao = rs.getDate("data_devolucao");
                Date dataPrevistaDevolucao = rs.getDate("data_prevista_devolucao");

                Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);
                Exemplar exemplar = buscarExemplarPorId(idExemplar);

                item = new ItemDeEmprestimo(idItemEmprestimo, emprestimo, exemplar,
                        dataDevolucao, dataPrevistaDevolucao, dataEmprestimoItem);
            }
        }

        return item;
    }

    public void salvarItemDeEmprestimo(ItemDeEmprestimo item) throws SQLException {
        String sql = "INSERT INTO ItemDeEmprestimo (ID_exemplar, ID_emprestimo, data_emprestimo, data_devolucao, data_prevista_devolucao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getExemplar().getIdExemplar());
            pstmt.setInt(2, item.getEmprestimo().getIdEmprestimo());
            pstmt.setDate(3, new java.sql.Date(item.getDataEmprestimoItem().getTime()));
            pstmt.setDate(4, new java.sql.Date(item.getDataDevolucao().getTime()));
            pstmt.setDate(5, new java.sql.Date(item.getDataPrevistaDevolucao().getTime()));

            pstmt.executeUpdate();
            System.out.println("Item de empréstimo cadastrado com sucesso");
        }
    }

    private Emprestimo buscarEmprestimoPorId(int idEmprestimo) throws SQLException {
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        return emprestimoDAO.buscaEmprestimo(idEmprestimo);
    }

    private Exemplar buscarExemplarPorId(int idExemplar) throws SQLException {
        ExemplarDAO exemplarDAO = new ExemplarDAO();
        return exemplarDAO.buscarExemplar(idExemplar);
    }
}
