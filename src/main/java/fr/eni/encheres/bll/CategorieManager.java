package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

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
	 */
	public CategorieManager() throws BLLException {
		//Instancier le Data Access Object
		daoCategories =DAOFactory.getCategorieDAO();
		
		//Charger la liste des catégories
		try {
			listCategories = daoCategories.selectAll();
		} catch (BLLException e) {
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
	public int addCategorie(Categorie newCategorie) throws BLLException {
		Categorie categorie;
		try {
			categorie = daoCategories.selectById(newCategorie);
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans addcategorie", e);
		}
		if (categorie!= null){
			throw new BLLException("categorie deja existante.");
		}
		try {
			validerCategorie(newCategorie);
			daoCategories.insert(newCategorie);
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
	 */
	public void updateCategorie(Categorie categorie) throws BLLException {
		Categorie existingCategorie;
		try {
			existingCategorie = daoCategories.selectById(categorie.getIdCategorie());
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans updateCategorie", e);
		}
		if (existingCategorie==null){
			throw new BLLException("utilisateur inexistant.");
		}
		categorie.setIdCategorie(existingCategorie.getIdCategorie());
		try {
			validerCategorie(categorie);
			daoCategories.update(categorie);
			
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
	 */
	public void removeCategorie(int index) throws BLLException {
		try {
			daoCategories.delete(listCategories.get(index).getIdCategorie());
			listCategories.remove(index);
		} catch (BLLException e) {
			throw new BLLException("Echec de la suppression de la catégorie - ", e);
		}
		
	}
}
