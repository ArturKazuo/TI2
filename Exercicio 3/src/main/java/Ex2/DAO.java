package Ex2;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "vietnamwarrior19";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirComentario(Comentario comentario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO comentario (codigo, comment, likes, dislikes) "
					       + "VALUES ("+comentario.getCodigo()+ ", '" + comentario.getComment() + "', '"  
					       + comentario.getLikes() + "', '" + comentario.getDislikes() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarComentario(Comentario comentario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE comentario SET comment = '" + comentario.getComment() + "', likes = '"  
				       + comentario.getLikes() + "', dislikes = '" + comentario.getDislikes() + "'"
					   + " WHERE codigo = " + comentario.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirComentario(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM comentario WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Comentario[] getComentarios() {
		Comentario[] comentarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM comentario");		
	         if(rs.next()){
	             rs.last();
	             comentarios = new Comentario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                comentarios[i] = new Comentario(rs.getInt("codigo"), rs.getString("comment"), 
	                		                  rs.getInt("likes"), rs.getInt("dislikes"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return comentarios;
	}
	
}