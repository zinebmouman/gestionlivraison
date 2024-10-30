<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Liste des Articles</title>
</head>
<body>
    <h1>Liste des Articles</h1>



    <!-- Affichage de la liste des articles -->
    <c:if test="${not empty articles}">
        <table border="1" cellpadding="10" cellspacing="0">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prix</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${articles}" var="article">
                    <tr>
                        <td>${article.nom}</td>
                        <td>${article.prix}</td>
                        <td>
                            <form action="artservlet" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="supprimer" />
                                <input type="hidden" name="id" value="${article.id}" />
                                <button type="submit">Supprimer</button>
                            </form>
                        </td>
                         <td>
                       <form action="modifierArticle.jsp" method="get">
    <input type="hidden" name="id" value="${article.id}">
    <button type="submit">Modifier article</button>
</form>



                        </td>
                        <td>
                       <form action="afficherArticle.jsp" method="get">
    <input type="hidden" name="id" value="${article.id}">
    <button type="submit">Afficher Article</button>
</form>



                        </td>
                    </tr>
                </c:forEach>
                    <!-- Formulaire pour ajouter un article -->
   <td>
                        <form action="ajouterarticle.jsp">
        <button type="submit">Ajouter un article</button>
    </form>
                        </td>
            </tbody>
        </table>
    </c:if>

</body>
</html>




  