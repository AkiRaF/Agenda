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
import javax.swing.table.DefaultTableModel;

import manager.Manager;
import tools.tools;
import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;

public class Accueil extends JFrame {

	private JPanel contentPane;
	private Accueil fenetre_courante;
	DefaultTableModel model;
	Calendar cal = new GregorianCalendar();
	JLabel label;
	private JTable table;
	

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
		setTitle("Agenda");
		
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
		setBounds(100, 100, 565, 380);
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
		
		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(new Color(0, 100, 0));
		btnNewButton_1.setBounds(0, 37, 86, 39);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(new Color(0, 100, 0));
		btnNewButton.setBounds(0, 75, 86, 39);
		panel.add(btnNewButton);
		
		JButton btnDconnexion = new JButton("");
		btnDconnexion.setBounds(0, 246, 86, 39);
		panel.add(btnDconnexion);
		btnDconnexion.setBackground(new Color(95, 158, 160));
		btnDconnexion.setIcon(new ImageIcon(Accueil.class.getResource("/images/1481726811_Exit.png")));
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(0, 100, 0));
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSupprimer.setBounds(0, 113, 86, 39);
		panel.add(btnSupprimer);
		
		JButton btnListe = new JButton("Liste");
		btnListe.setForeground(Color.WHITE);
		btnListe.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnListe.setBackground(new Color(0, 100, 0));
		btnListe.setBounds(0, 0, 86, 39);
		panel.add(btnListe);
		
		
		//
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBackground(new Color(0, 100, 0));
		panel_2.setBounds(0, 0, 549, 41);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblEmploiDuTemps = new JLabel("nomPersonne");
		lblEmploiDuTemps.setBounds(272, 0, 115, 41);
		panel_2.add(lblEmploiDuTemps);
		lblEmploiDuTemps.setBackground(Color.WHITE);
		lblEmploiDuTemps.setForeground(Color.WHITE);
		lblEmploiDuTemps.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel label = new JLabel(" Emploi du temps de");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setBackground(Color.WHITE);
		label.setBounds(104, 0, 162, 41);
		panel_2.add(label);
		
		//
		table = new JTable();
		table.setBackground(new Color(204, 255, 204));
		table.setBounds(534, 52, -442, 278);
		JScrollPane pane = new JScrollPane(table);
		
		
		//
		
		
		
		EcouteurBouton ecouteur = new EcouteurBouton();
		btnDconnexion.setName("Deconn");
		btnListe.setName("Liste");
		btnSupprimer.setName("Supp");
		btnNewButton.setName("Modif");
		btnNewButton_1.setName("Ajoute");
		
		
		btnListe.addActionListener(ecouteur);
		btnNewButton.addActionListener(ecouteur);
		btnDconnexion.addActionListener(ecouteur);
		btnNewButton_1.addActionListener(ecouteur);
		btnSupprimer.addActionListener(ecouteur);
		
		tools.Center(this);
	}
	
	private void calendar(){
		
	}
	
	void updateMonth() {
	    
	 
	  }
}
