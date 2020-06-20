package service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.RoteiroPersonalizadoDAO;
import dao.UserDAO;
import model.Endereco;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.RoteiroPersonalizado;
import model.User;
import spark.Request;
import spark.Response;

public class RoteiroPersonalizadoService {

	private RoteiroPersonalizadoDAO roteiroPersonalizadoDAO;

	public RoteiroPersonalizadoService() {
		try {
			roteiroPersonalizadoDAO = new RoteiroPersonalizadoDAO("roteiroPersonalizadoDAO.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) throws NumberFormatException, ParseException {
		int id = roteiroPersonalizadoDAO.getMaxId() + 1;

		List<String> linhasPacote = new ArrayList();
		for (int i = 0; i < linhasPacote.size(); i++) {
			String vetorString[] = linhasPacote.get(i).split(";");
			RoteiroPersonalizado roteiroPersonalizado = new RoteiroPersonalizado(id, vetorString[1], vetorString[2],
					new SimpleDateFormat("dd-MM-yyyy").parse(vetorString[3]),
					new SimpleDateFormat("dd-MM-yyyy").parse(vetorString[4]), Integer.parseInt(vetorString[5]),
					vetorString[6], vetorString[7], vetorString[8], vetorString[9], vetorString[10], vetorString[11],
					Double.parseDouble(vetorString[12]));

			roteiroPersonalizadoDAO.add(roteiroPersonalizado);
		}

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));

		RoteiroPersonalizado roteiroPersonalizado = (RoteiroPersonalizado) roteiroPersonalizadoDAO.get(id);

		if (roteiroPersonalizado != null) {
			response.header("Content-Type", "application/xml");
			response.header("Content-Encoding", "UTF-8");

			return "{\n" + "\"id\":" + roteiroPersonalizado.getId() + ",\n" + "	\"Nome\": "
					+ roteiroPersonalizado.getNome() + ",\n" + "	\"Destino\": " + roteiroPersonalizado.getDestino()
					+ ",\n" + "	\"Data Partida\": " + roteiroPersonalizado.getDataPartida() + ",\n"
					+ "	\"Data Retorno\": " + roteiroPersonalizado.getDataRetorno() + ",\n"
					+ "	\"Quantidade De Pessoas\": " + roteiroPersonalizado.getQuantidadePessoas() + ",\n"
					+ "	\"Atividades\": " + roteiroPersonalizado.getAtividades() + ",\n"
					+ "	\"Meio de Transporte\": " + roteiroPersonalizado.getMeioTransporte() + ",\n"
					+ "	\"Hospedagem\": " + roteiroPersonalizado.getHospedagem() + ",\n" + "	\"Tipo de Companhia\": "
					+ roteiroPersonalizado.getTipoCompanhia() + ",\n" + "	\"Tipo de atividade\": "
					+ roteiroPersonalizado.getTipoAtividade() + ",\n" + "	\"Motivo de Viagem\": "
					+ roteiroPersonalizado.getMotivoViagem() + ",\n" + "	\"Preço\": "
					+ roteiroPersonalizado.getPreco() + "\n" + "}";
		} else {
			response.status(404); // 404 Not found
			return "Pacote com o id " + id + " não foi encontrado.";
		}

	}
}
