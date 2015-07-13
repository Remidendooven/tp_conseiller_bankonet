package com.bankonet.test;

import com.bankonet.CompteStat;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public class DonneesTest {

	public static CompteStat[] construitEchantillonComptes() {
		Compte[] tabComptes = new Compte[5];
		tabComptes[0] = new CompteCourant(1, "compte courant 1", 0, 1000);
		tabComptes[1] = new CompteCourant(2, "compte courant 2", 500, 200);
		tabComptes[2] = new CompteCourant(3, "compte courant 3", 20, 0);
		tabComptes[3] = new CompteEpargne(4, "compte épargne 4", 1, 2.54F);
		tabComptes[4] = new CompteEpargne(5, "compte épargne 5", 1000000,13.42F);

		return tabComptes;

	}

}
