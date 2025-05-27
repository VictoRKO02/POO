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

	// Getters existentes
	public String getTitulo() {
		return titulo;
	}

	public String getGenero() {
		return genero;
	}

	public int getAnoLancamento() {
		return ano;
	}

	// Setters que estavam faltando
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setAnoLancamento(int ano) {
		this.ano = ano;
	}
}