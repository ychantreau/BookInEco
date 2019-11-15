package src.fr.dawan.bookInEco.bean;

import java.time.LocalDateTime;

public class FicheLivre {
	private int id;
	private String auteur;
	private String titre;
	private String etat;
	private String description;
	private String photo;
	private String format;
	private LocalDateTime dateCreation;
	private LocalDateTime dateDeCession;
	private int idUtilisateur;
	private int prix;
	private int poids;

	
	


	public FicheLivre() {
		super();
	}

	public FicheLivre(int id, String auteur, String titre, String etat, String description, String photo, String format,
			LocalDateTime dateCreation, LocalDateTime dateDeCession, int idUtilisateur, int prix, int poids) {
		super();
		this.id = id;
		this.auteur = auteur;
		this.titre = titre;
		this.etat = etat;
		this.description = description;
		this.photo = photo;
		this.format = format;
		this.dateCreation = dateCreation;
		this.dateDeCession = dateDeCession;
		this.idUtilisateur = idUtilisateur;
		this.prix = prix;
		this.poids = poids;
	}

	@Override
	public String toString() {
		return "FicheLivre [id=" + id + ", auteur=" + auteur + ", titre=" + titre + ", etat=" + etat + ", description="
				+ description + ", photo=" + photo + ", format=" + format + ", dateCreation=" + dateCreation
				+ ", dateDeCession=" + dateDeCession + ", idUtilisateur=" + idUtilisateur + ", prix=" + prix
				+ ", poids=" + poids + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public LocalDateTime getDateDeCession() {
		return dateDeCession;
	}

	public void setDateDeCession(LocalDateTime dateDeCession) {
		this.dateDeCession = dateDeCession;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

}
