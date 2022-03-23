package Ex2;

public class Comentario {
	private String comment;
	private int codigo;
	private int likes;
	private int dislikes;
	
	public Comentario() {
		this.codigo = -1;
		this.comment = "";
		this.likes = -1;
		this.dislikes = -1;
	}
	
	public Comentario(int codigo, String comment, int likes, int dislikes) {
		this.codigo = codigo;
		this.comment = comment;
		this.likes = likes;
		this.dislikes = dislikes;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	@Override
	public String toString() {
		return "Comentario [codigo=" + codigo + ", comment=" + comment + ", likes=" + likes + ", dislikes=" + dislikes + "]";
	}	

}