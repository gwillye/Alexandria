package controller;

import model.dao.EmprestimoDAO;
import model.entity.Cliente;
import model.entity.ItemDeEmprestimo;

public class Main {
    public static void main(String[] args) throws Exception {
        Controladora cont = new Controladora();

        int idExemplar = 15;
        int idEmprestimo = 11;

        ItemDeEmprestimo item = cont.adicionarItem(idExemplar, idEmprestimo);

        item.toString();
    }
}
