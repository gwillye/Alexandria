package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import model.dao.LivroDAO;
import model.entity.Livro;

public class JCadastroLivro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private JTextField textFieldISBN;
	private JTextField textFieldAutor;
	private JTextField textFieldEditora;
	private JLabel lblNewLabel_2;
	private JTextField textFieldGenero;
	private JTextField textFieldSubtitulo;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLivros jLivro;

	public JCadastroLivro(Livro livroSelecionado, JLivros jLivro) {
		this.jLivro = jLivro;
		LivroDAO dao = new LivroDAO();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Alterado para DISPOSE_ON_CLOSE

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Titulo");
		lblNewLabel.setBounds(22, 11, 45, 14);
		contentPane.add(lblNewLabel);

		textFieldTitulo = new JTextField();
		textFieldTitulo.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldTitulo.setBounds(22, 24, 390, 20);
		contentPane.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Autor");
		lblNewLabel_1.setBounds(223, 114, 62, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("ISBN");
		lblNewLabel_1_1.setBounds(22, 113, 62, 14);
		contentPane.add(lblNewLabel_1_1);

		textFieldISBN = new JTextField();
		textFieldISBN.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldISBN.setBounds(22, 127, 189, 20);
		contentPane.add(textFieldISBN);
		textFieldISBN.setColumns(10);

		textFieldAutor = new JTextField();
		textFieldAutor.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(223, 127, 189, 20);
		contentPane.add(textFieldAutor);

		textFieldEditora = new JTextField();
		textFieldEditora.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldEditora.setColumns(10);
		textFieldEditora.setBounds(22, 183, 189, 20);
		contentPane.add(textFieldEditora);

		lblNewLabel_2 = new JLabel("Subtitulo");
		lblNewLabel_2.setBounds(22, 55, 62, 14);
		contentPane.add(lblNewLabel_2);

		textFieldGenero = new JTextField();
		textFieldGenero.setColumns(10);
		textFieldGenero.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldGenero.setBounds(223, 183, 189, 20);
		contentPane.add(textFieldGenero);

		JLabel lblGnero = new JLabel("Gênero");
		lblGnero.setBounds(224, 169, 45, 14);
		contentPane.add(lblGnero);

		textFieldSubtitulo = new JTextField();
		textFieldSubtitulo.setColumns(10);
		textFieldSubtitulo.setBorder(new LineBorder(new Color(171, 173, 179)));
		textFieldSubtitulo.setBounds(20, 67, 390, 20);
		contentPane.add(textFieldSubtitulo);

		JLabel lblNewLabel_1_1_1 = new JLabel("Editora");
		lblNewLabel_1_1_1.setBounds(22, 169, 62, 14);
		contentPane.add(lblNewLabel_1_1_1);

		btnNewButton = new JButton(livroSelecionado == null ? "Adicionar" : "Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Livro livro = new Livro(textFieldISBN.getText(), textFieldEditora.getText(), textFieldAutor.getText(),
						textFieldTitulo.getText(), textFieldSubtitulo.getText(), textFieldGenero.getText(), 1);

				if (livroSelecionado == null) {
					dao.salvarLivro(livro);

				} else {
					dao.atualizarLivro(livro);
				}

				fecharECarregarJLivros();
			}
		});

		btnNewButton.setBounds(314, 224, 89, 23);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirLivro(livroSelecionado.getIsbn());
				fecharECarregarJLivros();
			}
		});
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBounds(32, 224, 89, 23);
		btnNewButton_1.setVisible(false);
		contentPane.add(btnNewButton_1);

		if (livroSelecionado != null) {
			btnNewButton_1.setVisible(true);
			textFieldAutor.setText(livroSelecionado.getAutor());
			textFieldGenero.setText(livroSelecionado.getGenero());
			textFieldTitulo.setText(livroSelecionado.getTitulo());
			textFieldSubtitulo.setText(livroSelecionado.getSubtitulo());
			textFieldEditora.setText(livroSelecionado.getEditora());
			textFieldISBN.setText(livroSelecionado.getIsbn());
		}
	}

	private void fecharECarregarJLivros() {
		dispose();

	}
}
