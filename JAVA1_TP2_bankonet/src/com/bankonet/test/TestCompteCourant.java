package com.bankonet.test;

import com.bankonet.model.CompteCourant;

public class TestCompteCourant {
	public static void main(String args[]) {
		CompteCourant compteCourant1 = new CompteCourant(1, "Compte de Rémi", 150000, 3000);
		CompteCourant compteCourant2 = new CompteCourant(2, "Compte de Thomas", 175000, 2500);
		CompteCourant compteCourant3 = new CompteCourant(3, "Compte de Simon", -100, 0);
		
		CompteCourant[] compteCourantTab = new CompteCourant[3];
		compteCourantTab[0] = compteCourant1;
		compteCourantTab[1] = compteCourant2;
		compteCourantTab[2] = compteCourant3;
		
//		for (int i = 0; i < compteCourantTab.length; i++) {
//			System.out.println(compteCourantTab[i].toString());
//		}
		
//		System.out.println("Nombre de comptes courants : " + CompteCourant.getNbComptesCourants());
//		
//		System.out.println(compteCourant1.creditAutorise(500));
//		System.out.println(compteCourant3.creditAutorise(500));
//		System.out.println(compteCourant1.debitAutorise(500));
//		System.out.println(compteCourant3.debitAutorise(500));
		
		switch(compteCourant1.typeDuCompte()) {
			case COURANT:
				System.out.println("On traite le compte courant");
				break;
			case EPARGNE:
				System.out.println("On traite le compte epargne");
				break;
			default:
				System.out.println("Ce n'est ni un compte epargne ni un compte courant");
		}
	}
}
