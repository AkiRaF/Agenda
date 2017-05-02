package iu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.PasswordAuthentication;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Dao.TableEntity;
import iu.Liste.MonModele;
import manager.Manager;
import tools.tools;
import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;

public class Accueil extends JFrame {

	private JPanel contentPane;
	private Accueil fenetre_courante;
	
	Calendar cal = new GregorianCalendar();
	JLabel label;
	private JTable table;
	private JScrollPane jsp;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil frame = new Accueil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
	}

	public Accueil() {
		setTitle("Liste de RDV");
		
		fenetre_courante=this;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int bouton_clique = tools.Confermation("Etes vous sûr de vouloir quitter l'application?");
				if(bouton_clique==JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		setBounds(100, 100, 796, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 51));
		panel.setBounds(0, 45, 80, 285);
		contentPane.add(panel);
		panel.setLayout(null);
		    
		//
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAjouter.setBackground(new Color(0, 100, 0));
		btnAjouter.setBounds(0, 0, 86, 39);
		panel.add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ajouter aj  = new Ajouter();
				aj.setVisible(true);
				
			}
		});
		
		JButton btnDconnexion = new JButton("");
		btnDconnexion.setBounds(0, 246, 86, 39);
		panel.add(btnDconnexion);
		btnDconnexion.setBackground(new Color(95, 158, 160));
		btnDconnexion.setIcon(new ImageIcon(Accueil.class.getResource("/images/1481726811_Exit.png")));
		btnDconnexion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				int bouton_clique = tools.Confermation("Etes vous sûr ?");
				if(bouton_clique==JOptionPane.YES_OPTION) {
					Login lg = new Login();
					lg.setVisible(true);
					dispose();
					
				}
					
			}
		});
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(0, 100, 0));
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSupprimer.setBounds(0, 39, 86, 39);
		panel.add(btnSupprimer);
		btnSupprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index < 0){
					JOptionPane.showMessageDialog(null,"Sélectionne une ligne à supprimer !");
				}else{
//					Manager m = new Manager("listerdv");
//					TableEntity items = new TableEntity();
					//JOptionPane.showMessageDialog(null,"row select !");
					
					int idRdv = Integer.parseInt(table.getValueAt(index, 0).toString());
					Object[] options = { "Yes", "No" };
			
					int n = JOptionPane.showOptionDialog(null,
					"Est-ce que voulez-vous supprimer : "+idRdv+"?",
					"Êtes-vous sûre ? Confirmation :",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options,
					options[1]);
					
					if(n==JOptionPane.YES_OPTION){
						Manager m = new Manager("listerdv");
						m.Supprimer(idRdv);
					
					}
						
				}
			}

		});
		
		JButton btnRafrachir = new JButton("Rafra\u00EEchir...");
		btnRafrachir.setBackground(new Color(0, 100, 0));
		btnRafrachir.setForeground(Color.WHITE);
		btnRafrachir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRafrachir.setBounds(-3, 212, 89, 23);
		panel.add(btnRafrachir);
		btnRafrachir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createJtable();
				
			}
		});
		
		
		//
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBackground(new Color(0, 100, 0));
		panel_2.setBounds(0, 0, 780, 41);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		
		JLabel label = new JLabel(" Liste de Rendez-vous");
		label.setHorizontalAlignment(label.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.BOLD, 23));
		label.setBackground(Color.WHITE);
		label.setBounds(0, 0, 780, 41);
		label.setAlignmentX(CENTER_ALIGNMENT);
		panel_2.add(label);
		
		
		//
		
		
		
		EcouteurBouton ecouteur = new EcouteurBouton();
		btnDconnexion.setName("Deconn");
		btnSupprimer.setName("Supp");
		btnAjouter.setName("Ajoute");
		btnRafrachir.setName("Refresh");
			
		btnDconnexion.addActionListener(ecouteur);
		btnAjouter.addActionListener(ecouteur);
		btnSupprimer.addActionListener(ecouteur);
		btnRafrachir.addActionListener(ecouteur);
		
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
		table.setBackground(Color.WHITE);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jsp= new JScrollPane(table);
		jsp.setBounds(90, 45, 680, 285);

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

