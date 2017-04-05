package iu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class EcouteurBouton implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		//CODE TRAITEMENT EVENT CLICK!!!
		
		if(e.getSource() instanceof JButton){
			//click sur bouton..
			
			JButton btn=(JButton)e.getSource();
			switch (btn.getName()) {
				case "Ajoute":
					Ajouter aj  = new Ajouter();
					aj.setVisible(true);
					
					break;
					
				case "Liste":
					Liste lt  = new Liste();
					lt.setVisible(true);
					
					break;
				case "Deconn":
					
					Login lg= new Login();
					lg.setVisible(true);
					
					break;
				default:
					System.out.println("Click sur refresh");
					break;
			}
		}else if(e.getSource() instanceof JMenuItem){
			//click sur option menu..
		}	
	}
}
