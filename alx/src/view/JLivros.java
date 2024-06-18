package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.LivroDAO;
import model.Livro;
import model.ModeloTabelaLivro;

import javax.swing.JScrollPane;
import java.awt.Color;

public class JLivros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JLivros jLivro;
	private ArrayList<Livro> livros;
	private ArrayList<Livro> livros1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLivros frame = new JLivros();
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
	public JLivros() {
		
		this.jLivro = this;
		
		LivroDAO dao = new LivroDAO();
		
		try {
			livros = dao.listarLivro();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		livros1 = new ArrayList<>();
		
		
		livros1.add(new Livro("12238-3-16-148410-0", "Editora ABC", "Autor A", "Todo mundo odeia Java","From Basics to Advanced", "Programming", 50));
		
		//livros.add(new Livro("978-85-950545-2-7", "Editora XYZ", "Autor B", "Desvendando os Segredos do Python","Aprenda a programar do zero", "Programação", 720));
		//livros.add(new Livro("3-446-39819-X", "Editora LTDA", "Autores C e D","Algoritmos e Estruturas de Dados em Java", "Fundamentos para Programadores", "Programação", 610));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastroLivro jCadastro = new JCadastroLivro( jLivro);
				jCadastro.setLocationRelativeTo(jCadastro);
				jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jCadastro.setVisible(true);
			}
		});
		btnNewButton.setBounds(51, 53, 123, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(199, 54, 518, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 112, 636, 330);
		contentPane.add(scrollPane);
		
		ModeloTabelaLivro modeloTabela = new ModeloTabelaLivro(livros);
		
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);
		
	}
}
