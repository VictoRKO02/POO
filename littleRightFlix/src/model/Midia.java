package model;

public abstract class Midia {
    protected String titulo;
    protected String genero;
    protected int ano;

    public Midia(String titulo, String genero, int ano) {
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnoLancamento() {
        return ano;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAnoLancamento(int ano) {
        this.ano = ano;
    }

    public abstract String getDescricao();

    public abstract String toLinhaArquivo();

	public String paraLinhaArquivo() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
   
    
    
    
    
    
    
    
}