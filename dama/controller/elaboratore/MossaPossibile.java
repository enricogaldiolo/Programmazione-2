package dama.controller.elaboratore;

import java.util.ArrayList;

import dama.view.Casella;

public class MossaPossibile {
	
	private Casella casella;
	private ArrayList<Casella> lista_mosse;
	private int [] voto = new int[4];
	
	protected MossaPossibile(Casella casella, ArrayList<Casella> mosse_possibili) {
		this.casella = casella;
		this.lista_mosse = mosse_possibili;	
	}
	
	
	protected MossaPossibile(Casella casella, ArrayList<Casella> mosse_possibili, int voto, int indice) {
		this(casella, mosse_possibili);
		this.voto[indice] = voto;
	}
	
	
	protected Casella getCasella() {
		return casella;
	}
	
	
	protected ArrayList<Casella> getListaMosse() {
		return lista_mosse;
	}
	
	
	protected int getVoto(int indice) {
		return voto[indice];
	}
	
	
	protected void setVoto(int voto, int indice) {
		
		System.out.println("Sto per salvare questo voto " + voto + ", con indice " + indice);
		
		this.voto[indice] = voto;
	}
}