package model;

import java.io.Serializable; // 1. Importar Serializable

public abstract class Midia implements Serializable { // 2. Implementar Serializable

	// 3. Adicionar serialVersionUID
	private static final long serialVersionUID = 2L; // Usando um ID diferente para esta classe

	private String titulo;
	private String genero;
	private int ano;

	public Midia(String titulo, String genero, int ano) {
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

	public int getAnoLancamento() {
		return ano;
	}

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