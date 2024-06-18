package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;
import model.Emprestimo;
import model.Funcionario;
import model.ItemDeEmprestimo;

public class EmprestimoDAO {
	private static final String url = "jdbc:sqlite:library.db";
	private ClienteDAO clienteDAO;
	private FuncionarioDAO funcionarioDAO;

	public EmprestimoDAO() {
		this.clienteDAO = new ClienteDAO();
		this.funcionarioDAO = new FuncionarioDAO();
	}

	public Emprestimo buscaEmprestimo(int idEmprestimo) {
		String sqlBuscaEmprestimo = "SELECT * FROM Emprestimo WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmtBuscaEmprestimo = conn.prepareStatement(sqlBuscaEmprestimo)) {

			pstmtBuscaEmprestimo.setInt(1, idEmprestimo);
			ResultSet rs = pstmtBuscaEmprestimo.executeQuery();

			if (rs.next()) {
				String cpfCliente = rs.getString("id_cliente");
				String cpfFuncionario = rs.getString("id_funcionario");

				// Buscar cliente e funcionário pelos CPFs utilizando seus respectivos DAOs
				Cliente cliente = clienteDAO.buscaCliente(cpfCliente);
				Funcionario funcionario = funcionarioDAO.buscaFuncionario(cpfFuncionario);

				// Criar objeto Emprestimo com os dados do banco
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setIdEmprestimo(idEmprestimo);
				emprestimo.setCliente(cliente);
				emprestimo.setFuncionario(funcionario);
				// Não há necessidade de definir data_emprestimo, data_devolucao,
				// data_vencimento e status aqui,
				// pois esses dados não existem na tabela Emprestimo

				return emprestimo;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar empréstimo: " + e.getMessage());
		}
		return null;
	}

	public void salvaEmprestimo(Emprestimo emp) {
		String sqlEmprestimo = "INSERT INTO Emprestimo (id_cliente, id_funcionario) " +
				"VALUES (?, ?)";

		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmtEmprestimo = conn.prepareStatement(sqlEmprestimo,
						Statement.RETURN_GENERATED_KEYS)) {

			pstmtEmprestimo.setString(1, emp.getCliente().getCpf()); // Supondo que Cliente tenha um método getCpf()
																		// para obter o CPF
			pstmtEmprestimo.setString(2, emp.getFuncionario().getCpf()); // Supondo que Funcionario tenha um método
																			// getCpf() para obter o CPF

			int rowsAffected = pstmtEmprestimo.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet generatedKeys = pstmtEmprestimo.getGeneratedKeys();
				if (generatedKeys.next()) {
					int idEmprestimo = generatedKeys.getInt(1);
					emp.setIdEmprestimo(idEmprestimo);
					System.out.println("Empréstimo salvo com sucesso. ID gerado: " + idEmprestimo);
				}
			} else {
				System.out.println("Falha ao salvar empréstimo, nenhum ID gerado.");
			}

		} catch (SQLException e) {
			System.err.println("Erro ao salvar empréstimo: " + e.getMessage());
		}
	}

	public void salvaItemDeEmprestimo(ItemDeEmprestimo idm) {
		// Implementar método para salvar item de empréstimo
	}

	public ItemDeEmprestimo buscaItemDeEmprestimo(int idItemEmp) {
		// Implementar método para buscar item de empréstimo
		return null;
	}
}
