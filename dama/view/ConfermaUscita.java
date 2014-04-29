package dama.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class ConfermaUscita extends JFrame {

	public ConfermaUscita() {
		super("Attenzione!!!");

		this.setLayout(new BorderLayout());
		
		JLabel scritta = new JLabel("Sei proprio sicuro di volere uscire?");
		scritta.setHorizontalAlignment(SwingConstants.CENTER);
		scritta.setFont(new Font("Sans-Serif", Font.BOLD, 16));
		add(scritta, BorderLayout.NORTH);
		
		JPanel risposta = new JPanel();
		risposta.setLayout(new FlowLayout());


		risposta.add(new JButton("Sì") {{
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}}
		);
		
		risposta.add(new JButton("No") {{
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
				
			});
		}}
		);

		add(risposta, BorderLayout.CENTER); 
		
		setSize(300, 110);
		setLocation(400, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}