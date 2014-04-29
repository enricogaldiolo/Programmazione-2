package dama.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import dama.controller.MovimentiPossibili;
import dama.controller.MovimentoDama;
import dama.controller.MovimentoPedine;
import dama.model.Tavola;


@SuppressWarnings("serial")
public class Casella extends JButton {
	
	/**
	 * costante che indica la dimensione della casella in px
	 */
	private final static int DIMENSIONE = 60;
	/**
	 * variabile che indica la riga della casella sulla tavola
	 */
	private int riga;
	/**
	 * variabile che indica la colonna della casella sulla tavola
	 */
	private int colonna;
	/**
	 * variabile che tiene in memoria la tavola di riferimento
	 */
	private Tavola tavola;
	/**
	 * variabile booleana che indica se la casella ha la pedina
	 */
	private boolean ha_pedina;
	/**
	 * variabile booleana che indica se la casella ha una dama
	 */
	private boolean ha_dama = false;
	/**
	 * variabile booleana che indica il colore dell'eventuale pedina sulla casella (false = bianco, true = nero)
	 */
	private boolean colore_pedina = false;
	/**
	 * variabile che durante uno spostamento memorizza la casella di origine
	 */
	private static Casella selezionata;
	private final static ImageIcon PEDINA_NERA = new ImageIcon("pedine/pedina_nera.png");
	private final static ImageIcon DAMA_NERA = new ImageIcon("pedine/dama_nera.png");
	private final static ImageIcon PEDINA_BIANCA = new ImageIcon("pedine/pedina_bianca.png");
	private final static ImageIcon DAMA_BIANCA = new ImageIcon("pedine/dama_bianca.png");
	private final static Color casella_scura = new Color(125, 78, 58);
	
	
	
	/**
	 * Costruttore delle caselle scure, cioè quelle su cui si muoveranno le pedine 
	 * 
	 * @param haPedina parametro che indica se la casella contiene una pedina pedina
	 */
	public Casella(final boolean haPedina) {
		setSize(DIMENSIONE, DIMENSIONE);
		
		this.ha_pedina = haPedina;
		
		
		setBackground(casella_scura);
		
		
		setIcona();
		
		this.addActionListener(new ActionListener() {
			
			/**
			 *  Action Listener che si attiva quando il pulsante rappresentante la casella viene premuto.
			 *  Controlla se era stato selezionata selezionata un'altra casella; se era stata selezionata ed
			 *  essa risulta compatibile allo spostamento, procede a chiamare la classe per effettuare il movimento,
			 *  altrimenti selezionerà la casella stessa, in attesa di destinazione.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				
						
				if (selezionata != null && (selezionata.getRiga() != riga || selezionata.getColonna() != colonna) && !ha_pedina && (selezionata.getHaPedina() || selezionata.getHaDama())) {								
					if (selezionata.getHaDama())
						new MovimentoDama(riga, colonna, tavola, selezionata);
					else
						new MovimentoPedine(riga, colonna, tavola, selezionata);
					selezionata = null;
					MovimentiPossibili.deselezionaTutto();
				}
				else {
					MovimentiPossibili.deselezionaTutto();
					selezionata = new Casella(ha_pedina);
					selezionata.setRigaColonnaTavola(riga, colonna, tavola);
					selezionata.setColorePedina(colore_pedina);
					selezionata.setDama(ha_dama);
					new MovimentiPossibili(tavola, selezionata);
				}
				
			}	
							
		});
		
	}
	
	
	public void setRigaColonnaTavola(int riga, int colonna, Tavola tavola) {
		this.riga = riga;
		this.colonna = colonna;
		this.tavola = tavola;
	}
	
	public void setDama(boolean ha_dama) {
		this.ha_dama = ha_dama;
		setIcona();
	}
	
	public void setColorePedina(boolean colore_pedina) {
		this.colore_pedina = colore_pedina;
		setIcona();
	}
	
	public void setHaPedina(boolean ha_pedina) {
		this.ha_pedina = ha_pedina;
		setIcona();
	}
		
	public boolean getHaDama() {
		return ha_dama;
	}
	
	public boolean getHaPedina()  {
		return ha_pedina;
	}
	
	public boolean getColorePedina() {
		return colore_pedina;
	}	
	
	public int getRiga() {
		return riga;
	}
	
	public int getColonna() {
		return colonna;
	}
	
	
	private void setIcona() {
		if (colore_pedina && ha_pedina && !ha_dama)
			setIcon(PEDINA_NERA);
		else if (colore_pedina && ha_dama)
			setIcon(DAMA_NERA);
		else if (ha_pedina && !ha_dama)
			setIcon(PEDINA_BIANCA);
		else if (ha_dama)
			setIcon(DAMA_BIANCA);
		else
			setIcon(null);
	}
	
	
	public boolean equals(Casella temp) {
		return riga == temp.getRiga() && colonna == temp.getColonna();
	}
	
	
}
