package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
    private static final String GET_CATEGORIE_BY_NO = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
    private static final String UPDATE_CATEGORIE    = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
    private static final String DELETE_CATEGORIE    = "DELETE FROM CATEGORIES WHERE no_categorie = ?";

    // Objet Connection pour la connexion à la base de données
    private Connection connection;
    
    /**
     * Constructor of the class CategorieDAOJdbcImpl which takes a connection as a parameter
     * @param connection
     */
    public CategorieDAOJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method in charge of creating a new category in the database
     * @param categorie Categorie
     * @throws DALException
     */
    @Override
    public void createCategorie(Categorie categorie) throws DALException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_CATEGORIE)) {
            statement.setString(1, categorie.getLibelle());
            statement.executeUpdate();
        }
    }

    /**
     * Method in charge of retrieving all the categories from the database
     * @return List of categories
     * @throws DALException
     */
    @Override
    public List<Categorie> getAllCategories() throws DALException {
        List<Categorie> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int noCategorie = resultSet.getInt("no_categorie");
                String libelle = resultSet.getString("libelle");
                Categorie categorie = new Categorie(noCategorie, libelle);
                categories.add(categorie);
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
    public Categorie getCategorieByNo(int noCategorie) throws DALException {
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORIE_BY_NO)) {
            statement.setInt(1, noCategorie);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String libelle = resultSet.getString("libelle");
                Categorie categorie = new Categorie(noCategorie, libelle);
                return categorie;
            }
        }
        return null;
    }

    /**
     * Method in charge of updating a category in the database
     * @param categorie Categorie
     * @throws DALException
     */
    @Override
    public void updateCategorie(Categorie categorie) throws DALException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORIE)) {
            statement.setString(1, categorie.getLibelle());
            statement.setInt(2, categorie.getNoCategorie());
            statement.executeUpdate();
        }
    }

    /**
     * Method in charge of deleting a category from the database
     * @param noCategorie int
     * @throws DALException
     */
    @Override
    public void deleteCategorie(int noCategorie) throws DALException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORIE)) {
            statement.setInt(1, noCategorie.getNoCategorie());
            statement.executeUpdate();
        }
    }
}
