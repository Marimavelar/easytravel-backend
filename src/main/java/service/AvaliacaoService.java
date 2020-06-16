import dao.AvaliacaoDAO;

public class AvaliacaoService {

    private AvaliacaoDAO avaliacaoDAO; 

    public UserService() {
		try {
			avaliacaoDAO = new AvaliacaoDAO("avaliacao.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
    
    public Object add(Request request, Response response) {
		int id = avaliacaoDAO.getMaxId() + 1;

		int idPacote = request.queryParams("idPacote");
		int id = request.queryParams("id");
		int nota = request.queryParams("nota");
		String comentario = request.queryParams("comentario");
        
        Avaliacao avaliacao = (Avaliacao) new Avaliacao(idPacote, id, nota, comentario);
        avaliacaoDAO.add(avaliacao);

		response.status(201); // 201 Created
		return id;
	}

	public Object getByIdPacote(Request request, Response response) {
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
}