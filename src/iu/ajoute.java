package iu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.TableEntity;
import manager.Manager;
import tools.tools;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class ajoute extends JFrame {
	
	private ajoute fenetre_courante;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	SpinnerDateModel spinnerDate;
	SpinnerDateModel spinnerDuree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajoute frame = new ajoute();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ajoute() {
		
		fenetre_courante=this;
		
	
		setTitle("Ajouter RDV");
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
		
		
		setBounds(100, 100, 365, 376);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(142, 64, 153, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNomDeClient = new JLabel("Nom de client :");
		lblNomDeClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomDeClient.setBounds(20, 66, 112, 14);
		contentPane.add(lblNomDeClient);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(142, 109, 81, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(142, 151, 51, 20);
		contentPane.add(textField_2);
		
		JLabel lblDateDeRdv = new JLabel("Date de RDV  :");
		lblDateDeRdv.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDateDeRdv.setBounds(20, 111, 112, 14);
		contentPane.add(lblDateDeRdv);
		
		JLabel lblHeurePrvue = new JLabel("Heure pr\u00E9vue : ");
		lblHeurePrvue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHeurePrvue.setBounds(20, 153, 112, 14);
		contentPane.add(lblHeurePrvue);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 100, 0));
		panel.setBounds(0, 0, 434, 36);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNouveauRendezvous = new JLabel("Nouveau Rendez-vous");
		lblNouveauRendezvous.setForeground(new Color(255, 255, 255));
		lblNouveauRendezvous.setBounds(10, 0, 227, 36);
		panel.add(lblNouveauRendezvous);
		lblNouveauRendezvous.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(new Color(255, 255, 255));
		btnAjouter.setBackground(new Color(0, 100, 0));
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAjouter.setBounds(134, 286, 89, 28);
		contentPane.add(btnAjouter);
		
		
		JLabel lblCommentaire = new JLabel("Commentaire :");
		lblCommentaire.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCommentaire.setBounds(20, 194, 112, 14);
		contentPane.add(lblCommentaire);
		
		textField_3 = new JTextField();
		textField_3.setBounds(142, 193, 153, 72);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		tools.Center(this);
		
	}
	
	public void actionPerformed(ActionEvent arg0){
		if(!textField.getText().trim().equals("")){
			Manager em = null;
			em = new Manager("listerdv");
			TableEntity items = new TableEntity();
			items.values.add(null);
			
			items.values.add(spinnerDate.getValue());
			items.values.add(spinnerDuree.getValue());
			items.values.add(textField.getText());
		}
	}
}
