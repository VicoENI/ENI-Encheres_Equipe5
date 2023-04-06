package fr.eni.encheres.dal.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.exceptions.DALException;


public class ArticleDAOJdbcImpl implements ArticleDAO {
    // Requêtes SQL
    private static final String INSERT_ARTICLE          = "INSERT INTO articles (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, id_utilisateur, id_categorie) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ARTICLES     = "SELECT * FROM articles";
    private static final String SELECT_ARTICLE_BY_ID    = "SELECT * FROM articles WHERE id_article = ?";
    private static final String UPDATE_ARTICLE          = "UPDATE articles SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, id_utilisateur = ?, id_categorie = ? WHERE id_article = ?";
    private static final String DELETE_ARTICLE          = "DELETE FROM articles WHERE id_article = ?";


    public void insertArticle(Article article) throws DALException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            // Ouverture de la connexion
            connection = JdbcTools.getConnection();

            // Création de la requête
            pstmt = connection.prepareStatement(INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);

            // Définition des paramètres de la requête
            pstmt.setString(1, article.getNomArticle());
            pstmt.setString(2, article.getDescription());
            pstmt.setDate(3, (Date) article.getDateDebutEncheres());
            pstmt.setDate(4, (Date) article.getDateFinEncheres());
            pstmt.setInt(5, article.getPrixInitial());
            pstmt.setInt(6, article.getUtilisateur().getId());
            pstmt.setInt(7, article.getCategorie().getNoCategorie());

            // Exécution de la requête
            pstmt.executeUpdate();

            // Récupération de l'id généré par la requête
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                article.setNoArticle(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DALException("Erreur lors de l'insertion de l'article en base de données", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur lors de la fermeture de la connexion", e);
            }
        }
    }

    @Override
    public List<Article> selectAllArticles() throws DALException {

        Connection connection = null;
        java.sql.Statement stmt = null;
        ResultSet rs = null;
        List<Article> listOfArticles = new ArrayList<Article>();
        try {
            connection = JdbcTools.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_ARTICLES);
            Article article = null;

            while (rs.next()) {
                article = new Article();
                article.setNoArticle(rs.getInt("no_article"));
                article.setNomArticle(rs.getString("nom_article"));
                article.setDescription(rs.getString("description"));
                article.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
                article.setDateFinEncheres(rs.getDate("date_fin_encheres"));
                article.setPrixInitial(rs.getInt("prix_initial"));
                article.setUtilisateur(rs.getInt("id_utilisateur"));
                article.setCategorie(rs.getInt("id_categorie"));
                listOfArticles.add(article);
            }
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la récupération de la liste des articles en base de données", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur lors de la fermeture de la connexion", e);
            }
        }
        return listOfArticles;
    }

    @Override
    public Article selectArticleById(int id) throws DALException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Article article = null;
        try {
            connection = JdbcTools.getConnection();
            pstmt = connection.prepareStatement(SELECT_ARTICLE_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                article = new Article();
                article.setNoArticle(rs.getInt("no_article"));
                article.setNomArticle(rs.getString("nom_article"));
                article.setDescription(rs.getString("description"));
                article.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
                article.setDateFinEncheres(rs.getDate("date_fin_encheres"));
                article.setPrixInitial(rs.getInt("prix_initial"));
                article.setUtilisateur(rs.getInt("id_utilisateur"));
                article.setCategorie(rs.getInt("id_categorie"));
            }
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la récupération de l'article en base de données", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur lors de la fermeture de la connexion", e);
            }
        }
        return article;
    }

    // @Override
    // public void updateArticleById(int idArticle) throws DALException {
    //     Connection connection = null;
    //     PreparedStatement pstmt = null;
    //     try {
    //         connection = JdbcTools.getConnection();
    //         pstmt = connection.prepareStatement(UPDATE_ARTICLE);
    //         pstmt.setInt(1, idArticle);
    //         pstmt.executeUpdate();
    //     } catch (SQLException e) {
    //         throw new DALException("Erreur lors de la mise à jour de l'article en base de données", e);
    //     } finally {
    //         try {
    //             if (connection != null) {
    //                 connection.close();
    //             }
    //         } catch (SQLException e) {
    //             throw new DALException("Erreur lors de la fermeture de la connexion", e);
    //         }
    //     }
    // }

    /**
     * Methode in charge to delete an article from the database
     * @param idArticle int
     * @throws DALException
     */
    @Override
    public void deleteArticleById(int idArticle) throws DALException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = JdbcTools.getConnection();
            pstmt = connection.prepareStatement(DELETE_ARTICLE);
            pstmt.setInt(1, idArticle);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la suppression de l'article en base de données", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DALException("Erreur lors de la fermeture de la connexion", e);
            }
        }
    }

}
