package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.exceptions.DALException;

/**
 * Interface in charge of managing the RETRAITS table
 * @author VicoENI
 * @version 1.0
 */
public interface RetraitDAO {

    // Méthodes CRUD

    /**
     * Method in charge of creating a new withdrawal in the database
     * @param retrait Retrait
     * @throws DALException
     */
    void createRetrait(Retrait retrait) throws DALException;
    
    /**
     * Method in charge of retrieving all the withdrawals from the database
     * @return List of withdrawals
     * @throws DALException
     */
    List<Retrait> getAllRetraits() throws DALException;
    
    /**
     * Method in charge of retrieving a withdrawal from the database by its number
     * @param noArticle int
     * @return List of withdrawals
     * @throws DALException
     */
    List<Retrait> getRetraitsByArticle(int noArticle) throws DALException;
    
    /**
     * Method in charge of updating a withdrawal in the database
     * @param retrait Retrait
     * @throws DALException
     */
    void updateRetrait(Retrait retrait) throws DALException;
    
    /**
     * Method in charge of deleting a withdrawal from the database
     * @param noArticle int
     * @throws DALException
     */
    void deleteRetrait(int noArticle) throws DALException;
    
}
