package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.exceptions.DALException;

/**
 * Class in charge of managing SQL queries for the RETRAITS table
 * @author VicoENI
 * @version 1.0
 */
public class RetraitDAOJdbcImpl implements RetraitDAO {

    // Création des requêtes SQL
    private static final String CREATE_RETRAIT          = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_RETRAIT         = "SELECT * FROM RETRAITS";
    private static final String GET_RETRAIT_BY_ARTICLE  = "SELECT * FROM RETRAITS WHERE no_article = ?";
    private static final String UPDATE_RETRAIT          = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
    private static final String DELETE_RETRAIT          = "DELETE FROM RETRAITS WHERE no_article = ?";

    // Objet Connection pour la connexion à la base de données
    private Connection connection;
    
    /**
     * Constructor of the class RetraitDAOJdbcImpl which takes a connection as a parameter
     * @param connection Connection
     */
    public RetraitDAOJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method in charge of creating a new retrait in the database
     * @param retrait Retrait
     */
    @Override
    public void createRetrait(Retrait retrait) throws DALException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_RETRAIT)) {
            statement.setInt(1, retrait.getNoArticle());
            statement.setString(2, retrait.getRue());
            statement.setString(3, retrait.getCodePostal());
            statement.setString(4, retrait.getVille());
            statement.executeUpdate();
        }
    }

    /**
     * Method in charge of retrieving all the retraits from the database
     * @return List of retraits
     * @throws DALException
     */
    @Override
    public List<Retrait> getAllRetraits() throws DALException {
        List<Retrait> retraits = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_RETRAIT)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int noArticle       = resultSet.getInt("no_article");
                String rue          = resultSet.getString("rue");
                String codePostal   = resultSet.getString("code_postal");
                String ville        = resultSet.getString("ville");
                Retrait retrait = new Retrait(noArticle, rue, codePostal, ville);
                retraits.add(retrait);
            }
        }
        return retraits;
    }

    /**
     * Method in charge of retrieving a retrait from the database by its article number
     * @param noArticle int
     * @return List of retraits
     * @throws DALException
     */
    @Override
    public List<Retrait> getRetraitsByArticle(int noArticle) throws DALException {
        List<Retrait> retraits = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_RETRAIT_BY_ARTICLE)) {
            statement.setInt(1, noArticle);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String rue = resultSet.getString("rue");
                String codePostal = resultSet.getString("code_postal");
                String ville = resultSet.getString("ville");
                Retrait retrait = new Retrait(noArticle, rue, codePostal, ville);
                retraits.add(retrait);
            }
        }
        return retraits;
    }

    /**
     * Method in charge of updating a retrait in the database
     * @param retrait Retrait
     */
    @Override
    public void updateRetrait(Retrait retrait) throws DALException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_RETRAIT)) {
            statement.setString(1, retrait.getRue());
            statement.setString(2, retrait.getCodePostal());
            statement.setString(3, retrait.getVille());
            statement.setInt(4, retrait.getNoArticle());
            statement.executeUpdate();
        }
    }

    /**
     * Method in charge of deleting a retrait from the database
     * @param noArticle int
     */
    @Override
    public void deleteRetrait(int noArticle) throws DALException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_RETRAIT)) {
            statement.setInt(1, noArticle);
            statement.executeUpdate();
        }
    }

}
