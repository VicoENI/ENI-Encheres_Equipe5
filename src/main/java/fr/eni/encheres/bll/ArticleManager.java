package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.encheres.exceptions.BLLException;
import fr.eni.encheres.exceptions.DALException;

/**
 * Class managing the articles.
 * @author mkebeEni
 * @version 1.0
 */
public class ArticleManager {

	// Liste des articles gérée par la classe ArticleManager
	private List<Article> listArticles;
	
	// Accès aux données des articles
	private ArticleDAO daoArticles;
	
	
	/**
	 * Constructor of the ArticleManager class. Initializes the list of articles and the connection to the database.
	 * 
	 * @throws BLLException if a business error occurs
	 * @throws DALException
	 */
	public ArticleManager() throws BLLException, DALException {
		daoArticles = (ArticleDAO) new ArticleDAOJdbcImpl();

		try {
			listArticles = daoArticles.selectAllArticles();
		} catch (Exception e) {
			throw new BLLException("Echec du chargement de la liste des articles - ", e);
		}
	}

	
	/**
	 * Return the list of articles managed by the ArticleManager class.
	 * 
	 * @return la liste des articles
	 */
	public List<Article> getListArticles() {
		return listArticles;
	}

	
	/**
	 * Adds a new article to the list of articles managed by the ArticleManager class.
	 * 
	 * @param newArticle Article
	 * @return the position of the article in the list
	 * @throws BLLException if a business error occurs
	 * @throws DALException
	 */
	public int addArticle(Article newArticle) throws BLLException, DALException {
		Article article;
		try {
			article = daoArticles.selectArticleById(newArticle.getNoArticle());
		} catch (Exception e) {
			throw new BLLException("Echec selectById dans addArticle", e);
		}
		if (article != null) {
			throw new BLLException("Article déjà existant.");
		}
		try {
			validerArticle(newArticle);
			daoArticles.insertArticle(newArticle);
			listArticles.add(newArticle);
		} catch (BLLException e) {
			throw new BLLException("Echec addArticle", e);
		}
		return listArticles.size() - 1;
	}
	
	
	/**
	 * Met à jour un article dans la liste d'articles gérée par la classe ArticleManager.
	 * 
	 * @param article l'article à mettre à jour
	 * @throws BLLException si une erreur métier survient
	 * @throws DALException
	 */
	public void updateArticle(Article article) throws BLLException, DALException {
		Article existingArticle;
		try {
			existingArticle = daoArticles.selectArticleById(article.getNoArticle());
		} catch (Exception e) {
			throw new BLLException("Echec selectById dans updateArticle", e);
		}
		if (existingArticle == null) {
			throw new BLLException("Article inexistant.");
		}
		article.setNoArticle(existingArticle.getNoArticle());
		try {
			validerArticle(article);
			daoArticles.updateArticleById(article);
		} catch (BLLException e) {
			throw new BLLException("Echec updateArticle - Article:" + article, e);
		}
	}
	
	/**
	 * Return the article at the specified index in the list of articles managed by the ArticleManager class.
	 * @param index int
	 * @return article where index is the position in the list
	 */
	public Article getArticle(int index) {
		return listArticles.get(index);
	}
	
	/**
	 * Removes the article at the specified index in the list of articles managed by the ArticleManager class.
	 * @param index int
	 * @throws BLLException
	 * @throws DALException
	 */
	public void removeArticleById(int index) throws BLLException, DALException {
	    try {
	        daoArticles.deleteArticleById(listArticles.get(index).getNoArticle());
	        listArticles.remove(index);
	    } catch (Exception e) {
	        throw new BLLException("Echec de la suppression de l'article - ", e);
	    }
	}

	/**
	 * Checks the validity of the article.
	 * 
	 * @param article Article
	 * @throws BLLException if a business error occurs
	 */
	private void validerArticle(Article article) throws BLLException {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if (article.getNomArticle() == null || article.getNomArticle().trim().length() == 0) {
			valide = false;
			sb.append("Le nom de l'article est obligatoire. ");
		}
		if (article.getDescription() == null || article.getDescription().trim().length() == 0) {
			valide = false;
			sb.append("La description de l'article est obligatoire. ");
		}
		if (article.getDateDebutEncheres() == null) {
			valide = false;
			sb.append("La date de début des enchères est obligatoire. ");
		}
		if (article.getDateFinEncheres() == null) {
			valide = false;
			sb.append("La date de fin des enchères est obligatoire. ");
		}
		if (article.getPrixInitial() == 0) {
			valide = false;
			sb.append("Le prix initial est obligatoire. ");
		}
		if (article.getPrixVente() == 0) {
			valide = false;
			sb.append("Le prix de vente est obligatoire. ");
		}
		if (article.getUtilisateur() == null) {
			valide = false;
			sb.append("L'utilisateur est obligatoire. ");
		}
		if (article.getCategorie() == null) {
			valide = false;
			sb.append("La catégorie est obligatoire. ");
		}
		if (!valide) {
			throw new BLLException(sb.toString());
		}
	}
}
