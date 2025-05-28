package model;

import java.io.Serializable; // 1. Importar Serializable

public class Episodio implements Serializable { // 2. Implementar Serializable

	// 3. Adicionar serialVersionUID
	private static final long serialVersionUID = 1L;

	private int numero;
	private String titulo;
	private String diretorio;

	public Episodio(int numero, String titulo) {
		if (numero <= 0) {
			throw new IllegalArgumentException("O número do episódio deve ser positivo.");
		}
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("O título do episódio não pode ser vazio.");
		}
		this.numero = numero;
		this.titulo = titulo;
	}

	// Getters
	public int getNumero() {
		return numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDiretorio() {
		return diretorio;
	}

	// Setters
	public void setNumero(int numero) {
		if (numero <= 0) {
			throw new IllegalArgumentException("O número do episódio deve ser positivo.");
		}
		this.numero = numero;
	}

	public void setTitulo(String titulo) {
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("O título do episódio não pode ser vazio.");
		}
		this.titulo = titulo;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	public String getDescricao() {
		return String.format("Episódio %d: \"%s\"", numero, titulo);
	}

	@Override
	public String toString() {
		return String.format("Ep. %02d: %s", numero, titulo);
	}
}