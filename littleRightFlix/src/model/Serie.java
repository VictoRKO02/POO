package model;

import java.util.ArrayList;
import java.util.List;

public class Serie extends Midia {
    private int numTemporadas;
    private int episodiosPorTemporada;
    private List<Episodio> episodios;

    public Serie(String titulo, String genero, int ano, int numTemporadas, int episodiosPorTemporada) {
        super(titulo, genero, ano);
        this.numTemporadas = numTemporadas;
        this.episodiosPorTemporada = episodiosPorTemporada;
        this.episodios = new ArrayList<>();
    }

    public void adicionarEpisodio(Episodio ep) {
        episodios.add(ep);
    }

    public void removerEpisodio(Episodio ep) {
        episodios.remove(ep);
    }

    public List<Episodio> getEpisodiosAdicionados() {
        return episodios;
    }

    public int getNumTemporadas() {
        return numTemporadas;
    }

    public int getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }

    public void setNumTemporadas(int numTemporadas) {
        this.numTemporadas = numTemporadas;
    }

    public void setEpisodiosPorTemporada(int episodiosPorTemporada) {
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    @Override
    public String getDescricao() {
        return "Série: " + titulo + " (" + ano + ") - Gênero: " + genero + 
               " | Temporadas: " + numTemporadas + " | Episódios/Temporada: " + episodiosPorTemporada;
    }

    @Override
    public String toLinhaArquivo() {
        return "S;" + titulo + ";" + genero + ";" + ano + ";" + numTemporadas + ";" + episodiosPorTemporada;
    }
    
    
    @Override
    public String paraLinhaArquivo() {
        return String.format("S;%s;%s;%d;%d;%d", getTitulo(), getGenero(), getAnoLancamento(), getNumTemporadas(), getEpisodiosPorTemporada());
    }
    
    
    
    
    
    
    
    
    
}