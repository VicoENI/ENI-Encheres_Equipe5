package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

/**
 * Class managing the Retrait.
 * @author mkebeEni
 * @version 1.0
 */
public class RetraitManager {

	// Liste des Retrait gérée par la classe RetraitManager
	private List<Categorie> listRetraits;
	
	// Accès aux données des Retrait
	private RetraitDAO daoRetraits;
	

	/**
	 * Constructor of the RetraitManager class. Initializes the list of Retrait and the connection to the database.
	 * @throws BLLException
	 */
	public RetraitManager() throws BLLException {
			//Instancier le Data Access Object
		daoRetraits =DAOFactory.getRetraitDAO();
		
		//Charger la liste des catégories
		try {
			listRetraits = daoRetraits.selectAll();
		} catch (BLLException e) {
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
			retrait = daoRetraits.selectById(newRetrait);
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans addRetrait", e);
		}
		if (retrait!= null){
			throw new BLLException("retrait deja existante.");
		}
		try {
			validerRetrait(newRetrait);
			daoRetraits.insert(newRetrait);
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
	public void updateRetrait(Retrait retrait) throws BLLException {
		Retrait existingRetrait;
		try {
			existingRetrait = daoRetraits.selectById(retrait.getIdRetrait());
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans updateCategorie", e);
		}
		if (existingRetrait==null){
			throw new BLLException("retrait inexistant.");
		}
		retrait.setIdRetrait(existingRetrait.getIdRetrait());
		try {
			validerRetrait(retrait);
			daoRetraits.update(retrait);
			
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
			daoRetraits.delete(listRetraits.get(index).getIdRetrait());
			listRetraits.remove(index);
		} catch (BLLException e) {
			throw new BLLException("Echec de la suppression du retrait - ", e);
		}
		
	}

}
