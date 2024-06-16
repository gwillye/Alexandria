import java.util.Date;

public class Pessoa {

	private String cpf;

	private String nome;

	private Date dataNasc;

	private int idade;

	private String endereco;

	private String senha;

	public Pessoa(String cpf, String nome, Date dataNasc, int idade, String Endereco, String senha){
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.idade =  idade;
		this.endereco = endereco;
		this.senha =  senha;
	}
	
	
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
