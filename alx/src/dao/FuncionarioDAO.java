package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Funcionario;

public class FuncionarioDAO {
    private static final String url = "jdbc:sqlite:library.db";

    public void salvarFuncionario(Funcionario funcionario) {
        String sqlPessoa = "INSERT INTO Pessoa (CPF, nome, sobrenome, senha) VALUES (?, ?, '', '')";
        String sqlFuncionario = "INSERT INTO Funcionario (ID_funcionario, e_admin) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmtPessoa = conn.prepareStatement(sqlPessoa,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                PreparedStatement pstmtFuncionario = conn.prepareStatement(sqlFuncionario)) {

            conn.setAutoCommit(false);

            // Inserir dados na tabela Pessoa
            pstmtPessoa.setString(1, funcionario.getCpf());
            pstmtPessoa.setString(2, funcionario.getNome());
            pstmtPessoa.executeUpdate();

            // Recuperar o ID gerado automaticamente
            ResultSet rs = pstmtPessoa.getGeneratedKeys();
            if (rs.next()) {
                long pessoaId = rs.getLong(1);

                // Inserir dados na tabela Funcionario
                pstmtFuncionario.setLong(1, pessoaId);
                pstmtFuncionario.setBoolean(2, funcionario.getEhAdministrador());
                pstmtFuncionario.executeUpdate();
            }

            conn.commit();
            System.out.println("Funcionário cadastrado com sucesso: " + funcionario.getNome());

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    public Funcionario buscaFuncionario(String cpf) {
        String sqlBuscaFuncionario = "SELECT p.CPF, p.nome, f.e_admin " +
                "FROM Pessoa p " +
                "JOIN Funcionario f ON p.ID = f.ID_funcionario " +
                "WHERE p.CPF = ?";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmtBuscaFuncionario = conn.prepareStatement(sqlBuscaFuncionario)) {

            pstmtBuscaFuncionario.setString(1, cpf);
            ResultSet rs = pstmtBuscaFuncionario.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                boolean ehAdmin = rs.getBoolean("e_admin");
                return new Funcionario(cpf, nome, ehAdmin);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário: " + e.getMessage());
        }
        return null;
    }
}
