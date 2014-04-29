package dama.view;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CasellaChiara extends JPanel {
	
	private final static int DIMENSIONE = 60;
	private final static Color casella_chiara = new Color(255, 228, 167);

	
	/**
	 * Costruttore della casella chiara, un semplice pannello colorato
	 */
	public CasellaChiara() {
		setSize(DIMENSIONE, DIMENSIONE);
		this.setBackground(casella_chiara);
	}


}
