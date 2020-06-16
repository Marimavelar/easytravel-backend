package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class AvaliacaoDAO {
	private List<Avaliacao> avaliacoes;
	private int maxId = 0;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public int getMaxId() {
		return maxId;
	}

	public AvaliacaoDAO(String filename) throws IOException {

		file = new File(filename);
		users = new ArrayList<Avaliacao>();
		if (file.exists()) {
			readFromFile();
		}
	}

	public void add(Avaliacao avaliacao) {
		try {
			avaliacoes.add(avaliacao);
			this.maxId = (avaliacao.getId() > this.maxId) ? avaliacao.getId() : this.maxId;
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar avaliação " + avaliacao.getId() + " no disco!");
		}
	}

	public Avaliacao get(int id) {
		for (Avaliacao avaliacao : avaliacoes) {
			if (id == avaliacao.getId()) {
				return avaliacao;
			}
		}
		return null;
    }
    
    // To Do: Retornar lista de avaliações
    public Avaliacao getByIdPacote(int idPacote) {
		for (Avaliacao avaliacao : avaliacoes) {
			if (idPacote == avaliacao.getIdPacote()) {
				return avaliacao;
			}
		}
		return null;
	}

	public void update(Avaliacao avaliacao) {
		int index = avaliacoes.indexOf(avaliacao);
		if (index != -1) {
			avaliacoes.set(index, avaliacao);
			this.saveToFile();
		}
	}

	public void remove(Avaliacao avaliacoes) {
		int index = avaliacoes.indexOf(u);
		if (index != -1) {
			avaliacoes.remove(index);
			this.saveToFile();
		}
	}

	public List<Avaliacao> getAll() {
		return avaliacoes;
	}

	private List<Avaliacao> readFromFile() {
		avaliacoes.clear();
		Avaliacao avaliacao = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				avaliacao = (Avaliacao) inputFile.readObject();
				avaliacoes.add(avaliacao);
				maxId = (avaliacao.getId() > maxId) ? avaliacao.getId() : maxId;
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar avaliação no disco!");
			e.printStackTrace();
		}
		return avaliacoes;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Avaliacao avaliacao : avaliacoes) {
				outputFile.writeObject(avaliacao);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar avaliação no disco!");
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			this.saveToFile();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar a base de dados no disco!");
			e.printStackTrace();
		}
	}
}
