package dama.controller.elaboratore;

import java.util.ArrayList;

import dama.controller.Movimento;
import dama.model.Tavola;
import dama.view.Casella;

public class MovimentiPossibiliElaboratore {
	
	private Tavola tavola;
	private Casella selezionata;
	private ArrayList<Casella> mosse_possibili = new ArrayList<Casella>(4);


	protected MovimentiPossibiliElaboratore(Tavola tavola, Casella selezionata) {
		this.tavola = tavola;
		this.selezionata = selezionata;
	}
	
	protected ArrayList<Casella> getMossePossibili() {
		
		try {
			if (selezionata.getHaDama()) {
				if (Movimento.getObbligoPresaNero() || Movimento.getObbligoPresaBianco()) {
					if ((selezionata.getRiga() == 0 || selezionata.getRiga() == 1) && (selezionata.getColonna() == 0 || selezionata.getColonna() == 1) && selezionata.getColorePedina() == Movimento.getTurno()) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2));
					}
					else if ((selezionata.getRiga() == 0 || selezionata.getRiga() == 1) && (selezionata.getColonna() == 6 || selezionata.getColonna() == 7) && selezionata.getColorePedina() == Movimento.getTurno()) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2));
					}
					else if (selezionata.getRiga() == 0 || selezionata.getRiga() == 1 && selezionata.getColorePedina() == Movimento.getTurno()) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2));
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2));
					}
					else if ((selezionata.getRiga() == 6 || selezionata.getRiga() == 7) && (selezionata.getColonna() == 0 || selezionata.getColonna() == 1) && selezionata.getColorePedina() == Movimento.getTurno()) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2));
					}
					else if ((selezionata.getRiga() == 6 || selezionata.getRiga() == 7) && (selezionata.getColonna() == 6 || selezionata.getColonna() == 7) && selezionata.getColorePedina() == Movimento.getTurno()) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2));
					}
					else if (selezionata.getRiga() == 6 || selezionata.getRiga() == 7 && selezionata.getColorePedina() == Movimento.getTurno()) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2));
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2));
					}
					else if (selezionata.getColonna() == 6 || selezionata.getColonna() == 7 && selezionata.getColorePedina() == Movimento.getTurno()) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2));
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2));
					}
					else if (selezionata.getColonna() == 1 || selezionata.getColonna() == 1 && selezionata.getColorePedina() == Movimento.getTurno()) {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2));
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2));
					}
					else {
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, 1) && selezionata.getColorePedina() == Movimento.getTurno())
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2));
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2));
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2));
						if (obbligoPresaDama(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
							mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2));
					}
				}
				else if (selezionata.getRiga() == 0 && selezionata.getColonna() == 0 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1));
				}
				else if (selezionata.getRiga() == 0 && selezionata.getColonna() == 6 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1));
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1));
				}
				else if (selezionata.getRiga() == 1 && selezionata.getColonna() == 7 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1));
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1));
				}
				else if (selezionata.getRiga() == 0 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1));
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1));
				}
				else if (selezionata.getRiga() == 6 && selezionata.getColonna() == 0 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1));
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1));
				}
				else if (selezionata.getRiga() == 7 && selezionata.getColonna() == 1 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1));
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1));
				}
				else if (selezionata.getRiga() == 7 && selezionata.getColonna() == 7 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1));
				}
				else if (selezionata.getRiga() == 7 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1));
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1));
				}
				else if (selezionata.getColonna() == 0 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1));
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1));
				}
				else if (selezionata.getColonna() == 7 && selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1));
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1));
				}
				else if (selezionata.getColorePedina() == Movimento.getTurno()) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1));
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1));
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1));
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1));
				}
				else
					System.out.println("Nessuna mossa dama");
				
			}
			else if (Movimento.getTurno() && selezionata.getColorePedina() && selezionata.getHaPedina())
				if (Movimento.getObbligoPresaNero() && (selezionata.getColonna() == 0 || selezionata.getColonna() == 1)) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2));
				}
				else if (Movimento.getObbligoPresaNero() && (selezionata.getColonna() == 6 || selezionata.getColonna() == 7)) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2));
				}
				else if (Movimento.getObbligoPresaNero()) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), 1, 1))
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() + 2));
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), 1, -1))
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 2, selezionata.getColonna() - 2));
				}
				else if (selezionata.getColonna() == 0) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1));
				}
				else if (selezionata.getColonna() == 7) {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1));
				}
				else {
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() + 1));
					if (!tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() + 1, selezionata.getColonna() - 1));				
				}
			else if (!Movimento.getTurno() && !selezionata.getColorePedina() && selezionata.getHaPedina())
				if (Movimento.getObbligoPresaBianco() && (selezionata.getColonna() == 0 || selezionata.getColonna() == 1)) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2));
				}
				else if (Movimento.getObbligoPresaBianco() && (selezionata.getColonna() == 6 || selezionata.getColonna() == 7)) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2));
				}
				else if (Movimento.getObbligoPresaBianco()) {
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), -1, 1))
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() + 2));
					if (obbligoPresaPedina(selezionata.getRiga(), selezionata.getColonna(), -1, -1))
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 2, selezionata.getColonna() - 2));
				}
				else if (selezionata.getColonna() == 0) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1));
				}
				else if (selezionata.getColonna() == 7) {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1));
				}
				else {
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() + 1));
					if (!tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaPedina() && !tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1).getHaDama())
						mosse_possibili.add(tavola.getCasella(selezionata.getRiga() - 1, selezionata.getColonna() - 1));				
				}

			}
		catch(NullPointerException e) {
			System.out.println("Attenziona puntatore nullo in movimenti possibili elaboratore");
		}
		
		return mosse_possibili;		
	}
	
	

	private boolean obbligoPresaPedina(int i, int j, int a, int b) {
		return tavola.getCasella(i + (a * 1), j + (b * 1)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaDama() &&
				!tavola.getCasella(i + (a * 1), j + (b * 1)).getHaDama() &&
				tavola.getCasella(i + (a * 1), j + (b * 1)).getColorePedina() == !tavola.getCasella(i, j).getColorePedina();
	}
	
	
	private boolean obbligoPresaDama(int i, int j, int a, int b) {
		return (tavola.getCasella(i + (a * 1), j + (b * 1)).getHaPedina() || tavola.getCasella(i + (a * 1), j + (b * 1)).getHaDama()) &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaDama() && 
				tavola.getCasella(i + (a * 1), j + (b * 1)).getColorePedina() == !tavola.getCasella(i, j).getColorePedina();
	}
	

}
