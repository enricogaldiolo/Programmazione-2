package dama.controller;

import dama.model.Tavola;
import dama.view.Casella;
//import dama.controller.elaboratore.Elaboratore;

public class MovimentoDama extends Movimento {

	/**
	 * Costruttore del metodo Movimento Dama
	 *  
	 * @param x indica la riga della casella di destinazione
	 * @param y indica la colonna della casella di destinazione
	 * @param tavola contiene il riferimento alla tavola di riferimento
	 * @param selezionata contiene il riferimento alla casella selezionata, cioé quella di origine
	 */
	public MovimentoDama(int x, int y, Tavola tavola, Casella selezionata) {
		super(x, y, tavola, selezionata);
	}

	/**
	 * Controlla se la casella sorgente e la casella destinazione sono compatibili;
	 * verifica inoltre che se c'è l'obbligo di presa venga effettuata una presa;
	 * a seconda dell'esito delle verifiche precedentemente elencate chiama il medodo
	 * che si occupa di eseguire "fisicamente" lo spostamento
	 */
	@Override
	protected void controlloSelezione() {
		
		if (controlloTurno())
			if ((!selezionata.getColorePedina() && obbligo_presa_bianco) || (selezionata.getColorePedina() && obbligo_presa_nero))
				if (x - 2 == selezionata.getRiga() && y - 2 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama())
					mangiaGiuDx();
				else if (x - 2 == selezionata.getRiga() && y + 2 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama())
					mangiaGiuSx();
				else if (x + 2 == selezionata.getRiga() && y - 2 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama())
					mangiaSuDx();
				else if (x + 2 == selezionata.getRiga() && y + 2 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama())
					mangiaSuSx();
				else
					System.out.println("Non ho mosso nulla");
			else
				if (x - 1 == selezionata.getRiga() && y - 1 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama())
					muoviGiuDx();
				else if (x - 1 == selezionata.getRiga() && y + 1 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama())
					muoviGiuSx();
				else if (x + 1 == selezionata.getRiga() && y - 1 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama())
					muoviSuDx();
				else if (x + 1 == selezionata.getRiga() && y + 1 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama())
					muoviSuSx();
				else
					System.out.println("Non ho mosso nulla");
	}
		
	/**
	 * Metodo che verifica che il giocatore di turno sia lo stesso che sta muovendo
	 * 
	 * @return Ritorna true se la verifica è andata a buon fine
	 */
	private boolean controlloTurno() {		
		if (selezionata.getColorePedina() == turno)
			return true;
		else
			return false;		
	}
	
	
	/**
	 * Metodo che si occupa di eseguire lo spostamento giù-dx
	 */
	protected void muoviGiuDx() {	
		tavola.getCasella(x - 1, y - 1).setDama(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		controlloObbligoPresa();
		setTurno();
/*		if (giocatore && !turno)
			new Elaboratore(tavola);
		else if (!giocatore && turno)
			new Elaboratore(tavola); */ 	// parte di codice riferita all'AI
	}
	
	/**
	 * Metodo che si occupa di eseguire lo spostamento giù-sx
	 */
	protected void muoviGiuSx() {	
		tavola.getCasella(x - 1, y + 1).setDama(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		controlloObbligoPresa();
		setTurno();
/*		if (giocatore && !turno)
			new Elaboratore(tavola);
		else if (!giocatore && turno)
			new Elaboratore(tavola); */		// idem sopra
	}
	
	/**
	 * Metodo che si occupa di eseguire la presa giù-dx
	 */
	protected void mangiaGiuDx() {
		tavola.getCasella(x - 2, y - 2).setDama(false);
		tavola.getCasella(x - 1, y - 1).setDama(false);
		tavola.getCasella(x - 1, y - 1).setHaPedina(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		if (!selezionata.getColorePedina())
			pedine_mangiate_bianco++;
		else	
			pedine_mangiate_nero++;
		controlloVittoria();
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaDama();
		setTurno();
/*		if (giocatore && !turno)
			new Elaboratore(tavola);
		else if (!giocatore && turno)
			new Elaboratore(tavola); */		// idem sopra
	}
	
	/**
	 * Metodo che si occupa di eseguire la presa giù-sx
	 */
	protected void mangiaGiuSx() {
		tavola.getCasella(x - 2, y + 2).setDama(false);
		tavola.getCasella(x - 1, y + 1).setDama(false);
		tavola.getCasella(x - 1, y + 1).setHaPedina(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		if (!selezionata.getColorePedina())
			pedine_mangiate_bianco++;
		else	
			pedine_mangiate_nero++;
		controlloVittoria();
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaDama();
		setTurno();
/*		if (giocatore && !turno)
			new Elaboratore(tavola);
		else if (!giocatore && turno)
			new Elaboratore(tavola); */ 	// idem sopra
	}
	
	/**
	 * Metodo che si occupa di eseguire lo spostamento su-dx
	 */
	protected void muoviSuDx() {	
		tavola.getCasella(x + 1, y - 1).setDama(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		controlloObbligoPresa();
		setTurno();
/*		if (giocatore && !turno)
			new Elaboratore(tavola);
		else if (!giocatore && turno)
			new Elaboratore(tavola); */		// idem sopra
	}
	
	/**
	 * Metodo che si occupa di eseguire lo spostamento giù-sx
	 */
	protected void muoviSuSx() {	
		tavola.getCasella(x + 1, y + 1).setDama(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		controlloObbligoPresa();
		setTurno();
/*		if (giocatore && !turno)
			new Elaboratore(tavola);
		else if (!giocatore && turno)
			new Elaboratore(tavola); */		// idem sopra
	}
	
	/**
	 * Metodo che si occupa di eseguire la presa su-dx
	 */
	protected void mangiaSuDx() {
		tavola.getCasella(x + 2, y - 2).setDama(false);
		tavola.getCasella(x + 1, y - 1).setDama(false);
		tavola.getCasella(x + 1, y - 1).setHaPedina(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());	
		turno = !turno;
		if (!selezionata.getColorePedina())
			pedine_mangiate_bianco++;
		else	
			pedine_mangiate_nero++;
		controlloVittoria();
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaDama();
		setTurno();
/*		if (giocatore && !turno)
			new Elaboratore(tavola);
		else if (!giocatore && turno)
			new Elaboratore(tavola); */		// idem sopra
	}
	
	/**
	 * Metodo che si occupa di eseguire la presa su-sx
	 */
	protected void mangiaSuSx() {
		tavola.getCasella(x + 2, y + 2).setDama(false);
		tavola.getCasella(x + 1, y + 1).setDama(false);
		tavola.getCasella(x + 1, y + 1).setHaPedina(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		if (!selezionata.getColorePedina())
			pedine_mangiate_bianco++;
		else	
			pedine_mangiate_nero++;
		controlloVittoria();
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaDama();
		setTurno();
/*		if (giocatore && !turno)
			new Elaboratore(tavola);
		else if (!giocatore && turno)
			new Elaboratore(tavola); */ 	// idem sopra
	}
	

}