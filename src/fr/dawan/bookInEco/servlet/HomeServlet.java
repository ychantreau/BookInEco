package fr.dawan.bookInEco.servlet;

import fr.dawan.bookInEco.bean.Commande;
import fr.dawan.bookInEco.bean.FicheLivre;
import fr.dawan.bookInEco.bean.Panier;
import fr.dawan.bookInEco.bean.Preference;
import fr.dawan.bookInEco.bean.Utilisateur;
import fr.dawan.bookInEco.dao.FicheLivreDao;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 *
 */
@WebServlet("/")
public class HomeServlet extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        FicheLivreDao.initialise();
        List<FicheLivre> liste = FicheLivreDao.getAllLivre();
        request.setAttribute("livres",liste);
        request.setAttribute("test","c'est un test");
        request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
