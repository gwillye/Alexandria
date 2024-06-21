package controller;

import model.dao.ClienteDAO;
import model.dao.FuncionarioDAO;

import model.entity.Cliente;

import model.entity.Funcionario;


public class Main {

    // URL de conexão com o banco de dados SQLite
    static String url = "jdbc:sqlite:library.db";

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Controladora controladora = new Controladora();
        String cpfBuscaFuncionario = "12345678927";
        Funcionario funcionarioEncontrado = funcionarioDAO.buscaFuncionario(cpfBuscaFuncionario);

        if (funcionarioEncontrado != null) {
            System.out.println("Funcionário encontrado:");
            System.out.println("CPF: " + funcionarioEncontrado.getCpf());
            System.out.println("Nome: " + funcionarioEncontrado.getNome());
        } else {
            System.out.println("Funcionário com CPF " + cpfBuscaFuncionario + " não encontrado.");
        }

      
        String cpfBuscaCliente = "12345678901";

        Cliente clienteEncontrado = clienteDAO.buscaCliente(cpfBuscaCliente);

        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado: ");
            System.out.println("CPF: " + clienteEncontrado.getCpf());
            System.out.println("Nome: " + clienteEncontrado.getNome());
            System.out.println("Data Cadastro: " + clienteEncontrado.getDataCadastro());

            controladora.iniciarEmprestimo(clienteEncontrado);
        } else {
            System.out.println("Cliente com CPF " + cpfBuscaCliente + " não encontrado.");
        }

    
    }

}