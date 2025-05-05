package littleRightFlix;

import model.Filme; // Import adicionado
import model.Midia;

public class Programa {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        
        sistema.exibirAsciiArt(); 
        
        sistema.carregarDadosIniciais(); 
        
        System.out.println("\n=== TESTE: Listagem Inicial ===");
        sistema.listarConteudos().forEach(m -> System.out.println(m.getDescricao()));
        
        // 4. Teste de adição
        System.out.println("\n=== TESTE: Adição de Novo Filme ===");
        Midia novoTitanic = new Filme("Titanic", "Romance", 1997, "James Cameron", 195);
        sistema.adicionarConteudo(novoTitanic);
        System.out.println("Adicionado: " + novoTitanic.getDescricao());
        
        // 5. Listagem atualizada (ambos os métodos abaixo funcionam)
        System.out.println("\n=== Listagem Atualizada ===");
        sistema.listarConteudos().forEach(m -> System.out.println(m.getDescricao())); 
        
     // 6. Teste de busca
        System.out.println("\n=== TESTE: Busca por Título ===");
        String tituloBusca = "Titanic";
        Midia encontrada = sistema.buscarPorTitulo(tituloBusca);
        if (encontrada != null) {
            System.out.println("Mídia encontrada: " + encontrada.getDescricao());
        } else {
            System.out.println("Mídia com título \"" + tituloBusca + "\" não encontrada.");
        }

    }
}