package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.exceptions.DALException;

/**
 * Interface in charge of managing the CATEGORIES table
 * @author VicoENI
 * @version 1.0
 */
public interface CategorieDAO {

    // Méthodes CRUD

    /**
     * Method in charge of creating a new category in the database
     * @param categorie Categorie
     * @throws DALException
     */
    void createCategorie(Categorie categorie) throws DALException;
    
    /**
     * Method in charge of retrieving all the categories from the database
     * @return List of categories
     * @throws DALException
     */
    List<Categorie> getAllCategories() throws DALException;
    
    /**
     * Method in charge of retrieving a category from the database by its number
     * @param noCategorie int
     * @return categorie Categorie
     * @throws DALException
     */
    Categorie getCategorieByNo(int noCategorie) throws DALException;
    
    /**
     * Method in charge of updating a category in the database
     * @param categorie Categorie
     * @throws DALException
     */
    void updateCategorie(Categorie categorie) throws DALException;
    
    /**
     * Method in charge of deleting a category from the database
     * @param noCategorie int
     * @throws DALException
     */
    void deleteCategorie(int noCategorie) throws DALException;
    
}
