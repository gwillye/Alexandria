package controller;

import view.JCadastroLivro;
import view.JEmprestimos;
import view.JLivros;
import model.entity.Livro;
import javax.swing.JFrame;

public class ControladoraView {

    private JLivros jLivros;
    private JEmprestimos jEmprestimos;
    private JCadastroLivro jCadastroLivro;

    public ControladoraView() {
        this.jLivros = new JLivros(this);
        this.jLivros.setVisible(true);
    }

    public void showCadastroLivro(Livro livroSelecionado) {
        jCadastroLivro = new JCadastroLivro(livroSelecionado, jLivros);
        jCadastroLivro.setVisible(true);
    }

    public void showEmprestimos() {
        jEmprestimos = new JEmprestimos();
        jEmprestimos.setVisible(true);
    }

    public void showLivros() {
        if (jLivros == null) {
            jLivros = new JLivros(this);
        }
        jLivros.setVisible(true);
    }

    public void closeView(JFrame view) {
        view.dispose();
    }
}
