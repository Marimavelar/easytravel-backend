package app;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.port;

import service.UserService;

public class App {
	private static UserService userService = new UserService();
	
    public static void main(String[] args) {
        port(3030);
        
        //Rotas Gerais
        post("/login", (request, response) -> userService.login(request, response));

        post("/cadastrar", (request, response) -> userService.add(request, response));

        //Rotas Usuário
        get("/usuario/:id", (request, response) -> userService.get(request, response));

        put("/usuario/:id", (request, response) -> userService.update(request, response));

        delete("/usuario/:id", (request, response) -> userService.remove(request, response));

        get("/usuarios", (request, response) -> userService.getAll(request, response));
        
        // Rota Pagamento
        post("/pagamento", (request, response) -> pagamentoService.efetuarPagamento(request, response));

        //Rotas Avaliacao
        post("/avaliacao", (request, response) -> avaliacaoService.add(request, response));

        get("/avaliacao", (request, response) -> avaliacaoService.getByIdPacote(request, response));
    }
}
