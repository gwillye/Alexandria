import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Sistema {
    private Map<String, Livro> livros;
    private Map<String, Pessoa> usuarios;
    private Map<String, Emprestimo> emprestimos;

    public Sistema() {
        this.livros = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.emprestimos = new HashMap<>();
    }

    public Map<String, Livro> getLivros() {
        return livros;
    }

    public String adicionarUsuario(Pessoa usuario) {
        usuarios.put(usuario.getCPF(), usuario);
        return "Usuário " + usuario.getNome() + " adicionado com sucesso.";
    }
}
