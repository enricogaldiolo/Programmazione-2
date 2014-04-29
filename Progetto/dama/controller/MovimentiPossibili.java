package dama.controller;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import dama.model.Tavola;
import dama.view.Casella;

public class MovimentiPossibili {
	
	private static Tavola tavola;
	private Casella selezionata;
	
	private static final Border bordo = BorderFactory.createLineBorder(Color.red, 2);

	/**
	 * Costruttore della classe
	 * 
	 * @param tavola contiene la tavola di riferimento
	 * @param selezionata contiene la casella selezionata
	 */
	public MovimentiPossibili(Tavola tavola, Casella selezionata) {
		MovimentiPossibili.tavola = tavola;
		this.selezionata = selezionata;
		
		selezionaMossePossibili();
	}
	
	/**
	 * Eseguenun controllo sulle mosse possibili per la casella selezionata;
	 * seleziona le possibili destinazioni con un bordo colorato
	 */
	private void selezionaMossePossibili() {
		
		try {
			if (selezionata.getHaDama()) {
				if (Movimento.obbligo_presa_nero || Movimento.obbligo_presa_bianco) {
					if ((selezionata.getRiga() == 0 || selezionata.getRiga() == 1) && (selezionata.getColonna() == 0 || selezionata.getColonna() == 1) && selezionata.getColorePedina() == Movimento.turno) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
							tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2).setBorder(bordo);
					}
					else if ((selezionata.getRiga() == 0 || selezionata.getRiga() == 1) && (selezionata.getColonna() == 6 || selezionata.getColonna() == 7) && selezionata.getColorePedina() == Movimento.turno) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
							tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2).setBorder(bordo);
					}
					else if ((selezionata.getRiga() == 0 || selezionata.getRiga() == 1) && selezionata.getColorePedina() == Movimento.turno) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
							tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2).setBorder(bordo);
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
							tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2).setBorder(bordo);
					}
					else if ((selezionata.getRiga() == 6 || selezionata.getRiga() == 7) && (selezionata.getColonna() == 0 || selezionata.getColonna() == 1) && selezionata.getColorePedina() == Movimento.turno) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
							tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2).setBorder(bordo);
					}
					else if ((selezionata.getRiga() == 6 || selezionata.getRiga() == 7) && (selezionata.getColonna() == 6 || selezionata.getColonna() == 7) && selezionata.getColorePedina() == Movimento.turno) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
							tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2).setBorder(bordo);
					}
					else if ((selezionata.getRiga() == 6 || selezionata.getRiga() == 7) && selezionata.getColorePedina() == Movimento.turno) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
							tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2).setBorder(bordo);
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
							tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2).setBorder(bordo);
					}
					else if ((selezionata.getColonna() == 6 || selezionata.getColonna() == 7) && selezionata.getColorePedina() == Movimento.turno) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
							tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2).setBorder(bordo);
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
							tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2).setBorder(bordo);
					}
					else if ((selezionata.getColonna() == 1 || selezionata.getColonna() == 1) && selezionata.getColorePedina() == Movimento.turno) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
							tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2).setBorder(bordo);
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
							tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2).setBorder(bordo);
					}
					else {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, 1) && selezionata.getColorePedina() == Movimento.turno)
							tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2).setBorder(bordo);
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
							tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2).setBorder(bordo);
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
							tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2).setBorder(bordo);
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
							tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2).setBorder(bordo);
					}
				}
				else if (selezionata.getRiga() == 0 && selezionata.getColonna() == 0 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).setBorder(bordo);
				}
				else if (selezionata.getRiga() == 0 && selezionata.getColonna() == 6 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).setBorder(bordo);
				}
				else if (selezionata.getRiga() == 1 && selezionata.getColonna() == 7 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).setBorder(bordo);
				}
				else if (selezionata.getRiga() == 0 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).setBorder(bordo);
				}
				else if (selezionata.getRiga() == 6 && selezionata.getColonna() == 0 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).setBorder(bordo);
				}
				else if (selezionata.getRiga() == 7 && selezionata.getColonna() == 1 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).setBorder(bordo);
				}
				else if (selezionata.getRiga() == 7 && selezionata.getColonna() == 7 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).setBorder(bordo);
				}
				else if (selezionata.getRiga() == 7 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).setBorder(bordo);
				}
				else if (selezionata.getColonna() == 0 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).setBorder(bordo);
				}
				else if (selezionata.getColonna() == 7 && selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).setBorder(bordo);
				}
				else if (selezionata.getColorePedina() == Movimento.turno) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).setBorder(bordo);
				}
				else
					System.out.println("Nessuna mossa dama");
					
			}
			else if (Movimento.turno && selezionata.getColorePedina() && selezionata.getHaPedina())
				if (Movimento.obbligo_presa_nero && (selezionata.getColonna() == 0 || selezionata.getColonna() == 1)) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
						tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2).setBorder(bordo);
				}
				else if (Movimento.obbligo_presa_nero && (selezionata.getColonna() == 6 || selezionata.getColonna() == 7)) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
						tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2).setBorder(bordo);
				}
				else if (Movimento.obbligo_presa_nero) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
						tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2).setBorder(bordo);
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
						tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2).setBorder(bordo);
				}
				else if (selezionata.getColonna() == 7) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).setBorder(bordo);
				}
				else {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).setBorder(bordo);				
				}
			else if (!Movimento.turno && !selezionata.getColorePedina() && selezionata.getHaPedina())
				if (Movimento.obbligo_presa_bianco && (selezionata.getColonna() == 0 || selezionata.getColonna() == 1)) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
						tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2).setBorder(bordo);
				}
				else if (Movimento.obbligo_presa_bianco && (selezionata.getColonna() == 6 || selezionata.getColonna() == 7)) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
						tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2).setBorder(bordo);
				}
				else if (Movimento.obbligo_presa_bianco) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
						tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2).setBorder(bordo);
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
						tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2).setBorder(bordo);
				}
				else if (selezionata.getColonna() == 7) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).setBorder(bordo);
				}
				else {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).setBorder(bordo);
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).setBorder(bordo);				
				}

			}
		catch(NullPointerException e) {
			System.out.println("Attenzione puntatore nullo in movimenti possibili");
		}
	}
	
	/**
	 * Cancella i bordi colorati
	 */
	public static void deselezionaTutto() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				try {
					tavola.getCasella(i, j).setBorder(null);
				}
				catch(NullPointerException e) {
				}
	}
	
	/**
	 * Metodo ausiliario che controlla l'eventuale obbligo di presa per le pedine
	 * 
	 * @param i contiene la riga
	 * @param j contiene la colonna
	 * @param a parametro ausiliario
	 * @param b parametro ausiliario
	 * @return ritorna true se c'è obbligo di presa
	 */
	private boolean obbligoPresaPedina(int i, int j, int a, int b) {
		return tavola.getCasella(i + (a * 1), j + (b * 1)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaDama() &&
				!tavola.getCasella(i + (a * 1), j + (b * 1)).getHaDama() &&
				tavola.getCasella(i + (a * 1), j + (b * 1)).getColorePedina() == !tavola.getCasella(i, j).getColorePedina();
	}
	
	/**
	 * Metodo ausiliario che controlla l'eventuale obbligo di presa per le dame
	 * 
	 * @param i contiene la riga
	 * @param j contiene la colonna
	 * @param a parametro ausiliario
	 * @param b parametro ausiliario
	 * @return ritorna true se c'è obbligo di presa
	 */
	private boolean obbligoPresaDama(int i, int j, int a, int b) {
		return (tavola.getCasella(i + (a * 1), j + (b * 1)).getHaPedina() || tavola.getCasella(i + (a * 1), j + (b * 1)).getHaDama()) &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaDama() && 
				tavola.getCasella(i + (a * 1), j + (b * 1)).getColorePedina() == !tavola.getCasella(i, j).getColorePedina();
	}
	

}
