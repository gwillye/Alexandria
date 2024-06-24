package controller;

import model.dao.EmprestimoDAO;

public class Main {
    public static void main(String[] args) {
        // Cria uma instância do EmprestimoDAO
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        // Chama o método buscaProximoCod e imprime o resultado
        int proximoId = emprestimoDAO.buscaProximoID();

        // Verifica se houve algum erro ao buscar o próximo ID
        if (proximoId != -1) {
            System.out.println("Próximo ID disponível: " + proximoId);
        } else {
            System.out.println("Erro ao buscar o próximo ID.");
        }
    }
}
