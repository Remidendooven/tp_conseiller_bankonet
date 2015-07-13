
package com.bankonet.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bankonet.CompteNonTrouveException;

public class Client implements Comparable<Client> {
	/* Attributs */
	private int identifiant;
	private String nom;
	private String prenom;
	private List<CompteCourant> comptesCourantsList = new ArrayList<CompteCourant>();
	private List<CompteEpargne> comptesEpargnesList = new ArrayList<CompteEpargne>();
	private List<Compte> comptes = new ArrayList<Compte>();
	
	/* Constructeurs */	
	public Client() {
		
	}
	
	public Client(int identifiant, String nom, String prenom) {
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	/* Méthodes */
	public float calculerAvoirGlobal() {
		float avoirGlobal = 0;
		for (Compte compte : this.comptes) {
			avoirGlobal += compte.getSolde();
		}
		return avoirGlobal;
	}
	
	public int compareTo(Client aClient) throws ClassCastException {
		if (aClient instanceof Client) {
			return aClient.getIdentifiant() - this.getIdentifiant();
		}
		throw new ClassCastException();
	}
	
	public void creerCompte(Compte compte) {
		this.comptes.add(compte);
	}
	
	public void supprimerCompte(Compte compte) {
		this.comptes.remove(compte);
	}
	
	public void supprimerCompte(int identifiant) throws CompteNonTrouveException {
		try {
			supprimerCompte(retournerCompte(identifiant));
		} catch(CompteNonTrouveException e) {
			System.err.println("Compte non trouvé.");
		}
	}
	
	public Compte retournerCompte(int identifiant) throws CompteNonTrouveException {
		for (Compte compte : this.comptes) {
			if (compte.getIdentifiant() == identifiant) {
				return compte;
			}
		}
		throw new CompteNonTrouveException();
	}
	
	public String toString() {
		String str = "";
		str += "Nom : " + this.getNom() + "\nPrénom : " + this.prenom;
		for (Compte compte : this.comptes) {
			str += "\n" + compte.toString();
		}		
		str+= "\nAvoir Global : " + this.calculerAvoirGlobal() + "€";
		return str;
	}
	
	/* Getters & Setters */
	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public List<Compte> getComptes() {
		List<Compte> tmpCompte = new ArrayList<Compte>();
		for (CompteCourant compteCourant : comptesCourantsList) {
			tmpCompte.add(compteCourant);
		}
		for (CompteEpargne compteEpargne : comptesEpargnesList) {
			tmpCompte.add(compteEpargne);
		}
		return tmpCompte;
	}
}
