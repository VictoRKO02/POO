package littleRightFlix;

import interfaces.GerenciadorConteudo;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema implements GerenciadorConteudo {
    private List<Midia> midias = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    
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
    
    
    public static void abrirVideo(String caminhoArquivo) {
        try {
            String comando = "cmd /c start \"\" \"" + caminhoArquivo + "\"";
            Runtime.getRuntime().exec(comando);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      public static void listarFilmes() {
    	  BufferedReader br = null;
  		  FileReader fr = null;
  		 String caminhoFilmes = "C:\\Users\\DELL\\Desktop\\LittleFlix\\Filmes\\filmes.txt"; 
    	  
    	  try {
  			fr = new FileReader(caminhoFilmes);
  			br = new BufferedReader(fr);
  			String linha = br.readLine();
  			int i = 1;
  			while (linha != null) {                              

  				System.out.println(i+": "+linha);
  				linha = br.readLine();
  				i++;
  			}
  		} catch (IOException e) {
  			System.out.println("Erro: " + e.getMessage());
  		} finally {
  			try {
  				if (br != null)
  					br.close();
  				if (fr != null)
  					fr.close();
  			} catch (IOException e) {
  				e.printStackTrace();
  			}
  		}
    	  
    	  
    	
      
    
    
    
    
    
    
    

}}