package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.exceptions.DALException;

/**
 * Class in charge of managing SQL queries for the CATEGORIES table
 * @author VicoENI
 * @version 1.0
*/
public class CategorieDAOJdbcImpl implements CategorieDAO {

    // Création des requêtes SQL
    private static final String CREATE_CATEGORIE    = "INSERT INTO CATEGORIES (libelle) VALUES (?)";
    private static final String GET_ALL_CATEGORIES  = "SELECT * FROM CATEGORIES";
    private static final String GET_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
    private static final String UPDATE_CATEGORIE    = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
    private static final String DELETE_CATEGORIE    = "DELETE FROM CATEGORIES WHERE no_categorie = ?";

    // Objet Connection pour la connexion à la base de données
    private Connection connection;
    
    /**
     * Constructor of the class CategorieDAOJdbcImpl which takes a connection as a parameter
     * @throws SQLException
     */
    public CategorieDAOJdbcImpl() throws SQLException {
        this.connection = JdbcTools.getConnection();
    }

    /**
     * Method in charge of creating a new category in the database
     * @param categorie Categorie
     * @throws DALException
     */
    @Override
    public void createCategorie(Categorie categorie) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcTools.getConnection();
            statement = connection.prepareStatement(CREATE_CATEGORIE);
            statement.setString(1, categorie.getLibelle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la création de la catégorie", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur lors de la création de la catégorie", e);
            }
        }
    }

    /**
     * Method in charge of retrieving all the categories from the database
     * @return List of categories
     * @throws DALException
     */
    @Override
    public List<Categorie> getAllCategories() throws DALException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Categorie> categories = new ArrayList<Categorie>();
        try {
            connection = JdbcTools.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_CATEGORIES);
            Categorie categorie = null;

            while (resultSet.next()) {
                categorie = new Categorie(resultSet.getString("libelle"));
                categories.add(categorie);
            }
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la récupération des catégories", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur lors de la récupération des catégories", e);
            }
        }
        return categories;
    }

    /**
     * Method in charge of retrieving a category from the database by its number
     * @param noCategorie int
     * @return categorie | null
     * @throws DALException
     */
    @Override
    public Categorie getCategorieById(int noCategorie) throws DALException {
        ResultSet resultSet = null;
        Categorie categorie = null;

        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORIE_BY_ID)) {
            statement.setInt(1, noCategorie);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String libelle = resultSet.getString("libelle");
                categorie = new Categorie(libelle);
            }
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la récupération de la catégorie", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur lors de la récupération de la catégorie", e);
            }
        }
        return categorie;
    }

    /**
     * Method in charge of updating a category in the database
     * @param id int
     * @param libelle String
     * @throws DALException
     */
    @Override
    public void updateCategorieById(int id, String libelle) throws DALException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcTools.getConnection();
            statement = connection.prepareStatement(UPDATE_CATEGORIE);
            statement.setString(1, libelle);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la modification de la catégorie", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur lors de la modification de la catégorie", e);
            }
        }
    }

    /**
     * Method in charge of deleting a category from the database
     * @param noCategorie int
     * @throws DALException
     * @throws SQLException
     */
    @Override
    public void deleteCategorieById(int noCategorie) throws DALException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcTools.getConnection();
            statement = connection.prepareStatement(DELETE_CATEGORIE);
            statement.setInt(1, noCategorie);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la suppression de la catégorie", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur lors de la suppression de la catégorie", e);
            }
        }
    }
}
