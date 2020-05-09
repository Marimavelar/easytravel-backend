package model;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String nome;
	private String endereco;
	private String cpf;
	private String login;
	private String senha;
	private String email;
	private String dataDeNascimento;
	private String telefone;

	public User(int id, String nome, String endereco, String cpf, String login, String senha, String email,
			String dataDeNascimento, String telefone) {
		setId(id);
		setNome(nome);
		setEndereco(endereco);
		setCpf(cpf);
		setLogin(login);
		setSenha(senha);
		setEmail(email);
		setDataDeNascimento(dataDeNascimento);
		setTelefone(telefone);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void login(String login, String senha) {
		
	}
	
	public void cadastro() {
		
	}
}
