package fr.dawan.bookInEco.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import fr.dawan.bookInEco.bean.Utilisateur;
import fr.dawan.bookInEco.tools.StringFunctions;

public class UtilisateurDao {

		/**
		 * Teste la validit� du format de l'adresse email envoy�e par l'utilisateur
		 * @param email
		 * Une adresse email, de la forme utilisateur@domain.ext
		 * @return
		 * true si l'adresse email fournie respecte le format d�fini par l'expression r�guli�re
		 */
		public static boolean isEmailAddress(String email){
			Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
			Matcher m = p.matcher(email.toUpperCase());
			return m.matches();
		}
		
		
		/**
		 * Configure un serveur d'envoi de courriel avec pi�ce jointe)
		 * @param email
		 * Objet Java MultiPartEmail qui permet d'envoyer des courriels avec pi�ce jointe
		 * @param subject
		 * Sujet de l'email
		 * @param htmlContent
		 * Corps de l'email
		 * @param to
		 * Adresse email du destinataire de l'email
		 * @return
		 * Un objet MultiPartEmail
		 * @throws Exception
		 */
		private static MultiPartEmail configureIMAP( MultiPartEmail email, Object subject, Object htmlContent, String to ) throws Exception {
			email.setCharset( "utf-8" );
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort( 465 );
			email.setAuthenticator(new DefaultAuthenticator("samson.charlonnai@gmail.com", "Webforce3"));
			email.setSSLOnConnect(true);
			email.setFrom( "DAWAN <samson.charlonnai@gmail.com>" );
			email.setSubject( subject.toString() );
			email.setMsg( htmlContent.toString() );
			email.addTo( to );
			return email;
		}
		
		/**
		 * Configure un serveur d'envoi de courriel sans pi�ce jointe)
		 * @param email
		 * Objet Java MultiPartEmail qui permet d'envoyer des courriels avec pi�ce jointe
		 * @param subject
		 * Sujet de l'email
		 * @param htmlContent
		 * Corps de l'email
		 * @param to
		 * Adresse email du destinataire de l'email
		 * @return
		 * Un objet MultiPartEmail
		 * @throws Exception
		 */
		private static Email configureIMAP( Email email, Object subject, Object htmlContent, String to ) throws Exception {
			email.setCharset( "utf-8" );
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort( 465 );
			email.setAuthenticator(new DefaultAuthenticator("samson.charlonnai@gmail.com", "Webforce3"));
			email.setSSLOnConnect(true);
			email.setFrom( "DAWAN <samson.charlonnai@gmail.com>" );
			email.setContent("Content-Type", "text/html; charset=UTF-8");
			email.setSubject( subject.toString() );
			email.setMsg( htmlContent.toString() );
			email.addTo( to );
			return email;
		}
		
		
		private static String bytesToHex(byte[] hash) {
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}

		private static String hashPassword(String password) {
			String hexHashedPassword = "";
			try {
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				byte[] psw = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
				hexHashedPassword = bytesToHex(psw);
			} catch (Exception e) {

			}
			return hexHashedPassword;
		}

		/*public static String attributeTitle(String pageTitle) {
			String result = pageTitle;
			Properties p = new Properties();
			try {
				p.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("resources/application.properties"));
				String dawanTitle = p.getProperty("title");
				if (!"".equals(pageTitle))
					result = LibraryDao.reduce(pageTitle, 20) + " - " + dawanTitle;
				else
					result = dawanTitle;
			} catch (Exception e) {

			}
			return result;
		}*/

