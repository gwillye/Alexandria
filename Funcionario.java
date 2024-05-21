import java.time.LocalDate;

public class Funcionario extends Servidor {
    public Funcionario(String CPF, String nome, LocalDate dataNasc, String[] endereco, String senha, String idFuncionario, String login) {
        super(CPF, nome, dataNasc, endereco, senha, idFuncionario, login, false);
    }
}
