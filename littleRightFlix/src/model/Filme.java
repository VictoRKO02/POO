package model;

public class Filme extends Midia {
    private String diretor;
    private int duracao; // em minutos

    public Filme(String titulo, String genero, int ano, String diretor, int duracao) {
        super(titulo, genero, ano);
        this.diretor = diretor;
        this.duracao = duracao;
    }

    public String getDiretor() {
        return diretor;
    }

    public int getDuracaoMinutos() {
        return duracao;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public void setDuracaoMinutos(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String getDescricao() {
        return "Filme: " + titulo + " (" + ano + ") - Gênero: " + genero + 
               " | Diretor: " + diretor + " | Duração: " + duracao + " min";
    }

    @Override
    public String toLinhaArquivo() {
        return "F;" + titulo + ";" + genero + ";" + ano + ";" + diretor + ";" + duracao;
    }
    
    
    @Override
    public String paraLinhaArquivo() {
        return String.format("F;%s;%s;%d;%s;%d", getTitulo(), getGenero(), getAnoLancamento(), getDiretor(), getDuracaoMinutos());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}