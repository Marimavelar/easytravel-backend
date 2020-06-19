package model;

import java.io.Serializable;

public class Avaliacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -713281904748340504L;
	private int idPacote;
	private int id;
	private int nota;
	private String comentario;

	public Avaliacao(int idPacote, int id, int nota, String comentario) {
		this.idPacote = idPacote;
		this.id = id;
		this.nota = nota;
		this.comentario = comentario;
	}

	public int getIdPacote() {
		return idPacote;
	}

	public void setIdPacote(int idPacote) {
		this.idPacote = idPacote;
	}

	public int getId() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}