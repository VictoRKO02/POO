package model;

import java.util.List;

public class Serie extends Midia {
    private int temporadas;
    private int episodiosPorTemporada;
    private List<Episodio> episodios;

    public Serie(String titulo, String genero, int ano, int temporadas, int episodiosPorTemporada) {
        super(titulo, genero, ano);
        this.temporadas = temporadas;
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    @Override
    public String getDescricao() {
        return String.format("Série: %s (%d), %s, %d temporadas (%d episódios)",
                getTitulo(), getAnoLancamento(), getGenero(),
                this.temporadas,
                episodiosPorTemporada);
    }

    // Getter para temporadas
    public int getNumeroTemporadas() { // Nome do método que Sistema.java espera
        return temporadas;
    }

    // Setter para temporadas
    public void setNumeroTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    // Getter e Setter para episodiosPorTemporada
    public int getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }

    public void setEpisodiosPorTemporada(int episodiosPorTemporada) {
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    // Getter e Setter para a lista de episódios (opcional, mas boa prática se for usado)
    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }
}