package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.Controladora;
import model.entity.Cliente;
import model.entity.Emprestimo;
import model.entity.ItemDeEmprestimo;

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
	private JTextArea textArea;

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

		controladora = new Controladora();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldCliente = new JTextField();
		textFieldCliente.setBounds(207, 110, 332, 20);
		contentPane.add(textFieldCliente);
		textFieldCliente.setColumns(10);

		JLabel lblNewLabel = new JLabel("CPF do cliente:");
		lblNewLabel.setBounds(86, 113, 103, 14);
		contentPane.add(lblNewLabel);

		textFieldLivro1 = new JTextField();
		textFieldLivro1.setBounds(207, 169, 332, 20);
		contentPane.add(textFieldLivro1);
		textFieldLivro1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID do Exemplar 1:");
		lblNewLabel_1.setBounds(86, 172, 103, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("ID do Exemplar 2:");
		lblNewLabel_1_1.setBounds(86, 200, 103, 14);
		contentPane.add(lblNewLabel_1_1);

		textFieldLivro3 = new JTextField();
		textFieldLivro3.setColumns(10);
		textFieldLivro3.setBounds(207, 228, 332, 20);
		contentPane.add(textFieldLivro3);

		JLabel lblNewLabel_1_2 = new JLabel("ID do Exemplar 3:");
		lblNewLabel_1_2.setBounds(86, 231, 103, 14);
		contentPane.add(lblNewLabel_1_2);

		textFieldLivro2 = new JTextField();
		textFieldLivro2.setColumns(10);
		textFieldLivro2.setBounds(207, 197, 332, 20);
		contentPane.add(textFieldLivro2);

		JLabel lblNewLabel_2 = new JLabel("Resultado:");
		lblNewLabel_2.setBounds(33, 324, 130, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Cadastro de Empréstimo");
		lblNewLabel_3.setBounds(272, 27, 168, 14);
		contentPane.add(lblNewLabel_3);

		JButton btnCliente = new JButton("+");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cpf = textFieldCliente.getText();
				Cliente cli = iniciaEmprestimo(cpf);

				if (cli != null) {
					String texto = "Empréstimo criado para o cliente " + cli.getNome();
					JOptionPane.showMessageDialog(null, texto, "Sucesso", JOptionPane.INFORMATION_MESSAGE);

					String cpfNome = cli.getCpf() + " (" + cli.getNome() + ")";
					textFieldCliente.setText(cpfNome);
					btnCliente.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não encontrado! Tente outro CPF.", "ERRO",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCliente.setBounds(549, 109, 41, 23);
		contentPane.add(btnCliente);

		JButton btnLivro1 = new JButton("+");
		btnLivro1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idExemplar = Integer.parseInt(textFieldLivro1.getText());
					ItemDeEmprestimo item = adicionarItem(idExemplar);

					textFieldLivro1.setText("Exemplar do livro: " + item.getExemplar().getLivro().getTitulo());
					btnLivro1.setEnabled(false);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para o ID do exemplar.",
							"ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLivro1.setBounds(549, 168, 41, 23);
		contentPane.add(btnLivro1);

		JButton btnLivro2 = new JButton("+");
		btnLivro2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idExemplar = Integer.parseInt(textFieldLivro2.getText());
					ItemDeEmprestimo item = adicionarItem(idExemplar);

					textFieldLivro2.setText("Exemplar do livro: " + item.getExemplar().getLivro().getTitulo());
					btnLivro2.setEnabled(false);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para o ID do exemplar.",
							"ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLivro2.setBounds(549, 196, 41, 23);
		contentPane.add(btnLivro2);

		JButton btnLivro3 = new JButton("+");
		btnLivro3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int idExemplar = Integer.parseInt(textFieldLivro3.getText());
					ItemDeEmprestimo item = adicionarItem(idExemplar);

					textFieldLivro3.setText("Exemplar do livro: " + item.getExemplar().getLivro().getTitulo());
					btnLivro3.setEnabled(false);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para o ID do exemplar.",
							"ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLivro3.setBounds(549, 227, 41, 23);
		contentPane.add(btnLivro3);

		JButton btnSave = new JButton("Salvar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imprimirResultado();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(103, 271, 89, 23);
		contentPane.add(btnSave);

		textFieldData = new JTextField();
		textFieldData.setColumns(10);
		textFieldData.setBounds(207, 79, 198, 20);
		contentPane.add(textFieldData);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(86, 82, 103, 14);
		contentPane.add(lblData);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(33, 349, 619, 207);
		contentPane.add(scrollPane);

	}

	private void imprimirResultado() throws SQLException {
		textArea.setText(controladora.imprimirResultado());
	}

	private Cliente iniciaEmprestimo(String cpf) {
		return controladora.iniciarEmprestimo(cpf);
	}

	private ItemDeEmprestimo adicionarItem(int idExemplar) {
		try {
			int idEmprestimo = controladora.buscaIdEmprestimo();
			return controladora.adicionarItem(idExemplar, idEmprestimo);
		} catch (Exception e) { // Captura todas as exceções
			JOptionPane.showMessageDialog(null, "Exemplar não cadastrado tente outro ID ", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

}
