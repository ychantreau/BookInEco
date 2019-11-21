package fr.dawan.bookInEco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionBDD {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            Properties p = new Properties();
            p.load( Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/application.properties") );
        	Class.forName( p.getProperty("driver") );
            connection = DriverManager.getConnection(
            		p.getProperty( "url" ) , 
            		p.getProperty("user") , 
            		p.getProperty("psw")
            );
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JBDC non trouv�");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Probl�me de connexion � la base de donn�es");
            e.printStackTrace();
            return null;
        }
  }
}
