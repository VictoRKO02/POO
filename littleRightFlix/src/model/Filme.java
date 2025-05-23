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
               getTitulo(), getAno(), getGenero(), diretor, duracaoMinutos);
    }
}