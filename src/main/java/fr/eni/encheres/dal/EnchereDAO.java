package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.exceptions.DALException;

/**
 * Interface in charge of managing the ENCHERES table
 * @author VicoENI
 * @version 1.0
 */
public interface EnchereDAO {

    // Méthodes CRUD

    /**
     * Method in charge of creating a new bid in the database
     * @param enchere Enchere
     * @throws DALException
     */
    void createEnchere(Enchere enchere) throws DALException;
    
    /**
     * Method in charge of retrieving all the bids from the database
     * @return List of bids
     * @throws DALException
     */
    List<Enchere> getAllEncheres() throws DALException;
    
    /**
     * Method in charge of retrieving a bid from the database by its number
     * @param noArticle int
     * @return List of bids
     * @throws DALException
     */
    List<Enchere> getEncheresByNoArticle(int noArticle) throws DALException;

    public Enchere selectById(int id) throws DALException;

    public void update(Enchere enchere) throws DALException;

    public void deleteEnchereById(int id) throws DALException;
}
