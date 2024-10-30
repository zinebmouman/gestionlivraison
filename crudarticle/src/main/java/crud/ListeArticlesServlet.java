package crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listeArticles")
public class ListeArticlesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        articleDAO articleDAO = new articleDAO();
        try {
            // Récupérer la liste des articles
            List<article> articles = articleDAO.getArticles();

            // Ajouter la liste des articles à la requête
            request.setAttribute("articles", articles);

            // Rediriger vers la page JSP pour afficher les articles
            request.getRequestDispatcher("articleAdded.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirection en cas d'erreur
        }
    }
}
