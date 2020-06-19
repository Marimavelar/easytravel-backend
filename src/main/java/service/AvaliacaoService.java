package service;

import dao.AvaliacaoDAO;
import model.Avaliacao;
import model.Pacote;

import java.io.IOException;
import spark.Request;
import spark.Response;

public class AvaliacaoService {

    private AvaliacaoDAO avaliacaoDAO; 

    public AvaliacaoService() {
		try {
			avaliacaoDAO = new AvaliacaoDAO("avaliacao.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
    
    public Object add(Request request, Response response) {
		int id = avaliacaoDAO.getMaxId() + 1;

		int idPacote = Integer.parseInt(request.queryParams("idPacote"));
		int idAvaliacao = Integer.parseInt(request.queryParams("idAvaliacao"));
		int nota = Integer.parseInt(request.queryParams("nota"));
		String comentario = request.queryParams("comentario");
        
        Avaliacao avaliacao = (Avaliacao) new Avaliacao(idPacote, idAvaliacao, nota, comentario);
        avaliacaoDAO.add(avaliacao);

		response.status(201); // 201 Created
		return idAvaliacao;
	}

	public Object getByIdPacote(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));

		/*Pacote pacote = (Pacote) pacoteDAO.get(id);

		if (user != null) {
			response.header("Content-Type", "application/xml");
			response.header("Content-Encoding", "UTF-8");

			return "{\n" + "	\"login\":" + user.getLogin() + ",\n"
					+ "	\"email\": " + user.getEmail() + ",\n"
					+ "}";
		} else {
			response.status(404); // 404 Not found
			return "Usuario com o id " + id + " não foi encontrado.";
		}*/

	}
}