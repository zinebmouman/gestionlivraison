package crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/supprimerArticle")
public class SupprimerArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer l'ID de l'article à supprimer
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);

        // Utiliser le DAO pour supprimer l'article
        articleDAO articleDAO = new articleDAO();
        try {
            articleDAO.supprimerArticle(id);
            // Redirection après suppression
            response.sendRedirect("listeArticles.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Redirection en cas d'erreur
            response.sendRedirect("error.jsp");
        }
    }
}
