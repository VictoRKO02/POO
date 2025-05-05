package littleRightFlix;

import interfaces.GerenciadorConteudo;
import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema implements GerenciadorConteudo {
    private List<Midia> midias = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    
    public void exibirAsciiArt() {
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
    public void carregarDadosIniciais() {
        midias.add(new Filme("Kronbauer: O Codificador", "Documentário", 2023, "Arthur K.", 90));
        midias.add(new Filme("O Poderoso Chefão", "Drama", 1972, "Francis Ford Coppola", 175));
    }

    @Override
    public void adicionarConteudo(Midia midia) {
        midias.add(midia);
    }

    @Override
    public List<Midia> listarConteudos() {
        return new ArrayList<>(midias); // Retorna cópia protegida
    }
    @Override
    public Midia buscarPorTitulo(String titulo) {
        for (Midia midia : midias) {
            if (midia.getTitulo().equalsIgnoreCase(titulo)) {
                return midia;
            }
        }
        return null; // Retorna null se não encontrar
    }

}