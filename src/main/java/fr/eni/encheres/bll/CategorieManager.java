package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

public class CategorieManager {

private List<Categorie> listCategories;
	
	private CategorieDAO daoCategories;
	

	public CategorieManager() throws BLLException, SQLException, DALException{
			//Instancier le Data Access Object
		daoCategories =DAOFactory.getCategorieDAO();
		
		 	//Charger la liste des catégories
			try {
				listCategories = daoCategories.selectAll();
			} catch (DALException e) {
				throw new DALException("Echec du chargement du listCategories - ", e);
			}
	}
	
	
	public List<Categorie> getlistCategories() {
		return listCategories;
	}
	
	/**
	 * Ajout d'une catégorie à la liste
	 * @param newUser
	 * @return index de la nouvelle catégorie dans la base de données
	 * @throws BLLException 
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public int addCategorie(Categorie newCategorie) throws BLLException, SQLException, DALException {
		Categorie categorie;
		try {
			categorie = daoCategories.selectById(newCategorie);
		} catch (DALException e) {
			throw new DALException("Echec selectById dans addcategorie", e);
		}
		if (categorie!= null){
//		if(newUser.getIdutilisateuricle()!=null){
			throw new BLLException("categorie deja existante.");
		}
		try {
			validerCategorie(newCategorie);
			daoCategories.insert(newCategorie);
			listCategories.add(newCategorie);
		} catch (DALException e) {
			throw new BLLException("Echec addCategorie", e);
		}
		return listCategories.size()-1;
	}
	
	/**
	 * updateCategorie : Modifier une categorie de la base
	 * @param categorie BLLException
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public void updateCategorie(Categorie categorie) throws BLLException, SQLException, DALException{
		Categorie existingCategorie;
		try {
			existingCategorie = daoCategories.selectById(categorie.getIdCategorie());
		} catch (DALException e) {
			throw new DALException("Echec selectById dans updateCategorie", e);
		}
		if (existingCategorie==null){
			throw new BLLException("utilisateur inexistant.");
		}
		categorie.setIdCategorie(existingCategorie.getIdCategorie());
		try {
			validerCategorie(categorie);
			daoCategories.update(categorie);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateCategorie -categorie:"+categorie, e);
		}
	}
	
	
	/**
	 * extraire une catégorie de la base de données
	 * @param ref
	 * @return
	 * @throws Exception
	 */
	public Categorie getCategorie(int index) {
		return listCategories.get(index);
	}
	
	/**
	 * Supprimer une catégorie de la liste
	 * @param index
	 * @throws BLLException
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public void removeCategorie(int index) throws BLLException, SQLException, DALException{
		try {
			daoCategories.delete(listCategories.get(index).getIdCategorie());
			listCategories.remove(index);
		} catch (DALException e) {
			throw new DALException("Echec de la suppression de la catégorie - ", e);
		}
		
	}
}
