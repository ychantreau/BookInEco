package fr.dawan.bookInEco.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dawan.bookInEco.bean.FicheLivre;
import fr.dawan.bookInEco.dao.DbConnexion;

/**
 * Servlet implementation class AjouterLivreServlet
 */
@WebServlet("/ajouterLivre")
public class AjouterLivreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterLivreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/ajouterLivre.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FicheLivre livre = new FicheLivre();
		livre.setAuteur(request.getParameter("auteur"));
		livre.setDescription(request.getParameter("description"));
		livre.setTitre(request.getParameter("titre"));
		livre.setFormat(request.getParameter("format"));
		
		try {
			Connection cnx = DbConnexion.getConnexion();
			
			request.getRequestDispatcher("WEB-INF/views/ajouterLivre.jsp").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("WEB-INF/views/ajouterLivre.jsp").forward(request, response);
		}

	}

}
