package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JAVA.Beans.PersonBean;

public class PersonDaoImpl implements PersonDAO {

	private DAOFactory          daoFactory;

    public PersonDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
	@Override
	public void create(PersonBean person) throws DAOException {
		String SQL_INSERT = "INSERT INTO articles (nom, prix) VALUES (?, ?)";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare(connexion, SQL_INSERT, person.getNom(), person.getPrix());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    } finally {
	        // Fermez les ressources ici (preparedStatement, connexion, etc.)
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connexion != null) {
	            try {
	                connexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		
	}
	private static PersonBean map( ResultSet resultSet ) throws SQLException {
		 PersonBean personBean = new PersonBean();
		personBean.setId( resultSet.getInt( "id" ) );
		personBean.setNom( resultSet.getString( "nom" ) );
		personBean.setPrix( resultSet.getInt( "prix" ) );
		return personBean;
		}
	public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}

	@Override
	public PersonBean find(int id) throws DAOException {
	    final String SQL_SELECT_PAR_ID = "SELECT id, nom, prix FROM articles WHERE id = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    PersonBean personBean = null;

	    try {
	        // Récupération d'une connexion depuis la Factory
	        connexion = daoFactory.getConnection();
	        preparedStatement = connexion.prepareStatement(SQL_SELECT_PAR_ID);
	        preparedStatement.setInt(1, id); // Définir l'id dans la requête
	        resultSet = preparedStatement.executeQuery();

	        // Parcours de la ligne de données de l'éventuel ResultSet retourné
	        if (resultSet.next()) {
	            personBean = map(resultSet); // Mapper le ResultSet à PersonBean
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e); // Propager l'exception
	    } finally {
	        // Fermez vos ressources ici
	        // ClosingAll(resultSet, preparedStatement, connexion);
	        // Vous pouvez remplacer par un bloc try-finally si nécessaire
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); // Logger ou gérer l'erreur
	            }
	        }
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); // Logger ou gérer l'erreur
	            }
	        }
	        if (connexion != null) {
	            try {
	                connexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); // Logger ou gérer l'erreur
	            }
	        }
	    }

	    return personBean; // Retourner l'objet trouvé ou null
	}
	@Override
	public List<PersonBean> findAll() throws DAOException {
	    final String SQL_SELECT_ALL = "SELECT id, nom, prix FROM articles";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<PersonBean> personList = new ArrayList<>();

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = connexion.prepareStatement(SQL_SELECT_ALL);
	        resultSet = preparedStatement.executeQuery();

	        /* Parcours de toutes les lignes de données du ResultSet */
	        while (resultSet.next()) {
	            PersonBean personBean = map(resultSet);
	            personList.add(personBean);
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    } finally {
	        // Fermez vos ressources ici
	        // ClosingAll(resultSet, preparedStatement, connexion);
	    }

	    return personList;
	}
	public void delete(int id) throws DAOException {
	    final String SQL_DELETE = "DELETE FROM articles WHERE id = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        // Récupération d'une connexion depuis la Factory
	        connexion = daoFactory.getConnection();
	        preparedStatement = connexion.prepareStatement(SQL_DELETE);
	        preparedStatement.setInt(1, id); // Lier l'identifiant à la requête

	        // Exécuter la requête de suppression
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    } finally {
	        // Fermez vos ressources ici
	        // ClosingAll(preparedStatement, connexion);
	    }
	}
	public void update(PersonBean person) throws DAOException {
	    final String SQL_UPDATE = "UPDATE articles SET nom = ?, prix = ? WHERE id = ?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connexion = daoFactory.getConnection();
	        preparedStatement = connexion.prepareStatement(SQL_UPDATE);
	        
	        // Définir les paramètres
	        preparedStatement.setString(1, person.getNom());
	        preparedStatement.setInt(2, person.getPrix());
	        preparedStatement.setInt(3, person.getId());

	        // Exécuter la mise à jour
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    } finally {
	        // Fermez vos ressources ici
	        // ClosingAll(preparedStatement, connexion);
	    }
	}


}
