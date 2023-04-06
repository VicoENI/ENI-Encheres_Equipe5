package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.encheres.exceptions.BLLException;
import fr.eni.encheres.exceptions.DALException;

/**
 * Class managing the categories.
 * @author mkebeEni
 * @version 1.0
 */
public class CategorieManager {

	// Liste des catégories gérée par la classe CategorieManager
	private List<Categorie> listCategories;
	
	// Accès aux données des catégories
	private CategorieDAO daoCategories;
	

	/**
	 * Constructor of the CategorieManager class. Initializes the list of categories and the connection to the database.
	 * @throws BLLException
	 * @throws DALException
	 * @throws SQLException
	 */
	public CategorieManager() throws BLLException, DALException, SQLException {
		//Instancier le Data Access Object
		daoCategories = new CategorieDAOJdbcImpl();
		
		//Charger la liste des catégories
		try {
			listCategories = daoCategories.getAllCategories();
		} catch (Exception e) {
			throw new BLLException("Echec du chargement du listCategories - ", e);
		}
	}
	
	/**
	 * Get the list of categories
	 * @return List of categories
	 */
	public List<Categorie> getlistCategories() {
		return listCategories;
	}
	
	/**
	 * Add a new category to the list
	 * @param newCategorie Categorie
	 * @return The size of the list -1
	 * @throws BLLException 
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public int addCategorie(Categorie newCategorie) throws BLLException, DALException {
		Categorie categorie;
		try {
			categorie = daoCategories.getCategorieById(newCategorie.getNoCategorie());
		} catch (Exception e) {
			throw new BLLException("Echec selectById dans addcategorie", e);
		}
		if (categorie!= null){
			throw new BLLException("categorie deja existante.");
		}
		try {
			validerCategorie(newCategorie);
			daoCategories.createCategorie(newCategorie);
			listCategories.add(newCategorie);
		} catch (BLLException e) {
			throw new BLLException("Echec addCategorie", e);
		}
		return listCategories.size()-1;
	}
	
	/**
	 * Update a category
	 * @param categorie BLLException
	 * @throws BLLException 
	 * @throws DALException
	 */
	public void updateCategorie(Categorie categorie) throws BLLException, DALException {
		Categorie existingCategorie;
		try {
			existingCategorie = daoCategories.getCategorieById(categorie.getNoCategorie());
		} catch (Exception e) {
			throw new BLLException("Echec selectById dans updateCategorie", e);
		}
		if (existingCategorie==null){
			throw new BLLException("utilisateur inexistant.");
		}
		categorie.setCategorieId(existingCategorie.getNoCategorie());
		try {
			validerCategorie(categorie);
			daoCategories.updateCategorieById(categorie.getNoCategorie(), categorie.getLibelle());
			
		} catch (BLLException e) {
			throw new BLLException("Echec updateCategorie -categorie:"+categorie, e);
		}
	}
	
	
	/**
	 * Get a category by its index
	 * @param index int
	 * @return index of the category
	 */
	public Categorie getCategorie(int index) {
		return listCategories.get(index);
	}
	
	/**
	 * Delete a category by its index in the list
	 * @param index int
	 * @throws BLLException
	 * @throws SQLException
	 * @throws DALException
	 */
	public void removeCategorie(int index) throws BLLException, DALException, SQLException {
		try {
			daoCategories.deleteCategorieById(listCategories.get(index).getNoCategorie());
			listCategories.remove(index);
		} catch (Exception e) {
			throw new BLLException("Echec de la suppression de la catégorie - ", e);
		}
	}

	/**
	 * Validate a category
	 * @param categorie Categorie
	 * @throws BLLException
	 */
	private void validerCategorie(Categorie categorie) throws BLLException {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if (categorie.getLibelle().trim().length() < 3 || categorie.getLibelle().trim().length() > 30) {
			valide = false;
			sb.append("Le libellé doit contenir entre 3 et 30 caractères. ");
		}
		
		if (!valide) {
			throw new BLLException(sb.toString());
		}
	}



}
