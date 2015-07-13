package com.bankonet.test;

//import com.bankonet.model.Compte;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bankonet.ClientComparator;
import com.bankonet.CompteNonTrouveException;
import com.bankonet.DebitException;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;
import com.bankonet.model.Client;

public class TestClient {
	public static void main(String args[]) throws DebitException, CompteNonTrouveException {
		Client simon = new Client(1, "Dendooven", "Rémi");
		CompteCourant compteCourantRemi = new CompteCourant(1, "Compte courant de Simon", 30, 0);
		CompteEpargne compteEpargneRemi = new CompteEpargne(1, "Compte épargne de Simon", 500, 2.5);
		simon.creerCompte(compteCourantRemi);
		simon.creerCompte(compteEpargneRemi);
		
		Client remi = new Client(2, "Fajoux", "Thomas");
		CompteCourant compteCourantThomas = new CompteCourant(1, "Compte courant de Remi", 50000, 3000);
		CompteEpargne compteEpargneThomas = new CompteEpargne(1, "Compte épargne de Remi", 16000, 2.5);
		remi.creerCompte(compteCourantThomas);
		remi.creerCompte(compteEpargneThomas);
		
		Client thomas = new Client(3, "Offredo", "Simon");
		CompteCourant compteCourantSimon = new CompteCourant(1, "Compte courant de Thomas", 58000, 3000);
		thomas.creerCompte(compteCourantSimon);
		
		List<Client> clientList = new ArrayList();
		clientList.add(simon);
		clientList.add(remi);
		clientList.add(thomas);		
		
		for (Client client : clientList) {
			System.out.println(client.toString() + "\n===================================================\n");
		}
		
		Collections.sort(clientList, new ClientComparator());
		
		System.out.println(clientList);
	}
	
}
