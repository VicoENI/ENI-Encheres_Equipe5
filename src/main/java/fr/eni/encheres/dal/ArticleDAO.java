package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.exceptions.DALException;

public interface ArticleDAO {

    // CRUD

    /**
     * Methode in charge of inserting an article in the database
     * @param article Article
     * @throws DALException
     */
    void insertArticle(Article article) throws DALException;

    /**
     * Methode in charge of retrieving all articles from the database
     * @return List of articles
     * @throws DALException
     */
    List<Article> selectAllArticles() throws DALException;

    /**
     * Methode in charge of retrieving an article from the database by its id
     * @param id int
     * @return article Article
     * @throws DALException
     */
    Article selectArticleById(int id) throws DALException;

    /**
     * Methode in charge of updating an article in the database
     * @param article Article
     * @throws DALException
     */
    void updateArticleById(Article article) throws DALException;

    /**
     * Methode in charge of deleting an article from the database by its id
     * @param id int
     * @throws DALException
     */
    void deleteArticleById(int id) throws DALException;
}

