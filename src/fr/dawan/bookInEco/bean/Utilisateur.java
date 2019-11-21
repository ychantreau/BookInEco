package fr.dawan.bookInEco.bean;

import java.util.List;

public class Utilisateur {

	private int id;
	private String prenom;
	private String nom;
	private String pseudo;
	private int nbrePoints;
	private List<Preference> preferenceLitteraire;
	private String email;
	private String motDePasse;
	private String avatar;
	private String adresse;
	private String pathPhoto;
	private List<FicheLivre> listeFicheLivre;
	private PanierCommande panier;
	//Entier qui correspond aux droits sur l'application ( admin, utilisateur ...)
	private int role;

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void obtenirLivre(FicheLivre fiche) {
		this.getPanier().getListeCommande();
	}
	
	public void ajouterFicheLivre(FicheLivre fiche) {
		getListeFicheLivre().add(fiche);
	}

	public String supprimerFicheLivre(FicheLivre fiche) {
		String message = "";
		if (getListeFicheLivre().contains(fiche)) {
			getListeFicheLivre().remove(fiche);
			message = "La fiche livre a �t� supprim�e";
		} else {
			message = "La fiche livre n'est pas dans la liste";
		}

		return message;
	}
	

	
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(int id, String prenom, String nom, String pseudo, String motDePasse, String adresse,
			int nbrePoints, List<Preference> preferenceLitteraire, String pathPhoto, List<FicheLivre> listeFicheLivre,
			PanierCommande panier, int role) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.adresse = adresse;
		this.nbrePoints = nbrePoints;
		this.preferenceLitteraire = preferenceLitteraire;
		this.pathPhoto = pathPhoto;
		this.listeFicheLivre = listeFicheLivre;
		this.panier = panier;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", pseudo=" + pseudo + ", motDePasse="
				+ motDePasse + ", adresse=" + adresse + ", nbrePoints=" + nbrePoints + ", preferenceLitteraire="
				+ preferenceLitteraire + ", pathPhoto=" + pathPhoto + ", listeFicheLivre=" + listeFicheLivre
				+ ", panier=" + panier + ", role=" + role + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getNbrePoints() {
		return nbrePoints;
	}

	public void setNbrePoints(int nbrePoints) {
		this.nbrePoints = nbrePoints;
	}

	public List<Preference> getPreferenceLitteraire() {
		return preferenceLitteraire;
	}

	public void setPreferenceLitteraire(List<Preference> preferenceLitteraire) {
		this.preferenceLitteraire = preferenceLitteraire;
	}

	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	public List<FicheLivre> getListeFicheLivre() {
		return listeFicheLivre;
	}

	public void setListeFicheLivre(List<FicheLivre> listeFicheLivre) {
		this.listeFicheLivre = listeFicheLivre;
	}

	public PanierCommande getPanier() {
		return panier;
	}

	public void setPanier(PanierCommande panier) {
		this.panier = panier;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	public void modififierProfil() {
		// Je récupère l'utilisateur connecté 
		
		// J
	}

}
