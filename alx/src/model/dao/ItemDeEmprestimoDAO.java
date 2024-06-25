package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import model.entity.Emprestimo;
import model.entity.Exemplar;
import model.entity.ItemDeEmprestimo;

public class ItemDeEmprestimoDAO {

    private static final String url = "jdbc:sqlite:library.db";

    public ItemDeEmprestimo buscaItemDeEmprestimo(int idItemEmprestimo) throws SQLException {
        String sql = "SELECT * FROM ItemEmprestimo WHERE id_item_emprestimo = ?";
        ItemDeEmprestimo itemDeEmprestimo = null;

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idItemEmprestimo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idExemplar = rs.getInt("ID_exemplar");
                int idEmprestimo = rs.getInt("ID_emprestimo");
                String dataEmprestimo = rs.getString("data_emprestimo");
                String dataDevolucao = rs.getString("data_devolucao");
                String dataPrevistaDevolucao = rs.getString("data_prevista_devolucao");
                int status = rs.getInt("status");

                Exemplar exemplar = buscarExemplarPorId(idExemplar);
                Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dataEmprestimoDate = dataEmprestimo != null ? dateFormat.parse(dataEmprestimo) : null;
                Date dataDevolucaoDate = dataDevolucao != null ? dateFormat.parse(dataDevolucao) : null;
                Date dataPrevistaDevolucaoDate = dataPrevistaDevolucao != null ? dateFormat.parse(dataPrevistaDevolucao)
                        : null;

                if (exemplar != null && emprestimo != null) {
                    itemDeEmprestimo = new ItemDeEmprestimo(emprestimo, exemplar,
                            dataDevolucaoDate, dataPrevistaDevolucaoDate, dataEmprestimoDate,
                            status);
                }
            }
        } catch (SQLException | ParseException e) {
            System.err.println("Erro ao buscar item de empréstimo: " + e.getMessage());
        }

        return itemDeEmprestimo;
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

    public List<ItemDeEmprestimo> buscarItensPorEmprestimo(int idEmprestimo) throws SQLException {
        String sql = "SELECT * FROM ItemEmprestimo WHERE ID_emprestimo = ?";
        List<ItemDeEmprestimo> itensDeEmprestimo = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEmprestimo);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int idExemplar = rs.getInt("ID_exemplar");
                int idDeEmprestimo = rs.getInt("ID_emprestimo");
                String dataEmprestimo = rs.getString("data_emprestimo");
                String dataDevolucao = rs.getString("data_devolucao");
                String dataPrevistaDevolucao = rs.getString("data_prevista_devolucao");
                int status = rs.getInt("status");

                Exemplar exemplar = buscarExemplarPorId(idExemplar);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dataEmprestimoDate = dataEmprestimo != null ? dateFormat.parse(dataEmprestimo) : null;
                Date dataDevolucaoDate = dataDevolucao != null ? dateFormat.parse(dataDevolucao) : null;
                Date dataPrevistaDevolucaoDate = dataPrevistaDevolucao != null ? dateFormat.parse(dataPrevistaDevolucao)
                        : null;

                Emprestimo emprestimo = buscarEmprestimoPorId(idDeEmprestimo);

                if (exemplar != null && emprestimo != null) {
                    ItemDeEmprestimo itemDeEmprestimo = new ItemDeEmprestimo(emprestimo, exemplar,
                            dataDevolucaoDate, dataPrevistaDevolucaoDate, dataEmprestimoDate, status);
                    itensDeEmprestimo.add(itemDeEmprestimo);
                }
            }
        } catch (SQLException | ParseException e) {
            System.err.println("Erro ao buscar itens de empréstimo: " + e.getMessage());
        }

        return itensDeEmprestimo;
    }

    public Emprestimo buscarEmprestimoPorId(int idEmprestimo) throws SQLException {
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        return emprestimoDAO.buscaEmprestimo(idEmprestimo);
    }

    public Exemplar buscarExemplarPorId(int idExemplar) throws SQLException {
        ExemplarDAO exemplarDAO = new ExemplarDAO();
        return exemplarDAO.buscarExemplar(idExemplar);
    }
}
