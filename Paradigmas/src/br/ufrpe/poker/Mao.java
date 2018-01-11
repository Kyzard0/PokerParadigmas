package br.ufrpe.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Mao {
	
	private ArrayList<Carta> mao;
	private RankingEnum ranking;
	private Carta highCard;
	private int valor_repeticao1 = 0;
	private int valor_repeticao2 = 0;
	
	public Mao(ArrayList<Carta> mao) {
		this.mao = mao;
	}
	public ArrayList<Carta> getMao() {
		return mao;
	}
	public void setMao(ArrayList<Carta> mao) {
		this.mao = mao;
	}
	public RankingEnum getRanking() {
		return ranking;
	}
	public void setRanking(RankingEnum ranking) {
		this.ranking = ranking;
	}
	public Carta getHighCard() {
		return highCard;
	}
	public void setHighCard(Carta highCard) {
		this.highCard = highCard;
	}
	public int getValor_repeticao1() {
		return valor_repeticao1;
	}
	public void setValor_repeticao1(int numero_repeticao1) {
		this.valor_repeticao1 = numero_repeticao1;
	}
	public int getValor_repeticao2() {
		return valor_repeticao2;
	}
	public void setValor_repeticao2(int numero_repeticao2) {
		this.valor_repeticao2 = numero_repeticao2;
	}
	public String toString() {
		return "Mao [mao=" + mao + ", ranking=" + ranking + ", highCard=" + highCard + ", numero_repeticao1="
				+ valor_repeticao1 + ", numero_repeticao2=" + valor_repeticao2 + "]";
	}
	public void ordenarMao(){
		Collections.sort(this.getMao());
	}
	
	public void acharRanking(){
		int mesmo_naipe = 0, royal = 0, sequencia = 0;
		int i = 1;
		char aux = this.getMao().get(0).getNaipe();
		this.ordenarMao();
		for (; i < 5; i++) {
			if (this.getMao().get(i).getNaipe() == aux) {
				mesmo_naipe = 1;
				aux = this.getMao().get(i).getNaipe();
			} else {
				mesmo_naipe = 0;
				break;
			}
		}
		
		i = 1;
		for (; i < 5; i++) {
			if (this.getMao().get(i).getValor() == this.getMao().get(i-1).getValor() + 1) {
				sequencia = 1;
			} else {
				sequencia = 0;
				break;
			}
		}
		
		if (sequencia == 1) {
			if (this.getMao().get(0).getValor() == 10) {
				royal = 1;
			}
		}
		
		if (sequencia == 1) {
			if (mesmo_naipe == 1) {
				if (royal == 1) {
					this.setRanking(RankingEnum.ROYAL_FLUSH);
				} else {
					this.setRanking(RankingEnum.STRAIGHT_FLUSH);
				}
			} else {
				this.setRanking(RankingEnum.SEQUENCIA);
			}
		} else {
			if (mesmo_naipe == 1) {
				this.setRanking(RankingEnum.FLUSH);
			} else {
				int k = 0, valor_atual = 0, count = 0, repeticoes[] = {0, 0, 0, 0};
				
				for (; k < 5; k++) {
					if (valor_atual == 0) {
						valor_atual = this.getMao().get(k).getValor();
						count = 1;
					} else {
						if (this.getMao().get(k).getValor() == valor_atual) {
							count++;				
							if (k == 4) {
								repeticoes[count - 1]++;
								if (count > 1) {
									if (valor_repeticao1 == 0 && count != 3) {
										valor_repeticao1 = valor_atual;
									} else {
										
										valor_repeticao2 = valor_atual;
									}
								}	
							}		
						} else {
							repeticoes[count - 1]++;
							
							if (count > 1) {
								if (valor_repeticao1 == 0 && count != 3) {								
									valor_repeticao1 = valor_atual;
								} else {						
									valor_repeticao2 = valor_atual;
								}
							}
							
							count = 1;
							valor_atual = this.getMao().get(k).getValor();
						}
					}
				}
				
				if (repeticoes[0] > 3) {
					this.setRanking(RankingEnum.HIGH_CARD);
					this.setHighCard(this.getMao().get(this.getMao().size()-1));
				}
				
				if (repeticoes[1] > 0) {
					if (repeticoes[1] == 1 && repeticoes[2] == 1) {
						this.setRanking(RankingEnum.FULL_HOUSE);
					} else {
						if (repeticoes[1] > 1) {
							this.setRanking(RankingEnum.DOIS_PARES);
						} else if (repeticoes[1] == 1) {
							this.setRanking(RankingEnum.UM_PAR);
						}
					}
				}
				
				if (repeticoes[2] == 1 && repeticoes[1] == 0) {
					this.setRanking(RankingEnum.TRINCA);
				} else if (repeticoes[3] == 1){
					this.setRanking(RankingEnum.QUADRA);
				}
			}
		}
	}
	

}
