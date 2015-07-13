package com.bankonet.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bankonet.model.Client;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public class ClientDao {
	/* Attributs */
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql:///banque";
	private final String DB_LOGIN = "root";
	private final String DB_PASSWORD = "";
	private Connection connection;
	
	/* Constructeurs */
	public ClientDao() throws CreationConnexionException {
			try {
				Class.forName(DRIVER_NAME);
				connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new CreationConnexionException("Erreur lors de la création de la connexion");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new CreationConnexionException("Erreur lors de la création de la connexion");
			}
	}
	
	/* Méthodes */
	public void closeConnection() {
		try {
			if (connection != null) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Client> lireClients() {
		List<Client> clients = new ArrayList<Client>();
		String query = "SELECT ID, NOM, PRENOM FROM CLIENT";		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Client client = new Client();
				client.setIdentifiant(rs.getInt("ID"));
				client.setNom(rs.getString("NOM"));
				client.setPrenom(rs.getString("PRENOM"));
				clients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}
	
	public Client lireClient(int id) {
		Client client = null;
		String query = "SELECT ID, NOM, PRENOM FROM CLIENT WHERE ID = ?";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				client = new Client(rs.getInt("ID"), rs.getString("NOM"), rs.getString("PRENOM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public List<CompteCourant> lireComptesCourant(Client unClient) {
		List<CompteCourant> comptesCourant = new ArrayList<CompteCourant>();
		String query = "SELECT ID, ID_CLIENT, LIBELLE, SOLDE, DECOUVERTAUTORISE, DISCRIMINANT "
				+ "FROM COMPTE "
				+ "WHERE ID_CLIENT = ? AND DISCRIMINANT = 'CC'";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, unClient.getIdentifiant());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CompteCourant compteCourant = new CompteCourant(rs.getInt("ID"), rs.getString("LIBELLE"), rs.getFloat("SOLDE"), rs.getFloat("DECOUVERTAUTORISE"));
				comptesCourant.add(compteCourant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comptesCourant;
	}
	
	public List<CompteEpargne> lireComptesEpargne(Client unClient) {
		List<CompteEpargne> comptesEpargne = new ArrayList<CompteEpargne>();
		String query = "SELECT ID, ID_CLIENT, LIBELLE, SOLDE, TAUX, DISCRIMINANT "
				+ "FROM COMPTE "
				+ "WHERE ID_CLIENT = ? AND DISCRIMINANT = 'CE'";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, unClient.getIdentifiant());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CompteEpargne compteEpargne = new CompteEpargne(rs.getInt("ID"), rs.getString("LIBELLE"), rs.getFloat("SOLDE"), rs.getDouble("TAUX"));
				comptesEpargne.add(compteEpargne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comptesEpargne;
	}
	
	public void ecrireClient(Client client) {
		String query = "INSERT INTO CLIENT (ID, NOM, PRENOM) VALUES (?, ?, ?)";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setInt(1, client.getIdentifiant());
			st.setString(2, client.getNom());
			st.setString(3, client.getPrenom());
			int nb = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ecrireComptecourant(CompteCourant unCompte, Client unClient) throws SQLException {
		String query = "INSERT INTO COMPTE (DECOUVERTAUTORISE, SOLDE, LIBELLE, DISCRIMINANT, ID_CLIENT) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setFloat(1, unCompte.getDecouvertAutorise());
			st.setFloat(2, unCompte.getSolde());
			st.setString(3, unCompte.getLibelle());
			st.setInt(4, unClient.getIdentifiant());
			int nb = st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ecrireCompteepargne(CompteEpargne unCompte, Client unClient) throws SQLException {
		String query = "INSERT INTO COMPTE (TAUX, SOLDE, LIBELLE, DISCRIMINANT, ID_CLIENT) VALUES (?, ?, ?, ?)" 
	+ unCompte.getTauxInteret() + ", " + unCompte.getSolde() + 
				", " + unCompte.getLibelle() + ", CC, " + unClient.getIdentifiant() + ")";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setDouble(1, unCompte.getTauxInteret());
			st.setFloat(2, unCompte.getSolde());
			st.setString(3, unCompte.getLibelle());
			st.setInt(4, unClient.getIdentifiant());
			int nb = st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimerClientEtComptes(Client client) {
		String queryCompte = "DELETE FROM COMPTE WHERE ID_CLIENT = ?";
		String queryClient = "DELETE FROM CLIENT WHERE ID = ?";
		try {
			PreparedStatement st1 = connection.prepareStatement(queryCompte);
			PreparedStatement st2 = connection.prepareStatement(queryClient);
			st1.setInt(1, client.getIdentifiant());
			st2.setInt(1, client.getIdentifiant());
			int nb1 = st1.executeUpdate();
			int nb2 = st2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
