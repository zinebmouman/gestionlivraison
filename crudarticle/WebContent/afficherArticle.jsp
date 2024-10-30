<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Afficher Article</title>
</head>
<body>
    <h1>Détails de l'article</h1>

    <c:if test="${not empty article}">
        <table border="1">
            <tr>
                <th>ID</th>
                <td>${article.id}</td>
            </tr>
            <tr>
                <th>Nom</th>
                <td>${article.nom}</td>
            </tr>
            <tr>
                <th>Prix</th>
                <td>${article.prix}</td>
            </tr>
        </table>
    </c:if>
    
</body>
</html>
