<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Article</title>
</head>
<body>
    <h2>Ajouter un Article</h2>
    
     <form action="artservlet" method="post">
        <input type="hidden" name="action" value="ajouter" />
        <label for="nom">Nom de l'article:</label>
        <input type="text" id="nom" name="nom" required />
        <label for="prix">Prix:</label>
        <input type="number" id="prix" name="prix" required />
        <button type="submit">Ajouter l'article</button>
    </form>
</body>
</html>
