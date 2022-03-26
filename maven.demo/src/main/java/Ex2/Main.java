package Ex2;

import Ex2.DAO;
import Ex2.Comentario;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
			
			DAO dao = new DAO();
			
			dao.conectar();
			
			int input, j;
			Scanner sc = new Scanner(System.in);
			
			do {	
				System.out.println("Escolha uma opção para interagir com o BD");
				System.out.println("1 - mostrar todos os comentarios");
				System.out.println("2 - inserir um novo comentario");
				System.out.println("3 - excluir um comentario");
				System.out.println("4 - atualizar um comentario existente");
				System.out.println("5 - sair do programa");
				input = sc.nextInt();
				switch(input){
					case 1:
						System.out.println("==== Listar Comentarios === ");
						Comentario[] comentarios = dao.getComentarios();
						for(int i = 0; i < comentarios.length; i++) {
							System.out.println(comentarios[i].toString());
						}
						System.out.println("==== FIM === ");
						System.out.println(" ");
						break;
						
					case 2:
						Comentario[] codigosComentarios = dao.getComentarios();
						j = (codigosComentarios[(codigosComentarios.length) - 1].getCodigo()) + 1;
						System.out.println("Digite o seu comentario: ");
						sc.nextLine();
						String comment = sc.nextLine();
						Comentario comentario = new Comentario(j, comment, 0, 0);
						if(dao.inserirComentario(comentario) == true) {
							System.out.println("InserÃ§Ã£o com sucesso");
						}
						System.out.println(" ");
						break;
						
					case 3:
						System.out.println("Digite o codigo do comentario que deseja deletar: ");
						int code = sc.nextInt();
						if(dao.excluirComentario(code) == true){
							System.out.println("Deletado com sucesso");
						}
						System.out.println(" ");
						break;
						
					case 4:
						Comentario[] atualComentario = dao.getComentarios();
						System.out.println("Digite o codigo do comentario que deseja atualizar: ");
						int atualCode = sc.nextInt();
						System.out.println("Digite o comentario: ");
						sc.nextLine();
						String atualComment = sc.nextLine();
						atualComentario[--atualCode].setComment(atualComment);
						if(dao.atualizarComentario(atualComentario[atualCode]) == true) {
							System.out.println("Atualizado com sucesso");
						}
						System.out.println(" ");
						break;
						
					case 5:
						System.out.println("Saindo do programa...");
						j = 0;
						break;
				}
			}while(input != 5);
			
			dao.close();
		}
}
