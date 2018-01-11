package br.ufrpe.poker;

import br.ufrpe.poker.RankingEnum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;




public class Principal {
	
	static ArrayList<Carta> maos = new ArrayList<>();
	static File file = new File("pokerM.txt");
	
	public static int encontrarMaior(Mao mao1, Mao mao2){
	 for (int i=4; i<5;i--){
		 if(mao1.getMao().get(i).compareTo(mao2.getMao().get(i))<0){
				return 2;
			}else if(mao1.getMao().get(i).compareTo(mao2.getMao().get(i))>0){
				return 1;
			}
	 	}
	 	return 0;
		
	}
	
	public static int verificarGanhador (Mao mao1, Mao mao2){
		
		mao1.acharRanking();
		mao2.acharRanking();
		
		if (mao1.getRanking().ordinal() > mao2.getRanking().ordinal()){
			return 1;
		}else if(mao1.getRanking().ordinal() < mao2.getRanking().ordinal()){
			return 2;
		}
		else if (mao1.getRanking().ordinal() == mao2.getRanking().ordinal()){
			switch (mao1.getRanking().ordinal()+1) {
			//Maior carta
			case 1:
				return encontrarMaior(mao1, mao2);
							
			//Um par	
			case 2:
				if (mao1.getValor_repeticao1() > mao2.getValor_repeticao1()) {
					return 1;
				}
				else if (mao1.getValor_repeticao1() < mao2.getValor_repeticao1()) {
					return 2;
				}
				else{
					return encontrarMaior(mao1, mao2);
				}
				
			//Dois pares	
			case 3:
				if (mao1.getValor_repeticao2() > mao2.getValor_repeticao2()) {
					return 1;
				}
				else if (mao1.getValor_repeticao2() < mao2.getValor_repeticao2()) {
					return 2;
				}
				else{
					return encontrarMaior(mao1, mao2);
				}

			
			//Uma trinca
			case 4:
				if (mao1.getValor_repeticao1() > mao2.getValor_repeticao1()) {
					return 1;
				}
				else if (mao1.getValor_repeticao1() < mao2.getValor_repeticao1()) {
					return 2;
				}else{
					return encontrarMaior(mao1, mao2);
				}

			
			//Straight
			case 5:
				return encontrarMaior(mao1, mao2);
					
			//Flush
			case 6:
				return encontrarMaior(mao1, mao2);
			
			//Full House	
			case 7:
				if (mao1.getValor_repeticao1() > mao2.getValor_repeticao1()) {
					return 1;
				}
				else if (mao1.getValor_repeticao1() < mao2.getValor_repeticao1()) {
					return 2;
				}
				if (mao1.getValor_repeticao2() > mao2.getValor_repeticao2()) {
					return 1;
				}
				else if (mao1.getValor_repeticao2() < mao2.getValor_repeticao2()) {
					return 2;
				}
				else{
					return encontrarMaior(mao1, mao2);
				}
			
			//Uma quadra.
			case 8:
				if (mao1.getValor_repeticao1() > mao2.getValor_repeticao1()) {
					return 1;
				}
				else if (mao1.getValor_repeticao1() < mao2.getValor_repeticao1()) {
					return 2;
				}
				else{
					return encontrarMaior(mao1, mao2);
				}
				
			
			//Straight Flush
			case 9:
				return encontrarMaior(mao1, mao2);
			
			//Royal Flush
			case 10:
				return 0;
			}
		}
		
		return 0;
	}

	public static void main(String[] args) {
		
		
		try{
			String vencedor = null;
			String vencedorA = null;	
			int divergencias = 0;
			Mao mao1 = new Mao(null);
			Mao mao2 = new Mao(null);
			long tempoInicio = System.currentTimeMillis();
					
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
				
			
				while(reader.ready()){
					String dados = null;
					dados = reader.readLine();
									
					String[] cartas = dados.split(" ");
					ArrayList<Carta> cartas1 = new ArrayList<>();
					ArrayList<Carta> cartas2 = new ArrayList<>();
					for(int j=0; j<cartas.length;j++){
						if(j<5){
							Carta card = new Carta(cartas[j].charAt(0),cartas[j].charAt(1));						 
							cartas1.add(card);
						}else if(j<10){
							Carta card = new Carta(cartas[j].charAt(0),cartas[j].charAt(1));						 
							cartas2.add(card);
						}else{
							vencedorA = cartas[j];
						}
							 
					}
					mao1.setMao(cartas1);
					mao2.setMao(cartas2);
					vencedor = "P"+ verificarGanhador(mao1,mao2);
					if (vencedorA.equals(vencedor)== false){
						divergencias++;
											
					}			
				}
				fileReader.close();
				reader.close();
				FileWriter arq = new FileWriter("saida.txt");
			    PrintWriter gravarArq = new PrintWriter(arq);
			    long tempoFinal = (System.currentTimeMillis());
			    
			    System.out.println();
			    gravarArq.printf("%d%n%d",divergencias,(tempoFinal - tempoInicio)); 
			    gravarArq.close();
			
				
		}catch(IOException e){
			e.printStackTrace();
		}
		

	}

}
