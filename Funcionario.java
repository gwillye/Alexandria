import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class Servidor extends Pessoa {
    private final String idFuncionario;
    private final String login;
    @SuppressWarnings("FieldMayBeFinal")
    private boolean eAdmin;
    private Map<String, Cliente> clientes;
    private Map<String, Emprestimo> emprestimos;

    public Servidor(String CPF, String nome, LocalDate dataNasc, String[] endereco, String senha, String idFuncionario, String login, boolean eAdmin) {
        super(CPF, nome, dataNasc, endereco, senha);
        this.idFuncionario = idFuncionario;
        this.login = login;
        this.eAdmin = eAdmin;
        this.clientes = new HashMap<>();
        this.emprestimos = new HashMap<>();
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public String getLogin() {
        return login;
    }

    public boolean iseAdmin() {
        return eAdmin;
    }

    public String criarEmprestimo(String ID, String ISBN, LocalDate dataEmp) {
        if (getLivro(ISBN).getExemplar() > 0) {
            Emprestimo novoEmprestimo = new Emprestimo(ID, ISBN, dataEmp, this);
            emprestimos.put(ID, novoEmprestimo);
            return "Empréstimo criado com sucesso.";
        } else {
            return "Livro indisponível para empréstimo.";
        }
    }

    public Emprestimo getEmprestimo(String ID) {
        return emprestimos.get(ID);
    }

    public String setDataDevolucao(String ID, LocalDate dataDevolucao) {
        if (emprestimos.containsKey(ID)) {
            Emprestimo emprestimo = emprestimos.get(ID);
            emprestimo.setDataDevolucao(dataDevolucao);
            return "Data de devolução atualizada com sucesso.";
        } else {
            return "Empréstimo não encontrado.";
        }
    }

    public String criarCliente(String CPF, String nome, LocalDate data_nasc, String[] endereco, String senha) {
        if (!clientes.containsKey(CPF)) {
            Cliente novoCliente = new Cliente(CPF, nome, data_nasc, endereco, senha);
            clientes.put(CPF, novoCliente);
            return "Cliente com CPF " + CPF + " criado com sucesso.";
        } else {
            return "Cliente já existe.";
        }
    }

    public String excluirCliente(String CPF, String senha) {
        if (clientes.containsKey(CPF)) {
            Cliente cliente = clientes.get(CPF);
            if (cliente.verificaSenha(senha)) {
                clientes.remove(CPF);
                return "Cliente com CPF " + CPF + " excluído com sucesso.";
            } else {
                return "Senha incorreta.";
            }
        } else {
            return "Cliente não encontrado.";
        }
    }

    public String atualizarCliente(String CPF, String senha, Map<String, Object> campos) {
        if (clientes.containsKey(CPF)) {
            Cliente cliente = clientes.get(CPF);
            if (cliente.verificaSenha(senha)) {
                campos.forEach(cliente::atualizarCampo);
                return "Cliente com CPF " + CPF + " atualizado com sucesso.";
            } else {
                return "Senha incorreta.";
            }
        } else {
            return "Cliente não encontrado.";
        }
    }
}
