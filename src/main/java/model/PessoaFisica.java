package model;

public class PessoaFisica extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5734508096213593712L;
	private String nome;
	private String cpf;
	private String dataDeNascimento;
	
	public PessoaFisica(int id, Endereco endereco, String login, String senha, String email, String telefone, String nome, String cpf, String dataDeNascimento){
		super(id, endereco, login, senha, email, telefone);
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
