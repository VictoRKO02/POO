package littleRightFlix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Filme; // Import adicionado

import model.Mid1ia;
import model.Serie;

public class Programa {

	public static void main(String[] args) {
		
		Locale local = new Locale("pt","BR");
			
		Scanner sc = new Scanner(System.in);
		Sistema.exibirAsciiArt();

		String caminhoFilmes = "C:\\Users\\DELL\\Desktop\\LittleFlix\\Filmes\\filmes.txt";
		
		BufferedReader br = null;
		FileReader fr = null;
		System.out.println("Lista de filmes disponíveis");
		System.out.println("---------------------------");
		
		try {
			fr = new FileReader(caminhoFilmes);
			br = new BufferedReader(fr);
			String linha = br.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = br.readLine();
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
		
		br = null;
		fr = null;
		System.out.println();
		System.out.println("Lista de series disponíveis");
		System.out.println("---------------------------");
		
		String caminhoSerie = "C:\\Users\\DELL\\Desktop\\LittleFlix\\Series\\series.txt";
			
		try {
			fr = new FileReader(caminhoSerie);
			br = new BufferedReader(fr);
			String linha = br.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = br.readLine();
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
		
		List<Filme> listFilmes = new ArrayList<Filme>();
		List<Serie> listSeries = new ArrayList<Serie>();
		
		Filme[] filmes = new Filme[9];
		
		 filmes[0] = new Filme("Titanic", "Romance/Drama", 1997, "James Cameron", 195);
		 filmes[1]= new Filme("O Poderoso Chefão", "Crime/Drama", 1972, "Francis Ford Coppola", 175);
		 filmes[2]= new Filme("Prenda-me se for capaz", "Biografia/Crime", 2002, "Steven Spielberg", 141);
		 filmes[3] = new Filme("O Dono do Jogo", "Biografia/Drama", 2015, "Stephen Frears", 115);
		 filmes[4] = new Filme("Vingadores: Ultimato", "Ação/Ficção", 2019, "Anthony e Joe Russo", 181);
		 filmes[5]= new Filme("Um Sonho de Liberdade", "Drama", 1994, "Frank Darabont", 142);
		 filmes[6] = new Filme("Star Wars: A Vingança dos Sith", "Ficção/Aventura", 2005, "George Lucas", 140);
		 filmes[7]= new Filme("Mortal Kombat", "Ação/Fantasia", 2021, "Simon McQuoid", 110);
		 filmes[8]= new Filme("Dragon Ball Super: Broly", "Animação/Ação", 2018, "Tatsuya Nagamine", 100);
		
		
		  Serie[] series = new Serie[9];
		
		    series[0] = new Serie("Death Note", "Animação/Suspense", 2006, 1, 37);
	        series[1] = new Serie("One Piece", "Aventura/Fantasia", 1999, 21, 50);
	        series[2] = new Serie("The Flash", "Ação/Ficção", 2014, 9, 20);
	        series[3] = new Serie("Peaky Blinders", "Crime/Drama", 2013, 6, 6);
	        series[4] = new Serie("La Casa de Papel", "Crime/Suspense", 2017, 5, 8);
	        series[5] = new Serie("Vinland Saga", "Animação/Aventura", 2019, 2, 24);
	        series[6] = new Serie("Breaking Bad", "Crime/Drama", 2008, 5, 13);
	        series[7] = new Serie("Attack on Titan", "Animação/Ação", 2013, 4, 22);
	        series[8] = new Serie("Fullmetal Alchemist: Brotherhood", "Animação/Aventura", 2009, 1, 64);
	       
		
		for(int i = 0; i < 9; i++) {
			listFilmes.add(filmes[i]);
			listSeries.add(series[i]);
		}
	        
	    System.out.println("--------DIGITE O NUMERO VINCULADO A OPÇÃO DESEJADA-------------");    
	        
	    System.out.println("1:  Ver filme");
	    System.out.println("2:  Ver serie");
	    System.out.println("3:  Adiciona filme");
		System.out.println("4:  Adiciona serie");
		System.out.println("5:  Remove filme");
	    System.out.println("6:  Remove serie");
	    System.out.println("7:  Listar filmes");
	    System.out.println("8:  Listar series");
	    System.out.println("9:  Alterar dados de um filme");
	    System.out.println("10: Alterar dados de uma serie");
	    
	    String opcao = sc.next();
	    
	    
	    switch (opcao) {
	    case "1":
	        System.out.println("Pronto. Digite um numero para ver o filme relacionado");
	        Sistema.listarFilmes();
	        
	       int filmeEscolhido =  sc.nextInt();
	        
	       List<String> listaFilmes = new ArrayList<>();

	       try (BufferedReader br1 = new BufferedReader(new FileReader(caminhoFilmes))) {
	           String linha = br1.readLine();
	           while (linha != null) {
	               listaFilmes.add(linha);
	               linha = br1.readLine();
	           }
	       } catch (IOException e) {
	           System.out.println("Erro: " + e.getMessage());
	       }
	       if (filmeEscolhido >= 1 && filmeEscolhido <= listaFilmes.size()) {
	    	    String filmeSelecionado = listaFilmes.get(filmeEscolhido - 1);
	    	    System.out.println("Abrindo filme: " );
	    	    String filmeDesejado = String.format("C:\\Users\\DELL\\Desktop\\LittleFlix\\Filmes\\%s.mp4",filmeSelecionado);
	    	    
	    	    Sistema.abrirVideo(filmeDesejado);
	    	    
	       
	       }
	        
	        
	        
	        break;
	    case "2":
	        System.out.println("Filme de Drama selecionado.");
	        break;
	    case "3":
	        System.out.println("Filme de Comédia selecionado.");
	        break;
	    case "4":
	        System.out.println("Filme de Ação selecionado.");
	        break;
	    case "5":
	        System.out.println("Filme de Drama selecionado.");
	        break;
	    case "6":
	        System.out.println("Filme de Comédia selecionado.");
	        break;
	    case "7":
	        System.out.println("Filme de Comédia selecionado.");
	        break;
	    case "8":
	        System.out.println("Filme de Ação selecionado.");
	        break;
	    case "9":
	        System.out.println("Filme de Drama selecionado.");
	        break;
	    case "10":
	        System.out.println("Filme de Comédia selecionado.");
	        break;       
	       
	    default:
	        System.out.println("Opção inválida");
	        break;
	}
	    
	    
	    
		
	}
}