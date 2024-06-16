package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    private static final String URL = "jdbc:mysql://localhost:5432/alexandria";
    private static final String USER = "postgres"; // substitua pelo seu usuário do MySQL
    private static final String PASSWORD = "senha123"; // substitua pela sua senha do MySQL

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String createPessoaTable = "CREATE TABLE Pessoa (" +
                    "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "CPF BIGINT(11) NOT NULL," +
                    "nome VARCHAR(64) NOT NULL," +
                    "sobrenome VARCHAR(128) NOT NULL," +
                    "senha VARCHAR(32) NOT NULL" +
                    ");";

            String createEnderecoTable = "CREATE TABLE Endereco (" +
                    "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "ID_pessoa INTEGER," +
                    "numero_casa INTEGER," +
                    "CEP BIGINT," +
                    "complemento VARCHAR(256)," +
                    "FOREIGN KEY (ID_pessoa) REFERENCES Pessoa(ID)" +
                    ");";

            String createClienteTable = "CREATE TABLE Cliente (" +
                    "ID_cliente INTEGER PRIMARY KEY," +
                    "dataCadastro DATE," +
                    "FOREIGN KEY (ID_cliente) REFERENCES Pessoa(ID)" +
                    ");";

            String createFuncionarioTable = "CREATE TABLE Funcionario (" +
                    "ID_funcionario INTEGER PRIMARY KEY," +
                    "e_admin BOOLEAN," +
                    "FOREIGN KEY (ID_funcionario) REFERENCES Pessoa(ID)" +
                    ");";

            String createLivroTable = "CREATE TABLE Livro (" +
                    "ISBN VARCHAR(13) PRIMARY KEY," +
                    "editora VARCHAR(32)," +
                    "autor VARCHAR(128)," +
                    "titulo VARCHAR(128)," +
                    "subtitulo VARCHAR(128)," +
                    "genero VARCHAR(16)," +
                    "quantidade_exemplar INTEGER" +
                    ");";

            String createExemplarTable = "CREATE TABLE Exemplar (" +
                    "ID_exemplar INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "ISBN VARCHAR(13)," +
                    "edicao INTEGER," +
                    "setor VARCHAR(32)," +
                    "FOREIGN KEY (ISBN) REFERENCES Livro(ISBN)" +
                    ");";

            String createEmprestimoTable = "CREATE TABLE Emprestimo (" +
                    "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "ID_cliente INTEGER," +
                    "ID_funcionario INTEGER," +
                    "FOREIGN KEY (ID_cliente) REFERENCES Cliente(ID_cliente)," +
                    "FOREIGN KEY (ID_funcionario) REFERENCES Funcionario(ID_funcionario)" +
                    ");";

            String createItemEmprestimoTable = "CREATE TABLE ItemEmprestimo (" +
                    "ID_exemplar INTEGER," +
                    "ID_emprestimo INTEGER," +
                    "data_emprestimo DATE," +
                    "data_devolucao DATE," +
                    "data_prevista_devolucao DATE," +
                    "status INTEGER," +
                    "PRIMARY KEY (ID_exemplar, ID_emprestimo)," +
                    "FOREIGN KEY (ID_exemplar) REFERENCES Exemplar(ID_exemplar)," +
                    "FOREIGN KEY (ID_emprestimo) REFERENCES Emprestimo(ID)" +
                    ");";

            statement.executeUpdate(createPessoaTable);
            statement.executeUpdate(createEnderecoTable);
            statement.executeUpdate(createClienteTable);
            statement.executeUpdate(createFuncionarioTable);
            statement.executeUpdate(createLivroTable);
            statement.executeUpdate(createExemplarTable);
            statement.executeUpdate(createEmprestimoTable);
            statement.executeUpdate(createItemEmprestimoTable);

            System.out.println("Tabelas criadas com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
