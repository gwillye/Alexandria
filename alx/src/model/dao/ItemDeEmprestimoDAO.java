package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entity.Emprestimo;
import model.entity.Exemplar;
import model.entity.ItemDeEmprestimo;

public class ItemDeEmprestimoDAO {

    private static final String url = "jdbc:sqlite:library.db";

    // Método para listar todos os itens de empréstimo
    public List<ItemDeEmprestimo> listarItensDeEmprestimo() throws SQLException {
        List<ItemDeEmprestimo> itens = new ArrayList<>();
        String sql = "SELECT * FROM ItemDeEmprestimo";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idItemEmp = rs.getInt("idItemEmp");
                int idExemplar = rs.getInt("ID_exemplar"); // Nome da coluna no banco de dados
                int idEmprestimo = rs.getInt("ID_emprestimo"); // Nome da coluna no banco de dados
                Date dataEmprestimoItem = rs.getDate("data_emprestimo");
                Date dataDevolucao = rs.getDate("data_devolucao");
                Date dataPrevistaDevolucao = rs.getDate("data_prevista_devolucao");

                Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);
                Exemplar exemplar = buscarExemplarPorId(idExemplar);

                ItemDeEmprestimo item = new ItemDeEmprestimo(idItemEmp, emprestimo, exemplar,
                        dataDevolucao, dataPrevistaDevolucao, dataEmprestimoItem);
                itens.add(item);
            }
        }

        return itens;
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
