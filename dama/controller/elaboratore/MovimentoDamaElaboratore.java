package dama.controller.elaboratore;

import dama.model.Tavola;
import dama.view.Casella;

public class MovimentoDamaElaboratore extends MovimentoElaboratore {

	public MovimentoDamaElaboratore(int x, int y, Tavola tavola, Casella selezionata) {
		super(x, y, tavola, selezionata);
	}

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
		
	
	private boolean controlloTurno() {
		
		if (selezionata.getColorePedina() == turno)
			return true;
		else
			return false;		
	}
	

	protected void muoviGiuDx() {	
		tavola.getCasella(x - 1, y - 1).setDama(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		controlloObbligoPresa();
	}
	
	protected void muoviGiuSx() {	
		tavola.getCasella(x - 1, y + 1).setDama(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		controlloObbligoPresa();
	}
	
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
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaDama();
	}
	
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
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaDama();
	}
	
	protected void muoviSuDx() {	
		tavola.getCasella(x + 1, y - 1).setDama(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		controlloObbligoPresa();
	}
	
	protected void muoviSuSx() {	
		tavola.getCasella(x + 1, y + 1).setDama(false);
		tavola.getCasella(x, y).setDama(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = !turno;
		controlloObbligoPresa();
	}
	
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
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaDama();
	}
	
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
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaDama();
	}
	

}