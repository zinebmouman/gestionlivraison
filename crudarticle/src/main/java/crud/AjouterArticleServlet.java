package crud;

import crud.articleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ajouterArticle")
public class AjouterArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String date = request.getParameter("date");

        // Créer un objet article et définir ses valeurs
        article article = new article();
        article.setNom(nom);
        article.setDate(date);

        // Utiliser le DAO pour ajouter l'article
        articleDAO articleDAO = new articleDAO();
        try {
            articleDAO.ajouterArticle(article); // Utiliser un nom de méthode en minuscule
            response.sendRedirect("articleAdded.jsp"); // Redirection après ajout réussi
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirection en cas d'erreur
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirection si la classe n'est pas trouvée
        }
    }
}
