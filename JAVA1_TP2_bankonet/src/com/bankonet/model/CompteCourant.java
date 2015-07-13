

package com.bankonet.model;

public final class CompteCourant extends Compte{
	/* Attributs */
	private float decouvertAutorise;
	private static int nbComptesCourants = 0;
	
	/* Constructeurs */
	public CompteCourant() {
		super();
	}
	
	public CompteCourant(int identifiant, String libelle, float solde, float decouvertAutorise) {
		super(identifiant, libelle, solde);
		this.decouvertAutorise = decouvertAutorise;
		nbComptesCourants++;
	}
	
	/* Méthodes */
	@Override
	public TypeCompte typeDuCompte() {
		return TypeCompte.COURANT;
	}
	
	@Override
	public boolean creditAutorise(float montant) {
		return true;
	}
	
	@Override
	public boolean debitAutorise(float montant) {
		float debitMaxAutorise = this.getSolde() + this.getDecouvertAutorise();
		return (montant < debitMaxAutorise);
	}
	
	public String toString() {
		return super.toString() + " Découvert autorisé : " + this.getDecouvertAutorise();
	}
	
	/* Getters & Setters */
	public float getDecouvertAutorise() {
		return this.decouvertAutorise;
	}
	
	public void setDecouvertAutorise(float decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}
	
	public static int getNbComptesCourants() {
		return nbComptesCourants;
	}
}

