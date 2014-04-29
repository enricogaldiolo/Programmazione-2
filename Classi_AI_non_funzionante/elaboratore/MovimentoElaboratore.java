package dama.controller.elaboratore;

import dama.model.Tavola;
import dama.view.Casella;

public abstract class MovimentoElaboratore {

	protected int x;
	protected int y;
	protected Tavola tavola;
	protected Casella selezionata = null;
	protected static int pedine_mangiate_nero = 0;
	protected static int pedine_mangiate_bianco = 0;
	protected static boolean turno = true;
	protected static boolean obbligo_presa_nero = false;
	protected static boolean obbligo_presa_bianco = false;
	protected static Casella ultima_mossa;
	protected static boolean giocatore = false;
	

	protected MovimentoElaboratore(int x, int y, Tavola tavola, Casella selezionata) {
		this.x = x;
		this.y = y;
		this.tavola = tavola;
		this.selezionata = selezionata;
		
		obbligo_presa_nero = false;
		obbligo_presa_bianco = false;
		
		controlloObbligoPresa();
		controlloSelezione();
	}
	
	
	protected abstract void controlloSelezione();
	
	protected abstract void muoviGiuDx();
	protected abstract void muoviGiuSx();
	protected abstract void mangiaGiuDx();
	protected abstract void mangiaGiuSx();
	protected abstract void muoviSuDx();
	protected abstract void muoviSuSx();
	protected abstract void mangiaSuDx();
	protected abstract void mangiaSuSx();
	
	
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
	
	protected void obbligoPresaMultiplaPedine() {
		if (!selezionata.getColorePedina() && obbligo_presa_bianco && obbligoPresaBianco(ultima_mossa.getRiga(), ultima_mossa.getColonna())) {
			turno = !turno;
			selezionata = tavola.getCasella(x, y);
		}
		else if (selezionata.getColorePedina() && obbligo_presa_nero && obbligoPresaNero(ultima_mossa.getRiga(), ultima_mossa.getColonna())) {
			turno = !turno;
			selezionata = tavola.getCasella(x, y);
			System.out.println("sono al nuovo movimento ella peppa");
			if ((tavola.getCasella(x + 1, y + 1).getHaPedina() || tavola.getCasella(x + 1, y + 1).getHaDama()) && (!tavola.getCasella(x + 2, y + 2).getHaPedina() || !tavola.getCasella(x + 2, y + 2).getHaDama()))
				System.out.println("sono al nuovo movimento el");//new MovimentoPedineElaboratore(x + 2, y + 2, tavola, selezionata);
			else if ((tavola.getCasella(x + 1, y - 1).getHaPedina() || tavola.getCasella(x + 1, y - 1).getHaDama()) && (!tavola.getCasella(x + 2, y - 2).getHaPedina() || !tavola.getCasella(x + 2, y - 2).getHaDama()))
				System.out.println("sono al nuovo movimento el");//new MovimentoPedineElaboratore(x + 2, y - 2, tavola, selezionata);
		}
		else {
			ultima_mossa = null;
		}
	}
		
	private boolean ausiliariaControlloObbligoPedine(int i, int j, int a, int b) {
		return tavola.getCasella(i + (a * 1), j + (b * 1)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaDama() &&
				!tavola.getCasella(i + (a * 1), j + (b * 1)).getHaDama() &&
				tavola.getCasella(i + (a * 1), j + (b * 1)).getColorePedina() == !tavola.getCasella(i, j).getColorePedina();
	}
	
	
	
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
	
	protected void obbligoPresaMultiplaDama() {
		if (obbligo_presa_bianco && obbligoPresaDama(ultima_mossa.getRiga(), ultima_mossa.getColonna())) {
			turno = !turno;
			selezionata = ultima_mossa;
			ultima_mossa = tavola.getCasella(x, y);
			if (giocatore && !turno)
				new Elaboratore(tavola);
			else if (!giocatore && turno)
				new Elaboratore(tavola);
		}
		else if (obbligo_presa_nero && obbligoPresaDama(ultima_mossa.getRiga(), ultima_mossa.getColonna())) {
			turno = !turno;
			selezionata = ultima_mossa;
			ultima_mossa = tavola.getCasella(x, y);
			if (giocatore && !turno)
				new Elaboratore(tavola);
			else if (!giocatore && turno)
				new Elaboratore(tavola);
		}
		else {
			ultima_mossa = null;
		}
	}
	
	private boolean ausiliariaControlloObbligoDama(int i, int j, int a, int b) {
		return (tavola.getCasella(i + (a * 1), j + (b * 1)).getHaPedina() || tavola.getCasella(i + (a * 1), j + (b * 1)).getHaDama()) &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaPedina() &&
				!tavola.getCasella(i + (a * 2), j + (b * 2)).getHaDama() && 
				tavola.getCasella(i + (a * 1), j + (b * 1)).getColorePedina() == !tavola.getCasella(i, j).getColorePedina();
	}
	
	
	public static void azzeraTutto() {
		pedine_mangiate_nero = 0;
		pedine_mangiate_bianco = 0;
		turno = true;
		obbligo_presa_nero = false;
		obbligo_presa_bianco = false;
		ultima_mossa = null;
		giocatore = false;
	}

}