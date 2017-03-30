package iu;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import manager.Manager;
import Dao.TableEntity;
import tools.tools;

public class ListeFenetre extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane jsp;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeFenetre frame = new ListeFenetre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Manager m;
	private JComboBox cmbTables;
	public ListeFenetre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 319);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmFermer = new JMenuItem("Fermer");
		mnFichier.add(mntmFermer);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CreateComboTables();
		CreateJTable();
				
		tools.Center(this);
	}
	
	private void CreateComboTables() {
		List<String> tables =  Manager.GetTables();
		
		tables.add(0, "Sélectionner Table..");
		cmbTables = new JComboBox(tables.toArray());
		cmbTables.setBounds(12, 0, 179, 35);
		contentPane.add(cmbTables);

		cmbTables.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cmbTables.getSelectedIndex()==1){
					//JOptionPane.showMessageDialog(null, cmbTables.getSelectedItem());
				String nomtable=(String) cmbTables.getSelectedItem();
					DisplayTable(nomtable);
				}
			}
		});
	}
	private void DisplayTable(String nomtable) {
		Manager m= new Manager(nomtable);
		List<TableEntity> data = m.GetListe();
		List<String> columns = m.GetColonnes();
		
		String[] colonnes = new String[columns.size()];
		columns.toArray(colonnes);
		
		JTableBDModele modele= new JTableBDModele(colonnes, data);
		table.setModel(modele);
	}
	private void CreateJTable(){
		table = new JTable();	
		jsp= new JScrollPane(table);
		jsp.setBounds(12, 36, 537, 180);
				
		//Création MODELE!!!
		MonModele modele = new MonModele();
		table.setModel(modele); //Associer modele de données à la JTable (grille)!!!
		
		//Définir rendu spécifique à colonne selon son Type!!
//		table.setDefaultRenderer(ImageIcon.class, new RenduCelluleImage());
//		table.setDefaultRenderer(String.class, new RenduCelluleString());
		
		contentPane.add(jsp);
	}
	
	
	class JTableBDModele extends AbstractTableModel{

		String[] colonnes;
		List<TableEntity> donnees;		
		
		public JTableBDModele(String[] colonnes,List<TableEntity> donnees ) {
			this.colonnes=colonnes;
			this.donnees=donnees;
		}	
		@Override
		public int getRowCount() {
			return donnees.size();
		}
		@Override
		public int getColumnCount() {
			return colonnes.length;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return donnees.get(rowIndex).values.get(columnIndex);
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			return colonnes[columnIndex];
		}
		
		@Override
		public Class<?> getColumnClass(int columnIndex) {
//			switch (columnIndex) {
//				case 2:
//					return ImageIcon.class;
//				case 0:
//					return Integer.class;
//				default:
					return String.class;
//			}
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return (columnIndex>0);
		}
		
	}
	class MonModele extends AbstractTableModel{		
		
		String[] colonnes;
		Object[][] donnees;				
		public MonModele() {
			colonnes= new String[]{"ID","Libelle","Photo"};
			donnees = new Object[][]{
				{1000,"PRODUIT 1","db.jpg"},
				{2000,"PRODUIT 2","exit.png"},
				{3000,"PRODUIT 3","exit2.png"}
			};
		}
		@Override
		public int getRowCount() {
			return donnees.length;
		}
		@Override
		public int getColumnCount() {
			return colonnes.length;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return donnees[rowIndex][columnIndex];
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			return colonnes[columnIndex];
		}
		
		/*@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
				case 2:
					return ImageIcon.class;
				case 0:
					return Integer.class;
				default:
					return String.class;
			}
		}*/
	}	
	/*class RenduCelluleImage extends DefaultTableCellRenderer{
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
			String filename = (String) table.getModel().getValueAt(row, column);
			setIcon(new ImageIcon(RenduCelluleImage.class.getResource("/images/"+filename)));
			return this;
		}
	}*/
//	class RenduCelluleString extends DefaultTableCellRenderer{
//		@Override
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {			
//			JLabel label= new JLabel((String) table.getModel().getValueAt(row, column));		
//			label.setForeground(row%2==0?Color.blue:Color.red);
//			
//			return label;
//		}
//	}
}
