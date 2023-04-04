package fr.eni.encheres.bll;

import java.util.List;

public class ArticleManager {

	// Liste des articles gérée par la classe ArticleManager
	private List<Article> listArticles;
	
	// Accès aux données des articles
	private ArticleDAO daoArticles;
	
	
	/**
	 * Constructor of the ArticleManager class. Initializes the list of articles and the connection to the database.
	 * 
	 * @throws BLLException if a business error occurs
	 * @throws SQLException if a SQL error occurs
	 * @throws DALException if a data access error occurs
	 */
	public ArticleManager() throws BLLException, SQLException, DALException {
		daoArticles = DAOFactory.getArticleDAO();

		try {
			listArticles = daoArticles.selectAll();
		} catch (DALException e) {
			throw new DALException("Echec du chargement de la liste des articles - ", e);
		}
	}

	
	/**
	 * Retourne la liste des articles gérée par la classe ArticleManager.
	 * 
	 * @return la liste des articles
	 */
	public List<Article> getListArticles() {
		return listArticles;
	}

	
	/**
	 * Adds a new article to the list of articles managed by the ArticleManager class.
	 * 
	 * @param newArticle the article to add
	 * @return the position of the article in the list
	 * @throws BLLException if a business error occurs
	 * @throws SQLException if a SQL error occurs
	 * @throws DALException if a data access error occurs
	 */
	public int addArticle(Article newArticle) throws BLLException, SQLException, DALException {
		Article article;
		try {
			article = daoArticles.selectById(newArticle);
		} catch (DALException e) {
			throw new DALException("Echec selectById dans addArticle", e);
		}
		if (article != null) {
			throw new BLLException("Article déjà existant.");
		}
		try {
			validerArticle(newArticle);
			daoArticles.insert(newArticle);
			listArticles.add(newArticle);
		} catch (DALException e) {
			throw new BLLException("Echec addArticle", e);
		}
		return listArticles.size() - 1;
	}
	
	
	/**
	 * Met à jour un article dans la liste d'articles gérée par la classe ArticleManager.
	 * 
	 * @param article l'article à mettre à jour
	 * @throws BLLException si une erreur métier survient
	 * @throws SQLException si une erreur SQL survient
	 * @throws DALException si une erreur d'accès aux données survient
	 */
	public void updateArticle(Article article) throws BLLException, SQLException, DALException {
		Article existingArticle;
		try {
			existingArticle = daoArticles.selectById(article.getIdArticle());
		} catch (DALException e) {
			throw new DALException("Echec selectById dans updateArticle", e);
		}
		if (existingArticle == null) {
			throw new BLLException("Article inexistant.");
		}
		article.setIdArticle(existingArticle.getIdArticle());
		try {
			validerArticle(article);
			daoArticles.update(article);
		} catch (DALException e) {
			throw new BLLException("Echec updateArticle - Article:" + article, e);
		}
	}
	
	public Article getArticle(int index) {
		return listArticles.get(index);
	}
	
	public void removeArticle(int index) throws BLLException, SQLException, DALException{
	    try {
	        daoArticles.delete(listArticles.get(index).getIdArticle());
	        listArticles.remove(index);
	    } catch (DALException e) {
	        throw new DALException("Echec de la suppression de l'article - ", e);
	    }
	}
	
	public void validerUser(Utilisateur u) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(u==null){
			throw new BLLException("Utilisateur null");
		}
		//Les attributs d'un utilisateur sont obligatoires
		if(u.getNom_article()==null || u.getNom_article().trim().length()==0){
			sb.append("Le nom de l'article est obligatoire.\n");
			valide = false;
		}
		if(u.getDescription()==null || u.getDescription().trim().length()==0){
			sb.append("La description est obligatoire.\n");
			valide = false;
		}
		if(u.getDate_fin_encheres()==null || u.getDate_fin_encheres().trim().length()==0){
			sb.append("Le nom  est obligatoire.\n");
			valide = false;
		}
		if(u.getPrenom()==null || u.getPrenom().trim().length()==0){
			sb.append("Le prénom  est obligatoire.\n");
			valide = false;
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
}
