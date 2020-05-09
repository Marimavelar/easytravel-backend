package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import dao.UserDAO;
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

            return "{\n" + 
            		"	\"nome\": " + user.getNome() + ",\n" + 
            		"	\"login\":" + user.getLogin() + ",\n" + 
            		"	\"senha\": " + user.getSenha() + ",\n" + 
            		"	\"email\": " + user.getEmail() + ",\n" + 
            		"	\"cpf\": " + user.getCpf() + ",\n" + 
            		"	\"dataDeNascimento\": " + user.getDataDeNascimento() + ",\n" + 
            		"	\"endereco\": " + user.getEndereco() + ",\n" + 
            		"	\"telefone\": " + user.getTelefone() + 
            		"}";
        } else {
            response.status(404); // 404 Not found
            return "Usuario " + login + " não foi encontrado.";
        }
	}

	// Alterar a função para receber dados no body como json e retornar como objeto
	public Object add(Request request, Response response) {
//		String body = request.body();
		
		String login = request.queryParams("login");
		String senha = request.queryParams("senha");
		String nome = request.queryParams("nome");
		String email = request.queryParams("email");
		String cpf = request.queryParams("cpf");
		String dataDeNascimento = request.queryParams("dataDeNascimento");
		String endereco = request.queryParams("endereco");
		String telefone = request.queryParams("telefone");

		int id = userDAO.getMaxId() + 1;
		User user = new User(id, nome, endereco, cpf, login, senha, email, dataDeNascimento, telefone);

		userDAO.add(user);

		response.status(201); // 201 Created
		return id;
	}

	// Retornar 
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		User user = (User) userDAO.get(id);
		
		if (user != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "{\n" + 
            		"	\"nome\": " + user.getNome() + ",\n" + 
            		"	\"login\":" + user.getLogin() + ",\n" + 
            		"	\"senha\": " + user.getSenha() + ",\n" + 
            		"	\"email\": " + user.getEmail() + ",\n" + 
            		"	\"cpf\": " + user.getCpf() + ",\n" + 
            		"	\"dataDeNascimento\": " + user.getDataDeNascimento() + ",\n" + 
            		"	\"endereco\": " + user.getEndereco() + ",\n" + 
            		"	\"telefone\": " + user.getTelefone() + 
            		"}";
        } else {
            response.status(404); // 404 Not found
            return "Usuario com o id " + id + " não foi encontrado.";
        }

	}

	// Alterar a função para receber dados no body como json e retornar como objeto
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		User user = (User) userDAO.get(id);

        if (user != null) {
        	user.setNome(request.queryParams("nome"));

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
			returnValue.append("{\n" + 
            		"	\"nome\": " + usuario.getNome() + ",\n" + 
            		"	\"login\":" + usuario.getLogin() + ",\n" + 
            		"	\"senha\": " + usuario.getSenha() + ",\n" + 
            		"	\"email\": " + usuario.getEmail() + ",\n" + 
            		"	\"cpf\": " + usuario.getCpf() + ",\n" + 
            		"	\"dataDeNascimento\": " + usuario.getDataDeNascimento() + ",\n" + 
            		"	\"endereco\": " + usuario.getEndereco() + ",\n" + 
            		"	\"telefone\": " + usuario.getTelefone() + 
            		"\n},\n");
		}
		returnValue.append("]");
		return returnValue.toString();
	}
}
