<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier Article</title>
</head>
<body>
    <h1>Modifier Article: ${article.nom}</h1>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form action="artservlet" method="post">
        <input type="hidden" name="action" value="modifier">
        <input type="hidden" name="id" value="${article.id}">

        <label for="nom">Nom de l'article :</label>
        <input type="text" name="nom" id="nom" value="${article.nom}" required>

        <label for="prix">Prix de l'article :</label>
        <input type="number" name="prix" id="prix" value="${article.prix}" required>

        <button type="submit">Modifier</button> 
    </form>
</body>
</html>
