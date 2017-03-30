package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	//Classe d'accès aux données : CRUD!!
	static Connection connection;
	static{
		connection = Connections.Instance(); //Recup. objet connection à la BD dès le chargement de la classe Dao!
	}

	private String nomtable;
	private Statement stm;
	private ResultSet rs;
	private PreparedStatement pstm;

	
	public static void Close(){
		Connections.Close();
	}
	
	public Dao(String nomtable) {	
		this.nomtable=nomtable;	
	}
	private TableEntity RsToItem(ResultSet rs){
		TableEntity item = new TableEntity();
		try {
			ResultSetMetaData rmd = rs.getMetaData();
			int nbcols =  rmd.getColumnCount();		
			for (int i = 1; i <= nbcols; i++) {
				String colname=rmd.getColumnName(i);	
				//recup. colonne
				item.columns.add(colname);				
				//..récup. valeur
				item.values.add(rs.getObject(i));
			}
		} catch (SQLException e) {
			System.err.println("==>"+e.getMessage());
		}	
		return item;
	}

	//Meta
	public static List<String> GetTables(){
		List<String> tables= new ArrayList<String>();
		String sql="SHOW TABLES";		
		try {				
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);					
			while(rs.next()){		
				tables.add(rs.getString(1));
			}
			rs.close();
			stm.close();			
		} catch (SQLException e) {
			System.err.println("Erreur SQL : "+e);
		}
		return tables;
	}
	public List<String> GetColonnes(){
		List<String> columns= new ArrayList<String>();
		String sql="SELECT * FROM "+nomtable;		
		try {				
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);		
			ResultSetMetaData rmd = rs.getMetaData();
			int nbcols =  rmd.getColumnCount();		
			for (int i = 1; i <= nbcols; i++) {
				String colname=rmd.getColumnName(i);				
				columns.add(colname);
			}
			rs.close();
			stm.close();
			
		} catch (SQLException e) {
			System.err.println("Erreur SQL : "+e);
		}

		return columns;
	}
	public String GetPrimaryColumn(){		
		String sql="SELECT COLUMN_NAME	FROM INFORMATION_SCHEMA.COLUMNS WHERE "; 
		sql +="TABLE_SCHEMA = '"+Connections.BD_NAME+"' AND TABLE_NAME = '"+nomtable+"'  AND COLUMN_KEY = 'PRI'";				
		String primary_column="";
		try {
			stm=connection.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next())
				primary_column=rs.getString(1);		

			rs.close();
			stm.close();
		} catch (SQLException e) {System.out.println("GetPrimaryColumn - SQLException : "+e.getMessage());}

		return primary_column;
	}

	//Méthodes CRUD
	public List<TableEntity> GetListe(){		
		List<TableEntity> items= new ArrayList<TableEntity>();
		String sql="SELECT * FROM "+nomtable;		
		try {				
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);		

			TableEntity item;
			while (rs.next()) {
				//Parcours résultat requete + Mapping!!!	
				item=RsToItem(rs);
				items.add(item);
			}	
			
			rs.close();
			stm.close();
		} catch (SQLException e) {
			System.err.println("Erreur SQL : "+e);
		}
		return items;
	}
	public TableEntity GetById(Object IdValue){		
		String sql="SELECT * FROM "+nomtable+" WHERE "+GetPrimaryColumn()+"= ? ";
		
		TableEntity item=null;
		try {				
			pstm = connection.prepareStatement(sql);
			pstm.setObject(1, IdValue);

			rs = pstm.executeQuery();						
			if (rs.next()) 
				item=RsToItem(rs);	
			
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			System.err.println("Erreur SQL : "+e);
		}

		return item;
	}	
	public  List<TableEntity> GetByFilters(String[] columns, Object[] values){
		List<TableEntity> items= new ArrayList<TableEntity>();				
		String critere="";		
		for (String col : columns) {
			critere += (critere.equals("")?"":" AND ")+ col +" = ? ";
		}
		String sql="SELECT * FROM "+nomtable+" WHERE "+critere;		
		TableEntity item=null;
		try {				
			pstm = connection.prepareStatement(sql);
			int i=1;
			for (Object value : values) {
				pstm.setObject(i++, value);
			}
			rs = pstm.executeQuery();						
			while (rs.next()) {
				item = RsToItem(rs);
				items.add(item);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			System.err.println("Erreur SQL : "+e);
		}
		return items;
	}	
	public boolean Ajouter(TableEntity item){
		List<String> colonnes= GetColonnes();
		
		String sql="INSERT INTO "+nomtable+"(";
		for (int i=0;i<colonnes.size();i++) {
			sql +=colonnes.get(i) + ((i==colonnes.size()-1)?"":",");
		}		
		sql +=") VALUES(";
		for (int i=0;i<item.values.size();i++) {
			sql +="?"+ ((i==item.values.size()-1)?"":",");
		}			
		sql +=")";
		
		try {
			pstm = connection.prepareStatement(sql);
			for (int i=0;i<item.values.size();i++) {
				pstm.setObject(i+1,item.values.get(i));
			}	
			int nb_insert = pstm.executeUpdate();		
			System.out.println(nb_insert>0?"Item ajouté avec succès!":"Aucun item ajouté!");

			return (nb_insert>0);
		} catch (SQLException e) {
			System.out.println("Erreur SQL : "+e.getMessage());
			return false;
		}
	}
	public boolean Supprimer(Object id){
		String sql="DELETE FROM "+nomtable+" WHERE "+GetPrimaryColumn()+"= ? ";	
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setObject(1,id);

			int nb_delete = pstm.executeUpdate();		
			System.out.println(nb_delete>0?"Item supprimé avec succès!":"Aucun item supprimé!");

			return (nb_delete>0);
		} catch (SQLException e) {
			System.out.println("Erreur SQL : "+e.getMessage());
			return false;
		}
		
	}
	public boolean Modifier(TableEntity item){
		List<String> colonnes= GetColonnes();

		String set_txt="";
		for (String col : colonnes) {
			if(!col.equals(GetPrimaryColumn()))
				set_txt += (set_txt.equals("")?"":", ") + col + " = ?";
		}
		String sql="UPDATE "+nomtable+" SET " +set_txt+" WHERE "+GetPrimaryColumn()+" = ?";

		System.out.println(sql);
		int i;

		try {
			pstm = connection.prepareStatement(sql);		
			for (i=1;i<item.values.size();i++) {
				pstm.setObject(i,item.values.get(i));
			}	
			pstm.setObject(i,item.values.get(0));

			int nb_update = pstm.executeUpdate();		
			System.out.println(nb_update>0?"Item modifié avec succès!":"Aucun item modifié!");

			return (nb_update>0);
		} catch (SQLException e) {
			System.out.println("Erreur SQL : "+e.getMessage());
			return false;
		}
	}

}
