import java.util.Date;

public class Cliente extends Pessoa {

	private Date dataCadastro;

	private Emprestimo[] emprestimo;
	
	public Cliente(String cpf, String nome, Date dataNasc, int idade, String Endereco, String senha, Date dataCadastro,
			Emprestimo[] emprestimo) {
		super(cpf, nome, dataNasc, idade, Endereco, senha);
		this.dataCadastro = dataCadastro;
		this.emprestimo = emprestimo;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Emprestimo[] getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo[] emprestimo) {
		this.emprestimo = emprestimo;
	}

	public boolean podeEmprestar() {
		return false;
	}

	public void associaEmprestimo(Emprestimo emp) {

	}

}
