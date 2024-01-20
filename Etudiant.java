package com.GestionEtudiants;

import java.sql.Date;

/**
 * 
 */
public class Etudiant {
private int id ;
private String nom ;
private String prenom;
private Date datenaiss;



public Etudiant() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the nom
 */
public String getNom() {
	return nom;
}
/**
 * @param nom the nom to set
 */
public void setNom(String nom) {
	this.nom = nom;
}
/**
 * @return the prenom
 */
public String getPrenom() {
	return prenom;
}
/**
 * @param prenom the prenom to set
 */
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
/**
 * @return the datenaiss
 */
public Date getDatenaiss() {
	return datenaiss;
}
/**
 * @param datenaiss the datenaiss to set
 */
public void setDatenaiss(Date datenaiss) {
	this.datenaiss = datenaiss;
}


}