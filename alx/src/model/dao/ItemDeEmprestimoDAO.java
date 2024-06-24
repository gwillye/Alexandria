package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.Emprestimo;
import model.entity.Exemplar;
import model.entity.ItemDeEmprestimo;

public class ItemDeEmprestimoDAO {

    private static final String url = "jdbc:sqlite:library.db";

    public ItemDeEmprestimo buscaItemDeEmprestimo(int idItemEmprestimo) throws SQLException {

        return null;
    }

    public void salvarItemDeEmprestimo(ItemDeEmprestimo item) throws SQLException {
        String sql = "INSERT INTO ItemEmprestimo (ID_exemplar, ID_emprestimo, data_emprestimo, data_prevista_devolucao, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getExemplar().getIdExemplar());
            pstmt.setInt(2, item.getEmprestimo().getIdEmprestimo());
            pstmt.setDate(3, new java.sql.Date(item.getDataEmprestimoItem().getTime()));
            pstmt.setDate(4, new java.sql.Date(item.getDataPrevistaDevolucao().getTime()));
            pstmt.setInt(5, item.getStatus());

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
