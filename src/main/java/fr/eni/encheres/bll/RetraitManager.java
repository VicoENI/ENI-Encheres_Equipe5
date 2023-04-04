package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

public class RetraitManager {

private List<Categorie> listRetraits;
	
	private RetraitDAO daoRetraits;
	

	public RetraitManager() throws BLLException, SQLException, DALException{
			//Instancier le Data Access Object
		daoRetraits =DAOFactory.getRetraitDAO();
		
		 	//Charger la liste des catégories
			try {
				lisRetraits = daoRetraits.selectAll();
			} catch (DALException e) {
				throw new DALException("Echec du chargement du listRetraits - ", e);
			}
	}
	
	
	public List<Retrait> getlistRetraits() {
		return listRetraits;
	}
	
	/**
	 * Add a new withdrawal
	 * @param newWithdrawal
	 * @return index of new withdrawal
	 * @throws BLLException 
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public int addRetrait(Retrait newRetrait) throws BLLException, SQLException, DALException {
		Retrait retrait;
		try {
			retrait = daoRetraits.selectById(newRetrait);
		} catch (DALException e) {
			throw new DALException("Echec selectById dans addRetrait", e);
		}
		if (retrait!= null){
//		if(newUser.getIdutilisateuricle()!=null){
			throw new BLLException("retrait deja existante.");
		}
		try {
			validerRetrait(newRetrait);
			daoRetraits.insert(newRetrait);
			listRetraits.add(newRetrait);
		} catch (DALException e) {
			throw new BLLException("Echec addRetrait", e);
		}
		return listRetraits.size()-1;
		}
	
	/**
	 * updateRetrait : update a withdrawal
	 * @param retrait BLLException
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public void updateRetrait(Retrait retrait) rows BLLException, SQLException, DALException{
		Retrait existingRetrait;
		try {
			existingRetrait = daoRetraits.selectById(retrait.getIdRetrait());
		} catch (DALException e) {
			throw new DALException("Echec selectById dans updateCategorie", e);
		}
		if (existingRetrait==null){
			throw new BLLException("retrait inexistant.");
		}
		retrait.setIdRetrait(existingRetrait.getIdRetrait());
		try {
			validerRetrait(retrait);
			daoRetraits.update(retrait);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateRetrait -retrait:"+retrait, e);
		}
	}
	
	/**
	 * extract a withdrawal from the database
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public Retrait getRetrait(int index) {
		return listRetraits.get(index);
	}
	
	/**
	 * delete a withdrawal from the database
	 * @param index
	 * @throws BLLException
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public void removeRetrait(int index) throws BLLException, SQLException, DALException{
		try {
			daoRetraits.delete(listRetraits.get(index).getIdRetrait());
			listRetraits.remove(index);
		} catch (DALException e) {
			throw new DALException("Echec de la suppression du retrait - ", e);
		}
		
	}

}
