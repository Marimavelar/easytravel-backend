package service;

import spark.Request;
import spark.Response;

public class PagamentoService {

    public Object efetuarPagamento(Request request, Response response) {

		String nomeTitular = request.queryParams("nomeTitular");
        String numeroCartao = request.queryParams("numeroCartao");
        String dataVencimento = request.queryParams("dataVencimento");
        String bandeira = request.queryParams("bandeira");
        String cvv = request.queryParams("cvv");

		if (nomeTitular != null && numeroCartao != null && dataVencimento != null && bandeira != null && cvv != null) {
			response.header("Content-Type", "application/xml");
			response.header("Content-Encoding", "UTF-8");

			return "Success";
		} else {
			response.status(400);
			return "Parâmetros inválidos!";
		}
	}
}