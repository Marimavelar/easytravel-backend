package service;

import dao.AvaliacaoDAO;
import model.Avaliacao;
import model.Pacote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		int nota = Integer.parseInt(request.queryParams("nota"));
		String comentario = request.queryParams("comentario");

		Avaliacao avaliacao = (Avaliacao) new Avaliacao(idPacote, id, nota, comentario);
		avaliacaoDAO.add(avaliacao);

		response.status(201); // 201 Created
		return id;
	}

	public Object getByIdPacote(Request request, Response response) {
		List<Avaliacao> avaliacoesPacoteEspecifico = new ArrayList<Avaliacao>();
		int id = Integer.parseInt(request.queryParams("idpacote"));

		avaliacoesPacoteEspecifico = avaliacaoDAO.getAllByIdPacote(id);

		if (avaliacoesPacoteEspecifico != null) {
			response.header("Content-Type", "application/xml");
			response.header("Content-Encoding", "UTF-8");

			String str = "";
			for (Avaliacao avaliacao : avaliacoesPacoteEspecifico) {
				str = str + "{\n" + "	\"id\":" + avaliacao.getId() + ",\n" + "	\"nota\": " + avaliacao.getNota()
						+ ",\n" + "	\"comentario\": " + avaliacao.getComentario() + ",\n" + "}";
			}

			return str;
		} else {
			response.status(404); // 404 Not found
			return "Avaliação do pacote com o id " + id + " não foi encontrado.";
		}

	}
}