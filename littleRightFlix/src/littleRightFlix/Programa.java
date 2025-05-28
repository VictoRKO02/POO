package littleRightFlix;

import java.util.List; 
import java.util.Locale;
import java.util.Scanner;
import model.Filme; 
import model.Midia;
import model.Serie;

public class Programa {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt", "BR")); // Define o Locale padrão
		Scanner sc = new Scanner(System.in);

		// Cria uma instância do Sistema, que já carrega os dados iniciais no construtor
		Sistema sistema = new Sistema();

		Sistema.exibirAsciiArt(); // Exibe a arte ASCII uma vez

		// Caminhos para os arquivos de texto (se for manter a funcionalidade de abrir vídeo)
		String caminhoFilmesTxt = "C:\\Users\\DELL\\Desktop\\LittleFlix\\Filmes\\filmes.txt";
		// String caminhoSeriesTxt = "C:\\Users\\DELL\\Desktop\\LittleFlix\\Series\\series.txt"; // Se houver um para séries

		boolean sair = false;
		while (!sair) {
			exibirMenu();
			String opcao = sc.nextLine().trim(); // Lê a linha inteira e remove espaços

			switch (opcao) {
				case "1":
					sistema.adicionarNovoFilme();
					break;
				case "2":
					sistema.adicionarNovaSerie();
					break;
				case "3":
					sistema.alterarMidia();
					break;
				case "4":
					sistema.removerMidia();
					break;
				case "5":
					sistema.listarConteudos(); // Lista tudo
					break;
				case "6":
					sistema.listarApenasFilmes();
					break;
				case "7":
					sistema.listarApenasSeries();
					break;
				case "8":
					System.out.print("Digite o título para buscar: ");
					String tituloBusca = sc.nextLine();
					Midia midiaEncontrada = sistema.buscarPorTitulo(tituloBusca);
					if (midiaEncontrada != null) {
						System.out.println("Mídia encontrada:");
						// Para exibir detalhes, idealmente Midia, Filme, Serie teriam um método bom de toString()
						// ou um método getDetalhesCompletos()
						System.out.println(midiaEncontrada.toString()); // Adapte conforme a implementação do toString()
					} else {
						System.out.println("Nenhuma mídia encontrada com o título: " + tituloBusca);
					}
					break;
				case "9":
					sistema.listarConteudosOrdenadosPorTitulo();
					break;
				case "10":
					// Funcionalidade de "Abrir Vídeo" (opcional, mantida do código original)
					System.out.println("--- Abrir Arquivo de Filme (via .txt) ---");
					List<String> titulosDeArquivo = Sistema.obterListaTitulosFilmesDeArquivo(caminhoFilmesTxt);
					if (titulosDeArquivo.isEmpty()) {
						System.out.println("Não foi possível carregar a lista de filmes do arquivo .txt ou está vazia.");
						break;
					}
					for (int i = 0; i < titulosDeArquivo.size(); i++) {
						System.out.println((i + 1) + ": " + titulosDeArquivo.get(i));
					}
					System.out.print("Digite o número do filme para tentar abrir: ");
					try {
						int filmeEscolhidoNum = Integer.parseInt(sc.nextLine());
						if (filmeEscolhidoNum >= 1 && filmeEscolhidoNum <= titulosDeArquivo.size()) {
							String nomeFilmeSelecionado = titulosDeArquivo.get(filmeEscolhidoNum - 1);
							// Atenção: O caminho do vídeo precisa ser construído corretamente.
							// O nome no .txt deve corresponder ao nome do arquivo .mp4 (ou outro formato).
							String caminhoVideo = "C:\\Users\\DELL\\Desktop\\LittleFlix\\Filmes\\" + nomeFilmeSelecionado + ".mp4"; // Adapte a extensão se necessário
							Sistema.abrirVideo(caminhoVideo);
						} else {
							System.out.println("Número inválido.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Entrada inválida. Por favor, digite um número.");
					}
					break;
				case "0":
					sair = true;
					System.out.println("Obrigado por usar o LittleRightFlix! Até mais!");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					break;
			}
			if (!sair) {
				System.out.println("\nPressione Enter para continuar...");
				sc.nextLine(); // Pausa para o usuário ler a saída antes de mostrar o menu novamente
			}
		}
		sc.close();
	}

	public static void exibirMenu() {
		System.out.println("\n--- MENU LittleRightFlix ---");
		System.out.println("1. Adicionar Novo Filme");
		System.out.println("2. Adicionar Nova Série");
		System.out.println("3. Alterar Mídia Existente");
		System.out.println("4. Remover Mídia");
		System.out.println("5. Listar Todas as Mídias");
		System.out.println("6. Listar Apenas Filmes");
		System.out.println("7. Listar Apenas Séries");
		System.out.println("8. Buscar Mídia por Título");
		System.out.println("9. Listar Todas as Mídias Ordenadas por Título");
		System.out.println("10. Tentar Abrir Vídeo de Filme (da lista .txt)"); 
		System.out.println("0. Sair");
		System.out.print("Escolha uma opção: ");
	}
}
