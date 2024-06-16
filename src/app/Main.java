package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    static String url = "jdbc:sqlite:library.db";

    public static void main(String[] args) {
        // URL de conexão com o banco de dados SQLite
    
        // Código SQL para criar as tabelas
        String[] sqls = {
            "CREATE TABLE IF NOT EXISTS Pessoa (" +
            "    ID INTEGER PRIMARY KEY," +
            "    CPF TEXT NOT NULL," +
            "    nome TEXT NOT NULL," +
            "    sobrenome TEXT NOT NULL," +
            "    senha TEXT NOT NULL" +
            ");",
            "CREATE TABLE IF NOT EXISTS Endereco (" +
            "    ID INTEGER PRIMARY KEY," +
            "    ID_pessoa INTEGER," +
            "    numero_casa INTEGER," +
            "    CEP TEXT," +
            "    complemento TEXT," +
            "    FOREIGN KEY (ID_pessoa) REFERENCES Pessoa(ID)" +
            ");",
            "CREATE TABLE IF NOT EXISTS Cliente (" +
            "    ID_cliente INTEGER PRIMARY KEY," +
            "    dataCadastro DATE," +
            "    FOREIGN KEY (ID_cliente) REFERENCES Pessoa(ID)" +
            ");",
            "CREATE TABLE IF NOT EXISTS Funcionario (" +
            "    ID_funcionario INTEGER PRIMARY KEY," +
            "    e_admin BOOLEAN," +
            "    FOREIGN KEY (ID_funcionario) REFERENCES Pessoa(ID)" +
            ");",
            "CREATE TABLE IF NOT EXISTS Livro (" +
            "    ISBN TEXT PRIMARY KEY," +
            "    editora TEXT," +
            "    autor TEXT," +
            "    titulo TEXT," +
            "    subtitulo TEXT," +
            "    genero TEXT," +
            "    quantidade_exemplar INTEGER" +
            ");",
            "CREATE TABLE IF NOT EXISTS Exemplar (" +
            "    ID_exemplar INTEGER PRIMARY KEY," +
            "    ISBN TEXT," +
            "    edicao INTEGER," +
            "    setor TEXT," +
            "    FOREIGN KEY (ISBN) REFERENCES Livro(ISBN)" +
            ");",
            "CREATE TABLE IF NOT EXISTS Emprestimo (" +
            "    ID INTEGER PRIMARY KEY," +
            "    ID_cliente INTEGER," +
            "    ID_funcionario INTEGER," +
            "    FOREIGN KEY (ID_cliente) REFERENCES Cliente(ID_cliente)," +
            "    FOREIGN KEY (ID_funcionario) REFERENCES Funcionario(ID_funcionario)" +
            ");",
            "CREATE TABLE IF NOT EXISTS ItemEmprestimo (" +
            "    ID_exemplar INTEGER," +
            "    ID_emprestimo INTEGER," +
            "    data_emprestimo DATE," +
            "    data_devolucao DATE," +
            "    data_prevista_devolucao DATE," +
            "    status INTEGER," +
            "    PRIMARY KEY (ID_exemplar, ID_emprestimo)," +
            "    FOREIGN KEY (ID_exemplar) REFERENCES Exemplar(ID_exemplar)," +
            "    FOREIGN KEY (ID_emprestimo) REFERENCES Emprestimo(ID)" +
            ");"
        };

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            // Criar as tabelas
            for (String sql : sqls) {
                stmt.execute(sql);
                System.out.println("Tabela criada com sucesso.");
            }

            System.out.println("Todas as tabelas foram criadas.");

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }

        cadastrarLivro("2222222", "JANE", "Livia", "Trabalho FInal", "Dor", "Terror", 10);
    }

        public static void cadastrarLivro(String isbn, String editora, String autor, String titulo, String subtitulo, String genero, int quantidadeExemplar) {
        String sql = "INSERT INTO Livro (ISBN, editora, autor, titulo, subtitulo, genero, quantidade_exemplar) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, isbn);
            pstmt.setString(2, editora);
            pstmt.setString(3, autor);
            pstmt.setString(4, titulo);
            pstmt.setString(5, subtitulo);
            pstmt.setString(6, genero);
            pstmt.setInt(7, quantidadeExemplar);

            pstmt.executeUpdate();
            System.out.println("Livro cadastrado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }
}
