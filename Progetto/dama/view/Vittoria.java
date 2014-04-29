package dama.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Vittoria extends JFrame {
	
	JLabel etichetta;
	
	
	/**
	 * Costruttore della classe che fa comparire una finestra che indica il vincitore alla conclusione della partita
	 * 
	 * @param vincitore contiene il valore indicante quale dei due giocatori è il vincitore
	 */
	public Vittoria(boolean vincitore) {
		super("Congratulazioni");
		
		setSize(300, 150);
		
		this.setLocation(400, 450);
		
		etichetta = scegliEtichetta(vincitore);
		etichetta.setHorizontalAlignment(SwingConstants.CENTER);
		etichetta.setFont(new Font("Sans-Serif", Font.BOLD, 24));
		etichetta.setForeground(Color.RED);
		
		add(etichetta, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	private JLabel scegliEtichetta(boolean vincitore) {
		if (!vincitore)
			return new JLabel("IL BIANCO VINCE!!!");
		else
			return new JLabel("IL NERO VINCE!!!");
	}

}
