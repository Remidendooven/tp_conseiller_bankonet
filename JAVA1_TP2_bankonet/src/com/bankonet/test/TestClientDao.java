package com.bankonet.test;

import java.util.ArrayList;
import java.util.List;

import com.bankonet.jdbc.ClientDao;
import com.bankonet.jdbc.CreationConnexionException;
import com.bankonet.model.Client;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public class TestClientDao {
	public static void main(String args[]) {
		List<Client> clients = new ArrayList<Client>();
		Client client1 = new Client();
		Client nouveauClient = new Client(4, "Offredo", "Simon");
		try {
			ClientDao clientDao = new ClientDao();
			clients = clientDao.lireClients();
			client1 = clientDao.lireClient(1);
			
			clientDao.ecrireClient(nouveauClient);
			clientDao.supprimerClientEtComptes(clients.get(1));
			
			for (Client client : clients) {
				List<CompteCourant> comptesCourant = new ArrayList<CompteCourant>();
				List<CompteEpargne> comptesEpargne = new ArrayList<CompteEpargne>();
				comptesCourant = clientDao.lireComptesCourant(client);
				comptesEpargne = clientDao.lireComptesEpargne(client);
				System.out.println(client.toString());
				
				for (CompteCourant compteCourant : comptesCourant) {
					System.out.println(compteCourant.toString());
				}
				
				for (CompteEpargne compteEpargne : comptesEpargne) {
					System.out.println(compteEpargne.toString());
				}
			}
			
			System.out.println("======== Client récupéré grâce à lireClient(1) : ========\n" + client1.toString());
			clientDao.closeConnection();
		} catch (CreationConnexionException e) {
			e.printStackTrace();
		}
	}
}
