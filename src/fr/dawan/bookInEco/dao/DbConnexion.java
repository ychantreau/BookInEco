package fr.dawan.bookInEco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnexion {

	public static Connection getConnexion() throws Exception {
		//création d'un objet Properties pour lire le fichier
		Properties p = new Properties();
		//permet de rechercher le fichier qui s'appelle conf.properties dans le dossier src
		p.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("conf.properties"));
		//on charge le driver mysql
		Class.forName(p.getProperty("driver"));
		//on créer la connexion avec l'url le user et le pwd du fichier properties
		Connection cnx = DriverManager.getConnection(p.getProperty("url"),
				p.getProperty("user"),p.getProperty("pwd"));
		return cnx;
	}
}
