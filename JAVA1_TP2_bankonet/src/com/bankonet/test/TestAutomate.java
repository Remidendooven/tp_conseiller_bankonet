package com.bankonet.test;

import com.bankonet.CompteStat;

public class TestAutomate {
	public static void main (String args[]) {
		CompteStat[] tabCompteStat = DonneesTest.construitEchantillonComptes();
		float sum = 0;
		for (int i=0; i<tabCompteStat.length;i++) {
			sum += tabCompteStat[i].getSolde();
		}
		float moy = sum / tabCompteStat.length;
		System.out.println("Moyenne des soldes : " + moy + "€");
	}	
}
