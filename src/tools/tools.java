package tools;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// C'est la class de utiliser les méthodes en plusier fois dans l'autre class ou fenetre.
public class tools{
	
	public static int Confermation(String message){ //afficher la fenetre de boite dialog pour confermer ... - pour utiliser cette dialoge en plusiuer fois.
		return JOptionPane.showConfirmDialog(null, message, "Confirmer..",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	public static void Center(JFrame fenetre){ // (...) on appel une parametre.
		// Pour donner la position de la fenetre au milieu de l'écran.
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		fenetre.setLocation(dim.width/2-fenetre.getSize().width/2,dim.height/2-fenetre.getSize().height/2);
	}
}