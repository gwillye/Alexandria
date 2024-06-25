package model.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Cliente;
import model.entity.Emprestimo;
import model.entity.Funcionario;
import model.entity.ItemDeEmprestimo;

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

				Cliente cliente = clienteDAO.buscaCliente(cpfCliente);
				Funcionario funcionario = funcionarioDAO.buscaFuncionario(cpfFuncionario);

				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setIdEmprestimo(idEmprestimo);
				emprestimo.setCliente(cliente);
				emprestimo.setFuncionario(funcionario);

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

			pstmtEmprestimo.setInt(1, emp.getCliente().getCodCliente()); // Supondo que Cliente tenha um método getCpf()
																			// para obter o CPF
			pstmtEmprestimo.setString(2, "21"); // Supondo que Funcionario tenha um método
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

	public Emprestimo buscaEmprestimoCliente(int idEmprestimo) {
		String sql = "SELECT e.ID AS emprestimo_id, " +
				"       c.ID_cliente AS cliente_id, " +
				"       p.ID AS pessoa_id, " +
				"       p.Nome AS cliente_nome, " +
				"       e.ID_funcionario " +
				"FROM Emprestimo e " +
				"INNER JOIN Cliente c ON e.ID_cliente = c.ID_cliente " +
				"INNER JOIN Pessoa p ON c.ID = p.ID " +
				"WHERE e.ID = ?";

		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, idEmprestimo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodCliente(rs.getInt("cliente_id"));
				cliente.setNome(rs.getString("cliente_nome"));

				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setIdEmprestimo(rs.getInt("emprestimo_id"));
				emprestimo.setCliente(cliente);

				return emprestimo;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar empréstimo: " + e.getMessage());
		}
		return null;
	}

	public int buscaProximoID() {
		String sql = "SELECT MAX(id) AS max_id FROM Emprestimo";

		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int maxId = rs.getInt("max_id");
				return maxId + 1;
			} else {

				return 1;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar próximo ID de empréstimo: " + e.getMessage());
			return -1;
		}
	}

	public void salvaItemDeEmprestimo(ItemDeEmprestimo idm) {
		// Implementar método para salvar item de empréstimo
	}

	public ItemDeEmprestimo buscaItemDeEmprestimo(int idItemEmp) {

		return null;
	}
}
