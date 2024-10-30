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
import java.util.List;

@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
    private PersonDAO personDAO;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        personDAO = daoFactory.getPersonDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<PersonBean> personList = personDAO.findAll(); // Récupérer la liste des personnes
            request.setAttribute("personList", personList); // Passer la liste à la JSP
            request.getRequestDispatcher("/afficherPerson.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Erreur lors de la récupération des personnes : " + e.getMessage());
            request.getRequestDispatcher("/erreur.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Vérifier si une demande de suppression est effectuée
        String action = request.getParameter("action");
        if ("supprimer".equals(action)) {
            String idParam = request.getParameter("id"); // Récupérer l'identifiant de la personne à supprimer

            if (idParam != null && !idParam.isEmpty()) {
                try {
                    int id = Integer.parseInt(idParam); // Convertir l'identifiant en entier
                    personDAO.delete(id); // Appeler la méthode delete

                    // Redirection après la suppression
                    response.sendRedirect("PersonServlet"); // Rediriger vers le servlet pour afficher la liste mise à jour
                    return; // Sortir de la méthode après redirection
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Identifiant invalide : " + e.getMessage());
                    request.getRequestDispatcher("/erreur.jsp").forward(request, response);
                    return; // Sortir après avoir géré l'erreur
                } catch (Exception e) {
                    request.setAttribute("error", "Erreur lors de la suppression de la personne : " + e.getMessage());
                    request.getRequestDispatcher("/erreur.jsp").forward(request, response);
                    return; // Sortir après avoir géré l'erreur
                }
            } else {
                request.setAttribute("error", "Identifiant de personne manquant.");
                request.getRequestDispatcher("/erreur.jsp").forward(request, response);
                return; // Sortir après avoir géré l'erreur
            }
        }

        // Si aucune action de suppression, traiter l'ajout d'une personne
        String nom = request.getParameter("nom");
        String prixParam = request.getParameter("prix"); // Changez ceci pour récupérer le prix en tant que chaîne
        int prix;

        try {
            if (prixParam == null || prixParam.isEmpty()) {
                throw new NumberFormatException("Le prix est manquant.");
            }
            prix = Integer.parseInt(prixParam); // Convertir le prix en entier
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Le prix doit être un nombre. Erreur : " + e.getMessage());
            request.getRequestDispatcher("/erreur.jsp").forward(request, response);
            return;
        }

        // Créer un nouvel objet PersonBean
        PersonBean person = new PersonBean();
        person.setNom(nom);
        person.setPrix(prix);

        try {
            // Appeler la méthode create pour ajouter la personne à la base de données
            personDAO.create(person);
            response.sendRedirect("PersonServlet"); // Redirection vers le servlet pour afficher la liste mise à jour
        } catch (Exception e) {
            request.setAttribute("error", "Erreur lors de l'ajout de la personne : " + e.getMessage());
            request.getRequestDispatcher("/erreur.jsp").forward(request, response);
        }
        
    }
    

}
