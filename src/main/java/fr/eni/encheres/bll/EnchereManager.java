package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

/**
 * Class managing the Enchere.
 * @author mkebeEni
 * @version 1.0
 */
public class EnchereManager {

	// Liste des Enchere gérée par la classe EnchereManager
	private List<Enchere> listEncheres;
	
	// Accès aux données des Enchere
	private EnchereDAO daoEncheres;
	

	/**
	 * Constructor of the EnchereManager class. Initializes the list of Enchere and the connection to the database.
	 * @throws BLLException
	 */
	public EnchereManager() throws BLLException {
		//Instancier le Data Access Object
		daoEncheres =DAOFactory.getEnchereDAO();
	
		//Charger le listUsers
		try {
			listEncheres = daoEncheres.selectAll();
		} catch (BLLException e) {
			throw new BLLException("Echec du chargement du listEnchere - ", e);
		}
	}
	
	/**
	 * Get the list of Enchere
	 * @return List of Enchere
	 */
	public List<Enchere> getlistEncheres() {
		return listEncheres;
	}
	
	/**
	 * Add user in the database
	 * @param newEnchere Utilisateur
	 * @return index du nouvel utilisateur dans la base de données
	 * @throws BLLException 
	 */
	public int Enchere(Utilisateur newEnchere) throws BLLException {
		Enchere enchere;
		try {
			enchere = daoEncheres.selectById(newEnchere);
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans addEnchere", e);
		}
		if (enchere!= null){
			throw new BLLException("Enchere deja existant.");
		}
		try {
			validerEnchere(newEnchere);
			daoEncheres.insert(newEnchere);
			listEncheres.add(newEnchere);
		} catch (BLLException e) {
			throw new BLLException("Echec addEnchere", e);
		}
		return listEncheres.size()-1;
	}
	
	/**
	 * Update an Enchere in the database
	 * @param enchere Enchere
	 * @throws BLLException
	 */
	public void Enchere(Enchere enchere) throws BLLException {
		Enchere existingEnchere;
		try {
			existingEnchere = daoEncheres.selectById(enchere.getIdEnchere());
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans updateEnchere", e);
		}
		if (existingEnchere==null){
			throw new BLLException("enchere inexistant.");
		}
		enchere.setIdEnchere(existingEnchere.getIdEnchere());
		try {
			validerEnchere(enchere);
			daoEncheres.update(enchere);
			
		} catch (BLLException e) {
			throw new BLLException("Echec updateEnchere-enchere:"+enchere, e);
		}
	}
	
	/**
	 * Get Enchere where position in list is the index
	 * @param index int
	 * @return enchere Enchere
	 */
	public Enchere getEnchere(int index) {
		return listEncheres.get(index);
	}
	
	/**
	 * Delete an Enchere in the database
	 * @param index int
	 * @throws BLLException
	 */
	public void removeEnchere(int index) throws BLLException {
		try {
			daoEncheres.delete(listEncheres.get(index).getIdEnchere());
			listEncheres.remove(index);
		} catch (BLLException e) {
			throw new BLLException("Echec de la suppression de l'enchere - ", e);
		}
		
	}
	
}
