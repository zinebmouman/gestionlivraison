package Articles;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/artservlet")
public class artservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer la liste des articles depuis la base de données
        List<Article> articles = Article.getArticles(request);
        request.setAttribute("articles", articles);

        // Rediriger vers la page JSP
        request.getRequestDispatcher("/articleBD.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("ajouter".equals(action)) {
            // Ajouter un article
            String nom = request.getParameter("nom");
            int prix = Integer.parseInt(request.getParameter("prix"));
            Article.ajouterArticle(nom, prix);

        } else if ("modifier".equals(action)) {
            String idParam = request.getParameter("id");
            String nom = request.getParameter("nom");
            String prixParam = request.getParameter("prix");

            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam.trim());

                if (nom != null && !nom.isEmpty() && prixParam != null && !prixParam.isEmpty()) {
                    try {
                        int prix = Integer.parseInt(prixParam.trim());
                        
                        // Modification de l'article
                        Article.modifierArticle(id, nom, prix);
                        request.setAttribute("message", "Article modifié avec succès.");
                        
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Le prix doit être un nombre.");
                        e.printStackTrace();
                    }
                } else {
                    // Récupérer l'article à modifier si le formulaire n'est pas soumis
                    Article article = Article.getArticleById(id);
                    if (article != null) {
                        request.setAttribute("article", article);
                    } else {
                        request.setAttribute("error", "L'article n'existe pas.");
                    }
                }
            } else {
                request.setAttribute("error", "ID de l'article manquant.");
            }

            // Rediriger vers la page de modification (modifierArticle.jsp)
            request.getRequestDispatcher("modifierArticle.jsp").forward(request, response);

        } else if ("supprimer".equals(action)) {
            // Supprimer un article
            int id = Integer.parseInt(request.getParameter("id"));
            Article.supprimerArticle(id);
            response.sendRedirect(request.getContextPath() + "/artservlet");

        } else if ("afficher".equals(action)) {
            String idParam = request.getParameter("id");

            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam.trim());
                Article article = Article.getArticleById(id);

                if (article != null) {
                    request.setAttribute("article", article);
                } else {
                    request.setAttribute("error", "Aucun article trouvé pour l'ID fourni.");
                }
            } else {
                request.setAttribute("error", "ID de l'article manquant.");
            }

            // Rediriger vers afficherArticle.jsp
            request.getRequestDispatcher("afficherArticle.jsp").forward(request, response);
        }
    }
}
