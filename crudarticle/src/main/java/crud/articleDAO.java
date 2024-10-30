package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.connectionDB;

public class articleDAO {

    // Ajouter un article
    public void ajouterArticle(article article) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Obtenir une connexion à la base de données
            conn = connectionDB.getConnection();

            // Requête SQL pour insérer un article
            String sql = "INSERT INTO article (nom, date) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);

            // Paramétrer la requête
            stmt.setString(1, article.getNom());
            stmt.setString(2, article.getDate());

            // Exécuter la requête
            stmt.executeUpdate();
            System.out.println("Article ajouté avec succès");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de l'ajout de l'article dans la BDD.");
        } finally {
            // Fermer la connexion et le statement
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // Récupérer tous les articles
    public List<article> getArticles() throws SQLException, ClassNotFoundException {
        List<article> articles = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = connectionDB.getConnection();
            String sql = "SELECT * FROM article"; // Remplacez par votre table et colonnes réelles
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                article article = new article();
                article.setId(rs.getInt("id")); // Assurez-vous que "id" est le bon nom de colonne
                article.setNom(rs.getString("nom"));
                article.setDate(rs.getString("date"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la récupération des articles.");
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return articles;
    }
    // Supprimer un article par ID
    public void supprimerArticle(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connexion à la base de données
            conn = connectionDB.getConnection();

            // Requête SQL pour supprimer un article par son ID
            String sql = "DELETE FROM article WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            // Exécuter la requête
            stmt.executeUpdate();
            System.out.println("Article supprimé avec succès");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la suppression de l'article.");
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
