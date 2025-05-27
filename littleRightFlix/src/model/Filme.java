package model;

public class Filme extends Midia {
    private String diretor;
    private int duracaoMinutos;
    private String diretorio;

    public Filme(String titulo, String genero, int ano, String diretor, int duracaoMinutos) {
        super(titulo, genero, ano);
        this.diretor = diretor;
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String getDescricao() {
        return String.format("Filme: %s (%d), %s, Diretor: %s, %d min",
                getTitulo(), getAnoLancamento(), getGenero(), diretor, duracaoMinutos);
    }

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