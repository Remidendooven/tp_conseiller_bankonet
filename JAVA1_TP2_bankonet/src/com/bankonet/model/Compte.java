
package com.bankonet.model;

import com.bankonet.CompteStat;
import com.bankonet.DebitException;

public abstract class Compte implements CompteStat{
	/* Attributs */
	private int identifiant;
	private String libelle;
	private float solde;
	
	/* Constructeurs */
	public Compte() {
	}
	
	public Compte(int identifiant, String libelle, float solde) {
		if (solde < 0) {
			System.out.println("Impossible de créer un compte avec un solde négatif. Initialisation du compte avec un solde de 0€");
			solde = 0;
		}
		this.identifiant = identifiant;
		this.libelle = libelle;
		this.solde = solde;
	}
	
	/* Méthodes */
	protected abstract boolean debitAutorise(float montant);
	protected abstract boolean creditAutorise(float montant);	
	public abstract TypeCompte typeDuCompte();
	
	public String toString() {
		return "Type du compte : " + this.typeDuCompte() + 
				" Id : " + this.getIdentifiant() + 
				" Libelle : " + this.getLibelle() + 
				" Solde : " + this.getSolde() + "€";
	}
	
	public void debiter(float montant) throws DebitException {
		if (!debitAutorise(montant)) {
			throw new DebitException("Débit de ce montant impossible");			
		}
		this.setSolde(this.getSolde() - montant);
	}
	
	public void crediter(float montant) {
		if (!creditAutorise(montant)) {
			System.err.println("Crédit de ce montant impossible.");
		}
		this.setSolde(this.getSolde() + montant);
	}
	
	public void effectuerVirement(Compte compteCible, float montant) throws DebitException {
		this.debiter(montant);
		compteCible.crediter(montant);
	}
	
	/* Getters & Setters */
	public int getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public float getSolde() {
		return solde;
	}
	
	public void setSolde(float solde) {
		this.solde = solde;
	}
}
