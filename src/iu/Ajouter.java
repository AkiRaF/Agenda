package iu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import Dao.TableEntity;
import manager.Manager;
import tools.tools;

public class Ajouter extends JFrame{
	
	private Ajouter fenetre_courante;
	private JPanel contentPane;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textUtili;
	private SpinnerDateModel spinnerDate;
	private SpinnerDateModel spinnerDuree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajouter frame = new Ajouter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Ajouter() {
		
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
		
		
		setBounds(100, 100, 365, 464);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		//Jlabel
		
		JLabel lblNomDeClient = new JLabel("Nom utilisateur :");
		lblNomDeClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomDeClient.setBounds(20, 66, 122, 14);
		contentPane.add(lblNomDeClient);
		
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
		
		
		
		JLabel lblCommentaire = new JLabel("Commentaire :");
		lblCommentaire.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCommentaire.setBounds(20, 243, 112, 14);
		contentPane.add(lblCommentaire);
		
		
		JLabel label = new JLabel("Nom de client :");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(20, 197, 112, 14);
		contentPane.add(label);
		
//		textUtili = new JTextField();
//		textUtili.setBounds(152, 65, 153, 20);
//		textUtili.add(textUtili);
//		textUtili.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(152, 242, 153, 72);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(152, 196, 153, 20);
		contentPane.add(textField_1);
		
		//SpinnerDateModel
		
		SpinnerDateModel spinnerDuree = new SpinnerDateModel();
		spinnerDuree.setCalendarField(Calendar.MINUTE);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(spinnerDuree);
		spinner.setEditor(new JSpinner.DateEditor(spinner, "hh:mm a"));
		spinner.setBounds(152, 152, 81, 20);
		contentPane.add(spinner);
		
		SpinnerDateModel spinnerDate = new SpinnerDateModel();
		spinnerDate.setCalendarField(Calendar.DAY_OF_YEAR);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(spinnerDate);
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1, "yyyy-MM-dd"));
		spinner_1.setBounds(152, 110, 81, 20);
		contentPane.add(spinner_1);
		
		//JComBox
		
		
		/*Manager em = new Manager("utilisateur");

		
		String[] xx=new String[]{};
		JComboBox comboBox = new JComboBox(xx);
		comboBox.setBounds(152, 65, 153, 20);
		
		contentPane.add(comboBox);*/
		
		//Jbutton
		
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(new Color(255, 255, 255));
		btnAjouter.setBackground(new Color(0, 100, 0));
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAjouter.setBounds(132, 368, 89, 28);
		
		btnAjouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!textUtili.getText().trim().equals("")){
					
					Manager em = new Manager("listerdv");
					
					TableEntity items = new TableEntity();
							
					
					try {
						items.values.add(null);
						//items.values.add(id);
						//items.values.add(textUtili.getText());
						items.values.add(spinnerDate.getValue());
						items.values.add(spinnerDuree.getValue());
						items.values.add(textField_1.getText());
						items.values.add(textField_3.getText());
						
						if(em.Ajouter(items)){
							JOptionPane.showMessageDialog(null, "Votre RDV a été ajouter !");
							Ajouter mainfen = new Ajouter();
							mainfen.setVisible(true);
							dispose();
						}
						
						
					} catch (Exception e2) {
						System.out.println("Err : "+e2.getMessage());
					}
				    
				}else{
					JOptionPane.showMessageDialog(null, "Avez vous remplir tous les champs ?", "Erreur", JOptionPane.ERROR_MESSAGE);
				}	
				
			}
		});
		contentPane.add(btnAjouter);
		
		
		
		
		//CreateComboValue();
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
