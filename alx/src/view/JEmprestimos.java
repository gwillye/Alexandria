package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.Controladora;
import model.entity.Emprestimo;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class JEmprestimos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCliente;
	private JTextField textFieldLivro1;
	private JTextField textFieldLivro3;
	private JTextField textFieldLivro2;
	private JTextField textFieldData;
	private Controladora controladora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JEmprestimos frame = new JEmprestimos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			 	
		});
		
		
	}
	

	/**
	 * Create the frame.
	 */
	public JEmprestimos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setBounds(137, 107, 332, 20);
		contentPane.add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF do cliente:");
		lblNewLabel.setBounds(29, 110, 90, 14);
		contentPane.add(lblNewLabel);
		
		textFieldLivro1 = new JTextField();
		textFieldLivro1.setBounds(137, 166, 332, 20);
		contentPane.add(textFieldLivro1);
		textFieldLivro1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ISBN do livro 1:");
		lblNewLabel_1.setBounds(33, 169, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ISBN do livro 2:");
		lblNewLabel_1_1.setBounds(33, 197, 86, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textFieldLivro3 = new JTextField();
		textFieldLivro3.setColumns(10);
		textFieldLivro3.setBounds(137, 225, 332, 20);
		contentPane.add(textFieldLivro3);
		
		JLabel lblNewLabel_1_2 = new JLabel("ISBN do livro 3:");
		lblNewLabel_1_2.setBounds(33, 228, 86, 14);
		contentPane.add(lblNewLabel_1_2);
		
		textFieldLivro2 = new JTextField();
		textFieldLivro2.setColumns(10);
		textFieldLivro2.setBounds(137, 194, 332, 20);
		contentPane.add(textFieldLivro2);
		
		
		JLabel lblNewLabel_2 = new JLabel("Resultado:");
		lblNewLabel_2.setBounds(33, 321, 130, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setBounds(33, 268, 89, 23);
		contentPane.add(btnSave);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(33, 346, 487, 149);
		
		textArea.setEditable(false);
       
       
        contentPane.add(textArea);
        
        JLabel lblNewLabel_3 = new JLabel("Cadastro de Empréstimo");
        lblNewLabel_3.setBounds(202, 24, 168, 14);
        contentPane.add(lblNewLabel_3);
        
        JButton btnLivro1 = new JButton("+");
        btnLivro1.setBounds(479, 165, 41, 23);
        contentPane.add(btnLivro1);
        
        JButton btnLivro2 = new JButton("+");
        btnLivro2.setBounds(479, 193, 41, 23);
        contentPane.add(btnLivro2);
        
        JButton btnLivro3 = new JButton("+");
        btnLivro3.setBounds(479, 224, 41, 23);
        contentPane.add(btnLivro3);

        //textFieldData.setText(emp.retornaData());
        
        JButton btnCliente = new JButton("+");
        btnCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					String cpf = textFieldCliente.getText();
					//Cliente cliente = controladora.iniciarEmprestimo(cpf);
					//JOptionPane.showMessageDialog(null, cpf, "cpf do cliente", JOptionPane.INFORMATION_MESSAGE);
				
				
				
					}
				});
        btnCliente.setBounds(479, 106, 41, 23);
        contentPane.add(btnCliente);
        
        textFieldData = new JTextField();
        textFieldData.setColumns(10);
        textFieldData.setBounds(137, 76, 198, 20);
        contentPane.add(textFieldData);
        
        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(29, 79, 90, 14);
        contentPane.add(lblData);
        
       
        
      
        
	}
	
	
}
