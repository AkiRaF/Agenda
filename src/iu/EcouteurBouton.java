package iu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class EcouteurBouton implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//CODE TRAITEMENT EVENT CLICK!!!
		
		if(e.getSource() instanceof JButton){
			//click sur bouton..
			
			JButton btn=(JButton)e.getSource();
		}
	}
}
