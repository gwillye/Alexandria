package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        initialize();
    }

    public void initialize() {
        setTitle("Main Frame");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Adiciona a operação de fechar
        setLocationRelativeTo(null); // Centraliza o frame na tela
        setVisible(true); // Torna o frame visível
    }

    public static void main(String[] args) {
        // Criar e exibir o frame na thread de despacho de eventos
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
