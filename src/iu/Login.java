package iu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Dao.TableEntity;
import manager.Manager;
import tools.tools;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JLabel lblSeConnecter;
	private Login fenetre_courante;
	private JPasswordField txtPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setBackground(new Color(65, 105, 225));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 462);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txtLogin = new JTextField();
		txtLogin.setBackground(new Color(245, 245, 245));
		txtLogin.setBounds(80, 165, 192, 26);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Sitka Text", Font.BOLD, 16));
		lblMotDePasse.setBounds(80, 202, 140, 26);
		contentPane.add(lblMotDePasse);
		
		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setFont(new Font("Sitka Text", Font.BOLD, 16));
		lblLogin.setBounds(80, 128, 84, 26);
		contentPane.add(lblLogin);
		
		lblSeConnecter = new JLabel("Se Connecter");
		lblSeConnecter.setForeground(new Color(0, 0, 128));
		lblSeConnecter.setFont(new Font("Century", Font.BOLD, 30));
		lblSeConnecter.setBounds(73, 35, 220, 53);
		contentPane.add(lblSeConnecter);
		
		JButton btnConnecter = new JButton("connecter");
		btnConnecter.setForeground(UIManager.getColor("Button.background"));
		btnConnecter.setFont(new Font("Sitka Text", Font.BOLD, 16));
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtPassword.equals("") && !txtLogin.equals("")) {
					Manager mc = new Manager("Utilisateur");
					
					List<TableEntity> users = mc.GetByFilters(new String[]{"Pseudo" ,"Password"}, new Object[]{txtLogin.getText(), txtPassword.getText()} );
				
					if(users.size() > 0) {
						TableEntity user = users.get(0);
						Accueil ac = new Accueil();
						ac.setVisible(true);
						dispose();	
						
						//JOptionPane.showMessageDialog(null, "Bienvenue.."+user.values.get(1));
						
					} else {
						JOptionPane.showMessageDialog(null, "Accès refusé");
					}
				}
			}
		});
		
		btnConnecter.setBackground(new Color(0, 100, 0));
		btnConnecter.setBounds(111, 306, 128, 31);
		contentPane.add(btnConnecter);
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(UIManager.getColor("Button.background"));
		txtPassword.setBounds(80, 239, 192, 26);
		contentPane.add(txtPassword);
		
		tools.Center(this);
	}
}
