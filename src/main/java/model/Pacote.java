package model;

import java.util.Date;

public class Pacote {
	private int id;
	private String destino;
	private Date dataPartida;
	private Date dataRetorno;
	private double preco;
	private int quantidadePessoas;
	private String atividades;
	private String meioTransporte;
	private String hospedagem;
	private String nome;
	private String tipoCompanhia;
	private String tipoAtividade;
	private String motivoViagem;

	public Pacote(int id, String nome, String destino, Date dataPartida, Date dataRetorno, int quantidadePessoas,
			String atividades, String meioTransporte, String hospedagem, String tipoCompanhia, String tipoAtividade,
			String motivoViagem, double preco) {
		this.id = id;
		this.nome = nome;
		this.destino = destino;
		this.dataPartida = dataPartida;
		this.dataRetorno = dataRetorno;
		this.quantidadePessoas = quantidadePessoas;
		this.atividades = atividades;
		this.meioTransporte = meioTransporte;
		this.hospedagem = hospedagem;
		this.tipoCompanhia = tipoCompanhia;
		this.tipoAtividade = tipoAtividade;
		this.motivoViagem = motivoViagem;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(int quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	public String getMeioTransporte() {
		return meioTransporte;
	}

	public void setMeioTransporte(String meioTransporte) {
		this.meioTransporte = meioTransporte;
	}

	public String getHospedagem() {
		return hospedagem;
	}

	public void setHospedagem(String hospedagem) {
		this.hospedagem = hospedagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoCompanhia() {
		return tipoCompanhia;
	}

	public void setTipoCompanhia(String tipoCompanhia) {
		this.tipoCompanhia = tipoCompanhia;
	}

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(String tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public String getMotivoViagem() {
		return motivoViagem;
	}

	public void setMotivoViagem(String motivoViagem) {
		this.motivoViagem = motivoViagem;
	}
}