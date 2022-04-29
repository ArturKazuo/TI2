package app;

import static spark.Spark.*;
import service.ComentarioService;


public class Aplicacao {
	
	private static ComentarioService comentarioService = new ComentarioService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/comentario/insert", (request, response) -> comentarioService.insert(request, response));

        get("/comentario/:id", (request, response) -> comentarioService.get(request, response));
        
        get("/comentario/list/:orderby", (request, response) -> comentarioService.getAll(request, response));

        get("/comentario/update/:id", (request, response) -> comentarioService.getToUpdate(request, response));
        
        post("/comentario/update/:id", (request, response) -> comentarioService.update(request, response));
           
        get("/comentario/delete/:id", (request, response) -> comentarioService.delete(request, response));

             
    }
}