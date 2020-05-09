package app;

import static spark.Spark.*;

import service.UserService;

public class App {
	private static UserService userService = new UserService();
	
    public static void main(String[] args) {
        port(3030);
        
        post("/login", (request, response) -> userService.login(request, response));

        post("/cadastrar", (request, response) -> userService.add(request, response));
        
//        post("/bensdeconsumo", (request, response) -> userService.add(request, response));
//
//        get("/bensdeconsumo/:id", (request, response) -> userService.get(request, response));
//
//        put("/bensdeconsumo/:id", (request, response) -> userService.update(request, response));
//
//        delete("/bensdeconsumo/:id", (request, response) -> userService.remove(request, response));
//
//        get("/bensdeconsumo", (request, response) -> userService.getAll(request, response));
        
        
    }
}
