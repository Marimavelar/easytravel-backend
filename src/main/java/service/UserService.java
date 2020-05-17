package service;

import java.io.IOException;
import dao.UserDAO;
import model.Endereco;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.User;
import spark.Request;
import spark.Response;

public class UserService {

	private UserDAO userDAO;

	public UserService() {
		try {
			userDAO = new UserDAO("usuario.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object login(Request request, Response response) {

		String login = request.queryParams("login");
		String senha = request.queryParams("senha");

		User user = (User) userDAO.getByLoginAndPassword(login, senha);

		if (user != null) {
			response.header("Content-Type", "application/xml");
			response.header("Content-Encoding", "UTF-8");

			return "Success";
		} else {
			response.status(404); // 404 Not found
			return "Usuario " + login + " não foi encontrado.";
		}
	}

	public Object add(Request request, Response response) {
		int id = userDAO.getMaxId() + 1;
		
		String login = request.queryParams("login");
		String senha = request.queryParams("senha");
		String email = request.queryParams("email");
		String telefone = request.queryParams("telefone");

		String logradouro = request.queryParams("endereco[logradouro]");
		int numero = Integer.parseInt(request.queryParams("endereco[numero]"));
		String complemento = request.queryParams("endereco[complemento]");
		String bairro = request.queryParams("endereco[bairro]");
		String cidade = request.queryParams("endereco[cidade]");
		String uf = request.queryParams("endereco[uf]");
		String cep = request.queryParams("endereco[cep]");
		Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
		
		String tipo = request.queryParams("dados[tipo]");
		
		if(tipo.equals("fisica")) {
			String nome = request.queryParams("dados[nome]");
			String cpf = request.queryParams("dados[cpf]");
			String dataDeNascimento = request.queryParams("dados[dataDeNascimento]");
			User pessoaFisica = (User) new PessoaFisica(id, endereco, login, senha, email, telefone, nome, cpf, dataDeNascimento);
			userDAO.add(pessoaFisica);
		} else {
			String cnpj = request.queryParams("dados[cnpj]");
			String razaoSocial = request.queryParams("dados[razaoSocial]");
			String nomeFantasia = request.queryParams("dados[nomeFantasia]");
			User pessoaJuridica = (User) new PessoaJuridica(id, endereco, login, senha, email, telefone, cnpj, razaoSocial, nomeFantasia);
			userDAO.add(pessoaJuridica);
		}		

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));

		User user = (User) userDAO.get(id);

		if (user != null) {
			response.header("Content-Type", "application/xml");
			response.header("Content-Encoding", "UTF-8");

			return "{\n" + "	\"login\":" + user.getLogin() + ",\n"
					+ "	\"email\": " + user.getEmail() + ",\n"
					+ "}";
		} else {
			response.status(404); // 404 Not found
			return "Usuario com o id " + id + " não foi encontrado.";
		}

	}

	public Object update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));

		User user = (User) userDAO.get(id);

		if (user != null) {
			userDAO.update(user);

			return id;
		} else {
			response.status(404); // 404 Not found
			return "Usuário não encontrado.";
		}

	}

	public Object remove(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));

		User user = (User) userDAO.get(id);

		if (user != null) {
			userDAO.remove(user);

			return id;
		} else {
			response.status(404); // 404 Not found
			return "Usuário não encontrado.";
		}
	}

	public Object getAll(Request request, Response response) {
		response.header("Content-Type", "application/xml");
		response.header("Content-Encoding", "UTF-8");
		StringBuffer returnValue = new StringBuffer("[\n");
		for (User user : userDAO.getAll()) {
			User usuario = (User) user;
			returnValue.append("{\n" 
					+ "	\"id\": " + usuario.getId() + ", \n"
					+ "	\"login\": " + usuario.getLogin() + " \n},\n");
		}
		returnValue.append("]");
		return returnValue.toString();
	}
}
