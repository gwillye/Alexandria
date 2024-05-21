import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public abstract class Pessoa {
    private final String CPF;
    private final String nome;
    private final LocalDate dataNasc;
    private final int idade;
    private final String[] endereco;
    private final String senha;
    private Map<String, Livro> livros;

    public Pessoa(String CPF, String nome, LocalDate dataNasc, String[] endereco, String senha) {
        this.CPF = CPF;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.idade = calcularIdade(dataNasc);
        this.endereco = endereco;
        this.senha = hashSenha(senha);
        this.livros = new HashMap<>();
    }

    private int calcularIdade(LocalDate dataNasc) {
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }

    private String hashSenha(String senha) {
        return Integer.toHexString(senha.hashCode());
    }

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public int getIdade() {
        return idade;
    }

    public String[] getEndereco() {
        return endereco;
    }

    public String getSenha() {
        return senha;
    }

    public Livro getLivro(String ISBN) {
        return livros.get(ISBN);
    }

    public String consultaLivro(String ISBN) {
        if (livros.containsKey(ISBN)) {
            return livros.get(ISBN).getLivro();
        } else {
            return "Livro não encontrado";
        }
    }
}
