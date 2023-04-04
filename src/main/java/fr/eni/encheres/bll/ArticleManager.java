package fr.eni.encheres.bll;

import java.util.List;

public class ArticleManager {

	private List<Article> listArticles;
	private ArticleDAO daoArticles;

	public ArticleManager() throws BLLException, SQLException, DALException {
		daoArticles = DAOFactory.getArticleDAO();

		try {
			listArticles = daoArticles.selectAll();
		} catch (DALException e) {
			throw new DALException("Echec du chargement de la liste des articles - ", e);
		}
	}

	public List<Article> getListArticles() {
		return listArticles;
	}

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
