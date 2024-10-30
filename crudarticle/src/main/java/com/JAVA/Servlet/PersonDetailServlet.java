package com.JAVA.Servlet;

import com.JAVA.Beans.PersonBean;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.PersonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/PersonDetailServlet")
public class PersonDetailServlet extends HttpServlet {
    private PersonDAO personDAO;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        personDAO = daoFactory.getPersonDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id"); // Récupérer l'identifiant en tant que chaîne
        String action = request.getParameter("action");
        if (idParam != null && !idParam.isEmpty()) { // Vérifier si l'id n'est pas vide
            try {
                int id = Integer.parseInt(idParam); // Convertir l'identifiant en entier
                PersonBean person = personDAO.find(id); // Utilisation de la méthode find

                if (person != null) {
                    request.setAttribute("person", person); 
                    if ("modifier".equals(action)) {
                        // Si action = "modifier", rediriger vers ModifierPersonne.jsp
                        request.getRequestDispatcher("/ModifierPersonne.jsp").forward(request, response);
                    } else {
                        // Sinon, afficher dans PersonDetail.jsp
                        request.getRequestDispatcher("/afficherPersonDetails.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Personne non trouvée.");
                    request.getRequestDispatcher("/erreur.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Identifiant invalide : " + e.getMessage());
                request.getRequestDispatcher("/erreur.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", "Erreur lors de la récupération de la personne : " + e.getMessage());
                request.getRequestDispatcher("/erreur.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Identifiant de personne manquant.");
            request.getRequestDispatcher("/erreur.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Vérifier si une action de modification est effectuée
        String action = request.getParameter("action");
        if ("modifier".equals(action)) {
            String idParam = request.getParameter("id");
            String nom = request.getParameter("nom");
            String prixParam = request.getParameter("prix");
            int prix;

            try {
                int id = Integer.parseInt(idParam); // Convertir l'identifiant en entier

                if (prixParam == null || prixParam.isEmpty()) {
                    throw new NumberFormatException("Le prix est manquant.");
                }
                prix = Integer.parseInt(prixParam); // Convertir le prix en entier

                // Créer un nouvel objet PersonBean pour mettre à jour
                PersonBean person = new PersonBean();
                person.setId(id); // Assurez-vous que vous avez un setter pour l'identifiant
                person.setNom(nom);
                person.setPrix(prix);

                // Appeler la méthode update pour modifier les données dans la base de données
                personDAO.update(person);

                // Redirection après la mise à jour
                response.sendRedirect("PersonServlet"); // Rediriger vers le servlet pour afficher la liste mise à jour
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Erreur de format : " + e.getMessage());
                request.getRequestDispatcher("/erreur.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", "Erreur lors de la modification de la personne : " + e.getMessage());
                request.getRequestDispatcher("/erreur.jsp").forward(request, response);
            }
        }
    }
}
