 package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.exceptions.DALException;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;
/**
 * Class in charge of managing SQL queries for the ENCHERES table
 * @author VicoENI
 * @version 1.0
*/
public class EnchereDAOJdbcImpl implements EnchereDAO {

    // Création des requêtes SQL
    private static final String CREATE_ENCHERE   = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_ENCHERES = "SELECT * FROM ENCHERES";
    private static final String GET_ENCHERE_BY_NO_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article = ?";
    private static final String UPDATE_ENCHERE   = "UPDATE ENCHERES SET no_utilisateur = ?, date_enchere = ?, montant_enchere = ? WHERE no_article = ?";
    private static final String DELETE_ENCHERE   = "DELETE FROM ENCHERES WHERE no_article = ?";
    
    
    // Objet Connection pour la connexion à la base de données
    private Connection connection;
    
    /**
     * Constructor of the class EnchereDAOJdbcImpl which takes a connection as a parameter
     */
    public EnchereDAOJdbcImpl() {
        this.connection = connection;
    }

    /**
     * Method in charge of creating a new bid in the database
     * @param enchere Enchere
     * @throws DALException
     */
    @Override
    public void createEnchere(Enchere enchere) throws DALException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ENCHERE)) {
            statement.setUtilisateur(1, enchere.getUtilisateur());
            statement.setArticleVendu(2, enchere.getArticleVendu());
            statement.setTimestamp(3, new java.sql.Timestamp(enchere.getDateEnchere().getTime()));
            statement.setInt(4, enchere.getMontantEnchere());
            statement.executeUpdate();
        }
    }

    /**
     * Method in charge of retrieving all the bids from the database
     * @return List of bids
     * @throws DALException
     */
    @Override
    public List<Enchere> getAllEncheres() throws DALException {
        List<Enchere> encheres = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ENCHERES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int noUtilisateur = resultSet.getIdUtilisateur("no_utilisateur");
                int noArticle = resultSet.getIdArticle("no_article");
                java.util.Date dateEnchere = new java.util.Date(resultSet.getTimestamp("date_enchere").getTime());
                int montantEnchere = resultSet.getInt("montant_enchere");
                Enchere enchere = new Enchere(dateEnchere, montantEnchere,noUtilisateur, noArticle);
                encheres.add(enchere);
            }
        }
        return encheres;
    }

    /**
     * Method in charge of updating a bid in the database
     * @param enchere Enchere
     * @throws DALException
     */
    @Override
    public List<Enchere> getEncheresByNoArticle(int noArticle) throws DALException {
        List<Enchere> encheres = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ENCHERE_BY_NO_ARTICLE)) {
            statement.setInt(1, noArticle);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int noUtilisateur = resultSet.getInt("no_utilisateur");
                java.util.Date dateEnchere = new java.util.Date(resultSet.getTimestamp("date_enchere").getTime());
                int montantEnchere = resultSet.getInt("montant_enchere");
                Enchere enchere = new Enchere(dateEnchere, montantEnchere,noUtilisateur, noArticle);
                encheres.add(enchere);
            }
        }
        return encheres;
    }

}

