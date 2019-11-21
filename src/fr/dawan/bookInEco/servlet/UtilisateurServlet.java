package fr.dawan.bookInEco.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dawan.bookInEco.bean.Utilisateur;
import fr.dawan.bookInEco.dao.ConnectionBDD;
import fr.dawan.bookInEco.dao.UtilisateurDao;

@WebServlet("/user")
public class UtilisateurServlet extends HttpServlet {




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<Integer, String> roles = new HashMap<>();
        for(RoleEnum role : RoleEnum.values()){

            roles.put(role.ordinal() + 1, role.name());
        }
        request.setAttribute("roles", roles);
        Connection connection = null;
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String role = request.getParameter("role");
        String password1 = request.getParameter("password1");
        switch( request.getParameter("action") ){
            case "addUser":
            	
                Utilisateur user = new Utilisateur();
                user.setPrenom(prenom);
                user.setEmail(email);
                user.setMotDePasse(motDePasse);
                user.setRole(Integer.parseInt(request.getParameter("role")));


                String userValidator = UserValidator.userValidator(user, password1);

                if(!userValidator.equals("")){
                    request.setAttribute("prenom", prenom);
                    request.setAttribute("email", email);
                    request.setAttribute("motDePasse", motDePasse);
                    request.setAttribute("requestRole", role);
                    if(userValidator.contains("prenom"))
                        request.setAttribute("prenomMessage", "Le champ pr�nom est obligatoire");
                    if(userValidator.contains("emailNotNull"))
                        request.setAttribute("messageEmail", "Le champ ne peut pas �tre vide");
                    if(userValidator.contains("invalidEmail"))
                        request.setAttribute("messageEmail", "Format incorrect de l'adresse e-mail");
                    if(userValidator.contains("motDePasse"))
                        request.setAttribute("messageMotDePasse", "Le mot de passe est obligatoire");
                    if(userValidator.contains("errorPasswordConfirmation"))
                        request.setAttribute("messagePassword1", "Les deux mots de passe ne correspondent pas");
                    if(userValidator.contains("alreadyExistMail"))
                        request.setAttribute("messageEmail" , "Cet e-mail est d�j� enregistr� dans la base de donn�es !");
                    request.setAttribute("action", "signUp");
                    //request.setAttribute("title", UtilisateurDao.attributeTitle("Sign Up"));
                    doGet(request, response);
                    return;
                } else {
                    try {
                        connection = ConnectionBDD.getConnection();
                        String rand = UtilisateurDao.hash(user.getPrenom() + "_" +  user.getEmail());
                        UtilisateurDao.sendEmail("DAWAN IT CONSULTING <dawan-test@gmail.com>" , user.getEmail(), "Votre compte sur Dawan", "<h1>Bienvenu sur DAWAN</h1><p><br /><br />"+ user.getForeName() + ", <br /></p><br />Bienvenu sur le site de Dawan, veuillez cliquer <a href=http://localhost:8080/07-Panier?action=activateUser&rand="+rand + ">ici</a> pour activer votre compte.<p><p>Cordialement,</p><p>L'<b>&eacute;quipe.</b></p>", null, null, null);
                        UtilisateurDao.insert(user, connection, false);
                        request.setAttribute("userMessage", "Votre inscription n'est pas termin�e. Ouvrez votre bo�te " + user.getEmail() + " et cliquez sur le lien pour finaliser votre inscription.");
                        request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
                        return;
                    } catch(Exception e){

                    }
                }
                break;
            case "login":
            	Utilisateur u = null;
            	try {
            	u = UtilisateurDao.findByEmail(email, ConnectionBDD.getConnection(), false);
                userValidator = UserValidator.userValidator(email, motDePasse, u.getValidation());
                if(!userValidator.equals("")){
                    request.setAttribute("motDePasse", motDePasse);
                    request.setAttribute("email", email);
                    if(userValidator.contains("emailNotNull"))
                        request.setAttribute("messagePassword", "Le mot de passe est obligatoire");
                    if(userValidator.contains("invalidEmail"))
                        request.setAttribute("messageEmail", "Format incorrect de l'adresse e-mail");
                    if(userValidator.contains("password"))
                        request.setAttribute("messageMotDePasse", "Le mot de passe est obligatoire");
                    if(userValidator.contains("EmailAndPasswordNotCorrespondant")) {
                        request.setAttribute("messagePassword", "Mot de passe incorrect");
                        request.setAttribute("password", "");
                    }
                    
                    
                    if(userValidator.contains("noValidationForThisUser"))
                        request.setAttribute("messageEmail" , "Pour valider votre compte, ouvrez votre email et cliquez sur le lien 'ici'<br />");

                    
                    doGet(request, response);
                    return;
                }
            	} catch(Exception e) {
            		e.getMessage();
            	}

                try {
                	Utilisateur client = UtilisateurDao.findByEmail(request.getParameter("email"), ConnectionBDD.getConnection(), false);
                    if(client.getPrenom() != null) {
                        
                    	
                    	HttpSession session = request.getSession();
                    	
                    	session.setAttribute("id", client.getId());
                        session.setAttribute("prenom", client.getPrenom());

                        session.setAttribute("motDePasse", client.getMotDePasse());
                        session.setAttribute("date", client.getDate());
                        session.setAttribute("role", client.getRole());
                        session.setAttribute("validation", client.getValidation());
                        session.setAttribute("email", client.getEmail());
                        session.setAttribute("user", client);
                        request.setAttribute("connecte", "oui");
                        
                        System.out.println("Redirection");
                        request.setAttribute("action", "users");
                        response.sendRedirect( request.getContextPath() + "/" ); //request.getContextPath()
                        return;
                        
                        
                    } else {
                        request.setAttribute("messagePassword", "Cet e-mail ne figure pas dans la base de donn�es");
                        doGet(request, response);
                        return;
                    }
                } catch (Exception e){
                    System.out.println("Email non trouv�");
                    e.printStackTrace();
                }

                //request.setAttribute("title", UtilisateurDao.attributeTitle(""));

                String idProduct = (String) request.getParameter("idProduct");
                
                
                

                
                if(!"".equals(idProduct)) {
                    request.setAttribute("idProduct", idProduct);
                } else {
                }
                response.sendRedirect( request.getContextPath() + "/" ); //request.getContextPath()
                break;
            default:

                break;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * First Attribute for SignUp Form
         */
    	
        switch( request.getParameter("action") ) {
        
        
        	case "addBook":
        		request.setAttribute("action", "addBook");
        		break;
        
            case "signUp":
                request.setAttribute("action", "signUp");
                request.setAttribute("title", UtilisateurDao.attributeTitle("Sign Up"));
                HashMap<Integer, String> roles = new HashMap<>();
                for(RoleEnum role : RoleEnum.values()){

                    roles.put(role.ordinal() + 1, role.name());
                }
                request.setAttribute("roles", roles);
                break;
            case "login":
                String parameter = request.getParameter("product_id");
                request.setAttribute("idProduct", parameter);
                request.setAttribute("action", "login");
                //request.setAttribute("title", UtilisateurDao.attributeTitle("Log In"));
                
                
                
                break;


            case "deconnect":
                request.setAttribute("title", UtilisateurDao.attributeTitle(""));
                Cart cart = new Cart();
                cart.empty();
                
                
                request.getSession().invalidate();
                System.out.println("session d�truite");
                
                
                //response.sendRedirect( request.getContextPath() );
                request.getRequestDispatcher( "WEB-INF/views/" ).forward(request, response);
                return;
                
                
            default:
                break;
        }

        request.getRequestDispatcher("WEB-INF/views/").forward(request, response);
    }
}


