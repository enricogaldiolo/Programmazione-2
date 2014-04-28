package dama.controller;

import javax.swing.JLabel;

import dama.model.Tavola;
import dama.view.Casella;
import dama.view.Vittoria;
//import dama.controller.elaboratore.Elaboratore;

public abstract class Movimento {

	protected int x;
	protected int y;
	protected Tavola tavola; 
	protected Casella selezionata = null;
	
	/**
	 * Variabile statica che memorizza il giocatore di turno
	 */
	protected static boolean turno = false;
	/**
	 * Variabile statica che memorizza se il nero ha obbligo di presa
	 */
	protected static boolean obbligo_presa_nero = false;
	/**
	 * Variabile statica che memorizza se il bianco ha obbligo di presa
	 */
	protected static boolean obbligo_presa_bianco = false;
	/**
	 * Variabile statica che memorizza il numero di pedine mengiate al nero
	 */
	protected static int pedine_mangiate_nero = 0;
	/**
	 * Variabile statica che memorizza il numero di pedine mengiate al bianco
	 */
	protected static int pedine_mangiate_bianco = 0;
	/**
	 * Variabile statica che memorizza l'ultima pedina mossa
	 */
	protected static Casella ultima_mossa;
	/**
	 * Variabile statica contentente l'etichetta indicante il giocatore a cui tocca la mossa
	 */
	private static JLabel etichetta_turno;
//	protected static boolean giocatore = false;			variabile inutilizzata senza l'AI
	
	
	
	/**
	 * Costruttore del metodo Movimento
	 *  
	 * @param x indica la riga della casella di destinazione
	 * @param y indica la colonna della casella di destinazione
	 * @param tavola contiene il riferimento alla tavola di riferimento
	 * @param selezionata contiene il riferimento alla casella selezionata, cioé quella di origine
	 */
	protected Movimento(int x, int y, Tavola tavola, Casella selezionata) {
		this.x = x;
		this.y = y;
		this.tavola = tavola;
		this.selezionata = selezionata;
		
		obbligo_presa_nero = false;
		obbligo_presa_bianco = false;
		
		controlloObbligoPresa();
		controlloSelezione();
	}
	
	
	public static boolean getObbligoPresaBianco() {
		return obbligo_presa_bianco;
	}
	
	public static boolean getObbligoPresaNero() {
		return obbligo_presa_nero;
	}
	
	public static boolean getTurno() {
		return turno;
	}
	
	public static int getPedineMangiateBianco() {
		return pedine_mangiate_bianco;
	}
	
	public static int getPedineMangiateNero() {
		return pedine_mangiate_nero;
	}
	
/*	public static boolean getGiocatore() {
		return giocatore;
	} */ 						// metodo inutilizzato senza l'AI
	
	
	protected abstract void controlloSelezione();
	
	protected abstract void muoviGiuDx();
	protected abstract void muoviGiuSx();
	protected abstract void mangiaGiuDx();
	protected abstract void mangiaGiuSx();
	protected abstract void muoviSuDx();
	protected abstract void muoviSuSx();
	protected abstract void mangiaSuDx();
	protected abstract void mangiaSuSx();
	
	/**
	 * Metodo che controlla se una pedina è arrivata a promozione
	 */
	protected void controllaPromozione() {
		if (tavola.getCasella(x, y).getColorePedina() == false)
			if (x == 0)
				promuovi();
		if (tavola.getCasella(x, y).getColorePedina() == true)
			if (x == 7)
				promuovi();
	}
	
	/**
	 * Metodo che esegue la promozione
	 */
	private void promuovi() {
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setHaPedina(false);
	}
	
