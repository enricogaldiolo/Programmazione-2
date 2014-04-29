package dama.controller.elaboratore;

import java.util.ArrayList;
import java.util.Random;

import dama.controller.Movimento;
import dama.controller.MovimentoDama;
import dama.controller.MovimentoPedine;
import dama.model.Tavola;
import dama.view.Casella;

public class Elaboratore {
	
	
	private Tavola tavola;
	
	private static int numero_mosse = 0;
	private final ArrayList<MossaPossibile> mosse_possibili_totali = new ArrayList<MossaPossibile>(12);
	
	
	public Elaboratore(Tavola tavola) {
		
		this.tavola = tavola;
		
		if (numero_mosse == 0)
			primaMossa();
		else if (!Movimento.getGiocatore() && Movimento.getObbligoPresaNero()) {
			mossePossibili();
			presaObbligatoria();
		}
		else
			System.out.println("Sono arrivato qua ma non so che fare");
/*		else {
			mossePossibili();
			scegliMossaCasuale();	
		}
*/		
		System.out.println("Ciao " + numero_mosse);
		numero_mosse++;
	}

	private void primaMossa() {
		
		Random r = new Random();
		Casella selezionata;
		int tmp = Math.abs(r.nextInt(3)) * 2;
		
		selezionata = new Casella(true);
		selezionata.setRigaColonnaTavola(2, tmp, tavola);
		selezionata.setColorePedina(true);
		selezionata.setDama(false);
		
		new MovimentoPedine(3, tmp + 1, tavola, selezionata);		
	}
	
	
	private void mossePossibili() {
		
		ArrayList<Casella> tmp;
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (tavola.getCasella(i, j) instanceof Casella)
					if (tavola.getCasella(i, j).getHaPedina() || tavola.getCasella(i, j).getHaDama()) {
						tmp = new MovimentiPossibiliElaboratore(tavola, tavola.getCasella(i, j)).getMossePossibili();
						if (tmp != null && tmp.size() > 0)
							mosse_possibili_totali.add(new MossaPossibile(tavola.getCasella(i, j), tmp));
					}
	}
	
	/*
	private void scegliMossa() {
		
		votoMossa();
		
	}
	
	private void votoMossa() {
		
		int voto = 0;
		int indice = 0;
		
		for (MossaPossibile selezionata: mosse_possibili_totali) {
			for (Casella destinazione: selezionata.getListaMosse()) {
				Tavola tavola_tmp = tavola.clone();
				muovi(selezionata.getCasella(), destinazione, tavola_tmp);
				selezionata.setVoto(voto, indice);
				indice++;
			}
		}
		
	}
	
*/	
	private void presaObbligatoria() {
		
		Casella selezionata_tmp = null;
		Casella destinazione_tmp = null;
		int max = 0;
		
		votoPresaObbligatoria();
		
		for (MossaPossibile selezionata: mosse_possibili_totali) {
			for (Casella destinazione: selezionata.getListaMosse())
				for (int i = 0; i < 4; i++) {
					if (selezionata.getVoto(i) >= max) {
						max = selezionata.getVoto(i);
						selezionata_tmp = selezionata.getCasella();
						destinazione_tmp = destinazione;
					}		
				}
		}
		
		muovi(selezionata_tmp, destinazione_tmp, tavola);
	}

	private void votoPresaObbligatoria() {
		
		int voto = 0;
		int indice = 0;
		
		for (MossaPossibile selezionata: mosse_possibili_totali) {
			for (Casella destinazione: selezionata.getListaMosse()) {
				Tavola tavola_tmp = tavola.clone();
 				muoviProva(selezionata.getCasella(), destinazione, tavola_tmp);
				voto = voto + MovimentoElaboratore.pedine_mangiate_nero;
				selezionata.setVoto(voto, indice);
				indice++;
			}
		}
		
	}
	
	
	private void scegliMossaCasuale() {
		
		for (MossaPossibile selezionata: mosse_possibili_totali) {
			for (Casella destinazione: selezionata.getListaMosse())
				muovi(selezionata.getCasella(), destinazione, tavola);
		}
	}
	
	
	private void muoviProva(Casella selezionata, Casella destinazione, Tavola tavola) {
		if (selezionata.getHaPedina())
			new MovimentoPedineElaboratore(destinazione.getRiga(), destinazione.getColonna(), tavola, selezionata);
		else if (selezionata.getHaDama())
			new MovimentoDamaElaboratore(destinazione.getRiga(), destinazione.getColonna(), tavola, selezionata);
	}
	

	private void muovi(Casella selezionata, Casella destinazione, Tavola tavola) {
		try {
			if (selezionata.getHaPedina())
				new MovimentoPedine(destinazione.getRiga(), destinazione.getColonna(), tavola, selezionata);
			else if (selezionata.getHaDama())
				new MovimentoDama(destinazione.getRiga(), destinazione.getColonna(), tavola, selezionata);
			}
		catch (NullPointerException e) {}
	}
	
	
}
