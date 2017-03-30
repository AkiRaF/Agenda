package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connections {
	//Url de connection..
	//charger le driver / type de base de données
	
	public static final String BD_NAME = "tabrdv";
	private static final String BD_USER = "root";
	private static final String BD_PASSWORD = "";
	
	final static String UrlConnMySQL="jdbc:mysql://localhost/"+BD_NAME+"?user="+BD_USER+"&password="+BD_PASSWORD+"";
	final static String DriverNameMySQL="com.mysql.jdbc.Driver";
	
	private static Connection connection=null; 
	
	//Singleton..
	public static Connection Instance(){
		if( connection==null){
			//création connection vers BD.. 
			try {				
				Class.forName(DriverNameMySQL);						  //1 : Chargement du driver (pilote)..					
				connection=DriverManager.getConnection(UrlConnMySQL); //2 : Création objet 'Connection' au server de BD!			
				System.out.println("Connection établie avec succès! [Base Données : "+connection.getCatalog().toUpperCase()+"]");
				
			} catch (SQLException | ClassNotFoundException e) {
				System.err.println("Erreur Connection : "+e);
			} 					 
		}
		
		return connection;
	}
	public static void Close(){
		try {
			if(connection!=null && !connection.isClosed())
				connection.close();
			System.out.println("Connection fermée avec succès!");
		} catch (SQLException e) {}
	}
	
	
}
