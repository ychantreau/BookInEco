package fr.dawan.bookInEco.bean;

import java.util.List;

public class Panier {
	private int id;
	private int idUtilisateur;
	private List<Commande> listeCommande;
	private int prixTotal;
	
	public void ajouterCommande(Commande commande) {
		listeCommande.add(commande);
		prixTotal += commande.getPrixTotalCommande();
	}
	
	
	public Panier() {
		super();
	}


	public Panier(int id, int idUtilisateur, List<Commande> listeCommande, int prixTotal) {
		this.id = id;
		this.idUtilisateur = idUtilisateur;
		this.listeCommande = listeCommande;
		this.prixTotal = prixTotal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public List<Commande> getListeCommande() {
		return listeCommande;
	}
	public void setListeCommande(List<Commande> listeCommande) {
		this.listeCommande = listeCommande;
	}
	public int getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(int prixTotal) {
		this.prixTotal = prixTotal;
	}
	
	
}
