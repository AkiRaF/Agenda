package iu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import Dao.Connections;
import Dao.TableEntity;
import iu.ListeFenetre.JTableBDModele;
import iu.ListeFenetre.MonModele;
import manager.Manager;
import tools.tools;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.UIManager;

public class Liste extends JFrame {
	
	private Liste fenetre_courante;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane jsp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Liste frame = new Liste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Liste() {
		
		fenetre_courante=this;
		
		
		setTitle("Liste de RDV");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int bouton_clique = tools.Confermation("Etes vous sûr ?");
				if(bouton_clique==JOptionPane.YES_OPTION) {
					fenetre_courante.dispose();
				}
			}
		});
		
		
		
		setBounds(100, 100, 552, 497);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		
		createJtable();
		tools.Center(this);
	}
	
	private void DisplayList(String nomtable){
		//Récupérer les détailles de BDD par "Manager".
		
		Manager m= new Manager(nomtable);
		
		List<TableEntity> data = m.GetListe();
		List<String> columns = m.GetColonnes();
		
		String[] colonnes = new String[columns.size()];
		columns.toArray(colonnes);	
		
		//Création MODELE
		MonModele modele = new MonModele(colonnes,data );
		table.setModel(modele); //Associer modele de données à la JTable (grille)
		
	}
	
	private void createJtable() {
		contentPane.setLayout(null);
		// créer une table de ListeRDV
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBackground(new Color(204, 255, 204));
		jsp= new JScrollPane(table);
		jsp.setBackground(new Color(204, 255, 204));
		jsp.setBounds(68, 5, 463, 448);

		//Définir rendu spécifique à colonne selon son Type
		contentPane.add(jsp);
		
		DisplayList("listerdv");
		
		tools.Center(this);
	}
	
	class MonModele extends AbstractTableModel{		
		
		//Récupérer les données de la table "listerdv".
		
		String[] colonnes;
		List<TableEntity> data; //appel data dans la table liste
		
		
		public MonModele(String[] colonnes, List<TableEntity> data) {
			this.colonnes= colonnes;
			this.data=data;
		}

		@Override
		public int getRowCount() { //compte la taille de rangée
			return data.size();
		}
		@Override
		public int getColumnCount() { //compte le longueur de colonne
			return colonnes.length;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) { // get les index de chaque colonne et rangée 
			return data.get(rowIndex).values.get(columnIndex);
		}
		
		@Override
		public String getColumnName(int columnIndex) { //get nom de colonne
			return colonnes[columnIndex];
		}
		
	}	
		
}
