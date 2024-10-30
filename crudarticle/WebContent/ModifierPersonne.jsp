<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier Personne</title>
</head>
<body>
    <h1>Modifier Personne: ${person.nom}</h1>
    <form action="PersonDetailServlet" method="post"> <!-- Changer l'action vers le servlet de détail -->
        <input type="hidden" name="action" value="modifier"/>
        <input type="hidden" name="id" value="${person.id}"/> <!-- ID caché -->
        
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" value="${person.nom}" required/><br/>

        <label for="prix">Prix:</label>
        <input type="number" id="prix" name="prix" value="${person.prix}" required/><br/>

        <input type="submit" value="Modifier"/>
    </form>
</body>
</html>
