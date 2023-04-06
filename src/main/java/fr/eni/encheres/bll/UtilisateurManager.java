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
		
		//Charger le listUtilisateurs
		try {
			listUtilisateurs = daoUtilisateurs.getUtilisateurs();
		} catch (Exception e) {
			throw new BLLException("Echec du chargement du listUtilisateur - ", e);
		}
	}
	
	/**
	 * Get the list of Utilisateur
	 * @return List of Utilisateur
	 */
	public List<Utilisateur> getlistUtilisateurs() {
		return listUtilisateurs;
	}
	
	/**
	 * Add Utilisateur in the database
	 * @param newUtilisateur Utilisateur
	 * @return index of the new Utilisateur in the database
	 * @throws BLLException
	 */
	public int addUtilisateur(Utilisateur newUtilisateur) throws BLLException {
		Utilisateur utilisateur;
		try {
			utilisateur = daoUtilisateurs.selectUtilisateurById(newUtilisateur);
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans addUtilisateur", e);
		}
		if (utilisateur!= null){
			throw new BLLException("Utilisateur deja existant.");
		}
		try {
			validerUtilisateur(newUtilisateur);
			daoUtilisateurs.addUtilisateur(newUtilisateur);
			listUtilisateurs.add(newUtilisateur);
		} catch (BLLException e) {
			throw new BLLException("Echec addUtilisateur", e);
		}
		return listUtilisateurs.size()-1;
	}
	
	
	/**
	 * Update Utilisateur in the database
	 * @param utilisateur Utilisateur
	 * @throws BLLException
	 */
	public void updateUtilisateur(Utilisateur utilisateur) throws BLLException {
		Utilisateur existingUtilisateur;
		try {
			existingUtilisateur = daoUtilisateurs.selectUtilisateurById(utilisateur.getNoUtilisateur());
		} catch (BLLException e) {
			throw new BLLException("Echec selectById dans updateUtilisateur", e);
		}
		if (existingUtilisateur==null){
			throw new BLLException("utilisateur inexistant.");
		}
		utilisateur.setNoUtilisateur(existingUtilisateur.getNoUtilisateur());
		try {
			validerUtilisateur(utilisateur);
			daoUtilisateurs.updateUtilisateur(utilisateur);
			
		} catch (BLLException e) {
			throw new BLLException("Echec updateUtilisateur-utilisateur:"+utilisateur, e);
		}
	}
	
	/**
	 * Extract an Utilisateur from the list of Utilisateurs
	 * @param index int
	 * @return Utilisateur
	 * @throws Exception
	 */
	public Utilisateur getUtilisateurs(int index) {
		return listUtilisateurs.get(index);
	}
	
	/**
	 * Delete an Utilisateur from the list of Utilisateurs
	 * @param index int
	 * @throws BLLException
	 */
	public void removeUtilisateur(int index) throws BLLException {
		try {
			daoUtilisateurs.deleteUtilisateur(listUtilisateurs.get(index).getNoUtilisateur());
			listUtilisateurs.remove(index);
		} catch (Exception e) {
			throw new BLLException("Echec de la suppression de l'utilisateur - ", e);
		}
		
	}
	
	/**
	 * Check Utilisateur data
	 * @param u Utilisateur
	 * @throws BLLException
	 */
	public void validerUtilisateur(Utilisateur u) throws BLLException
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
		if(u.getEmail()==null || u.getEmail().trim().length()==0){
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
		if(u.getCredit()==0 || u.getCredit().trim().length()==0){
			sb.append("Le crédit  est obligatoire.\n");
			valide = false;
		}
		if(u.getCodePostal()==null || u.getCodePostal().trim().length()==0){
			sb.append("Le code postal est obligatoire.\n");
			valide = false;
		}
		if(u.getRue()==null || u.getRue().trim().length()==0){
			sb.append("La rue est obligatoire.\n");
			valide = false;
		}
		if(u.getMotDePasse()==null || u.getMotDePasse().trim().length()==0){
			sb.append("Le mot de passe est obligatoire.\n");
			valide = false;
		}	
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}

}