	/**
	 * Metodo che controlla se c'è obbligo di presa
	 */
	public void controlloObbligoPresa() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (tavola.getCasella(i, j) instanceof Casella)
						if (tavola.getCasella(i, j).getHaDama() && obbligoPresaDama(i, j)) {
							if (!tavola.getCasella(i, j).getColorePedina())
								obbligo_presa_bianco = true;
							else if (tavola.getCasella(i, j).getColorePedina())
								obbligo_presa_nero = true;
						}
						else if (tavola.getCasella(i, j).getHaPedina()) {
							if (!tavola.getCasella(i, j).getColorePedina() && obbligoPresaBianco(i, j))
								obbligo_presa_bianco = true;		
							if (tavola.getCasella(i, j).getColorePedina() && obbligoPresaNero(i, j))
								obbligo_presa_nero = true;
				}
	}

	/**
	 * Metodo che controlla se c'è obbligo di presa per il nero
	 * 
	 * @param i parametro contenente la riga
	 * @param j parametro contentente la colonna
	 * @return ritorna true se c'è obbligo di presa
	 */
	protected boolean obbligoPresaNero(int i, int j) {
		try {
			if (i == 6 || i == 7)
				return false;
			else if ((j == 0 || j == 1) && ausiliariaControlloObbligoPedine(i, j, 1, 1))
				return true;
			else if ((j == 6 || j == 7) && ausiliariaControlloObbligoPedine(i, j, 1, -1))
				return true;
			else if (ausiliariaControlloObbligoPedine(i, j, 1, -1))
				return true;
			else if (ausiliariaControlloObbligoPedine(i, j, 1, 1))
				return true;
			else
				return false;
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * Metodo che controlla se c'è obbligo di presa per il bianco
	 * 
	 * @param i parametro contenente la riga
	 * @param j parametro contentente la colonna
	 * @return ritorna true se c'è obbligo di presa
	 */
	private boolean obbligoPresaBianco(int i, int j) {
		try {
			if (i == 0 || i == 1)
				return false;
			else if ((j == 6 || j == 7) && ausiliariaControlloObbligoPedine(i, j, -1, -1))
				return true;
			else if ((j == 0 || j == 1) && ausiliariaControlloObbligoPedine(i, j, -1, 1))
				return true;
			else if (ausiliariaControlloObbligoPedine(i, j, -1, 1))
				return true;
			else if (ausiliariaControlloObbligoPedine(i, j, -1, -1))
				return true;
			else
				return false;
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * Metodo che controlla se c'è obbligo di eseguire prese consecutive
	 */
	protected void obbligoPresaMultiplaPedine() {
		if (!selezionata.getColorePedina() && obbligo_presa_bianco && obbligoPresaBianco(ultima_mossa.getRiga(), ultima_mossa.getColonna())) {
			turno = !turno;
			selezionata = ultima_mossa;
			ultima_mossa = tavola.getCasella(x, y);
/*			if (giocatore && !turno)
				new Elaboratore(tavola);
			else if (!giocatore && turno)
				new Elaboratore(tavola); */		// parte di codice riguardante l'AI
		}
		else if (selezionata.getColorePedina() && obbligo_presa_nero && obbligoPresaNero(ultima_mossa.getRiga(), ultima_mossa.getColonna())) {
			turno = !turno;
			selezionata = ultima_mossa;
			ultima_mossa = tavola.getCasella(x, y);
/*			if (giocatore && !turno)
				new Elaboratore(tavola);
			else if (!giocatore && turno)
				new Elaboratore(tavola); */		// idem sopra
		}
		else {
			ultima_mossa = null;
		}
	}
	
	/**
	 * Metodo ausiliario che si occupa di eseguire il controllo sull'obbligo di presa in una determinata casella
	 * 
	 * @param i riga della casella
	 * @param j colonna della casella
	 * @param a parametro ausiliario
	 * @param b parametro ausiliario
	 * @return ritorna true se c'è obbligo di presa
	 */
	private boolean ausiliariaControlloObbligoPedine(int i, int j, int a, int b) {
		return tavola.getCasella(i + (a * 1), j + (b * 1)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaDama() &&
				!tavola.getCasella(i + (a * 1), j + (b * 1)).getHaDama() &&
				tavola.getCasella(i + (a * 1), j + (b * 1)).getColorePedina() == !tavola.getCasella(i, j).getColorePedina();
	}
	
	/**
	 * Metodo che si occupa di controllare se c'è obbligo di presa per le dame
	 * 
	 * @param i riga della casella
	 * @param j colonna della casella
	 * @return ritorna true se c'è obbligo di presa
	 */
	private boolean obbligoPresaDama(int i, int j) {
		
		try {
			if ((i == 0 || i == 1) && (j == 0 || j == 1) && ausiliariaControlloObbligoDama(i, j, 1, 1))
				return true;
			else if ((i == 0 || i == 1) && (j == 6 || j == 7) && ausiliariaControlloObbligoDama(i, j, 1, -1))
				return true;
			else if ((i == 0 || i == 1) && (ausiliariaControlloObbligoDama(i, j, 1, -1) || ausiliariaControlloObbligoDama(i, j, 1, 1)))
				return true;
			else if ((i == 6 || i == 7) && (j == 0 || j == 1) && ausiliariaControlloObbligoDama(i, j, -1, 1))
				return true;
			else if ((i == 6 || i == 7) && (j == 6 || j == 7) && ausiliariaControlloObbligoDama(i, j, -1, -1))
				return true;
			else if ((i == 6 || i == 7) && (ausiliariaControlloObbligoDama(i, j, -1, -1) || ausiliariaControlloObbligoDama(i, j, -1, 1)))
				return true;
			else if ((j == 0 || j == 1) && (ausiliariaControlloObbligoDama(i, j, 1, 1) || ausiliariaControlloObbligoDama(i, j, 1, -1)))
				return true;
			else if ((j == 6 || j == 7) && (ausiliariaControlloObbligoDama(i, j, 1, -1) || ausiliariaControlloObbligoDama(i, j, -1, -1)))
				return true;
			else if (ausiliariaControlloObbligoDama(i, j, -1, 1))
				return true;
			else if (ausiliariaControlloObbligoDama(i, j, 1, 1))
				return true;
			else if (ausiliariaControlloObbligoDama(i, j, -1, -1))
				return true;
			else if (ausiliariaControlloObbligoDama(i, j, 1, -1))
				return true;
			else
				return false;
		}
		catch(NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * Metodo che controlla se c'è obbligo di eseguire prese consecutive
	 */
	protected void obbligoPresaMultiplaDama() {
		if (obbligo_presa_bianco && obbligoPresaDama(ultima_mossa.getRiga(), ultima_mossa.getColonna())) {
			turno = !turno;
			selezionata = ultima_mossa;
			ultima_mossa = tavola.getCasella(x, y);
/*			if (giocatore && !turno)
				new Elaboratore(tavola);
			else if (!giocatore && turno)
				new Elaboratore(tavola); */		// idem sopra
		}
		else if (obbligo_presa_nero && obbligoPresaDama(ultima_mossa.getRiga(), ultima_mossa.getColonna())) {
			turno = !turno;
			selezionata = ultima_mossa;
			ultima_mossa = tavola.getCasella(x, y);
/*			if (giocatore && !turno)
				new Elaboratore(tavola);
			else if (!giocatore && turno)
				new Elaboratore(tavola); */		// idem sopra
		}
		else {
			ultima_mossa = null;
		}
	}
	
	/**
	 * Metodo ausiliario che si occupa di eseguire il controllo sull'obbligo di presa in una determinata casella contenente dama
	 * 
	 * @param i riga della casella
	 * @param j colonna della casella
	 * @param a parametro ausiliario
	 * @param b parametro ausiliario
	 * @return ritorna true se c'è obbligo di presa
	 */
	private boolean ausiliariaControlloObbligoDama(int i, int j, int a, int b) {
		return (tavola.getCasella(i + (a * 1), j + (b * 1)).getHaPedina() || tavola.getCasella(i + (a * 1), j + (b * 1)).getHaDama()) &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaDama() && 
				tavola.getCasella(i + (a * 1), j + (b * 1)).getColorePedina() == !tavola.getCasella(i, j).getColorePedina();
	}
	
	/**
	 * Metodo che dopo ogni mossa controlla se un giocatore ha vinto
	 */
	protected void controlloVittoria() {
		if (pedine_mangiate_bianco == 12)
			new Vittoria(false).setVisible(true);
		else if (pedine_mangiate_nero == 12)
			new Vittoria(true).setVisible(true);
	}
	
	/**
	 * Metodo che imposta l'etichetta contentente informazioni
	 * @param etichetta riferimento alla Label 'etichetta'
	 */
	public static void setEtichettaTurno(JLabel etichetta) {
		etichetta_turno = etichetta;
	}
	
	/**
	 * Metodo che si occupa di settare il contenuto della Label etichetta
	 */
	protected void setTurno() {
		if (turno == false && !obbligo_presa_bianco)
			etichetta_turno.setText("Turno del bianco  --  Bianco: " + pedine_mangiate_bianco + " - Nero: " + pedine_mangiate_nero);
		else if (turno == true && !obbligo_presa_nero)
			etichetta_turno.setText("Turno del nero  --  Bianco: " + pedine_mangiate_bianco + " - Nero: " + pedine_mangiate_nero);
		else if (turno == false && obbligo_presa_bianco)
			etichetta_turno.setText("Turno del bianco con obbligo di presa  --  Bianco: " + pedine_mangiate_bianco + " - Nero: " + pedine_mangiate_nero);
		else if (turno == true && obbligo_presa_nero)
			etichetta_turno.setText("Turno del nero con obbligo di presa  --  Bianco: " + pedine_mangiate_bianco + " - Nero: " + pedine_mangiate_nero);
	}
	
}