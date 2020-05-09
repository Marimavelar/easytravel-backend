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

public class UserDAO {
	private List<User> usuarios;
	private int maxId = 0;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public int getMaxId() {
		return maxId;
	}

	public UserDAO(String filename) throws IOException {

		file = new File(filename);
		usuarios = new ArrayList<User>();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(User user) {
		try {
			usuarios.add(user);
			this.maxId = (user.getId() > this.maxId) ? user.getId() : this.maxId;
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o usuario '" + user.getLogin() + "' no disco!");
		}
	}

	public User get(int id) {
		for (User usuario : usuarios) {
			if (id == usuario.getId()) {
				return usuario;
			}
		}
		return null;
	}
	
	public User getByLoginAndPassword(String login, String senha) {
		for (User usuario : usuarios) {
			if (login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
				return usuario;
			}
		}
		return null;
	}

	public void update(User u) {
		int index = usuarios.indexOf(u);
		if (index != -1) {
			usuarios.set(index, u);
			this.saveToFile();
		}
	}

	public void remove(User u) {
		int index = usuarios.indexOf(u);
		if (index != -1) {
			usuarios.remove(index);
			this.saveToFile();
		}
	}

	public List<User> getAll() {
		return usuarios;
	}

	private List<User> readFromFile() {
		usuarios.clear();
		User usuario = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				usuario = (User) inputFile.readObject();
				usuarios.add(usuario);
				maxId = (usuario.getId() > maxId) ? usuario.getId() : maxId;
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar usuario no disco!");
			e.printStackTrace();
		}
		return usuarios;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (User usuario : usuarios) {
				outputFile.writeObject(usuario);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar usuario no disco!");
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
