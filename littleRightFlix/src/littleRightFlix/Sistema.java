package littleRightFlix;

import interfaces.GerenciadorConteudo;
import model.*; // Assume que Midia, Filme, Serie estão neste pacote

import java.io.BufferedReader;
import java.io.File; // Import necessário
import java.io.FileInputStream; // Import necessário
import java.io.FileOutputStream; // Import necessário
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream; // Import necessário
import java.io.ObjectOutputStream; // Import necessário
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Sistema implements GerenciadorConteudo {
    private final List<Midia> midias; // Não inicializa aqui, será feito no construtor
    private final Scanner scanner = new Scanner(System.in);

    // Nome do arquivo para persistência de dados
    private static final String NOME_ARQUIVO_DADOS = "littlerightflix_dados.dat";

    public Sistema() {
        this.midias = new ArrayList<>(); // Inicializa a lista vazia primeiro
        carregarDados(); // Tenta carregar do arquivo
    }

    // <editor-fold defaultstate="collapsed" desc="Persistência de Dados">
    @SuppressWarnings("unchecked") // Suprime o warning de cast não verificado para List<Midia>
    private void carregarDados() {
        File arquivo = new File(NOME_ARQUIVO_DADOS);
        if (arquivo.exists() && !arquivo.isDirectory()) {
            try (FileInputStream fis = new FileInputStream(arquivo);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                List<Midia> midiasCarregadas = (List<Midia>) ois.readObject();
                this.midias.clear(); // Limpa a lista atual (caso houvesse algo)
                this.midias.addAll(midiasCarregadas); // Adiciona os dados carregados
                System.out.println("Dados carregados com sucesso de '" + NOME_ARQUIVO_DADOS + "'.");

            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                System.err.println("Erro ao carregar dados do arquivo '" + NOME_ARQUIVO_DADOS + "': " + e.getMessage());
                System.out.println("Utilizando dados iniciais padrão.");
                this.midias.clear(); // Garante que a lista esteja limpa antes de carregar dados iniciais
                carregarDadosIniciais(); // Fallback para dados iniciais
            }
        } else {
            System.out.println("Arquivo de dados '" + NOME_ARQUIVO_DADOS + "' não encontrado. Carregando dados iniciais padrão.");
            carregarDadosIniciais(); // Carrega dados iniciais se o arquivo não existe
        }
    }

    public void salvarDados() {
        try (FileOutputStream fos = new FileOutputStream(NOME_ARQUIVO_DADOS);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(new ArrayList<>(this.midias)); // Salva uma cópia para evitar ConcurrentModificationException se a lista for modificada em outra thread ( improvavel aqui, mas boa pratica)
            System.out.println("Dados salvos com sucesso em '" + NOME_ARQUIVO_DADOS + "'.");

        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo '" + NOME_ARQUIVO_DADOS + "': " + e.getMessage());
        }
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="ASCII Art e Dados Iniciais">
    public static void exibirAsciiArt() {
        System.out.println("  ######  ######   ##   ##           ##   ##   ####    ##   ##  #####     #####      ##      ##       ##            ##      #####           ##        ####    ######   ######   ####     #######   #######     ####      ####   ##   ##  ######       #####   ####      ####    ##  ##     ##");
        // ... (restante da ASCII art) ...
        System.out.println("  #####   ######   ##   ##              #      ####    ##   ##  #####     #####      ##    ##  ##     ##          ##  ##    #####           #######   ####     ####     ####    #######  #######  ####   ####  ####      #####  ##   ##   ####        ###     #######   ####    ##  ##    ####");
        System.out.println("\n--- Bem-vindo ao LITTLE RIGHT FLIX ---");
        System.out.println("O streaming dos *certinhos* do Arthur Kronbauer!\n");
    }

    private void carregarDadosIniciais() {
        // Este método será chamado se não houver arquivo de dados ou se houver erro ao carregá-lo.
        // Garante que a lista 'midias' esteja limpa antes de adicionar os dados iniciais.
        // this.midias.clear(); // Já é feito em carregarDados() antes de chamar este como fallback

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
        Serie deathNote = new Serie("Death Note", "Animação/Suspense", 2006, 1, 37);
        deathNote.adicionarEpisodio(new Episodio(1, "Renascer"));
        deathNote.adicionarEpisodio(new Episodio(2, "Confronto"));
        midias.add(deathNote);

        Serie onePiece = new Serie("One Piece", "Aventura/Fantasia", 1999, 21, 50);
        onePiece.adicionarEpisodio(new Episodio(1, "Eu sou Luffy! O Homem que se Tornará o Rei dos Piratas!"));
        midias.add(onePiece);

        midias.add(new Serie("The Flash", "Ação/Ficção", 2014, 9, 20));
        midias.add(new Serie("Peaky Blinders", "Crime/Drama", 2013, 6, 6));
        midias.add(new Serie("La Casa de Papel", "Crime/Suspense", 2017, 5, 8));
        midias.add(new Serie("Vinland Saga", "Animação/Aventura", 2019, 2, 24));
        midias.add(new Serie("Breaking Bad", "Crime/Drama", 2008, 5, 13));
        midias.add(new Serie("Attack on Titan", "Animação/Ação", 2013, 4, 22));
        System.out.println("Dados iniciais carregados no catálogo.");
    }
    // </editor-fold>

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
            return serie.getNumeroTemporadas() + " temps, " + serie.getEpisodiosPorTemporada() + " eps/temp (" + serie.getEpisodiosAdicionados().size() + " ep(s) cadastrados)";
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

    // ... (listarApenasFilmes, listarApenasSeries, buscarPorTitulo permanecem os mesmos) ...
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
                        serie.getNumeroTemporadas(), serie.getEpisodiosPorTemporada(),
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
                if (ano > 1800 && ano < 2100) break;
                System.out.println("Ano inválido. Por favor, insira um ano entre 1801 e 2099.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para ano. Por favor, digite um número.");
            }
        }
        int temporadas;
        while (true) {
            System.out.print("Número de Temporadas: ");
            try {
                temporadas = Integer.parseInt(scanner.nextLine());
                if (temporadas > 0) break;
                System.out.println("Número de temporadas deve ser positivo. Tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
        int episodiosPorTemporada;
        while (true) {
            System.out.print("Média de Episódios por Temporada: ");
            try {
                episodiosPorTemporada = Integer.parseInt(scanner.nextLine());
                if (episodiosPorTemporada > 0) break;
                System.out.println("Número de episódios deve ser positivo. Tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
        Serie novaSerie = new Serie(titulo, genero, ano, temporadas, episodiosPorTemporada);
        adicionarConteudo(novaSerie);

        System.out.print("Deseja adicionar episódios para '" + novaSerie.getTitulo() + "' agora? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            boolean adicionarMaisEpisodios = true;
            while (adicionarMaisEpisodios) {
                System.out.println("\nAdicionando episódio para: " + novaSerie.getTitulo());
                int numeroEp;
                while (true) {
                    System.out.print("Número do episódio: ");
                    try {
                        numeroEp = Integer.parseInt(scanner.nextLine());
                        if (numeroEp > 0) break;
                        System.out.println("Número do episódio deve ser positivo.");
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Digite um número.");
                    }
                }
                System.out.print("Título do episódio: ");
                String tituloEp = scanner.nextLine();

                try {
                    Episodio novoEpisodio = new Episodio(numeroEp, tituloEp);
                    novaSerie.adicionarEpisodio(novoEpisodio);
                    System.out.println("Episódio '" + novoEpisodio.getTitulo() + "' adicionado com sucesso!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro ao criar episódio: " + e.getMessage());
                }

                System.out.print("Deseja adicionar outro episódio para esta série? (s/n): ");
                if (!scanner.nextLine().trim().equalsIgnoreCase("s")) {
                    adicionarMaisEpisodios = false;
                }
            }
        }
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
            System.out.println("Mídia com o título '" + tituloParaRemover + "' foi removida com sucesso.");
        } else {
            System.out.println("Mídia com o título '" + tituloParaRemover + "' não encontrada.");
        }
    }

    public void alterarMidia() {
        System.out.println("\n--- Alterar Mídia ---");
        if (this.midias.isEmpty()) {
            System.out.println("Nenhuma mídia cadastrada para alterar.");
            return;
        }
        System.out.print("Digite o título da mídia a ser alterada: ");
        String tituloParaAlterar = scanner.nextLine();

        Midia midiaParaAlterar = null;
        for (Midia midia : this.midias) {
            if (midia.getTitulo().equalsIgnoreCase(tituloParaAlterar)) {
                midiaParaAlterar = midia;
                break;
            }
        }

        if (midiaParaAlterar == null) {
            System.out.println("Mídia com o título '" + tituloParaAlterar + "' não encontrada.");
            return;
        }

        System.out.println("Mídia encontrada: " + midiaParaAlterar.getDescricao());
        System.out.println("Quais informações você deseja alterar? (Deixe em branco para não alterar o campo)");

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
                if (novoAno != 0) {
                    if (novoAno > 1800 && novoAno < 2100) {
                        midiaParaAlterar.setAnoLancamento(novoAno);
                    } else {
                        System.out.println("Ano inválido. Mantendo o original: " + midiaParaAlterar.getAnoLancamento());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato de ano inválido. Mantendo o original: " + midiaParaAlterar.getAnoLancamento());
            }
        }

        if (midiaParaAlterar instanceof Filme filme) {
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
                    if (novaDuracao != 0) {
                        if (novaDuracao > 0) {
                            filme.setDuracaoMinutos(novaDuracao);
                        } else {
                            System.out.println("Duração inválida. Mantendo a original: " + filme.getDuracaoMinutos());
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Formato de duração inválido. Mantendo a original: " + filme.getDuracaoMinutos());
                }
            }
        } else if (midiaParaAlterar instanceof Serie serie) {
            System.out.print("Novo Número de Temporadas (atual: " + serie.getNumeroTemporadas() + ", ou 0 para não alterar): ");
            String novasTemporadasStr = scanner.nextLine();
            if (!novasTemporadasStr.trim().isEmpty()) {
                try {
                    int novasTemporadas = Integer.parseInt(novasTemporadasStr);
                    if (novasTemporadas != 0) {
                        if (novasTemporadas > 0) {
                            serie.setNumeroTemporadas(novasTemporadas);
                        } else {
                            System.out.println("Número de temporadas inválido. Mantendo o original: " + serie.getNumeroTemporadas());
                        }
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
                    if (novosEpisodios != 0) {
                        if (novosEpisodios > 0) {
                            serie.setEpisodiosPorTemporada(novosEpisodios);
                        } else {
                            System.out.println("Número de episódios inválido. Mantendo o original: " + serie.getEpisodiosPorTemporada());
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Formato de episódios inválido. Mantendo o original: " + serie.getEpisodiosPorTemporada());
                }
            }
        }
        System.out.println("Mídia alterada com sucesso!");
        System.out.println("Dados atualizados: " + midiaParaAlterar.getDescricao());
    }
    // </editor-fold>

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
    public void gerenciarEpisodiosDeSerie() {
        System.out.println("\n--- Gerenciar Episódios de Série ---");
        if (this.midias.stream().noneMatch(m -> m instanceof Serie)) {
            System.out.println("Nenhuma série cadastrada no sistema para gerenciar episódios.");
            return;
        }

        System.out.print("Digite o título da série para gerenciar os episódios: ");
        String tituloSerie = scanner.nextLine();

        Midia midiaEncontrada = buscarPorTitulo(tituloSerie);

        if (midiaEncontrada == null) {
            System.out.println("Série com o título '" + tituloSerie + "' não encontrada.");
            return;
        }

        if (!(midiaEncontrada instanceof Serie serieSelecionada)) {
            System.out.println("A mídia encontrada não é uma série: " + midiaEncontrada.getTitulo());
            return;
        }

        System.out.println("Gerenciando episódios para a série: " + serieSelecionada.getTitulo());

        boolean voltar = false;
        while (!voltar) {
            System.out.println("\nOpções de Gerenciamento de Episódios para '" + serieSelecionada.getTitulo() + "':");
            System.out.println("1. Adicionar Novo Episódio");
            System.out.println("2. Listar Episódios da Série");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            String opcaoEp = scanner.nextLine().trim();

            switch (opcaoEp) {
                case "1":
                    adicionarNovoEpisodioASerie(serieSelecionada);
                    break;
                case "2":
                    listarEpisodiosDaSerie(serieSelecionada);
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private void adicionarNovoEpisodioASerie(Serie serie) {
        System.out.println("\nAdicionando novo episódio para: " + serie.getTitulo());
        int numeroEp;
        while (true) {
            System.out.print("Número do episódio: ");
            try {
                numeroEp = Integer.parseInt(scanner.nextLine());
                if (numeroEp > 0) break;
                System.out.println("Número do episódio deve ser positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
        System.out.print("Título do episódio: ");
        String tituloEp = scanner.nextLine();

        try {
            Episodio novoEpisodio = new Episodio(numeroEp, tituloEp);
            serie.adicionarEpisodio(novoEpisodio);
            System.out.println("Episódio '" + novoEpisodio.getTitulo() + "' adicionado com sucesso à série '" + serie.getTitulo() + "'!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar episódio: " + e.getMessage());
        }
    }

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
    // </editor-fold>
}