package fr.dawan.bookInEco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import fr.dawan.bookInEco.bean.FicheLivre;

public class FicheLivreDao {

	public static void insert(Connection cnx,FicheLivre livre,boolean closeCnx) throws Exception {
		LocalDateTime date = LocalDateTime.now();
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO livres(titre, description, auteur, format,id_utilisateur) VALUES (?,?,?,?,?)");
		ps.setString(1, livre.getTitre());
		ps.setString(2, livre.getDescription());
		ps.setString(3, livre.getAuteur());
		ps.setString(4, livre.getFormat());
		ps.setInt(4, livre.getIdUtilisateur());
	}
}
