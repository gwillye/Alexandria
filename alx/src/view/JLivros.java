package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.dao.LivroDAO;
import model.entity.Livro;
import view.ModeloTabelaLivro;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class JLivros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLivros jLivro;
	private ArrayList<Livro> livros;
	private ArrayList<Livro> livros1;
	private JLivros jLivros;

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

		this.jLivros = this;

		LivroDAO dao = new LivroDAO();

		try {
			livros = dao.listarLivro();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JCadastroLivro jCadastroLivro = new JCadastroLivro(null, jLivros);
				jCadastroLivro.setLocationRelativeTo(jCadastroLivro);
				jCadastroLivro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jCadastroLivro.setVisible(true);
			}
		});
		btnNewButton.setBounds(626, 440, 123, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 80, 705, 312);
		contentPane.add(scrollPane);

		ModeloTabelaLivro modeloTabela = new ModeloTabelaLivro(livros);

		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Livros Cadastrados");
		lblNewLabel.setBounds(49, 56, 155, 14);
		contentPane.add(lblNewLabel);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					try {
						Livro livroSelecionado = dao
								.consultaLivro(modeloTabela.getValueAt(table.getSelectedRow(), 0).toString());

						JCadastroLivro jCadastroLivro = new JCadastroLivro(livroSelecionado, jLivros);
						jCadastroLivro.setLocationRelativeTo(jCadastroLivro);
						jCadastroLivro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						jCadastroLivro.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

	}

}
