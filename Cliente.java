import java.util.Date;

public class Cliente extends Pessoa {

	private Date dataCadastro;

	private Emprestimo[] emprestimo;

	public boolean podeEmprestar() {
		return false;
	}

	public void associaEmprestimo(Emprestimo emp) {

	}

}
