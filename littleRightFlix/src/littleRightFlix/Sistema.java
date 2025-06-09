package littleRightFlix;

import interfaces.GerenciadorConteudo;
import model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Sistema implements GerenciadorConteudo {
    private final List<Midia> midias; // Não inicializa aqui, será feito no construtor
    private final Scanner scanner = new Scanner(System.in);
    Locale local = null;
    
    
    // Nome do arquivo para persistência de dados
    private static final String NOME_ARQUIVO_DADOS = "C:\\Users\\DELL\\Desktop\\midias.txt";

    public Sistema() throws IOException {
        this.midias = carregarMidiasDoTxt(NOME_ARQUIVO_DADOS);
        carregarMidiasDoTxt("C:\\Users\\DELL\\Desktop\\midias.txt");
        local = new Locale("pt", "BR");
        Locale.setDefault(Locale.of("pt", "BR"));
        
        
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="Persistência de Dados">
    @SuppressWarnings("unchecked") // Suprime o warning de cast não verificado para List<Midia>
    
    
    

	public static void exibirMenu() {
		System.out.println("\n--- MENU LittleRightFlix ---");
		System.out.println("1.  Adicionar Novo Filme");
		System.out.println("2.  Adicionar Nova Série (com opção para episódios iniciais)");
		System.out.println("3.  Alterar Mídia Existente");
		System.out.println("4.  Remover Mídia");
		System.out.println("5.  Listar Todas as Mídias");
		System.out.println("6.  Listar Apenas Filmes");
		System.out.println("7.  Listar Apenas Séries");
		System.out.println("8.  Buscar Mídia por Título");
		System.out.println("9.  Listar Todas as Mídias Ordenadas por Título");
		System.out.println("10. Listar Todas as Mídias Ordenadas por Ano");
		System.out.println("11. Listar Todas as Mídias Ordenadas por Gênero");
		System.out.println("12. Gerenciar Episódios de uma Série");
		System.out.println("13. Buscar/Filtrar Mídias por Gênero");
		System.out.println("14. Buscar/Filtrar Mídias por Ano");
		System.out.println("15. Buscar/Filtrar Filmes por Diretor");
		System.out.println("16. Tentar Abrir Vídeo de Filme (da lista .txt)");
		System.out.println("0.  Sair");
		System.out.print("Escolha uma opção: ");
	}
	
	
    
    public static List<Midia> carregarMidiasDoTxt(String caminho) throws IOException {
        List<Midia> midias = new ArrayList<>();
        Map<String, Serie> seriesMap = new HashMap<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";", -1);
                String tipo = partes[0];

                switch (tipo) {
                    case "F": {
                        // F;Título;Gênero;Ano;Diretor;Duração
                        String titulo = partes[1].trim();
                        String genero = partes[2].trim();
                        int ano = Integer.parseInt(partes[3].trim());
                        String diretor = partes[4].trim();
                        int duracao = Integer.parseInt(partes[5].trim());

                        Filme filme = new Filme(titulo, genero, ano, diretor, duracao);
                        midias.add(filme);
                        break;
                    }

                    case "S": {
                        // S;Título;Gênero;Ano;Temporadas;EpsPorTemporada
                        String titulo = partes[1].trim();
                        String genero = partes[2].trim();
                        int ano = Integer.parseInt(partes[3].trim());
                        int temporadas = Integer.parseInt(partes[4].trim());
                        int epsPorTemporada = Integer.parseInt(partes[5].trim());

                        Serie serie = new Serie(titulo, genero, ano, temporadas, epsPorTemporada);
                        midias.add(serie);
                        seriesMap.put(titulo, serie);
                        break;
                    }

                    case "E": {
                        // E;NomeDaSérie;NúmeroDoEpisódio;TítuloDoEpisódio
                        String nomeSerie = partes[1].trim();
                        int numero = Integer.parseInt(partes[2].trim());
                        String tituloEp = partes[3].trim();

                        Serie serie = seriesMap.get(nomeSerie);
                        if (serie != null) {
                            Episodio episodio = new Episodio(nomeSerie, 1, numero, tituloEp); // Temporada = 1 por padrão
                            serie.adicionarEpisodio(episodio);
                        } else {
                            System.out.println("Série não encontrada para episódio: " + nomeSerie);
                        }
                        break;
                    }

                    default:
                        System.out.println("Tipo desconhecido na linha: " + linha);
                }
            }
        }

        return midias;
    }
    
    
    
    public void salvarFilmeNoFinalDoArquivo(Filme filme, String caminhoArquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            bw.write(String.format("F;%s;%s;%d;%s;%d", 
                    filme.getTitulo(), 
                    filme.getGenero(), 
                    filme.getAnoLancamento(), 
                    filme.getDiretor(), 
                    filme.getDuracaoMinutos()));
            bw.newLine();
            System.out.println("Filme salvo no final do arquivo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar filme: " + e.getMessage());
        }
    }

    public static void salvarMidiasEmTxt(List<Midia> midias, String caminho) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))) {
            for (Midia midia : midias) {
                if (midia instanceof Filme) {
                    Filme f = (Filme) midia;
                    writer.printf("F;%s;%s;%d;%s;%d%n",
                            f.getTitulo(), f.getGenero(), f.getAnoLancamento(), f.getDiretor(), f.getDuracaoMinutos());
                } else if (midia instanceof Serie) {
                    Serie s = (Serie) midia;
                    writer.printf("S;%s;%s;%d;%d;%d%n",
                            s.getTitulo(), s.getGenero(), s.getAnoLancamento(), s.getNumTemporadas(), s.getEpisodiosPorTemporada());

                    // Salvar episódios da série
                    for (Episodio ep : s.getEpisodiosAdicionados()){
                        writer.printf("E;%s;%d;%s%n",
                                ep.getTituloSerie(), ep.getNumero(), ep.getTitulo());
                    }
                }
            }

            System.out.println("Midias salvas com sucesso em: " + caminho);
        } catch (IOException e) {
            System.err.println("Erro ao salvar mídias no TXT: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    // <editor-fold defaultstate="collapsed" desc="ASCII Art e Dados Iniciais">
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

   

    // <editor-fold defaultstate="collapsed" desc="Métodos Auxiliares de Impressão Tabular">
    private static final String SEPARADOR_LINHA_LONGA = String.join("", Collections.nCopies(128, "-"));
    private static final String SEPARADOR_LINHA_FILMES = String.join("", Collections.nCopies(104, "-"));
    private static final String SEPARADOR_LINHA_SERIES_CORRIGIDO = String.join("", Collections.nCopies(102, "-"));


    private void imprimirCabecalhoComum(String tituloLista) {
        System.out.println("\n--- " + tituloLista.toUpperCase() + " ---");
        System.out.printf("%-6s | %-35s | %-25s | %-5s | %-45s\n",
                "TIPO", "TÍTULO", "GÊNERO", "ANO", "DETALHES ESPECÍFICOS");
        System.out.println(SEPARADOR_LINHA_LONGA);
    }

    private String obterDetalhesEspecificosConcatenados(Midia midia) {
        if (midia instanceof Filme filme) {
            return "Dir: " + filme.getDiretor() + ", " + filme.getDuracaoMinutos() + " min";
        } else if (midia instanceof Serie serie) {
            return serie.getNumTemporadas() + " temps, " + serie.getEpisodiosPorTemporada() + " eps/temp (" + serie.getEpisodiosAdicionados().size() + " ep(s) cadastrados)";
        }
        return "N/A";
    }

    private void imprimirLinhaMidiaFormatada(Midia midia) {
        String tipo = "";
        if (midia instanceof Filme) {
            tipo = "Filme";
        } else if (midia instanceof Serie) {
            tipo = "Série";
        }
        String detalhesEspecificosConcatenados = obterDetalhesEspecificosConcatenados(midia);

        System.out.printf("%-6s | %-35.35s | %-25.25s | %-5d | %-45.45s\n",
                tipo, midia.getTitulo(), midia.getGenero(), midia.getAnoLancamento(), detalhesEspecificosConcatenados);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CRUD e Listagens Principais">
    @Override
    public void adicionarConteudo(Midia midia) {
        if (midia != null) {
            this.midias.add(midia); // Adiciona à lista da instância
            System.out.println("'" + midia.getTitulo() + "' adicionado(a) ao catálogo.");
        } else {
            System.out.println("Não foi possível adicionar a mídia (dados inválidos).");
        }
    }

    @Override
    public List<Midia> listarConteudos() {
        if (this.midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada no momento.");
        } else {
            imprimirCabecalhoComum("Catálogo Completo");
            for (Midia midia : this.midias) {
                imprimirLinhaMidiaFormatada(midia);
            }
            System.out.println(SEPARADOR_LINHA_LONGA);
        }
        return new ArrayList<>(this.midias); // Retorna cópia
    }

    public void listarApenasFilmes() {
        System.out.println("\n--- FILMES NO CATÁLOGO ---");
        boolean encontrou = false;
        System.out.printf("%-35s | %-25s | %-5s | %-20s | %-10s\n", "TÍTULO", "GÊNERO", "ANO", "DIRETOR", "DURAÇÃO");
        System.out.println(SEPARADOR_LINHA_FILMES);
        for (Midia midia : this.midias) {
            if (midia instanceof Filme filme) {
                System.out.printf("%-35.35s | %-25.25s | %-5d | %-20.20s | %-7d min\n",
                        filme.getTitulo(), filme.getGenero(), filme.getAnoLancamento(),
                        filme.getDiretor(), filme.getDuracaoMinutos());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum filme cadastrado.");
        }
        System.out.println(SEPARADOR_LINHA_FILMES);
    }

    public void listarApenasSeries() {
        System.out.println("\n--- SÉRIES NO CATÁLOGO ---");
        boolean encontrou = false;
        System.out.printf("%-35s | %-25s | %-5s | %-5s | %-8s | %-12s\n", "TÍTULO", "GÊNERO", "ANO", "TEMPS", "EPS/TEMP", "EPS CADAST.");
        System.out.println(SEPARADOR_LINHA_SERIES_CORRIGIDO);

        for (Midia midia : this.midias) {
            if (midia instanceof Serie serie) {
                System.out.printf("%-35.35s | %-25.25s | %-5d | %-5d | %-8d | %-12d\n",
                        serie.getTitulo(), serie.getGenero(), serie.getAnoLancamento(),
                        serie.getNumTemporadas(), serie.getEpisodiosPorTemporada(),
                        serie.getEpisodiosAdicionados().size());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma série cadastrada.");
        }
        System.out.println(SEPARADOR_LINHA_SERIES_CORRIGIDO);
    }


    @Override
    public Midia buscarPorTitulo(String titulo) {
        for (Midia midia : this.midias) {
            if (midia.getTitulo().equalsIgnoreCase(titulo.trim())) {
                return midia;
            }
        }
        return null;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos de Adicionar (com validação)">
    public void adicionarNovoFilme() {
        System.out.println("\n--- Adicionar Novo Filme ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        int ano;
        while (true) {
            System.out.print("Ano de Lançamento (ex: 2023): ");
            try {
                ano = Integer.parseInt(scanner.nextLine());
                if (ano > 1800 && ano < 2100) break;
                System.out.println("Ano inválido. Por favor, insira um ano entre 1801 e 2099.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para ano. Por favor, digite um número.");
            }
        }
        System.out.print("Diretor: ");
        String diretor = scanner.nextLine();
        int duracao;
        while (true) {
            System.out.print("Duração (minutos): ");
            try {
                duracao = Integer.parseInt(scanner.nextLine());
                if (duracao > 0) break;
                System.out.println("Duração deve ser um número positivo. Tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para duração. Por favor, digite um número.");
            }
        }
        Filme novoFilme = new Filme(titulo, genero, ano, diretor, duracao);
        adicionarConteudo(novoFilme);
        salvarFilmeNoFinalDoArquivo(novoFilme,NOME_ARQUIVO_DADOS);
    }

    public void adicionarNovaSerie() {
        System.out.println("\n--- Adicionar Nova Série ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        int ano;
        while (true) {
            System.out.print("Ano de Lançamento (ex: 2023): ");
            try {
                ano = Integer.parseInt(scanner.nextLine());
                if (ano > 1900 && ano < 2100) break;
                System.out.println("Ano inválido. Tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }

        int numTemporadas;
        while (true) {
            System.out.print("Número de temporadas: ");
            try {
                numTemporadas = Integer.parseInt(scanner.nextLine());
                if (numTemporadas > 0) break;
                System.out.println("Deve ser positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Número inválido.");
            }
        }

        int epsPorTemporada;
        while (true) {
            System.out.print("Episódios por temporada: ");
            try {
                epsPorTemporada = Integer.parseInt(scanner.nextLine());
                if (epsPorTemporada > 0) break;
                System.out.println("Deve ser positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Número inválido.");
            }
        }

        // Cria a série
        Serie novaSerie = new Serie(titulo, genero, ano, numTemporadas, epsPorTemporada);

        // Adiciona episódios manualmente (opcional)
        System.out.print("Deseja adicionar episódios agora? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        if (resposta.equals("s")) {
            for (int i = 1; i <= epsPorTemporada; i++) {
                System.out.printf("Título do episódio %d da temporada 1: ", i);
                String tituloEp = scanner.nextLine();
                Episodio ep = new Episodio(titulo, 1, i, tituloEp); // Temporada = 1 por padrão
                novaSerie.adicionarEpisodio(ep);
            }
        }

        // Adiciona à lista
        midias.add(novaSerie);
        salvarTodasMidiasNoArquivo(NOME_ARQUIVO_DADOS);
        System.out.println("Série adicionada com sucesso!");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos de Alterar e Remover">
    public void removerMidia() {
        System.out.println("\n--- Remover Mídia ---");
        if (this.midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para remover.");
            return;
        }

        System.out.print("Digite o título da mídia a ser removida: ");
        String tituloParaRemover = scanner.nextLine();

        boolean removido = this.midias.removeIf(midia -> midia.getTitulo().equalsIgnoreCase(tituloParaRemover));

        if (removido) {
            salvarTodasMidiasNoArquivo(NOME_ARQUIVO_DADOS); // <-- ESSA LINHA É O QUE FALTAVA
            System.out.println("Mídia com o título '" + tituloParaRemover + "' foi removida com sucesso.");
        } else {
            System.out.println("Mídia com o título '" + tituloParaRemover + "' não encontrada.");
        }
    }

    
    public void alterarMidia() {
        System.out.println("\n--- Alterar Mídia ---");
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada.");
            return;
        }

        System.out.print("Digite o título da mídia que deseja alterar: ");
        String titulo = scanner.nextLine().trim();

        Midia midia = buscarPorTitulo(titulo);
        if (midia == null) {
            System.out.println("Mídia não encontrada.");
            return;
        }

        System.out.println("Mídia encontrada:");
        System.out.println(midia.getDescricao());

        System.out.println("\nO que deseja alterar?");
        System.out.println("1. Título");
        System.out.println("2. Gênero");
        System.out.println("3. Ano de Lançamento");

        if (midia instanceof Filme) {
            System.out.println("4. Diretor");
            System.out.println("5. Duração (min)");
        } else if (midia instanceof Serie) {
            System.out.println("4. Número de Temporadas");
            System.out.println("5. Episódios por Temporada");
        }

        System.out.print("Escolha uma opção: ");
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                System.out.print("Novo título: ");
                String novoTitulo = scanner.nextLine().trim();
                midia.setTitulo(novoTitulo);
                break;
            case "2":
                System.out.print("Novo gênero: ");
                String novoGenero = scanner.nextLine().trim();
                midia.setGenero(novoGenero);
                break;
            case "3":
                System.out.print("Novo ano: ");
                try {
                    int novoAno = Integer.parseInt(scanner.nextLine());
                    midia.setAnoLancamento(novoAno);
                } catch (NumberFormatException e) {
                    System.out.println("Ano inválido.");
                    return;
                }
                break;
            case "4":
                if (midia instanceof Filme) {
                    System.out.print("Novo diretor: ");
                    String novoDiretor = scanner.nextLine().trim();
                    ((Filme) midia).setDiretor(novoDiretor);
                } else if (midia instanceof Serie) {
                    System.out.print("Novo número de temporadas: ");
                    try {
                        int novasTemporadas = Integer.parseInt(scanner.nextLine());
                        ((Serie) midia).setNumTemporadas(novasTemporadas);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido.");
                        return;
                    }
                }
                break;
            case "5":
                if (midia instanceof Filme) {
                    System.out.print("Nova duração (min): ");
                    try {
                        int novaDuracao = Integer.parseInt(scanner.nextLine());
                        ((Filme) midia).setDuracaoMinutos(novaDuracao);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido.");
                        return;
                    }
                } else if (midia instanceof Serie) {
                    System.out.print("Novo número de episódios por temporada: ");
                    try {
                        int novosEps = Integer.parseInt(scanner.nextLine());
                        ((Serie) midia).setEpisodiosPorTemporada(novosEps);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido.");
                        return;
                    }
                }
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        // Após alteração, salva no arquivo
        salvarTodasMidiasNoArquivo(NOME_ARQUIVO_DADOS);
        System.out.println("Mídia atualizada com sucesso!");
    }

    
    
    
    
    
    
    
    



    // <editor-fold defaultstate="collapsed" desc="Listagens Ordenadas">
    public void listarConteudosOrdenadosPorTitulo() {
        if (this.midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para ordenar.");
            return;
        }
        List<Midia> midiasOrdenadas = new ArrayList<>(this.midias);
        midiasOrdenadas.sort(Comparator.comparing(Midia::getTitulo, String.CASE_INSENSITIVE_ORDER));

        imprimirCabecalhoComum("Catálogo Completo Ordenado por Título");
        for (Midia midia : midiasOrdenadas) {
            imprimirLinhaMidiaFormatada(midia);
        }
        System.out.println(SEPARADOR_LINHA_LONGA);
    }

    public void listarConteudosOrdenadosPorAno() {
        if (this.midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para ordenar.");
            return;
        }
        List<Midia> midiasOrdenadas = new ArrayList<>(this.midias);
        midiasOrdenadas.sort(
                Comparator.comparingInt(Midia::getAnoLancamento)
                        .thenComparing(Midia::getTitulo, String.CASE_INSENSITIVE_ORDER)
        );

        imprimirCabecalhoComum("Catálogo Completo Ordenado por Ano de Lançamento");
        for (Midia midia : midiasOrdenadas) {
            imprimirLinhaMidiaFormatada(midia);
        }
        System.out.println(SEPARADOR_LINHA_LONGA);
    }

    public void listarConteudosOrdenadosPorGenero() {
        if (this.midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para ordenar.");
            return;
        }
        List<Midia> midiasOrdenadas = new ArrayList<>(this.midias);
        midiasOrdenadas.sort(
                Comparator.comparing(Midia::getGenero, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Midia::getTitulo, String.CASE_INSENSITIVE_ORDER)
        );

        imprimirCabecalhoComum("Catálogo Completo Ordenado por Gênero");
        for (Midia midia : midiasOrdenadas) {
            imprimirLinhaMidiaFormatada(midia);
        }
        System.out.println(SEPARADOR_LINHA_LONGA);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Buscas / Filtros Específicos">
    public void buscarPorGenero() {
        System.out.println("\n--- BUSCAR MÍDIAS POR GÊNERO ---");
        if (this.midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para buscar.");
            return;
        }
        System.out.print("Digite o gênero que deseja buscar (ex: Ação, Drama, Comédia): ");
        String generoBusca = scanner.nextLine().trim();

        if (generoBusca.isEmpty()) {
            System.out.println("Gênero não pode ser vazio.");
            return;
        }

        List<Midia> midiasEncontradas = new ArrayList<>();
        for (Midia midia : this.midias) {
            if (midia.getGenero().toLowerCase().contains(generoBusca.toLowerCase())) {
                midiasEncontradas.add(midia);
            }
        }

        if (midiasEncontradas.isEmpty()) {
            System.out.println("Nenhuma mídia encontrada para o gênero: " + generoBusca);
        } else {
            imprimirCabecalhoComum("Mídias Encontradas para o Gênero: " + generoBusca.toUpperCase());
            for (Midia midia : midiasEncontradas) {
                imprimirLinhaMidiaFormatada(midia);
            }
            System.out.println(SEPARADOR_LINHA_LONGA);
        }
    }

    public void buscarPorAno() {
        System.out.println("\n--- BUSCAR MÍDIAS POR ANO DE LANÇAMENTO ---");
        if (this.midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para buscar.");
            return;
        }
        System.out.print("Digite o ano de lançamento que deseja buscar (ex: 2023): ");
        String anoBuscaStr = scanner.nextLine().trim();
        int anoBusca;

        try {
            anoBusca = Integer.parseInt(anoBuscaStr);
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido. Por favor, digite um número (ex: 2023).");
            return;
        }

        List<Midia> midiasEncontradas = new ArrayList<>();
        for (Midia midia : this.midias) {
            if (midia.getAnoLancamento() == anoBusca) {
                midiasEncontradas.add(midia);
            }
        }

        if (midiasEncontradas.isEmpty()) {
            System.out.println("Nenhuma mídia encontrada para o ano: " + anoBusca);
        } else {
            imprimirCabecalhoComum("Mídias Encontradas para o Ano: " + anoBusca);
            for (Midia midia : midiasEncontradas) {
                imprimirLinhaMidiaFormatada(midia);
            }
            System.out.println(SEPARADOR_LINHA_LONGA);
        }
    }

    public void buscarFilmesPorDiretor() {
        System.out.println("\n--- BUSCAR FILMES POR DIRETOR ---");
        boolean existeAlgumFilme = false;
        for (Midia m : this.midias) {
            if (m instanceof Filme) {
                existeAlgumFilme = true;
                break;
            }
        }
        if (!existeAlgumFilme) {
            System.out.println("Nenhum filme cadastrado no sistema para buscar por diretor.");
            return;
        }

        System.out.print("Digite o nome do diretor que deseja buscar: ");
        String diretorBusca = scanner.nextLine().trim();

        if (diretorBusca.isEmpty()) {
            System.out.println("Nome do diretor não pode ser vazio.");
            return;
        }

        List<Filme> filmesEncontrados = new ArrayList<>();
        for (Midia midia : this.midias) {
            if (midia instanceof Filme filme) {
                if (filme.getDiretor().toLowerCase().contains(diretorBusca.toLowerCase())) {
                    filmesEncontrados.add(filme);
                }
            }
        }

        if (filmesEncontrados.isEmpty()) {
            System.out.println("Nenhum filme encontrado para o diretor: " + diretorBusca);
        } else {
            System.out.println("\n--- FILMES ENCONTRADOS PARA O DIRETOR: " + diretorBusca.toUpperCase() + " ---");
            System.out.printf("%-35s | %-25s | %-5s | %-20s | %-10s\n", "TÍTULO", "GÊNERO", "ANO", "DIRETOR", "DURAÇÃO");
            System.out.println(SEPARADOR_LINHA_FILMES);
            for (Filme filme : filmesEncontrados) {
                System.out.printf("%-35.35s | %-25.25s | %-5d | %-20.20s | %-7d min\n",
                        filme.getTitulo(), filme.getGenero(), filme.getAnoLancamento(),
                        filme.getDiretor(), filme.getDuracaoMinutos());
            }
            System.out.println(SEPARADOR_LINHA_FILMES);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gerenciamento de Episódios">
   

   
    
    
    private void listarEpisodiosDaSerie(Serie serie) {
        List<Episodio> episodios = serie.getEpisodiosAdicionados();
        System.out.println("\n--- Episódios da Série: " + serie.getTitulo() + " ---");
        if (episodios.isEmpty()) {
            System.out.println("Nenhum episódio cadastrado para esta série.");
        } else {
            episodios.sort(Comparator.comparingInt(Episodio::getNumero));
            System.out.printf("%-5s | %-50s\n", "Nº EP", "TÍTULO DO EPISÓDIO");
            System.out.println(String.join("", Collections.nCopies(60, "-")));
            for (Episodio ep : episodios) {
                System.out.printf("Ep.%-3d | %-50.50s\n", ep.getNumero(), ep.getTitulo());
            }
            System.out.println(String.join("", Collections.nCopies(60, "-")));
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Funcionalidades Extras (Ex: Abrir Vídeo)">
    public static void abrirVideo(String caminhoArquivo) {
        try {
            String so = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            if (so.contains("win")) {
                pb = new ProcessBuilder("cmd", "/c", "start", "\"\"", "\"" + caminhoArquivo + "\"");
            } else if (so.contains("mac")) {
                pb = new ProcessBuilder("open", caminhoArquivo);
            } else if (so.contains("nix") || so.contains("nux")) { // Linux
                pb = new ProcessBuilder("xdg-open", caminhoArquivo);
            } else {
                System.out.println("Sistema operacional não suportado para abrir vídeo automaticamente.");
                return;
            }
            pb.start();
            System.out.println("Tentando abrir o vídeo: " + caminhoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao tentar abrir o vídeo: " + e.getMessage());
        }
    }

    public static List<String> obterListaTitulosFilmesDeArquivo(String caminhoFilmesTxt) {
        List<String> listaTitulos = new ArrayList<>();
        try (FileReader fr = new FileReader(caminhoFilmesTxt);
             BufferedReader br = new BufferedReader(fr)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                listaTitulos.add(linha);
            }
        } catch (IOException e) {
            // System.err.println("Erro ao ler arquivo de títulos de filmes: " + e.getMessage());
        }
        return listaTitulos;
    }
    
    
    
    
    
    
    public void salvarSeriesEpisodiosNoFinal(String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) { // true = append
            for (Midia midia : midias) {
                if (midia instanceof Serie) {
                    Serie serie = (Serie) midia;
                    writer.write(String.format("S;%s;%s;%d;%d;%d\n",
                        serie.getTitulo(), serie.getGenero(), serie.getAnoLancamento(),
                        serie.getNumTemporadas(), serie.getEpisodiosPorTemporada()));

                    for (Episodio ep : serie.getEpisodiosAdicionados()) {
                        writer.write(String.format("E;%s;%d;%s\n",
                            ep.getTituloSerie(), ep.getNumero(), ep.getTitulo()));
                    }
                }
            }
            System.out.println("Séries e episódios salvos no final do arquivo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar séries no arquivo: " + e.getMessage());
        }
    }
    
    
    public void salvarTodasMidiasNoArquivo(String caminho) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))) {
            for (Midia midia : midias) {
                escritor.write(midia.paraLinhaArquivo());
                escritor.newLine();

                if (midia instanceof Serie) {
                    Serie serie = (Serie) midia;
                    for (Episodio ep : serie.getEpisodiosAdicionados()) {
                        escritor.write("E;" + serie.getTitulo() + ";" + ep.getNumero() + ";" + ep.getTitulo());
                        escritor.newLine();
                    }
                }
            }
            System.out.println("Mídias salvas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar mídias: " + e.getMessage());
        }
    }
    public void gerenciarEpisodiosDeSerie() {
        System.out.println("\n--- Gerenciar Episódios de Série ---");

        if (midias.stream().noneMatch(m -> m instanceof Serie)) {
            System.out.println("Nenhuma série cadastrada.");
            return;
        }

        System.out.print("Digite o título da série: ");
        String titulo = scanner.nextLine().trim();

        Midia midia = buscarPorTitulo(titulo);
        if (!(midia instanceof Serie)) {
            System.out.println("Série não encontrada.");
            return;
        }

        Serie serie = (Serie) midia;

        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Episódios da Série: " + serie.getTitulo() + " ---");
            System.out.println("1. Listar Episódios");
            System.out.println("2. Adicionar Episódio");
            System.out.println("3. Remover Episódio");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    List<Episodio> episodios = serie.getEpisodiosAdicionados();
                    if (episodios.isEmpty()) {
                        System.out.println("Nenhum episódio cadastrado.");
                    } else {
                        for (Episodio ep : episodios) {
                            System.out.println(ep); // toString do Episodio deve estar bonito
                        }
                    }
                    break;

                case "2":
                    System.out.print("Temporada: ");
                    int temporada = lerInteiroPositivo();
                    System.out.print("Número do Episódio: ");
                    int numeroEp = lerInteiroPositivo();
                    System.out.print("Título do Episódio: ");
                    String tituloEp = scanner.nextLine().trim();

                    Episodio novoEp = new Episodio(serie.getTitulo(), temporada, numeroEp, tituloEp);
                    serie.adicionarEpisodio(novoEp);
                    System.out.println("Episódio adicionado.");
                    break;

                case "3":
                    System.out.print("Digite o título do episódio a remover: ");
                    String tituloRemover = scanner.nextLine().trim();
                    boolean removido = serie.getEpisodiosAdicionados().removeIf(ep -> ep.getTitulo().equalsIgnoreCase(titulo));
                    if (removido) {
                        System.out.println("Episódio removido.");
                    } else {
                        System.out.println("Episódio não encontrado.");
                    }
                    break;

                case "0":
                    salvarTodasMidiasNoArquivo(NOME_ARQUIVO_DADOS);
                    System.out.println("Alterações salvas. Voltando ao menu.");
                    voltar = true;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    
    
    
    
    private int lerInteiroPositivo() {
        while (true) {  // repete pra sempre até dar return
            try {
                int valor = Integer.parseInt(scanner.nextLine());  // tenta converter
                if (valor > 0) return valor;                       // se for positivo, retorna
                System.out.print("Digite um valor positivo: ");    // se não for, pede de novo
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Tente novamente: ");  // se digitar letra, etc.
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
}