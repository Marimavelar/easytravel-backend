package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

import model.Pacote;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PacoteDAO {

	private List<Pacote> pacotes;
	private int maxId = 0;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public int getMaxId() {
		return maxId;
	}

	public PacoteDAO(String filename) throws IOException {

		file = new File(filename);
		pacotes = new ArrayList();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(Pacote pacote) {
		try {
			pacotes.add(pacote);
			this.maxId = (pacote.getId() > this.maxId) ? pacote.getId() : this.maxId;
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o pacote '" + pacote.getNome() + "' no disco!");
		}
	}

	public Pacote get(int id) {
		for (Pacote pacote : pacotes) {
			if (id == pacote.getId()) {
				return pacote;
			}
		}
		return null;
	}

	public void update(Pacote pacote) {
		int index = pacotes.indexOf(pacote);
		if (index != -1) {
			pacotes.set(index, pacote);
			this.saveToFile();
		}
	}

	public void remove(Pacote pacote) {
		int index = pacotes.indexOf(pacote);
		if (index != -1) {
			pacotes.remove(index);
			this.saveToFile();
		}
	}

	public List<Pacote> getAll() {
		return pacotes;
	}

	private List<Pacote> readFromFile() {
		pacotes.clear();
		Pacote pacote = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream inputFile = new ObjectInputStream(fis);

			while (fis.available() > 0) {
				pacote = (Pacote) inputFile.readObject();
				pacotes.add(pacote);
				maxId = (pacote.getId() > maxId) ? pacote.getId() : maxId;
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar pacote no disco!");
			e.printStackTrace();
		}
		return pacotes;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Pacote pacote : pacotes) {
				outputFile.writeObject(pacote);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar pacote no disco!");
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

	public static List lerLinhasArquivo(String caminhoDoArquivo) throws IOException {
		List<String> linhasArquivo = new ArrayList();
		String linha;

		BufferedReader br = new BufferedReader(new FileReader(caminhoDoArquivo));

		while (br.ready()) {
			linha = br.readLine();

			if (linha != null)
				linhasArquivo.add(linha);
		}
		br.close();

		return linhasArquivo;
	}

	public static Object leituraDosDados(String caminho, Class<?> cls)
			throws IOException, NumberFormatException, ParseException {
		List <String> linhasArquivo = new ArrayList();
		linhasArquivo = lerLinhasArquivo(caminho);

		List <Pacote> pacotes = new ArrayList(); 

			for(int i=0; i<linhasArquivo.size(); i++) {

				String vetorString[] = linhasArquivo.get(i).split(";");
				pacotes.add(
						new Pacote(
								Integer.parseInt(vetorString[0]),
								vetorString[1],
								vetorString[2],
								new SimpleDateFormat("dd-MM-yyyy").parse(vetorString[3]),
								new SimpleDateFormat("dd-MM-yyyy").parse(vetorString[4]),
								Integer.parseInt(vetorString[5]),
								vetorString[6],
								vetorString[7],
								vetorString[8],
								vetorString[9],
								vetorString[10],
								vetorString[11],
								Double.parseDouble(vetorString[12]))
						);
			}
			return pacotes;
	}
}