		public static Boolean pswAndLoginMatche(String email, String typedPassword, Connection cnx,
				boolean willConnectionClosed) throws Exception {
			Boolean result = true;
			PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				String oldPassword = resultSet.getString("motDePasse");
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				byte[] psw1 = messageDigest.digest(typedPassword.getBytes(StandardCharsets.UTF_8));
				String oldHexHashedPassword1 = bytesToHex(psw1);
				if (!oldPassword.equals(oldHexHashedPassword1)) {
					result = false;
				}
			}
			return result;
		}

		public static List<Utilisateur> findAll(Connection connection, boolean willBeClosed) throws Exception {
			List<Utilisateur> list = new ArrayList<>();
			String mySql = "SELECT * FROM utilisateur";

			PreparedStatement stmt = connection.prepareStatement(mySql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Utilisateur u = new Utilisateur();
				u.setId(rs.getInt("id"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getInt("role"));
				u.setMotDePasse(rs.getString("motDePasse"));
				u.setValidation(rs.getBoolean("validation"));
				list.add(u);

			}
			rs.close();
			if (willBeClosed)
				connection.close();

			return list;
		}

		public static Utilisateur findByEmail(String email, Connection cnx, boolean closeCnx) throws Exception {
			Utilisateur u = new Utilisateur();
			PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				u.setId(rs.getInt("id"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
				u.setEmail(rs.getString("email"));
				u.setRole(rs.getInt("role"));
				u.setMotDePasse(rs.getString("motDePasse"));
				u.setValidation(rs.getBoolean("validation"));
			}
			rs.close();
			if (closeCnx)
				cnx.close();

			return u;
		}

		public static Boolean doesEmailExist(String email, Connection cnx, boolean willConnectionClosed) throws Exception {
			Boolean result = false;
			int nb = 0;
			PreparedStatement stmt = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				nb = rs.getInt(1);
			}
			if (nb > 0)
				result = true;
			if (willConnectionClosed)
				cnx.close();
			return result;
		}

		public static int insert(Utilisateur user, Connection cnx, boolean willConnectionClosed) throws Exception {
			System.setProperty("file.encoding", "UTF-8");
			String password = user.getMotDePasse();
			Date now = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(now);
			password = hashPassword(password);
			PreparedStatement stmt = cnx.prepareStatement(
					"INSERT INTO `utilisateur` (`nom``prenom`,`motDePasse`,`email`,`date`,`role`, `validation`) VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getNom().trim());
			stmt.setString(2, user.getPrenom().trim());
			stmt.setString(3, password);
			stmt.setString(4, user.getEmail().trim());
			stmt.setString(5, date);
			stmt.setInt(6, user.getRole());
			stmt.setBoolean(7, false);
			int resultSet = stmt.executeUpdate();
			if (willConnectionClosed)
				cnx.close();
			return resultSet;
		}

		public static int delete(Integer id, Connection connection, Boolean willBeClosed) throws Exception {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM utilisateur WHERE idUtilisateur=?");
			ps.setInt(1, id);
			int resultSet = ps.executeUpdate();
			if (willBeClosed)
				connection.close();
			return resultSet;
		}

		public static int update(Integer id, Integer role, Connection connection, Boolean willBeClosed) throws Exception {
			PreparedStatement ps = connection.prepareStatement("UPDATE utilisateur SET role=? WHERE idUtilisateur=?");
			ps.setInt(1, role);
			ps.setInt(2, id);
			int resultSet = ps.executeUpdate();

			if (willBeClosed)
				connection.close();
			return resultSet;
		}

		public static int update(Utilisateur user, Connection connection, Boolean willBeClosed) throws Exception {
			PreparedStatement ps = connection.prepareStatement("UPDATE utilisateur SET validation=? WHERE idUtilisateur=?");
			ps.setBoolean(1, true);
			ps.setInt(2, user.getId());
			int resultSet = ps.executeUpdate();

			if (willBeClosed)
				connection.close();
			return resultSet;
		}

		public static String hash(String word) {

			// Cryptage du mot de passe
			String hash = "";
			for (int i = word.length() - 1; i >= 0; i--) {
				hash += Integer.toString((byte) ((int) word.charAt(i) / 16 * 10 + (int) word.charAt(i) % 16));
			}
			return hash;
		}

		public static void sendEmail(String from, String to, String subject, String htmlContent, String attachmentUrl,
				String name, String description) throws Exception {

			attachmentUrl = "";
			
			// pi�ce jointe : attachmentUrl
			try {

				if (!attachmentUrl.equals("")) {
					// Avec pi�ce jointe
					MultiPartEmail email = new MultiPartEmail();
					configureIMAP(email, StringFunctions.utf8Encode(subject), StringFunctions.utf8Encode(htmlContent), to);
					EmailAttachment attachement = new EmailAttachment(); // Objet Java pi�ce jointe
					attachement.setPath(attachmentUrl); // Chemin et nom du fichier
					attachement.setDisposition(EmailAttachment.ATTACHMENT);
					attachement.setDescription(description); // description de l'image
					email.addHeader("Content-Type", "text/html; charset=UTF-8"); // Pour que les balises HTML soient ex�cut�es mais pas affich�es
					attachement.setName(name); // le nom du fichier quand le destinataire enregistre la pi�ce
					email.attach(attachement); // attacher la pi�ce � l'email
					email.send();
				} else {
					// Sans pi�ce jointe
					Email email = new SimpleEmail();
					configureIMAP(email, StringFunctions.utf8Encode(subject.toString()),
							StringFunctions.utf8Encode(htmlContent.toString()), to);
					email.addHeader("Content-Type", "text/html; charset=UTF-8"); // Pour que les balises soient ex�cut�es mais pas affich�es
					email.setFrom("DAWAN <samson.charlonnai@gmail.com>");
					email.send();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

