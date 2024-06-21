package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.entity.Cliente;

public class ClienteDAO {
  private static final String url = "jdbc:sqlite:library.db";
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  public Cliente buscaCliente(String cpf) {

    String sqlBuscaCliente = "SELECT p.CPF, p.nome, c.dataCadastro " +
    "FROM Pessoa p " +
    "JOIN Cliente c ON p.ID = c.ID_cliente " +
    "WHERE p.CPF = ?";

    try (Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmtBuscaPessoa = conn.prepareStatement(sqlBuscaCliente)) {

      pstmtBuscaPessoa.setString(1, cpf);
      ResultSet result = pstmtBuscaPessoa.executeQuery();

      if (result.next()) {
        String nome = result.getString("nome");
        String cpfString = result.getString("cpf");
        String dataString = result.getString("dataCadastro");

        Cliente cliente = new Cliente(cpfString, nome);

        Date dataCadastro = null;
        try {
            dataCadastro = dateFormat.parse(dataString);
        } catch (ParseException e) {
            System.err.println("Erro ao converter data: " + e.getMessage());
        }
        cliente.setDataCadastro(dataCadastro);
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

      long pessoaId = pstmtPessoa.getGeneratedKeys().getLong(1);

      pstmtCliente.setLong(1, pessoaId);
      pstmtCliente.executeUpdate();

      conn.commit();
      System.out.println("Cliente cadastrado com sucesso: " + cli.getNome());

    } catch (SQLException e) {
      System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
    }
  }
}
