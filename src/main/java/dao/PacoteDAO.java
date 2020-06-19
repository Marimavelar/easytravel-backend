package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

import model.Pacote;

public class PacoteDAO{

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
		pacotes = new List();
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
		try{
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
		List <String> linhasArquivo = new List();
		String linha;

		BufferedReader br = new BufferedReader(new FileReader(caminhoDoArquivo));

		while(br.ready()){
			linha = br.readLine();
			
			if(!linha.isBlank())
				linhasArquivo.add(linha);
		}
		br.close();

		return linhasArquivo;
	} 

	// Cria um vetor de objetos a partir do arquivo ".txt" lido
	// Cada posicao do vetor possui um objeto do tipo do arquivo lido (ex.: municipio, partido politico, etc)
	//@params {String} caminho - caminho do arquivo; {Object} objeto - tipo do objeto que estah guardado no arquivo
	public static Object leituraDosDados(String caminho, Class<?> cls) throws IOException {
		List <String> linhasArquivo = lerLinhasArquivo(caminho);

		List <Pacote> pacotes = new ArrayList(); 

			for(int i=0; i<linhasArquivo.size(); i++) {

				String vetorString[] = linhasArquivo.get(i).
				pacotes.add(new Pacote()) = new PartidoPolitico(vetorString[0],vetorString[1]);
				linha = aux.prox.objeto.toString();
			}
			return partidosPoliticos;

}

if(cls.getName().equals(UrnaEletronica.class.getName())) {

	UrnaEletronica [] urna = new UrnaEletronica[linhasArquivo.getTamanho()];

	Celula aux = linhasArquivo.inicio;
	String linha = aux.objeto.toString();

	for(int i=0; i<linhasArquivo.getTamanho(); i++) {

		String vetorString[] = linha.split(";");
		urna[i] = new UrnaEletronica (vetorString[0], Integer.parseInt(vetorString[1]), Integer.parseInt(vetorString[2]));
		aux = aux.prox;

		if(aux!=null) {

			linha = aux.objeto.toString();

		}
	}

	return urna;

}