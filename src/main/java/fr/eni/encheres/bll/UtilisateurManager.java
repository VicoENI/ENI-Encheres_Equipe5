package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJdbcImpl;
import fr.eni.encheres.exceptions.BLLException;

/**
 * Class managing the Utilisateur.
 * @author mkebeEni
 * @version 1.0
 */
public class UtilisateurManager {

	// Liste des Utilisateur gérée par la classe UtilisateurManager
	private List<Utilisateur> listUtilisateurs;
	
	// Accès aux données des Utilisateur
	private UtilisateurDAO daoUtilisateurs;
	

	/**
	 * Constructor of the UtilisateurManager class. Initializes the list of Utilisateur and the connection to the database.
	 * @throws BLLException
	 */
	public UtilisateurManager() throws BLLException {
		//Instancier le Data Access Object
		daoUtilisateurs = (UtilisateurDAO) new UtilisateurDAOJdbcImpl();
		
		//Charger le listUsers
		try {
			listUtilisateurs = daoUtilisateurs.selectAll();
		} catch (Exception e) {
			throw new BLLException("Echec du chargement du listUsers - ", e);
		}
	}
	
	/**
	 * Get the list of Utilisateur
	 * @return List of Utilisateur
	 */
	public List<Utilisateur> getlistUsers() {
		return listUsers;
	}
	
	/**
	 * Add user in the database
	 * @param newUser Utilisateur
	 * @return index of the new user in the database
	 * @throws BLLException
	 */
	public int addUser(Utilisateur newUser) throws BLLException {
		Utilisateur utilisateur;
		try {
			utilisateur = daoUtilisateurs.selectById(newUser);
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans addUser", e);
		}
		if (utilisateur!= null){
			throw new BLLException("Utilisateur deja existant.");
		}
		try {
			validerUser(newUser);
			daoUtilisateurs.insert(newUser);
			listUsers.add(newUser);
		} catch (BLLException e) {
			throw new BLLException("Echec addUser", e);
		}
		return listUsers.size()-1;
	}
	
	
	/**
	 * Update user in the database
	 * @param utilisateur Utilisateur
	 * @throws BLLException
	 */
	public void updateUser(Utilisateur utilisateur) throws BLLException {
		Utilisateur existingUtilisateur;
		try {
			existingUtilisateur = daoUtilisateurs.selectById(utilisateur.getIdUtilisateur());
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans updateUser", e);
		}
		if (existingUtilisateur==null){
			throw new BLLException("utilisateur inexistant.");
		}
		utilisateur.setIdUtilisateur(existingUtilisateur.getIdUtilisateur());
		try {
			validerUser(utilisateur);
			daoUtilisateurs.update(utilisateur);
			
		} catch (BLLException e) {
			throw new BLLException("Echec updateUtilisateur-utilisateur:"+utilisateur, e);
		}
	}
	
	/**
	 * Extract an user from the list of users
	 * @param index int
	 * @return Utilisateur
	 * @throws Exception
	 */
	public Utilisateur getUtilisateur(int index) {
		return listUsers.get(index);
	}
	
	/**
	 * Delete an user from the list of users
	 * @param index int
	 * @throws BLLException
	 */
	public void removeUser(int index) throws BLLException {
		try {
			daoUtilisateurs.delete(listUsers.get(index).getIdUtilisateur());
			listUsers.remove(index);
		} catch (BLLException e) {
			throw new BLLException("Echec de la suppression de l'utilisateur - ", e);
		}
		
	}
	
	/**
	 * Check user data
	 * @param u Utilisateur
	 * @throws BLLException
	 */
	public void validerUser(Utilisateur u) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(u==null){
			throw new BLLException("Utilisateur null");
		}
		//Les attributs d'un utilisateur sont obligatoires
		if(u.getPseudo()==null || u.getPseudo().trim().length()==0){
			sb.append("Le pseudo est obligatoire.\n");
			valide = false;
		}
		if(u.getMail()==null || u.getMail().trim().length()==0){
			sb.append("L'adresse mail est obligatoire.\n");
			valide = false;
		}
		if(u.getNom()==null || u.getNom().trim().length()==0){
			sb.append("Le nom  est obligatoire.\n");
			valide = false;
		}
		if(u.getPrenom()==null || u.getPrenom().trim().length()==0){
			sb.append("Le prénom  est obligatoire.\n");
			valide = false;
		}
		if(u.getVille()==null || u.getVille().trim().length()==0){
			sb.append("La ville est obligatoire.\n");
			valide = false;
		}
		if(u.getCredit()==null || u.getCredit().trim().length()==0){
			sb.append("Le crédit  est obligatoire.\n");
			valide = false;
		}
		if(u.getCode_postal()==null || u.getCode_postal().trim().length()==0){
			sb.append("Le code postal est obligatoire.\n");
			valide = false;
		}
		if(u.getRue()==null || u.getRue().trim().length()==0){
			sb.append("La rue est obligatoire.\n");
			valide = false;
		}
		if(u.getMot_de_passe()==null || u.getMot_de_passe().trim().length()==0){
			sb.append("Le mot de passe est obligatoire.\n");
			valide = false;
		}	
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}

}
