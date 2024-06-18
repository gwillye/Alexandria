package controller;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.LivroDAO;
import model.Cliente;
import model.Emprestimo;
import model.Exemplar;
import model.Funcionario;
import model.Livro;

public class Main {

    // URL de conexão com o banco de dados SQLite
    static String url = "jdbc:sqlite:library.db";

    public static void main(String[] args) {

       //DatabaseSetup.setupDatabase();

        // Livros
        Livro livro1 = new Livro("12238-3-16-148410-0", "Editora ABC", "Autor A", "Todo mundo odeia Java",
                "From Basics to Advanced", "Programming", 50);
        Livro livro2 = new Livro("978-85-950545-2-7", "Editora XYZ", "Autor B", "Desvendando os Segredos do Python",
                "Aprenda a programar do zero", "Programação", 720);
        Livro livro3 = new Livro("3-446-39819-X", "Editora LTDA", "Autores C e D","Algoritmos e Estruturas de Dados em Java", "Fundamentos para Programadores", "Programação", 610);

        // Instância do LivroDAO
        LivroDAO livroDAO = new LivroDAO();

        // Cadastrar o livro
        livroDAO.salvarLivro(livro1);
        livroDAO.salvarLivro(livro2);
        livroDAO.salvarLivro(livro3);

        // Dados dos exemplares a serem cadastrados
        Exemplar exemplar1 = new Exemplar("Disponivel", 1, 101, 10, livro1);
        Exemplar exemplar2 = new Exemplar("Disponivel", 2, 102, 20, livro2);

        // Cadastrar os exemplares
        livroDAO.salvarExemplar(exemplar1);
        livroDAO.salvarExemplar(exemplar2);

        Cliente cliente1 = new Cliente("12345678901", "Ana");
        Cliente cliente2 = new Cliente("23456789012", "Bruno");
        Cliente cliente3 = new Cliente("34567890123", "Carla");

        // Instância do ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO();

        // Cadastrar os clientes
        clienteDAO.salvaCliente(cliente1);
        clienteDAO.salvaCliente(cliente2);
        clienteDAO.salvaCliente(cliente3);

        Funcionario funcionario1 = new Funcionario("45678901234", "David", "Gerente", true);
        Funcionario funcionario2 = new Funcionario("56789012345", "Eva", "Assistente", false);

        // Instância do FuncionarioDAO
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        // Cadastrar os funcionários
        funcionarioDAO.salvarFuncionario(funcionario1);
        funcionarioDAO.salvarFuncionario(funcionario2);

        // Buscar funcionários
        String cpfBuscaFuncionario = "45678901234";
        Funcionario funcionarioEncontrado = funcionarioDAO.buscaFuncionario(cpfBuscaFuncionario);

        if (funcionarioEncontrado != null) {
            System.out.println("Funcionário encontrado:");
            System.out.println("CPF: " + funcionarioEncontrado.getCpf());
            System.out.println("Nome: " + funcionarioEncontrado.getNome());
        } else {
            System.out.println("Funcionário com CPF " + cpfBuscaFuncionario + " não encontrado.");
        }

        // Buscar cliente para iniciar empréstimo
        String cpfBuscaCliente = "12345678901"; // CPF que você quer buscar

        Cliente clienteEncontrado = clienteDAO.buscaCliente(cpfBuscaCliente);

        Controladora controladora = new Controladora();

        // Verificar se cliente é apto para iniciar empréstimo
        controladora.iniciarEmprestimo(clienteEncontrado);

        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado: ");
            System.out.println("CPF: " + clienteEncontrado.getCpf());
            System.out.println("Nome: " + clienteEncontrado.getNome());
            System.out.println("Data Cadastro: " + clienteEncontrado.getDataCadastro());
        } else {
            System.out.println("Cliente com CPF " + cpfBuscaCliente + " não encontrado.");
        }

        // Simular registro de empréstimo utilizando o ID gerado pelo banco de dados
        int idEmprestimo = 1; // Suponha que o ID do empréstimo seja 1 (gerado pelo banco de dados)

        controladora.iniciarEmprestimo(cliente1); // Iniciar o empréstimo

        // Verificar se o empréstimo foi iniciado antes de registrar
        controladora.registrarEmprestimo(idEmprestimo, cliente1);
       
    }

}
