package Ex2;

import Ex2.DAO;
import Ex2.Comentario;

public class Main {
	public static void main(String[] args) {
			
			DAO dao = new DAO();
			
			dao.conectar();
	
			//inserir comentario
			Comentario comentario = new Comentario(2, "Este é o segundo comentário a ser inserido na tabela", 0, 0);
			if(dao.inserirComentario(comentario) == true) {
				System.out.println("InserÃ§Ã£o com sucesso -> " + comentario.toString());
			}
				
			//Atualizar comentario
			comentario.setComment("novo comentario atualizado");
			dao.atualizarComentario(comentario);
	
			//Mostrar todos os comentarios
			System.out.println("==== Mostrar comentarios === ");
			Comentario[] comentarios = dao.getComentarios();
			for(int i = 0; i < comentarios.length; i++) {
				System.out.println(comentarios[i].toString());
			}
			
			//Excluir comentario
			dao.excluirComentario(comentario.getCodigo());
			
			dao.close();
		}
}
