package model;

import java.io.Serializable; // Importar Serializable
import java.util.ArrayList;
import java.util.List;

public class Serie extends Midia implements Serializable { // Implementar Serializable

    private static final long serialVersionUID = 4L; // UID para Serie

    private int temporadas;
    private int episodiosPorTemporada; // Média de episódios por temporada
    private List<Episodio> episodiosAdicionados;

    public Serie(String titulo, String genero, int ano, int temporadas, int episodiosPorTemporada) {
        super(titulo, genero, ano);
        this.temporadas = temporadas;
        this.episodiosPorTemporada = episodiosPorTemporada;
        this.episodiosAdicionados = new ArrayList<>(); // Importante inicializar
    }

    public int getNumeroTemporadas() {
        return temporadas;
    }

    public void setNumeroTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }

    public void setEpisodiosPorTemporada(int episodiosPorTemporada) {
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    public void adicionarEpisodio(Episodio episodio) {
        if (episodio != null) {
            this.episodiosAdicionados.add(episodio);
        }
    }

    public List<Episodio> getEpisodiosAdicionados() {
        return new ArrayList<>(episodiosAdicionados); // Retorna cópia
    }

    public void setEpisodiosAdicionados(List<Episodio> episodios) {
        this.episodiosAdicionados = new ArrayList<>(episodios);
    }

    @Override
    public String getDescricao() {
        StringBuilder descricao = new StringBuilder();
        descricao.append(String.format("Série: %s (%d), Gênero: %s, %d temporadas (%d eps/temp em média)",
                getTitulo(), getAnoLancamento(), getGenero(),
                temporadas, episodiosPorTemporada));

        if (this.episodiosAdicionados != null && !this.episodiosAdicionados.isEmpty()) {
            descricao.append(String.format(". Contém %d episódio(s) cadastrado(s):", this.episodiosAdicionados.size()));
            int count = 0;
            for (Episodio ep : this.episodiosAdicionados) {
                if (count < 3) {
                    descricao.append(String.format("\n  - %s", ep.toString()));
                    count++;
                } else {
                    descricao.append("\n  - ...e mais.");
                    break;
                }
            }
        } else {
            descricao.append(". Nenhum episódio cadastrado ainda.");
        }
        return descricao.toString();
    }
}