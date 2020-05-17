package model;

public class PessoaJuridica extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7495027717525634381L;
	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;
	
	public PessoaJuridica(int id, Endereco endereco, String login, String senha, String email, String telefone, String cnpj, String razaoSocial, String nomeFantasia){
		super(id, endereco, login, senha, email, telefone);
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
}
