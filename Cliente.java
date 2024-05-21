import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends Pessoa {
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<String[]> historico;

    public Cliente(String CPF, String nome, LocalDate dataNasc, String[] endereco, String senha) {
        super(CPF, nome, dataNasc, endereco, senha);
        this.historico = new ArrayList<>();
    }

    public ArrayList<String[]> getHistorico() {
        return historico;
    }

    public void adicionarHistorico(String ISBN, String idEmprestimo, LocalDate dataEmprestimo, int status) {
        String[] registro = {ISBN, idEmprestimo, dataEmprestimo.toString(), String.valueOf(status)};
        this.historico.add(registro);
    }
}
