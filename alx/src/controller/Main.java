package controller;

import java.sql.SQLException;

import model.dao.EmprestimoDAO;
import model.entity.Cliente;
import model.entity.ItemDeEmprestimo;

public class Main {
    public static void main(String[] args) throws SQLException {
        Controladora cont = new Controladora();
        
        int idExemplar = 5;
        int idEmprestimo = 11;
        
        ItemDeEmprestimo item = cont.adicionarItem(idExemplar, idEmprestimo);

        item.toString();
    }
}
