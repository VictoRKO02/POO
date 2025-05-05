package model;

public class Serie extends Midia {
    private int temporadas;
    private int episodiosPorTemporada;

    public Serie(String titulo, String genero, int ano, int temporadas, int episodiosPorTemporada) {
        super(titulo, genero, ano);
        this.temporadas = temporadas;
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    @Override
    public String getDescricao() {
        return String.format("Série: %s (%d), %s, %d temporadas (%d episódios)", 
               getTitulo(), getAno(), getGenero(), 
               temporadas, episodiosPorTemporada);
    }

    public int getTemporadas() {
        return temporadas;
    }

    public int getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }
}