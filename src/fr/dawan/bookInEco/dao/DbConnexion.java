package fr.dawan.bookInEco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnexion {

	public static Connection getConnexion() throws Exception {
		//création d'un objet Properties pour lire le fichier
		Properties p = new Properties();
		p.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("conf.properties"));
		
		Class.forName(p.getProperty("driver"));
		Connection cnx = DriverManager.getConnection(p.getProperty("url"),
				p.getProperty("user"),p.getProperty("pwd"));
		return cnx;
	}
}
