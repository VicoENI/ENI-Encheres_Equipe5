package fr.eni.encheres.bll;

import java.util.List;

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
	 */
	public ArticleManager() throws BLLException {
		daoArticles = DAOFactory.getArticleDAO();

		try {
			listArticles = daoArticles.selectAll();
		} catch (BLLException e) {
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
	 */
	public int addArticle(Article newArticle) throws BLLException {
		Article article;
		try {
			article = daoArticles.selectById(newArticle);
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans addArticle", e);
		}
		if (article != null) {
			throw new BLLException("Article déjà existant.");
		}
		try {
			validerArticle(newArticle);
			daoArticles.insert(newArticle);
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
	 */
	public void updateArticle(Article article) throws BLLException {
		Article existingArticle;
		try {
			existingArticle = daoArticles.selectById(article.getIdArticle());
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans updateArticle", e);
		}
		if (existingArticle == null) {
			throw new BLLException("Article inexistant.");
		}
		article.setIdArticle(existingArticle.getIdArticle());
		try {
			validerArticle(article);
			daoArticles.update(article);
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
	 */
	public void removeArticle(int index) throws BLLException {
	    try {
	        daoArticles.delete(listArticles.get(index).getIdArticle());
	        listArticles.remove(index);
	    } catch (BLLException e) {
	        throw new BLLException("Echec de la suppression de l'article - ", e);
	    }
	}
	
	/**
	 * User validation
	 * @param u Utilisateur
	 * @throws BLLException
	 */
	public void validerUser(Article a) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(a==null){
			throw new BLLException("Article null");
		}
		//Les attributs d'un article sont obligatoires
		if(a.getNom_article()==null || a.getNom_article().trim().length()==0){
			sb.append("Le nom de l'article est obligatoire.\n");
			valide = false;
		}
		if(a.getDescription()==null || a.getDescription().trim().length()==0){
			sb.append("La description est obligatoire.\n");
			valide = false;
		}
		if(a.getDate_fin_encheres()==null || a.getDate_fin_encheres().trim().length()==0){
			sb.append("La date fin enchere est obligatoire.\n");
			valide = false;
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
}
