package model;

public class Episodio {
    private String tituloSerie;
    private int temporada;
    private int numero;
    private String titulo;

    public Episodio(String tituloSerie, int temporada, int numero, String titulo) {
        this.tituloSerie = tituloSerie;
        this.temporada = temporada;
        this.numero = numero;
        this.titulo = titulo;
    }

    public String getTituloSerie() {
        return tituloSerie;
    }

    public int getTemporada() {
        return temporada;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String toLinhaArquivo() {
        return "E;" + tituloSerie + ";" + numero + ";" + titulo;
    }

    @Override
    public String toString() {
        return "Temporada " + temporada + " | Episódio " + numero + ": " + titulo;
    }
}