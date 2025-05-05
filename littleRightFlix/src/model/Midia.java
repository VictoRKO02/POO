package model;

public abstract class Midia {
	private String titulo;
	private String genero;
	private int ano;

	public Midia (String titulo, String genero, int ano) {
		this.titulo = titulo;
		this.genero = genero;
		this.ano = ano;
	}
	public abstract String getDescricao();
	
	public String getTitulo() { 
		return titulo;
	}
	public String getGenero() {
		return genero;
	}
	public int getAno() {
		return ano;
	}
}
