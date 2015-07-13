package com.bankonet;

import java.util.Comparator;

import com.bankonet.model.Client;

public class ClientComparator implements Comparator<Client> {

	@Override
	public int compare(Client client0, Client client1) {
		if (!client0.getPrenom().equals(client1.getPrenom())) {
			return client0.getPrenom().compareTo(client1.getPrenom());
		}
		else{
			return client0.getNom().compareTo(client1.getNom());
		}
	}

}
