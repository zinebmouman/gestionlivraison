<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails de la Personne</title>
</head>
<body>
    <h1>Détails de la Personne</h1>

    <c:if test="${not empty person}">
        <table border="1">
            <tr>
                <th>ID</th>
                <td>${person.id}</td>
            </tr>
            <tr>
                <th>Nom</th>
                <td>${person.nom}</td>
            </tr>
            <tr>
                <th>Prix</th>
                <td>${person.prix}</td>
            </tr>
        </table>
    </c:if>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <a href="PersonServlet">Retour à la liste des personnes</a>
</body>
</html>
