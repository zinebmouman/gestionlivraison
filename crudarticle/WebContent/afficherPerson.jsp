<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails des Personnes</title>
</head>
<body>
    <h1>Détails des Personnes</h1>

    <c:if test="${not empty personList}">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prix</th>
            </tr>
            <c:forEach items="${personList}" var="person">
                <tr>
                    <td>${person.id}</td>
                    <td>${person.nom}</td>
                    <td>${person.prix}</td>
                    <td>
                        <a href="PersonDetailServlet?id=${person.id}">Voir Détails</a>
                    </td>
                    <td>
    					<form action="PersonServlet" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette personne ?');">
        				<input type="hidden" name="id" value="${person.id}"/>
        				<input type="hidden" name="action" value="supprimer"/> <!-- Champ caché pour l'action -->
        				<input type="submit" value="Supprimer"/>
    					</form>
						</td>
						<td>
    						<a href="<c:url value='/PersonDetailServlet'/>?id=${person.id}&action=modifier">Modifier personne</a>
						</td>

                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
    <a href="ajouterPersonne.jsp">Ajouter une autre personne</a>
</body>
</html>
