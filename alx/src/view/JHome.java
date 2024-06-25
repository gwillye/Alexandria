package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.Controladora;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JHome frame = new JHome();
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
	public JHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(354, 58, 202, 294);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Gerenciar Livros");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLivros jLivro = new JLivros();
				jLivro.setLocationRelativeTo(jLivro);
				jLivro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jLivro.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 91, 182, 34);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Novo Empréstimo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEmprestimos jEmprestimo = new JEmprestimos();
				jEmprestimo.setLocationRelativeTo(jEmprestimo);
				jEmprestimo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jEmprestimo.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 161, 182, 34);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Home");
		lblNewLabel.setBounds(86, 28, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(JHome.class.getResource("/view/Screenshot_2.png")));
		lblNewLabel_1.setBounds(-15, 58, 368, 302);
		contentPane.add(lblNewLabel_1);
	}
}
