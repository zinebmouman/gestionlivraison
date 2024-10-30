<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une Personne</title>
</head>
<body>
    <h1>Ajouter une Personne</h1>
    <form action="PersonServlet" method="post">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" required><br><br>
        <label for="prix">Prix:</label>
        <input type="number" id="prix" name="prix" required><br><br>
        <input type="submit" value="Ajouter">
    </form>
</body>
</html>
