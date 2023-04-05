package fr.eni.encheres.dal.jdbc;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.exceptions.DALException;


public class ArticleDAOJdbcImpl {
    // Requêtes SQL
    private static final String INSERT_ARTICLE = "INSERT INTO articles (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, id_utilisateur, id_categorie) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ARTICLES = "SELECT * FROM articles";
    private static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM articles WHERE id_article = ?";
    private static final String UPDATE_ARTICLE = "UPDATE articles SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, id_utilisateur = ?, id_categorie = ? WHERE id_article = ?";
    private static final String DELETE_ARTICLE = "DELETE FROM articles WHERE id_article = ?";


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
        }

        // List<Article> articles = new ArrayList<>();
        // try (Connection con = ConnectionProvider.getConnection();
        //     PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_ARTICLES)) {

        //     ResultSet rs = pstmt.executeQuery();

        //     // Parcours du résultat de la requête
        //     while (rs.next()) {
        //         Article article = new Article();
        //         article.setId(rs.getInt("id_article"));
        //         article.setNomArticle(rs.getString("nom_article"));
        //         article.setDescription(rs.getString("description"));
        //         article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
        //         article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
        //         article.setPrixInitial(rs.getInt("prix_initial"));
        //         // Récupération de l'utilisateur associé à l'article
        //         UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
        //         article.setUtilisateur(utilisateurDAO.selectUtilisateurById(rs.getInt("id_utilisateur")));
        //         // Récupération de la catégorie associée à l'article
        //         CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
        //         article.setCategorie(categorieDAO.selectCategorieById(rs.getInt("id_categorie")));

        //         articles.add(article);
        //     }
        // } catch (SQLException e) {
        //     throw new DALException("Erreur lors de la récupération de la liste des articles en base de données", e);
        // }
        // return articles;
    }

    @Override
    public Article selectArticleById(int id) throws DALException {
        Article article = null;
        try (Connection con = ConnectionProvider.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SELECT_ARTICLE_BY_ID)) {

            // Définition des paramètres de la requête
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            // Si un article correspondant à l'ID est trouvé, on le crée et on le renvoie
            if (rs.next()) {
                article = new Article();
                article.setId(rs.getInt("id_article"));
                article.setNomArticle(rs.getString("nom_article"));
                article.setDescription(rs.getString("description"));
                article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
                article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
                article.setPrixInitial(rs.getInt("prix_initial"));
                // Récupération de l'utilisateur associé à l'article
                UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
                article.setUtilisateur(utilisateurDAO.selectUtilisateurById(rs.getInt("id_utilisateur")));
                // Récupération de la catégorie associée à l'article
                CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
                article.setCategorie(categorieDAO.selectCategorieById(rs.getInt("id_categorie")));
            }
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la récupération de l'article en base de données", e);
        }
        return article;
    }

    @Override
    public void updateArticleById(int idArticle) throws DALException {
        try (Connection con = ConnectionProvider.getConnection();
            PreparedStatement pstmt = con.prepareStatement(UPDATE_ARTICLE)) {

            // Définition des paramètres de la requête
            pstmt.setString(1, article.getNomArticle());
            pstmt.setString(2, article.getDescription());
            pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
            pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
            pstmt.setInt(5, article.getPrixInitial());
            pstmt.setInt(6, article.getUtilisateur().getId());
            pstmt.setInt(7, article.getCategorie().getId());
            pstmt.setInt(8, article.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la mise à jour de l'article en base de données", e);
        }
    }

    /**
     * Methode in charge to delete an article from the database
     * @param idArticle int
     * @throws DALException
     */
    @Override
    public void deleteArticleById(int idArticle) throws DALException {
        try (Connection con = ConnectionProvider.getConnection();
            PreparedStatement pstmt = con.prepareStatement(DELETE_ARTICLE)) {

            // Définition des paramètres de la requête
            pstmt.setInt(1, idArticle);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la suppression de l'article en base de données", e);
        }
    }

}
