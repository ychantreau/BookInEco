package fr.dawan.bookInEco.main;

import fr.dawan.bookInEco.bean.Commande;
import fr.dawan.bookInEco.bean.FicheLivre;
import fr.dawan.bookInEco.bean.PanierCommande;
import fr.dawan.bookInEco.bean.Preference;
import fr.dawan.bookInEco.bean.Utilisateur;
import fr.dawan.bookInEco.dao.Jdbc;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		ArrayList<Preference> listPref = new ArrayList<>();
		ArrayList<FicheLivre> listeFicheLivre = new ArrayList<>();
		ArrayList<Commande> listeCommande = new ArrayList<>();
		PanierCommande panier = new PanierCommande(1, 1, listeCommande, 0);
		Utilisateur util = new Utilisateur(1, "yohan", "chantreau", "yopsuedo", "yomdp", "une adresse", 30, listPref,
				"le chemin de la photo", listeFicheLivre, panier,1);

		FicheLivre livre1 = new FicheLivre(1, "auteur1", "titre1", "bon", "c'est un livre classique",
				"le chemin de la photo", "le format", LocalDateTime.now(), LocalDateTime.now(), 1, 5,10);
		FicheLivre livre2 = new FicheLivre(2, "auteur2", "titre2", "ok", "c'est encore un livre classique",
				"le chemin de la photo2", "le format", LocalDateTime.now(), LocalDateTime.now(), 1, 10,10);


		util.ajouterFicheLivre(livre1);
		util.ajouterFicheLivre(livre2);
		System.out.println(util.getListeFicheLivre().get(0));
		System.out.println(util.getListeFicheLivre().get(1));
		
		Utilisateur util2 =new Utilisateur();
		util2.setNom("Dupont");
		util2.setPrenom("Michel");
		
		Jdbc jdbc = Jdbc.creer("test1","test2","test3","test4","test5","test6");
		

		
		

	}

}