<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="crud.article" %> <!-- Importer la classe article -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des articles</title>
</head>
<body>
    <h2>Liste des articles</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Date</th>
            <th>Action</th>
        </tr>
        <%
            // Utiliser un cast sécurisé
            List<article> articles = null;
            Object attr = request.getAttribute("articles");
            if (attr instanceof List<?>) {
                articles = (List<article>) attr; // Cast sécurisé
            }
            if (articles != null && !articles.isEmpty()) {
                for (article article : articles) {
        %>
        <tr>
            <td><%= article.getId() %></td>
            <td><%= article.getNom() %></td>
            <td><%= article.getDate() %></td>
            <td>
                <a href="supprimerArticle?id=<%= article.getId() %>">Supprimer</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">Aucun article trouvé.</td>
        </tr>
        <%
            }
        %>
    </table>

    <!-- Bouton pour ajouter un article -->
    <form action="ajouterarticle.jsp">
        <button type="submit">Ajouter un article</button>
    </form>
</body>
</html>
l