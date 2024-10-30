package Articles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class Article {
    private int id;
    private String nom;
    private int prix;

    // Constructeur de la classe Article
    public Article(int id, String nom, int prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    // Méthode pour récupérer la liste des articles depuis la base de données
    public static List<Article> getArticles(HttpServletRequest request) {
        List<Article> articles = new ArrayList<Article>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/tpjava";
        String utilisateur = "root";
        String motDePasse = "";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id, nom, prix FROM articles;");

            while (resultat.next()) {
                int idarticle = resultat.getInt("id");
                String nomarticle = resultat.getString("nom");
                int prixarticle = resultat.getInt("prix");

                // Ajouter l'article à la liste
                articles.add(new Article(idarticle, nomarticle, prixarticle));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultat != null) {
                try {
                    resultat.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return articles;
    }

    // Méthode pour ajouter un article
    public static void ajouterArticle(String nom, int prix) {
        String url = "jdbc:mysql://localhost:3306/tpjava";
        String utilisateur = "root";
        String motDePasse = "";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            String sql = "INSERT INTO articles (nom, prix) VALUES (?, ?)";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, prix);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    // Méthode pour supprimer un article par ID
    public static void supprimerArticle(int id) {
        String url = "jdbc:mysql://localhost:3306/tpjava";
        String utilisateur = "root";
        String motDePasse = "";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            String sql = "DELETE FROM articles WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
 // Méthode pour modifier un article
    public static void modifierArticle(int id, String nom, int prix) {
        String url = "jdbc:mysql://localhost:3306/tpjava";
        String utilisateur = "root";
        String motDePasse = "";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            String sql = "UPDATE articles SET nom = ?, prix = ? WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, prix);
            preparedStatement.setInt(3, id);

            // Exécuter la mise à jour
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
    public static Article getArticleById(int id) {
        Article article = null;
        String url = "jdbc:mysql://localhost:3306/tpjava";
        String utilisateur = "root";
        String motDePasse = "";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            String sql = "SELECT * FROM articles WHERE id = ?";
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                article = new Article(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getInt("prix")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connexion != null) try { connexion.close(); } catch (SQLException ignore) {}
        }
        return article;
    }




}

