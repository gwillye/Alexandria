package controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.dao.ClienteDAO;
import model.dao.EmprestimoDAO;
import model.dao.ExemplarDAO;
import model.dao.ItemDeEmprestimoDAO;
import model.entity.Cliente;
import model.entity.Emprestimo;
import model.entity.Exemplar;
import model.entity.Funcionario;
import model.entity.ItemDeEmprestimo;

public class Controladora {
    private EmprestimoDAO emprestimoDAO;
    private ExemplarDAO exemplarDAO;
    private ItemDeEmprestimoDAO itemDeEmprestimoDAO;
    private ClienteDAO clienteDAO;

    public Controladora() {
        this.emprestimoDAO = new EmprestimoDAO();
        this.exemplarDAO = new ExemplarDAO();
        this.itemDeEmprestimoDAO = new ItemDeEmprestimoDAO();
        this.clienteDAO = new ClienteDAO();
    }

    public ItemDeEmprestimo adicionarItem(int idExemplar, int idEmprestimo) throws Exception {
        Exemplar exemplar = exemplarDAO.buscarExemplar(idExemplar);
        if (exemplar == null) {
            throw new Exception("Exemplar não encontrado com ID: " + idExemplar);
        }

        Emprestimo emprestimo = emprestimoDAO.buscaEmprestimo(idEmprestimo);
        if (emprestimo == null) {
            throw new Exception("Empréstimo não encontrado com ID: " + idEmprestimo);
        }

        ItemDeEmprestimo item = emprestimo.adicionarItem(exemplar, emprestimo);
        if (item == null) {
            throw new Exception("Falha ao adicionar item de empréstimo.");
        }

        exemplar.setStatus(0);
        itemDeEmprestimoDAO.salvarItemDeEmprestimo(item);
        exemplarDAO.atualizarExemplar(exemplar);

        return item;
    }

    public int buscaIdEmprestimo() {
        return emprestimoDAO.buscaProximoID() - 1;
    }

    public Cliente iniciarEmprestimo(String cpf) {
        Cliente cli = null;
        try {
            cli = clienteDAO.buscaCliente(cpf);
            if (cli == null) {
                System.err.println("Cliente não encontrado para o CPF: " + cpf);
                return null;
            }

            boolean apto = cli.podeEmprestar();

            if (apto) {
                Date dataHoraAtual = new Date();
                int idEmprestimo = emprestimoDAO.buscaProximoID();

                Emprestimo novoEmprestimo = new Emprestimo(idEmprestimo, dataHoraAtual, cli);
                novoEmprestimo.setStatus("Iniciado");

                emprestimoDAO.salvaEmprestimo(novoEmprestimo);
                System.out.println("Empréstimo iniciado para o cliente: " + cli.getNome());
            } else {
                System.out.println("Cliente não está apto para iniciar um novo empréstimo.");
            }
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
        return cli;
    }

    public String imprimirResultado() throws SQLException {
        int idEmprestimo = buscaIdEmprestimo();
        Emprestimo emprestimo = emprestimoDAO.buscaEmprestimo(idEmprestimo);
        List<ItemDeEmprestimo> itensDeEmprestimo = itemDeEmprestimoDAO.buscarItensPorEmprestimo(idEmprestimo);

        if (emprestimo != null) {
            Cliente cliente = emprestimo.getCliente();

            Funcionario funcionario = emprestimo.getFuncionario();

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Empréstimo ID: %d\nCliente: %s\nFuncionário: %s\n",
                    emprestimo.getIdEmprestimo(),
                    cliente != null ? cliente.getNome() : "N/A",

                    funcionario != null ? funcionario.getNome() : "N/A"));

            sb.append("Detalhes dos Itens de Empréstimo:\n");
            for (ItemDeEmprestimo item : itensDeEmprestimo) {
                Exemplar exemplar = item.getExemplar();

                sb.append(
                        String.format("\n Exemplar: %s   ",
                                exemplar != null ? exemplar.getLivro().getTitulo() : "N/A"));
                sb.append(
                        String.format("\n Id: %s   ", exemplar != null ? exemplar.getIdExemplar() : "N/A"));
                sb.append(String.format("Status do Exemplar: %s   ", exemplar.getStatus()));
                sb.append(String.format("Status: %s   ", item.getStatus()));
                sb.append(String.format("Data Prevista de Devolução: %s   ", item.getDataPrevistaDevolucao()));
                sb.append("\n");
            }

            return sb.toString();
        } else {
            return "Empréstimo não encontrado.";
        }
    }

}
