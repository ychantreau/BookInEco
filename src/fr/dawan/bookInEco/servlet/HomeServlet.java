package src.fr.dawan.bookInEco.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.fr.dawan.bookInEco.bean.FicheLivre;
import src.fr.dawan.bookInEco.dao.FicheLivreDao;

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
