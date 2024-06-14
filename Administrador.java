import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Administrador extends Funcionario {
    private Map<String, Funcionario> funcionariosMap;
    private Map<String, Administrador> administradores;

    public Administrador(String CPF, String nome, LocalDate dataNasc, String[] endereco, String senha, String idFuncionario, String login) {
        super(CPF, nome, dataNasc, endereco, senha, idFuncionario, login, true);
        this.servidores = new HashMap<>();
        this.administradores = new HashMap<>();
    }

    public String criarFuncionario(String CPF, String nome, LocalDate data_nasc, String[] endereco, String senha, String idFuncionario, String login) {
        if (!servidores.containsKey(idFuncionario)) {
            Funcionario novoFuncionario = new Funcionario(CPF, nome, data_nasc, endereco, senha, idFuncionario, login);
            servidores.put(idFuncionario, novoFuncionario);
            return "Funcionário com ID " + idFuncionario + " criado com sucesso.";
        } else {
            return "Funcionário já existe.";
        }
    }

    public String excluirFuncionario(String idFuncionario) {
        if (servidores.containsKey(idFuncionario)) {
            servidores.remove(idFuncionario);
            return "Funcionário com ID " + idFuncionario + " excluído com sucesso.";
        } else {
            return "Funcionário não encontrado.";
        }
    }

    public String atualizarFuncionario(String idFuncionario, Map<String, Object> campos) {
        if (servidores.containsKey(idFuncionario)) {
            Funcionario funcionario = servidores.get(idFuncionario);
            campos.forEach(funcionario::atualizarCampo);
            return "Funcionário com ID " + idFuncionario atualizado com sucesso.";
        } else {
            return "Funcionário não encontrado.";
        }
    }

    public String criarAdministrador(String CPF, String nome, LocalDate data_nasc, String[] endereco, String senha, String idFuncionario, String login) {
        if (!administradores.containsKey(idFuncionario)) {
            Administrador novoAdmin = new Administrador(CPF, nome, data_nasc, endereco, senha, idFuncionario, login);
            administradores.put(idFuncionario, novoAdmin);
            return "Administrador com ID " + idFuncionario + " criado com sucesso.";
        } else {
            return "Administrador já existe.";
        }
    }

    public String excluirAdministrador(String idFuncionario) {
        if (administradores.containsKey(idFuncionario)) {
            administradores.remove(idFuncionario);
            return "Administrador com ID " + idFuncionario + " excluído com sucesso.";
        } else {
            return "Administrador não encontrado.";
        }
    }

    public String atualizarAdministrador(String idFuncionario, Map<String, Object> campos) {
        if (administradores.containsKey(idFuncionario)) {
            Administrador admin = administradores.get(idFuncionario);
            campos.forEach(admin::atualizarCampo);
            return "Administrador com ID " + idFuncionario atualizado com sucesso.";
        } else {
            return "Administrador não encontrado.";
        }
    }

    public String criarLivro(String ISBN, String titulo, String edicao, int setor, int exemplar, String autor, String genero) {
        if (!getLivros().containsKey(ISBN)) {
            Livro novoLivro = new Livro(ISBN, titulo, edicao, setor, exemplar, autor, genero);
            getLivros().put(ISBN, novoLivro);
            return "Livro com ISBN " + ISBN + " criado com sucesso.";
        } else {
            return "Livro com ISBN " + ISBN + " já existe.";
        }
    }

    public String excluirLivro(String ISBN) {
        if (getLivros().containsKey(ISBN)) {
            getLivros().remove(ISBN);
            return "Livro com ISBN " + ISBN + " excluído com sucesso.";
        } else {
            return "Livro não encontrado.";
        }
    }

    public String atualizarLivro(String ISBN, Map<String, Object> campos) {
        if (getLivros().containsKey(ISBN)) {
            Livro livro = getLivros().get(ISBN);
            campos.forEach(livro::atualizarCampo);
            return "Livro com ISBN " + ISBN + " atualizado com sucesso.";
        } else {
            return "Livro não encontrado.";
        }
    }
}
