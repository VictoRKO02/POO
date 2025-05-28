package littleRightFlix;

import interfaces.GerenciadorConteudo;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Sistema implements GerenciadorConteudo {
    // Lista principal de todas as mídias (Filmes e Séries)
    private List<Midia> midias = new ArrayList<>();
    // Scanner para entrada de dados dentro dos métodos do Sistema
    private Scanner scanner = new Scanner(System.in);

    /**
     * Construtor do Sistema.
     * Ao instanciar o Sistema, os dados iniciais são carregados.
     */
    public Sistema() {
        carregarDadosIniciais();
    }

    public static void exibirAsciiArt() {
        System.out.println("  ######  ######   ##   ##           ##   ##   ####    ##   ##  #####     #####      ##      ##       ##            ##      #####           ##        ####    ######   ######   ####     #######   #######     ####      ####   ##   ##  ######       #####   ####      ####    ##  ##     ##");
        System.out.println("  ##  ##  ##       ### ###           ##   ##    ##     ###  ##   ## ##   ##   ##    ##      ####       ##          ####    ##   ##          ##         ##     # ## #   # ## #    ##      ##        #     ##     ##      ##  ##  ##   ##  # ## #       ###      ##        ##     ##  ##     ##");
        System.out.println("  ##   #  ##       #######            ## ##     ##     #### ##   ##  ##  ##   ##   ##      ##  ##       ##        ##  ##   ##   ##          ##         ##       ##       ##      ##      ##        #     ##     ##     ##       ##   ##    ##         ##       ##        ##      ####      ##");
        System.out.println("  ######  ######   #######            ## ##     ##     ## ####   ##  ##  ##   ##   ##      ##  ##       ##        ##  ##   ##   ##          ##         ##       ##       ##      ##      #######   ########     ##     ##       #######    ##         #####    ##        ##       ##       ##");
        System.out.println("  ##  ##  ##       ## # ##             ###      ##     ##  ###   ##  ##  ##   ##   ##      ######       ##        ######   ##   ##          ##         ##       ##       ##      ##   #  ##        ##    ###    ##     ##  ###  ##   ##    ##         ##       ##   #    ##      ####        ");
        System.out.println("  ##  ##  ##       ##   ##             ###      ##     ##   ##   ## ##   ##   ##    ##     ##  ##      ##         ##  ##   ##   ##          ####       ##       ##       ##      ##  ##  ##        ##    ###    ##      ##  ##  ##   ##    ##         ##       ##  ##    ##     ##  ##    ####");
        System.out.println("  #####   ######   ##   ##              #      ####    ##   ##  #####     #####      ##    ##  ##     ##          ##  ##    #####           #######   ####     ####     ####    #######  #######  ####   ####  ####      #####  ##   ##   ####        ###     #######   ####    ##  ##    ####");
        System.out.println("\n--- Bem-vindo ao LITTLE RIGHT FLIX ---");
        System.out.println("O streaming dos *certinhos* do Arthur Kronbauer!\n");
    }

    private void carregarDadosIniciais() {
        // Filmes Iniciais
        midias.add(new Filme("Titanic", "Romance/Drama", 1997, "James Cameron", 195));
        midias.add(new Filme("O Poderoso Chefão", "Crime/Drama", 1972, "Francis Ford Coppola", 175));
        midias.add(new Filme("Prenda-me se for Capaz", "Biografia/Crime", 2002, "Steven Spielberg", 141));
        midias.add(new Filme("O Dono do Jogo", "Biografia/Drama", 2015, "Stephen Frears", 115));
        midias.add(new Filme("Vingadores: Ultimato", "Ação/Ficção", 2019, "Anthony e Joe Russo", 181));
        midias.add(new Filme("Um Sonho de Liberdade", "Drama", 1994, "Frank Darabont", 142));
        midias.add(new Filme("Star Wars: A Vingança dos Sith", "Ficção/Aventura", 2005, "George Lucas", 140));
        midias.add(new Filme("Mortal Kombat", "Ação/Fantasia", 2021, "Simon McQuoid", 110));
        midias.add(new Filme("Dragon Ball Super: Broly", "Animação/Ação", 2018, "Tatsuya Nagamine", 100));
        midias.add(new Filme("Kronbauer: O Codificador", "Documentário", 2023, "Arthur K.", 90));

        // Séries Iniciais
        midias.add(new Serie("Death Note", "Animação/Suspense", 2006, 1, 37));
        midias.add(new Serie("One Piece", "Aventura/Fantasia", 1999, 21, 50));
        midias.add(new Serie("The Flash", "Ação/Ficção", 2014, 9, 20));
        midias.add(new Serie("Peaky Blinders", "Crime/Drama", 2013, 6, 6));
        midias.add(new Serie("La Casa de Papel", "Crime/Suspense", 2017, 5, 8));
        midias.add(new Serie("Vinland Saga", "Animação/Aventura", 2019, 2, 24));
        midias.add(new Serie("Breaking Bad", "Crime/Drama", 2008, 5, 13));
        midias.add(new Serie("Attack on Titan", "Animação/Ação", 2013, 4, 22));
    }

    @Override
    public void adicionarConteudo(Midia midia) {
        if (midia != null) {
            midias.add(midia);
            System.out.println("'" + midia.getTitulo() + "' adicionado(a) ao catálogo.");
        } else {
            System.out.println("Não foi possível adicionar a mídia (dados inválidos).");
        }
    }

    @Override
    public List<Midia> listarConteudos() {
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada no momento.");
        } else {
            System.out.println("\n--- Catálogo Completo ---");
            System.out.printf("%-4s | %-30s | %-20s | %-4s | %-40s\n", "Tipo", "Título", "Gênero", "Ano", "Detalhes Específicos");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            for (Midia midia : midias) {
                String tipo = "";
                String detalhesEspecificos = "";
                if (midia instanceof Filme) {
                    tipo = "Film"; 
                    Filme filme = (Filme) midia;
                    detalhesEspecificos = "Dir: " + filme.getDiretor() + ", " + filme.getDuracaoMinutos() + " min";
                } else if (midia instanceof Serie) {
                    tipo = "Série";
                    Serie serie = (Serie) midia;
                    detalhesEspecificos = serie.getNumeroTemporadas() + " temps, " + serie.getEpisodiosPorTemporada() + " eps/temp";
                }
                System.out.printf("%-4s | %-30.30s | %-20.20s | %-4d | %-40.40s\n",
                        tipo, midia.getTitulo(), midia.getGenero(), midia.getAnoLancamento(), detalhesEspecificos);
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        }
        return new ArrayList<>(midias);
    }

    public void listarApenasFilmes() {
        System.out.println("\n--- Filmes no Catálogo ---");
        boolean encontrou = false;
        System.out.printf("%-30s | %-20s | %-4s | %-20s | %-5s\n", "Título", "Gênero", "Ano", "Diretor", "Duração");
        System.out.println("---------------------------------------------------------------------------------------------------");
        for (Midia midia : midias) {
            if (midia instanceof Filme) {
                Filme filme = (Filme) midia;
                System.out.printf("%-30.30s | %-20.20s | %-4d | %-20.20s | %-5d min\n",
                        filme.getTitulo(), filme.getGenero(), filme.getAnoLancamento(),
                        filme.getDiretor(), filme.getDuracaoMinutos());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum filme cadastrado.");
        }
        System.out.println("---------------------------------------------------------------------------------------------------");
    }

    public void listarApenasSeries() {
        System.out.println("\n--- Séries no Catálogo ---");
        boolean encontrou = false;
        System.out.printf("%-30s | %-20s | %-4s | %-4s | %-4s\n", "Título", "Gênero", "Ano", "Temp", "Eps");
        System.out.println("------------------------------------------------------------------------------------------");
        for (Midia midia : midias) {
            if (midia instanceof Serie) {
                Serie serie = (Serie) midia;
                System.out.printf("%-30.30s | %-20.20s | %-4d | %-4d | %-4d\n",
                        serie.getTitulo(), serie.getGenero(), serie.getAnoLancamento(),
                        serie.getNumeroTemporadas(), serie.getEpisodiosPorTemporada());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma série cadastrada.");
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }

    @Override
    public Midia buscarPorTitulo(String titulo) {
        for (Midia midia : midias) {
            if (midia.getTitulo().equalsIgnoreCase(titulo.trim())) {
                return midia;
            }
        }
        return null;
    }

    public static void abrirVideo(String caminhoArquivo) {
        try {
            String so = System.getProperty("os.name").toLowerCase();
            String comando;
            if (so.contains("win")) {
                comando = "cmd /c start \"\" \"" + caminhoArquivo + "\"";
            } else if (so.contains("nix") || so.contains("nux") || so.contains("mac")) {
                comando = so.contains("mac") ? "open \"" + caminhoArquivo + "\"" : "xdg-open \"" + caminhoArquivo + "\"";
            } else {
                System.out.println("Sistema operacional não suportado para abrir vídeo automaticamente.");
                return;
            }
            Runtime.getRuntime().exec(comando);
            System.out.println("Tentando abrir o vídeo: " + caminhoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao tentar abrir o vídeo: " + e.getMessage());
        }
    }

    public static List<String> obterListaTitulosFilmesDeArquivo(String caminhoFilmesTxt) {
        List<String> listaTitulos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoFilmesTxt))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                listaTitulos.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de títulos de filmes: " + e.getMessage());
        }
        return listaTitulos;
    }

    public void adicionarNovoFilme() {
        System.out.println("\n--- Adicionar Novo Filme ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        int ano = 0;
        while (true) {
            System.out.print("Ano de Lançamento: ");
            try {
                ano = Integer.parseInt(scanner.nextLine());
                if (ano > 1800 && ano < 2100) break; // Validação básica
                System.out.println("Ano inválido, tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para ano. Por favor, digite um número.");
            }
        }
        System.out.print("Diretor: ");
        String diretor = scanner.nextLine();
        int duracao = 0;
        while (true) {
            System.out.print("Duração (minutos): ");
            try {
                duracao = Integer.parseInt(scanner.nextLine());
                if (duracao > 0) break;
                System.out.println("Duração inválida, tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para duração. Por favor, digite um número.");
            }
        }
        Filme novoFilme = new Filme(titulo, genero, ano, diretor, duracao);
        adicionarConteudo(novoFilme);
    }

    public void adicionarNovaSerie() {
        System.out.println("\n--- Adicionar Nova Série ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        int ano = 0;
        while (true) {
            System.out.print("Ano de Lançamento: ");
            try {
                ano = Integer.parseInt(scanner.nextLine());
                if (ano > 1800 && ano < 2100) break;
                System.out.println("Ano inválido, tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para ano. Por favor, digite um número.");
            }
        }
        int temporadas = 0;
        while (true) {
            System.out.print("Número de Temporadas: ");
            try {
                temporadas = Integer.parseInt(scanner.nextLine());
                if (temporadas > 0) break;
                System.out.println("Número de temporadas inválido, tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
        int episodios = 0;
        while (true) {
            System.out.print("Média de Episódios por Temporada: ");
            try {
                episodios = Integer.parseInt(scanner.nextLine());
                if (episodios > 0) break;
                System.out.println("Número de episódios inválido, tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
        Serie novaSerie = new Serie(titulo, genero, ano, temporadas, episodios);
        adicionarConteudo(novaSerie);
    }

    public void removerMidia() {
        System.out.println("\n--- Remover Mídia ---");
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para remover.");
            return;
        }
        System.out.print("Digite o título da mídia a ser removida: ");
        String tituloParaRemover = scanner.nextLine();

        Midia midiaParaRemover = buscarPorTitulo(tituloParaRemover);

        if (midiaParaRemover != null) {
            midias.remove(midiaParaRemover);
            System.out.println("'" + midiaParaRemover.getTitulo() + "' foi removido(a) com sucesso.");
        } else {
            System.out.println("Mídia com o título '" + tituloParaRemover + "' não encontrada.");
        }
    }

    /**
     * Permite ao usuário alterar os dados de uma mídia existente.
     * Solicita o título da mídia e, se encontrada, permite a edição
     * de seus atributos comuns e específicos (para Filme ou Série).
     */
    public void alterarMidia() {
        System.out.println("\n--- Alterar Mídia ---");
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para alterar.");
            return;
        }
        System.out.print("Digite o título da mídia a ser alterada: ");
        String tituloParaAlterar = scanner.nextLine();

        Midia midiaParaAlterar = null;
        // Loop para encontrar a mídia ignorando maiúsculas/minúsculas no título fornecido
        for (Midia midia : midias) {
            if (midia.getTitulo().equalsIgnoreCase(tituloParaAlterar)) {
                midiaParaAlterar = midia;
                break;
            }
        }

        if (midiaParaAlterar == null) {
            System.out.println("Mídia com o título '" + tituloParaAlterar + "' não encontrada.");
            return;
        }

        System.out.println("Mídia encontrada: " + midiaParaAlterar.getDescricao()); // Usar getDescricao() é mais informativo
        System.out.println("Quais informações você deseja alterar? (Deixe em branco para não alterar o campo)");

        // Campos comuns a todas as mídias
        System.out.print("Novo Título (atual: " + midiaParaAlterar.getTitulo() + "): ");
        String novoTitulo = scanner.nextLine();
        if (!novoTitulo.trim().isEmpty()) {
            midiaParaAlterar.setTitulo(novoTitulo);
        }

        System.out.print("Novo Gênero (atual: " + midiaParaAlterar.getGenero() + "): ");
        String novoGenero = scanner.nextLine();
        if (!novoGenero.trim().isEmpty()) {
            midiaParaAlterar.setGenero(novoGenero);
        }

        System.out.print("Novo Ano de Lançamento (atual: " + midiaParaAlterar.getAnoLancamento() + ", ou 0 para não alterar): ");
        String novoAnoStr = scanner.nextLine();
        if (!novoAnoStr.trim().isEmpty()) {
            try {
                int novoAno = Integer.parseInt(novoAnoStr);
                if (novoAno == 0) {
                    
                } else if (novoAno > 1800 && novoAno < 2100) { // Validação simples de ano
                    midiaParaAlterar.setAnoLancamento(novoAno);
                } else {
                    System.out.println("Ano inválido. Mantendo o original: " + midiaParaAlterar.getAnoLancamento());
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato de ano inválido. Mantendo o original: " + midiaParaAlterar.getAnoLancamento());
            }
        }

        // Campos específicos
        if (midiaParaAlterar instanceof Filme) {
            Filme filme = (Filme) midiaParaAlterar;
            System.out.print("Novo Diretor (atual: " + filme.getDiretor() + "): ");
            String novoDiretor = scanner.nextLine();
            if (!novoDiretor.trim().isEmpty()) {
                filme.setDiretor(novoDiretor);
            }

            System.out.print("Nova Duração em minutos (atual: " + filme.getDuracaoMinutos() + ", ou 0 para não alterar): ");
            String novaDuracaoStr = scanner.nextLine();
            if (!novaDuracaoStr.trim().isEmpty()) {
                try {
                    int novaDuracao = Integer.parseInt(novaDuracaoStr);
                    if (novaDuracao == 0) {
                        
                    } else if (novaDuracao > 0) {
                        filme.setDuracaoMinutos(novaDuracao);
                    } else {
                        System.out.println("Duração inválida. Mantendo a original: " + filme.getDuracaoMinutos());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Formato de duração inválido. Mantendo a original: " + filme.getDuracaoMinutos());
                }
            }
        } else if (midiaParaAlterar instanceof Serie) {
            Serie serie = (Serie) midiaParaAlterar;
            System.out.print("Novo Número de Temporadas (atual: " + serie.getNumeroTemporadas() + ", ou 0 para não alterar): ");
            String novasTemporadasStr = scanner.nextLine();
            if (!novasTemporadasStr.trim().isEmpty()) {
                try {
                    int novasTemporadas = Integer.parseInt(novasTemporadasStr);
                    if (novasTemporadas == 0) {
                    
                    } else if (novasTemporadas > 0) {
                        serie.setNumeroTemporadas(novasTemporadas);
                    } else {
                        System.out.println("Número de temporadas inválido. Mantendo o original: " + serie.getNumeroTemporadas());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Formato de temporadas inválido. Mantendo o original: " + serie.getNumeroTemporadas());
                }
            }

            System.out.print("Nova Média de Episódios por Temporada (atual: " + serie.getEpisodiosPorTemporada() + ", ou 0 para não alterar): ");
            String novosEpisodiosStr = scanner.nextLine();
            if (!novosEpisodiosStr.trim().isEmpty()) {
                try {
                    int novosEpisodios = Integer.parseInt(novosEpisodiosStr);
                    if (novosEpisodios == 0) {
                       
                    } else if (novosEpisodios > 0) {
                        serie.setEpisodiosPorTemporada(novosEpisodios);
                    } else {
                        System.out.println("Número de episódios inválido. Mantendo o original: " + serie.getEpisodiosPorTemporada());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Formato de episódios inválido. Mantendo o original: " + serie.getEpisodiosPorTemporada());
                }
            }
        }
        System.out.println("Mídia alterada com sucesso!");
        System.out.println("Dados atualizados: " + midiaParaAlterar.getDescricao());
    }


    public void listarConteudosOrdenadosPorTitulo() {
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para ordenar.");
            return;
        }
        List<Midia> midiasOrdenadas = new ArrayList<>(this.midias);

        midiasOrdenadas.sort(Comparator.comparing(Midia::getTitulo, String.CASE_INSENSITIVE_ORDER));

        System.out.println("\n--- Catálogo Completo Ordenado por Título ---");
        System.out.printf("%-4s | %-30s | %-20s | %-4s | %-40s\n", "Tipo", "Título", "Gênero", "Ano", "Detalhes Específicos");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        for (Midia midia : midiasOrdenadas) {
            String tipo = "";
            String detalhesEspecificos = "";
            if (midia instanceof Filme) {
                tipo = "Film";
                Filme filme = (Filme) midia;
                detalhesEspecificos = "Dir: " + filme.getDiretor() + ", " + filme.getDuracaoMinutos() + " min";
            } else if (midia instanceof Serie) {
                tipo = "Série";
                Serie serie = (Serie) midia;
                detalhesEspecificos = serie.getNumeroTemporadas() + " temps, " + serie.getEpisodiosPorTemporada() + " eps/temp";
            }
            System.out.printf("%-4s | %-30.30s | %-20.20s | %-4d | %-40.40s\n",
                    tipo, midia.getTitulo(), midia.getGenero(), midia.getAnoLancamento(), detalhesEspecificos);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    }
}
