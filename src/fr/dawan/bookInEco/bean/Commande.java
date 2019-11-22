package fr.dawan.bookInEco.bean;

import java.util.List;

public class Commande {
	private int id;
	private int idUtilisateur;
	private List<FicheLivre> listeFicheLivre;
	private int prixTotalCommande;
	
	public void ajouterLivreCommande(FicheLivre livre) {
		listeFicheLivre.add(livre);
		prixTotalCommande += livre.getPrix();
	}
	
	
	public Commande() {
	}


	public Commande(int id, int idUtilisateur, List<FicheLivre> listeFicheLivre, int prixTotalCommande) {
		this.id = id;
		this.idUtilisateur = idUtilisateur;
		this.listeFicheLivre = listeFicheLivre;
		this.prixTotalCommande = prixTotalCommande;
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
	public List<FicheLivre> getListeFicheLivre() {
		return listeFicheLivre;
	}
	public void setListeFicheLivre(List<FicheLivre> listeFicheLivre) {
		this.listeFicheLivre = listeFicheLivre;
	}
	public int getPrixTotalCommande() {
		return prixTotalCommande;
	}
	public void setPrixTotalCommande(int prixTotalCommande) {
		this.prixTotalCommande = prixTotalCommande;
	}
	
	
}
