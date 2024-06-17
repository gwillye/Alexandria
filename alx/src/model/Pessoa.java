package model;

import java.util.Date;

public class Pessoa {
  
  private String cpf;

	private String nome;

	private Date dataNasc;

	private String endereco;

	private String senha;

	public Pessoa(String cpf, String nome, Date dataNasc, String Endereco, String senha, String endereco){
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.endereco = endereco;
		this.setSenha(senha);
	}
	
	
	public Pessoa(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
