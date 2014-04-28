package dama.view;

import dama.controller.Movimento;
import dama.model.Tavola;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class Damiera extends JFrame {

	private static final int HEIGHT = 780;
	private static final int WIDTH = 750;
	
	
	/**
	 * Costruttore della damiera
	 * 
	 * @param tavolaIniziale parametro che contiene un array con la disposizione iniziale delle pedine
	 */
	public Damiera(Tavola tavolaIniziale) {
		super("Il Gioco della Dama!");
		
		setSize(WIDTH, HEIGHT);
		this.setLocation(300, 100);
		
		add(pannelloDamiera(tavolaIniziale), BorderLayout.CENTER);
		JLabel turno = addTurno();
		Movimento.setEtichettaTurno(turno);
		
		/**
		 * Action Listener che apre la finestra di conferma prima di uscire
		 */
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				new ConfermaUscita().setVisible(true);
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
			
		});
		
		this.setDefaultCloseOperation(0);
	}
	
	
	/**
	 * Metodo che crea il pannello della damiera
	 * 
	 * @param tavolaIniziale parametro che contiene un array con la disposizione iniziale delle pedine
	 * @return ritorna il pannello costruito
	 */	
	private JPanel pannelloDamiera(Tavola tavolaIniziale) {	
		JPanel table = new JPanel();		
		table.setLayout(new GridLayout(8, 8));
		table.setSize(650, 650);
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if(tavolaIniziale.getCasella(i, j) instanceof Casella)
					table.add(tavolaIniziale.getCasella(i, j));
				else
					table.add(new CasellaChiara());
				
		return table;
	}
	
	
	/**
	 * Metodo che crea la JLabel contente il turno e il numero di pedine mangiate
	 * 
	 * @return ritorna la JLabel costruita
	 */
	private JLabel addTurno() {
		JLabel etichetta = new JLabel("Turno del bianco  --  Bianco: 0 - Nero: 0");
		etichetta.setHorizontalAlignment(SwingConstants.CENTER);
		etichetta.setFont(new Font("Sans-Serif", Font.CENTER_BASELINE , 20));
		add(etichetta, BorderLayout.SOUTH);
		
		return etichetta;
	}


	public static void main(String[] args) {
		new Damiera(new Tavola()).setVisible(true);
	}

}
