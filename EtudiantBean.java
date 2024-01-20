/**
 * 
 */
package com.GestionEtudiants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;



/**
 * 
 */
@RequestScoped 
@Named
public class EtudiantBean {
	private Etudiant etudiant;
	private List<Etudiant> listeEtudiant; 
	private Date date;
	private boolean modif = false;
	private static int eId;
	
	
	 //private List<Etudiant> images= new ArrayList<Etudiant>();
	 
	
		
	
	
	private EtudiantBean() {
		// TODO Auto-generated constructor stub
		etudiant = new Etudiant();
		
	}
	public Connection connect() {
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcrudjsf","root","");
			return con ;
		}catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		Connection con = null;
		return con ;
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Connection con = null;
			return con ;
	}

  }
	
	
	public List<Etudiant> afficheEtudiant() {
		listeEtudiant= new ArrayList<Etudiant>();
		
		String req = "select * from etudiant";
	
		try {
			PreparedStatement ps= connect().prepareStatement(req);
			
			ResultSet res = ps.executeQuery();
			
			
			while(res.next()) {
				
				Etudiant e= new Etudiant();
				e.setId(res.getInt("id"));
				e.setNom(res.getString("nom"));
				e.setPrenom(res.getString("prenom"));
				e.setDatenaiss(res.getDate("Datenaiss"));
				listeEtudiant.add(e);
				
			}
			return listeEtudiant;
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return listeEtudiant;
		}
	}
	
	public void ajoutEtudiant() {
		// la requet sql
	String req = " insert into etudiant(nom, prenom,datenaiss) value(?,?,?) ";
	etudiant.setDatenaiss(convDate(date));
	
	try {
		
//reparation de la requete
	PreparedStatement ps = connect().prepareStatement(req);
	//ajout dans la requete sql
	ps.setString(1, etudiant.getNom());
	ps.setString(2, etudiant.getPrenom());
	ps.setDate(3, etudiant.getDatenaiss());

	
	ps.execute();
	
	afficheEtudiant();
	etudiant = new Etudiant();
	date=null;
} catch (SQLException e1) {
// TODO Auto-generated catch block
    e1.printStackTrace();
}
	}
	
	public void deleteEtudiant(Etudiant e) {
		String req ="delete from etudiant where id = ?";
		try {
			PreparedStatement ps = connect().prepareStatement(req);
			ps.setInt(1, e.getId());
			ps.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			    e1.printStackTrace();
		}
	}
	

	public void affiche(Etudiant e) {
		eId = e.getId();
		date = e.getDatenaiss();
		etudiant = e;
		modif=true;
	}
	
	
	public void modifEtudiant() {
		etudiant.setDatenaiss(convDate(date));
	try {
		String req = " UPDATE etudiant SET nom = ?, prenom = ?,datenaiss = ? WHERE id = ? ";
	PreparedStatement ps = connect().prepareStatement(req);
	ps.setString(1, etudiant.getNom());
	ps.setString(2, etudiant.getPrenom());
	ps.setDate(3, etudiant.getDatenaiss());
    ps.setInt(4,eId);
    
    System.out.println(ps);
	
	ps.executeUpdate();
	
	afficheEtudiant();
	etudiant = new Etudiant();
	date=null;
} catch (SQLException e1) {
// TODO Auto-generated catch block
    e1.printStackTrace();
}
	}

	
	public java.sql.Date convDate(java.util.Date calendarDate){
		return new java.sql.Date(calendarDate.getTime());
	}

	
	
	

	/**
	 * @return the eId
	 */
	public int geteId() {
		return eId;
	}
	/**
	 * @param eId the eId to set
	 */
	public void seteId(int eId) {
		EtudiantBean.eId = eId;
	}
	/**
	 * @return the modif
	 */
	public boolean isModif() {
		return modif;
	}
	/**
	 * @param modif the modif to set
	 */
	public void setModif(boolean modif) {
		this.modif = modif;
	}
	/**
	 * @return the pistEtudiant
	 */
	public List<Etudiant> getPistEtudiant() {
		return afficheEtudiant();
	}


	/**
	 * @param pistEtudiant the pistEtudiant to set
	 */
	public void setPistEtudiant(List<Etudiant> pistEtudiant) {
	}


	/**
	 * @return the etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}


	/**
	 * @param etudiant the etudiant to set
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}


	/**
	 * @return the listeEtudiant
	 */
	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}


	/**
	 * @param listeEtudiant the listeEtudiant to set
	 */
	public void setListeEtudiant(List<Etudiant> listeEtudiant) {
		this.listeEtudiant = listeEtudiant;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	//public List<Etudiant> getImages() {
		//return images;
	//}
	
	//public void setImages(List<Etudiant> images) {
		//this.images= images;
	//}
	
}