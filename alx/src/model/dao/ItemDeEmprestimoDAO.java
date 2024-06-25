package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getExemplar().getIdExemplar());
            pstmt.setInt(2, item.getEmprestimo().getIdEmprestimo());
            pstmt.setString(3, dateFormat.format(item.getDataEmprestimoItem()));
            pstmt.setString(4, dateFormat.format(item.getDataPrevistaDevolucao()));
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
