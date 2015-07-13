

package com.bankonet.model;

public final class CompteEpargne extends Compte{
	/* Attributs */
	private double tauxInteret;
	
	/* Constructeurs */
	public CompteEpargne() {
	}
	
	public CompteEpargne(int identifiant, String libelle, float solde, double tauxInteret) {
		super(identifiant, libelle, solde);
		this.tauxInteret = tauxInteret;
	}

	/* Méthodes */
	@Override
	public TypeCompte typeDuCompte() {
		return TypeCompte.EPARGNE;
	}
	
	@Override
	public boolean creditAutorise(float montant) {
		return true;
	}
	
	@Override
	public boolean debitAutorise(float montant) {
		return (montant < this.getSolde());
	}
	
	@Override
	public String toString() {
		return super.toString() + " Taux d'intérêt : " + this.getTauxInteret();
	}
	
	/* Getters & Setters */
	public double getTauxInteret() {
		return tauxInteret;
	}
	public void setTauxInteret(float tauxInteret) {
		this.tauxInteret = tauxInteret;
	}
}
