package littleRightFlix;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Filme;
import model.Midia;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		// Correção do aviso de depreciação:
	    Locale local = new Locale("pt", "BR");
		Locale.setDefault(Locale.of("pt", "BR")); // Define o Locale padrão usando o método de fábrica
		Scanner sc = new Scanner(System.in);

		// Cria uma instância do Sistema, que já carrega os dados iniciais no construtor ou do arquivo
		Sistema sistema = null;
		try {
			sistema = new Sistema();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sistema.exibirAsciiArt(); // Exibe a arte ASCII uma vez

		// Caminho para o arquivo de texto de filmes (se for manter a funcionalidade de abrir vídeo)
		String caminhoFilmesTxt = "C:\\Users\\DELL\\Desktop\\LittleFlix\\Filmes\\filmes.txt"; // Considere tornar este caminho mais flexível

		boolean sair = false;
		while (!sair) {
			Sistema.exibirMenu();
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
						System.out.println(midiaEncontrada.getDescricao());
					} else {
						System.out.println("Nenhuma mídia encontrada com o título: " + tituloBusca);
					}
					break;
				case "9":
					sistema.listarConteudosOrdenadosPorTitulo();
					break;
				case "10":
					sistema.listarConteudosOrdenadosPorAno();
					break;
				case "11":
					sistema.listarConteudosOrdenadosPorGenero();
					break;
				case "12":
					sistema.gerenciarEpisodiosDeSerie();
					break;
				case "13":
					sistema.buscarPorGenero();
					break;
				case "14":
					sistema.buscarPorAno();
					break;
				case "15":
					sistema.buscarFilmesPorDiretor();
					break;
				case "16": // Opção "Abrir Vídeo" renumerada
					System.out.println("--- Abrir Arquivo de Filme (da lista .txt) ---");
					List<String> titulosDeArquivo = Sistema.obterListaTitulosFilmesDeArquivo(caminhoFilmesTxt);
					if (titulosDeArquivo.isEmpty()) {
						System.out.println("Não foi possível carregar a lista de filmes do arquivo .txt ou está vazia.");
						System.out.println("Verifique o caminho do arquivo: " + caminhoFilmesTxt);
						break;
					}
					System.out.println("Lista de filmes disponíveis no arquivo .txt:");
					for (int i = 0; i < titulosDeArquivo.size(); i++) {
						System.out.println((i + 1) + ": " + titulosDeArquivo.get(i));
					}
					System.out.print("Digite o número do filme para tentar abrir: ");
					try {
						int filmeEscolhidoNum = Integer.parseInt(sc.nextLine());
						if (filmeEscolhidoNum >= 1 && filmeEscolhidoNum <= titulosDeArquivo.size()) {
							String nomeFilmeSelecionado = titulosDeArquivo.get(filmeEscolhidoNum - 1);
							String caminhoVideo = "C:\\Users\\DELL\\Desktop\\LittleFlix\\Filmes\\" + nomeFilmeSelecionado + ".mp4";
							System.out.println("Tentando abrir: " + caminhoVideo);
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
				default: // Apenas um default é permitido
					System.out.println("Opção inválida. Tente novamente.");
					break;
				// O segundo default foi removido daqui
			}
			if (!sair) {
				System.out.println("\nPressione Enter para continuar...");
				sc.nextLine();
			}
		}
		sc.close();
	}

	
}