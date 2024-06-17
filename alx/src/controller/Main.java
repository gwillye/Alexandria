package controller;
import dao.ClienteDAO;
import dao.LivroDAO;
import model.Cliente;
import model.Livro;

public class Main {
    
    // URL de conexão com o banco de dados SQLite
    static String url = "jdbc:sqlite:library.db";

    public static void main(String[] args) {
        
    
        DatabaseSetup.setupDatabase();
        
        // Livros
        Livro livro1 = new Livro("12238-3-16-148410-0", "Editora ABC", "Autor A", "Todo mundo odeia Java", "From Basics to Advanced", "Programming", 50);
        Livro livro2 = new Livro("978-85-950545-2-7", "Editora XYZ", "Autor B","Desvendando os Segredos do Python", "Aprenda a programar do zero","Programação", 720);
        Livro livro3 = new Livro("3-446-39819-X", "Editora LTDA", "Autores C e D","Algoritmos e Estruturas de Dados em Java", "Fundamentos para Programadores","Programação", 610);

        // Instância do LivroDAO
        LivroDAO livroDAO = new LivroDAO();
        LivroDAO livroDAO2 = new LivroDAO();
        LivroDAO livroDAO3 = new LivroDAO();

        // Cadastrar o livro
        livroDAO.salvarLivro(livro1);
        livroDAO2.salvarLivro(livro2);
        livroDAO3.salvarLivro(livro3);

        Cliente cliente1 = new Cliente("12345678901", "Ana");
        Cliente cliente2 = new Cliente("23456789012", "Bruno");
        Cliente cliente3 = new Cliente("34567890123", "Carla");

        // Instância do ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();

        // Cadastrar os clientes
        clienteDAO.salvaCliente(cliente1);
        clienteDAO.salvaCliente(cliente2);
        clienteDAO.salvaCliente(cliente3);
        

        
        
    }

       
}
