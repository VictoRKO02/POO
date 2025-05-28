package model;

import java.io.Serializable; // Importar Serializable

public class Filme extends Midia implements Serializable { // Implementar Serializable (embora herdado, é bom ser explícito)

    private static final long serialVersionUID = 3L; // UID para Filme

    private String diretor;
    private int duracaoMinutos;
    private String diretorio; // Campo que já existia

    public Filme(String titulo, String genero, int ano, String diretor, int duracaoMinutos) {
        super(titulo, genero, ano);
        this.diretor = diretor;
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String getDescricao() {
        return String.format("Filme: %s (%d), Gênero: %s, Diretor: %s, Duração: %d min",
                getTitulo(), getAnoLancamento(), getGenero(), diretor, duracaoMinutos);
    }

    // Getters e Setters para os campos específicos de Filme
    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }
}