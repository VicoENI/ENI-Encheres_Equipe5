package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.encheres.exceptions.BLLException;
import fr.eni.encheres.exceptions.DALException;

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
	 * @throws DALException
	 */
	public EnchereManager() throws BLLException, DALException {
		//Instancier le Data Access Object
		daoEncheres = (EnchereDAO) new EnchereDAOJdbcImpl();
	
		//Charger le listUsers
		try {
			listEncheres = daoEncheres.getAllEncheres();
		} catch (Exception e) {
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
	 * Update an Enchere in the database
	 * @param enchere Enchere
	 * @throws BLLException
	 * @throws DALException
	 */
	public void Enchere(Enchere enchere) throws BLLException, DALException {
		Enchere existingEnchere;
		existingEnchere = daoEncheres.selectById(enchere.getId());
		if (existingEnchere==null){
			throw new BLLException("enchere inexistant.");
		}
		enchere.setId(existingEnchere.getId());
		try {
			validerEnchere(enchere);
			daoEncheres.update(enchere);
			
		} catch (BLLException e) {
			throw new BLLException("Echec updateEnchere-enchere:"+enchere, e);
		}
	}

	// fait pour valider echange. 
	public void validerEnchere(Enchere enchere) throws BLLException {
		if (enchere.getMontantEnchere() <= enchere.getArticleVendu().getPrixVente()) {
			throw new BLLException("Le montant de l'enchère doit être supérieur au prix de vente de l'article !");
		}
		try {
			daoEncheres.update(enchere);
		} catch (DALException e) {
			throw new BLLException("Erreur lors de la validation de l'enchère : " + enchere, e);
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
	 * @throws DALException
	 */
	public void removeEnchere(int index) throws BLLException, DALException {
		try {
			Enchere enchereToRemove = listEncheres.get(index);
			daoEncheres.deleteEnchereById(enchereToRemove.getId());
			listEncheres.remove(enchereToRemove);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de l'enchere - ", e);
		}
		
	}
	
}
