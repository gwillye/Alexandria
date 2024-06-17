package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;
import model.Emprestimo;

public class ClienteDAO {
  private static final String url = "jdbc:sqlite:library.db";

  public Cliente buscaCliente(String cpf) {
    String sqlBuscaPessoa = "SELECT p.CPF, p.nome, c.dataCadastro " +
        "FROM Pessoa p " +
        "INNER JOIN Cliente c ON p.ID = c.ID_cliente " +
        "WHERE p.CPF = ?";
    try (Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmtBuscaPessoa = conn.prepareStatement(sqlBuscaPessoa)) {

      pstmtBuscaPessoa.setString(1, cpf);
      ResultSet rsBusca = pstmtBuscaPessoa.executeQuery();

      if (rsBusca.next()) {
        Cliente cliente = new Cliente(rsBusca.getString("CPF"), rsBusca.getString("nome"));
        cliente.setDataCadastro(rsBusca.getDate("dataCadastro"));

        return cliente;
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar cliente: " + e.getMessage());
    }
    return null;
  }

	public void salvaCliente(Cliente cli) {
		String sqlPessoa = "INSERT INTO Pessoa (CPF, nome, sobrenome, senha) VALUES (?, ?, '', '')";
    String sqlCliente = "INSERT INTO Cliente (ID_cliente, dataCadastro) VALUES (?, ?)";

    try (Connection conn = DriverManager.getConnection(url);
          PreparedStatement pstmtPessoa = conn.prepareStatement(sqlPessoa);
          PreparedStatement pstmtCliente = conn.prepareStatement(sqlCliente)) {

        conn.setAutoCommit(false);

        // Inserir dados na tabela Pessoa
        pstmtPessoa.setString(1, cli.getCpf());
        pstmtPessoa.setString(2, cli.getNome());
        pstmtPessoa.executeUpdate();

        // Recuperar o ID gerado automaticamente
        long pessoaId = pstmtPessoa.getGeneratedKeys().getLong(1);

        // Inserir dados na tabela Cliente
        pstmtCliente.setLong(1, pessoaId);
        pstmtCliente.executeUpdate();

        conn.commit();
        System.out.println("Cliente cadastrado com sucesso: " + cli.getNome());

    } catch (SQLException e) {
        System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
    }
  }
}
