package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

//	public Object get(Request request, Response response) {
//		int id = Integer.parseInt(request.params(":id"));
//		
//		BemDeConsumo bemDeConsumo = (BemDeConsumo) bemDeConsumoDAO.get(id);
//		
//        if (bemDeConsumo != null) {
//    	    response.header("Content-Type", "application/xml");
//    	    response.header("Content-Encoding", "UTF-8");
//
//            return "<bemdeconsumo>\n" + 
//            		"\t<id> " + bemDeConsumo.getId() + "</id>\n" +
//            		"\t<descricao> " + bemDeConsumo.getDescricao() + "</descricao>\n" +
//            		"\t<preco> " + bemDeConsumo.getPreco() + "</preco>\n" +
//            		"\t<quantidade> " + bemDeConsumo.getQuant() + "</quantidade>\n" +
//            		"\t<fabricacao> " + bemDeConsumo.getDataFabricacao() + "</fabricacao>\n" +
//            		"\t<validade> " + bemDeConsumo.getDataValidade() + "</validade>\n" +
//            		"</bemdeconsumo>\n";
//        } else {
//            response.status(404); // 404 Not found
//            return "Produto " + id + " n�o encontrado.";
//        }
//
//	}

//	public Object update(Request request, Response response) {
//        int id = Integer.parseInt(request.params(":id"));
//        
//		BemDeConsumo bemDeConsumo = (BemDeConsumo) bemDeConsumoDAO.get(id);
//
//        if (bemDeConsumo != null) {
//        	bemDeConsumo.setDescricao(request.queryParams("descricao"));
//        	bemDeConsumo.setPreco(Float.parseFloat(request.queryParams("preco")));
//        	bemDeConsumo.setQuant(Integer.parseInt(request.queryParams("quantidade")));
//        	bemDeConsumo.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
//        	bemDeConsumo.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));
//
//        	bemDeConsumoDAO.update(bemDeConsumo);
//        	
//            return id;
//        } else {
//            response.status(404); // 404 Not found
//            return "Bem de consumo n�o encontrado.";
//        }
//
//	}
//
//	public Object remove(Request request, Response response) {
//        int id = Integer.parseInt(request.params(":id"));
//        
//		BemDeConsumo bemDeConsumo = (BemDeConsumo) bemDeConsumoDAO.get(id);
//
//        if (bemDeConsumo != null) {
//
//        	bemDeConsumoDAO.remove(bemDeConsumo);
//
//        	return id;
//        } else {
//            response.status(404); // 404 Not found
//            return "Bem de consumo n�o encontrado.";
//        }
//	}
//
//	public Object getAll(Request request, Response response) {
//		StringBuffer returnValue = new StringBuffer("<bensdeconsumo type=\"array\">");
//		for (Produto produto : bemDeConsumoDAO.getAll()) {
//			BemDeConsumo bemDeConsumo = (BemDeConsumo) produto;
//			returnValue.append("\n<bemdeconsumo>\n" + 
//            		"\t<id> " + bemDeConsumo.getId() + "</id>\n" +
//            		"\t<descricao> " + bemDeConsumo.getDescricao() + "</descricao>\n" +
//            		"\t<preco> " + bemDeConsumo.getPreco() + "</preco>\n" +
//            		"\t<quantidade> " + bemDeConsumo.getQuant() + "</quantidade>\n" +
//            		"\t<fabricacao> " + bemDeConsumo.getDataFabricacao() + "</fabricacao>\n" +
//            		"\t<validade> " + bemDeConsumo.getDataValidade() + "</validade>\n" +
//            		"</bemdeconsumo>\n");
//		}
//		returnValue.append("</bensdeconsumo>");
//	    response.header("Content-Type", "application/xml");
//	    response.header("Content-Encoding", "UTF-8");
//		return returnValue.toString();
//
//	}
}
