package br.ufrpe.poker;

public class Carta implements Comparable{
	private int valor;
	private char naipe;
	
	public Carta(char valor, char naipe) {
		
		
		if(valor=='D'){
			this.valor = 10;
		}
		else if(valor=='J'){
			this.valor = 11;
		}
		else if(valor=='Q'){
			this.valor = 12;
		}
		else if(valor=='K'){
			this.valor = 13;
		}
		else if(valor=='A'){
			this.valor = 14;
		}
		else{this.valor = Integer.parseInt(""+valor);}
		this.naipe = naipe;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(char valor) {
		this.valor = valor;
	}
	public char getNaipe() {
		return naipe;
	}
	public void setNaipe(char naipe) {
		this.naipe = naipe;
	}

	public String toString() {
		return "" + valor + "" + naipe + " ";
	}
	public boolean mesmoNaipe(Carta carta) {
		if(this.naipe == carta.naipe){
			return true;
		}
		else{return false;}
		
	}
	public int compareTo(Object carta1) {
		if (this.getValor() < ((Carta) carta1).getValor()) {
            return -1;
        }
        if (this.getValor() > ((Carta) carta1).getValor()) {
            return 1;
        }
        return 0;
	}
	
	
}
