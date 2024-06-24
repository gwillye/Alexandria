package controller;

import model.dao.EmprestimoDAO;
import model.entity.Cliente;

public class Main {
    public static void main(String[] args) {
        Controladora cont = new Controladora();
        String cpf = "12345678901";
        Cliente cliente = cont.iniciarEmprestimo(cpf);

        System.out.println("Próximo ID disponível: " + cliente.getNome());

    }
}
