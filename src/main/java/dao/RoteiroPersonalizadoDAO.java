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

import model.RoteiroPersonalizado;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RoteiroPersonalizadoDAO {

	private List<RoteiroPersonalizado> roteirosPersonalizados;
	private int maxId = 0;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public int getMaxId() {
		return maxId;
	}

	public RoteiroPersonalizadoDAO(String filename) throws IOException {

		file = new File(filename);
		roteirosPersonalizados = new ArrayList();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(RoteiroPersonalizado roteiroPersonalizado) {
		try {
			roteirosPersonalizados.add(roteiroPersonalizado);
			this.maxId = (roteiroPersonalizado.getId() > this.maxId) ? roteiroPersonalizado.getId() : this.maxId;
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o Roteiro Personalizado no disco!");
		}
	}

	public RoteiroPersonalizado get(int id) {
		for (RoteiroPersonalizado roteiroPersonalizado : roteirosPersonalizados) {
			if (id == roteiroPersonalizado.getId()) {
				return roteiroPersonalizado;
			}
		}
		return null;
	}

	public void update(RoteiroPersonalizado roteiroPersonalizado) {
		int index = roteirosPersonalizados.indexOf(roteiroPersonalizado);
		if (index != -1) {
			roteirosPersonalizados.set(index, roteiroPersonalizado);
			this.saveToFile();
		}
	}

	public void remove(RoteiroPersonalizado roteiroPersonalizado) {
		int index = roteirosPersonalizados.indexOf(roteiroPersonalizado);
		if (index != -1) {
			roteirosPersonalizados.remove(index);
			this.saveToFile();
		}
	}

	public List<RoteiroPersonalizado> getAll() {
		return roteirosPersonalizados;
	}

	private List<RoteiroPersonalizado> readFromFile() {
		roteirosPersonalizados.clear();
		RoteiroPersonalizado roteiroPersonalizado = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream inputFile = new ObjectInputStream(fis);

			while (fis.available() > 0) {
				roteiroPersonalizado = (RoteiroPersonalizado) inputFile.readObject();
				roteirosPersonalizados.add(roteiroPersonalizado);
				maxId = (roteiroPersonalizado.getId() > maxId) ? roteiroPersonalizado.getId() : maxId;
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar roteiro no disco!");
			e.printStackTrace();
		}
		return roteirosPersonalizados;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (RoteiroPersonalizado roteiroPersonalizado : roteirosPersonalizados) {
				outputFile.writeObject(roteiroPersonalizado);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar roteiro no disco!");
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

			if (!linha.isBlank())
				linhasArquivo.add(linha);
		}
		br.close();

		return linhasArquivo;
	}
	
	public static Object leituraRoteiroPersonalizado(String caminho, Class<?> cls)
			throws IOException, NumberFormatException, ParseException {
		List <String> linhasArquivo = new ArrayList();
		linhasArquivo = lerLinhasArquivo(caminho);

		List <RoteiroPersonalizado> roteirosPersonalizados = new ArrayList(); 

			for(int i=0; i<linhasArquivo.size(); i++) {

				String vetorString[] = linhasArquivo.get(i).split(";");
				roteirosPersonalizados.add(
						new RoteiroPersonalizado(
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
			return roteirosPersonalizados;
	}
}