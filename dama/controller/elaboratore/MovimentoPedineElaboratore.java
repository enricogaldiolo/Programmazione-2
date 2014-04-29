package dama.controller.elaboratore;

import dama.model.Tavola;
import dama.view.Casella;

public class MovimentoPedineElaboratore extends MovimentoElaboratore {

	public MovimentoPedineElaboratore(int x, int y, Tavola tavola, Casella selezionata) {
		super(x, y, tavola, selezionata);
	}
	

	@Override
	protected void controlloSelezione() {		
		if ((!selezionata.getColorePedina() && obbligo_presa_bianco) || (selezionata.getColorePedina() && obbligo_presa_nero))
			if (x - 2 == selezionata.getRiga() && y - 2 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama() && selezionata.getColorePedina() && turno && !tavola.getCasella(x - 1, y - 1).getHaDama())
				mangiaGiuDx();
			else if (x - 2 == selezionata.getRiga() && y + 2 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama() && selezionata.getColorePedina() && turno && !tavola.getCasella(x - 1, y + 1).getHaDama())
				mangiaGiuSx();
			else if (x + 2 == selezionata.getRiga() && y - 2 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama() && !selezionata.getColorePedina() && !turno && !tavola.getCasella(x + 1, y - 1).getHaDama())
				mangiaSuDx();
			else if (x + 2 == selezionata.getRiga() && y + 2 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama() && !selezionata.getColorePedina() && !turno && !tavola.getCasella(x + 1, y + 1).getHaDama())
				mangiaSuSx();
			else
				System.out.println("Non ho mosso nulla");
		else
			if (x - 1 == selezionata.getRiga() && y - 1 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama() && tavola.getCasella(x - 1, y - 1).getColorePedina() && turno)
				muoviGiuDx();
			else if (x - 1 == selezionata.getRiga() && y + 1 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama() && tavola.getCasella(x - 1, y + 1).getColorePedina() && turno)
				muoviGiuSx();
			else if (x + 1 == selezionata.getRiga() && y - 1 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama() && !tavola.getCasella(x + 1, y - 1).getColorePedina() && !turno)
				muoviSuDx();
			else if (x + 1 == selezionata.getRiga() && y + 1 == selezionata.getColonna() && !tavola.getCasella(x, y).getHaPedina() && !tavola.getCasella(x, y).getHaDama() && !tavola.getCasella(x + 1, y + 1).getColorePedina() && !turno)
				muoviSuSx();
			else
				System.out.println("Non ho mosso nulla");
	}
	

	protected void muoviGiuDx() {	
		tavola.getCasella(x - 1, y - 1).setHaPedina(false);
		tavola.getCasella(x, y).setHaPedina(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = false;
		controlloObbligoPresa();
	}
	
	protected void muoviGiuSx() {	
		tavola.getCasella(x - 1, y + 1).setHaPedina(false);
		tavola.getCasella(x, y).setHaPedina(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = false;
		controlloObbligoPresa();
	}
	
	protected void mangiaGiuDx() {
		tavola.getCasella(x - 2, y - 2).setHaPedina(false);
		tavola.getCasella(x - 1, y - 1).setHaPedina(false);
		tavola.getCasella(x - 1, y - 1).setDama(false);
		tavola.getCasella(x, y).setHaPedina(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		pedine_mangiate_nero++;
		turno = false;
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaPedine();
	}
	
	protected void mangiaGiuSx() {
		tavola.getCasella(x - 2, y + 2).setHaPedina(false);
		tavola.getCasella(x - 1, y + 1).setHaPedina(false);
		tavola.getCasella(x - 1, y + 1).setDama(false);
		tavola.getCasella(x, y).setHaPedina(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		pedine_mangiate_nero++;
		turno = false;
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaPedine();
	}
	
	protected void muoviSuDx() {	
		tavola.getCasella(x + 1, y - 1).setHaPedina(false);
		tavola.getCasella(x, y).setHaPedina(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = true;
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
	}
	
	protected void muoviSuSx() {	
		tavola.getCasella(x + 1, y + 1).setHaPedina(false);
		tavola.getCasella(x, y).setHaPedina(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		turno = true;
		controlloObbligoPresa();
	}
	
	protected void mangiaSuDx() {
		tavola.getCasella(x + 2, y - 2).setHaPedina(false);
		tavola.getCasella(x + 1, y - 1).setHaPedina(false);
		tavola.getCasella(x + 1, y - 1).setDama(false);
		tavola.getCasella(x, y).setHaPedina(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());	
		pedine_mangiate_bianco++;
		turno = true;
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaPedine();
	}
	
	protected void mangiaSuSx() {
		tavola.getCasella(x + 2, y + 2).setHaPedina(false);
		tavola.getCasella(x + 1, y + 1).setHaPedina(false);
		tavola.getCasella(x + 1, y + 1).setDama(false);
		tavola.getCasella(x, y).setHaPedina(true);
		tavola.getCasella(x, y).setColorePedina(selezionata.getColorePedina());
		pedine_mangiate_bianco++;
		turno = true;
		ultima_mossa = tavola.getCasella(x, y);
		obbligo_presa_bianco = false;
		obbligo_presa_nero = false;
		controlloObbligoPresa();
		obbligoPresaMultiplaPedine();
	}

	
}
