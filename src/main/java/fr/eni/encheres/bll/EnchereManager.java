package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

public class EnchereManager {

private List<Enchere> listEncheres;
	
	private EnchereDAO daoEncheres;
	

	public EnchereManager() throws BLLException, SQLException, DALException{
			//Instancier le Data Access Object
		daoEncheres =DAOFactory.getEnchereDAO();
		
		 	//Charger le listUsers
			try {
				listEncheres = daoEncheres.selectAll();
			} catch (DALException e) {
				throw new DALException("Echec du chargement du listEnchere - ", e);
			}
	}
	
	
	public List<Enchere> getlistEncheres() {
		return listEncheres;
	}
	
	/**
	 * Ajout d'un utilisateur à la base de données
	 * @param newUser
	 * @return index du nouvel utilisateur dans la base de données
	 * @throws BLLException 
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public int Enchere(Utilisateur newEnchere) throws BLLException, SQLException, DALException {
		Enchere enchere;
		try {
			enchere = daoEncheres.selectById(newEnchere);
		} catch (DALException e) {
			throw new DALException("Echec selectById dans addEnchere", e);
		}
		if (enchere!= null){
			throw new BLLException("Enchere deja existant.");
		}
		try {
			validerEnchere(newEnchere);
			daoEncheres.insert(newEnchere);
			listEncheres.add(newEnchere);
		} catch (DALException e) {
			throw new BLLException("Echec addEnchere", e);
		}
		return listEncheres.size()-1;
	}
	
	/**
	 * updateUtilisateur : Modifier un utilisateur de la base
	 * @param utilisateur
	 * @throws BLLException
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public void Enchere(Enchere enchere) throws BLLException, SQLException, DALException{
		Enchere existingEnchere;
		try {
			existingEnchere = daoEncheres.selectById(enchere.getIdEnchere());
		} catch (DALException e) {
			throw new DALException("Echec selectById dans updateEnchere", e);
		}
		if (existingEnchere==null){
			throw new BLLException("enchere inexistant.");
		}
		enchere.setIdEnchere(existingEnchere.getIdEnchere());
		try {
			validerEnchere(enchere);
			daoEncheres.update(enchere);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateEnchere-enchere:"+enchere, e);
		}
	}
	
	/**
	 * extraire un utilisateur de la base de données
	 * @param ref
	 * @return
	 * @throws Exception
	 */
	public Enchere getEnchere(int index) {
		return listEncheres.get(index);
	}
	
	/**
	 * Supprimer un article du catalogue
	 * @param index
	 * @throws BLLException
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public void removeEnchere(int index) throws BLLException, SQLException, DALException{
		try {
			daoEncheres.delete(listEncheres.get(index).getIdEnchere());
			listEncheres.remove(index);
		} catch (DALException e) {
			throw new DALException("Echec de la suppression de l'enchere - ", e);
		}
		
	}
	
}
