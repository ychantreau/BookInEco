/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.dawan.bookInEco.dao;

import fr.dawan.bookInEco.bean.Commande;
import fr.dawan.bookInEco.bean.FicheLivre;
import fr.dawan.bookInEco.bean.Panier;
import fr.dawan.bookInEco.bean.Preference;
import fr.dawan.bookInEco.bean.Utilisateur;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin stagiaire
 */
public class FicheLivreDao {
    private static Utilisateur util;
    
    public static void initialise(){
        	ArrayList<Preference> listPref = new ArrayList<>();
		ArrayList<FicheLivre> listeFicheLivre = new ArrayList<>();
		ArrayList<Commande> listeCommande = new ArrayList<>();
		Panier panier = new Panier(1, 1, listeCommande, 0);
		util = new Utilisateur(1, "yohan", "chantreau", "yopsuedo", "yomdp", "une adresse", 30, listPref,
				"le chemin de la photo", listeFicheLivre, panier,1);

		FicheLivre livre1 = new FicheLivre(1, "auteur1", "titre1", "bon", "c'est un livre classique",
				"le chemin de la photo", "le format", LocalDateTime.now(), LocalDateTime.now(), 1, 5,10);
		FicheLivre livre2 = new FicheLivre(2, "auteur2", "titre2", "ok", "c'est encore un livre classique",
				"le chemin de la photo2", "le format", LocalDateTime.now(), LocalDateTime.now(), 1, 10,10);


		util.ajouterFicheLivre(livre1);
		util.ajouterFicheLivre(livre2);
    }
    
    public static List<FicheLivre> getAllLivre(){
        return util.getListeFicheLivre();
    }
}
