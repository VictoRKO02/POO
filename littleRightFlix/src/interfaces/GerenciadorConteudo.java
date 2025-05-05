package interfaces;

import model.Midia;
import java.util.List;

public interface GerenciadorConteudo {
	void adicionarConteudo(Midia midia); 
	List<Midia> listarConteudos(); 
	Midia buscarPorTitulo(String titulo); 
}
