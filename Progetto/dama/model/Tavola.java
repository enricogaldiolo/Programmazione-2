package dama.model;

import dama.view.*;

public class Tavola {
	
	/**
	 * L'array di Caselle chiamato 'caselle' contiene la situazione iniziale della damiera, con la corretta
	 * disposizione delle caselle e l'assegnamento alla giuste caselle di una pedina pedina.
	 */

	private Casella caselle[][] = {
			{new Casella(true), null, new Casella(true), null, new Casella(true), null, new Casella(true), null},
			{null, new Casella(true), null, new Casella(true), null, new Casella(true), null, new Casella(true)},
			{new Casella(true), null, new Casella(true), null, new Casella(true), null, new Casella(true), null},
			{null, new Casella(false), null, new Casella(false), null, new Casella(false), null, new Casella(false)},
			{new Casella(false), null, new Casella(false), null, new Casella(false), null, new Casella(false), null},
			{null, new Casella(true), null, new Casella(true), null, new Casella(true), null, new Casella(true)},
			{new Casella(true), null, new Casella(true), null, new Casella( true), null, new Casella(true), null},
			{null, new Casella(true), null, new Casella(true), null, new Casella(true), null, new Casella(true)}
		};
	
	
	
	/**
	 * Costruttore della classe Tavola. Assegna ad ogni casella la propria riga e colonna e il giusto colore della padina
	 */
	
	public Tavola() {
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if(caselle[i][j] instanceof Casella)
					caselle[i][j].setRigaColonnaTavola(i, j, this);
				
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 8; j++)
				if(caselle[i][j] instanceof Casella)
					caselle[i][j].setColorePedina(true);
		
		
	}
	
	
	public Casella getCasella(int i, int j) {
		try {
			return caselle[i][j];
		}
		catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}
	
	
	public Casella[][] getTavola() {
		return caselle;
	}



/*												// metodo che clona la tavola, inutilizzato senza AI
	@Override
	public Tavola clone() {
		
		Tavola tavola_tmp = new Tavola();
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (caselle[i][j] instanceof Casella)
				tavola_tmp.caselle[i][j] = caselle[i][j].clone();
		
		return tavola_tmp;		
	}
*/
}
