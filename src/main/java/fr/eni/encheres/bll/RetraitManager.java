package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.dal.jdbc.RetraitDAOJdbcImpl;
import fr.eni.encheres.exceptions.BLLException;

/**
 * Class managing the Retrait.
 * @author mkebeEni
 * @version 1.0
 */
public class RetraitManager {

	// Liste des Retrait gérée par la classe RetraitManager
	private List<Retrait> listRetraits;
	
	// Accès aux données des Retrait
	private RetraitDAO daoRetraits;
	

	/**
	 * Constructor of the RetraitManager class. Initializes the list of Retrait and the connection to the database.
	 * @throws BLLException
	 */
	public RetraitManager() throws BLLException {
			//Instancier le Data Access Object
		daoRetraits = (RetraitDAO) new RetraitDAOJdbcImpl();
		
		//Charger la liste des catégories
		try {
			listRetraits = daoRetraits.getAllRetraits();
		} catch (Exception e) {
			throw new BLLException("Echec du chargement du listRetraits - ", e);
		}
	}
	
	/**
	 * Get the list of Retrait
	 * @return List of Retrait
	 */
	public List<Retrait> getlistRetraits() {
		return listRetraits;
	}
	
	/**
	 * Add a new Retrait in the database
	 * @param newRetrait Retrait
	 * @return index of new retrait
	 * @throws BLLException 
	 */
	public int addRetrait(Retrait newRetrait) throws BLLException {
		Retrait retrait;
		try {
			retrait = daoRetraits.selectRetraitById(newRetrait.getId());
		} catch (Exception e) {
			throw new BLLException("Echec selectById dans addRetrait", e);
		}
		if (retrait!= null){
			throw new BLLException("retrait deja existante.");
		}
		try {
			daoRetraits.createRetrait(newRetrait);
			listRetraits.add(newRetrait);
		} catch (BLLException e) {
			throw new BLLException("Echec addRetrait", e);
		}
		return listRetraits.size()-1;
		}
	
	/**
	 * Update a retrait in the database
	 * @param retrait Retrait
	 * @throws BLLException
	 */
	public void updateRetraitById(Retrait retrait) throws BLLException {
		Retrait existingRetrait;
		try {
			existingRetrait = daoRetraits.selectRetraitById(retrait.getId());
		} catch (Exception e) {
			throw new BLLException("Echec selectById dans updateCategorie", e);
		}
		if (existingRetrait==null){
			throw new BLLException("retrait inexistant.");
		}
		retrait.setId(existingRetrait.getId());
		try {
			daoRetraits.updateRetrait(retrait);
			
		} catch (BLLException e) {
			throw new BLLException("Echec updateRetrait -retrait:"+retrait, e);
		}
	}
	
	/**
	 * Extract retrait from the database
	 * @param index int
	 * @return Retrait
	 */
	public Retrait getRetrait(int index) {
		return listRetraits.get(index);
	}
	
	/**
	 * Delete a retrait from the database
	 * @param index int
	 * @throws BLLException
	 */
	public void removeRetrait(int index) throws BLLException {
		try {
			daoRetraits.deleteRetrait(listRetraits.get(index).getId());
			listRetraits.remove(index);
		} catch (Exception e) {
			throw new BLLException("Echec de la suppression du retrait - ", e);
		}
		
	}

}
