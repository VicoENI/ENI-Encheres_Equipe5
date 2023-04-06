package fr.eni.encheres.bo;

import java.util.Date;

/**
 * Class representing an article.
 * @author BaptistePoupet
 * @version 1.0
 */
public class Article {
    // Attributs
    private int         noArticle;
    private String      nomArticle; 
    private String      description;
    private Date        dateDebutEncheres;
    private Date        dateFinEncheres;
    private int         prixInitial;
    private int         prixVente;
    private int utilisateur;
    private int   categorie;

    // Constructeurs
    /**
     * Default constructor.
     */
    public Article() {}
    
    /**
     * Constructor with all attributes.
     * @param noArticle int
     * @param nomArticle String
     * @param description String
     * @param dateDebutEncheres Date
     * @param dateFinEncheres Date
     * @param prixInitial int
     * @param prixVente int
     * @param utilisateur Utilisateur
     * @param categorie Categorie
     */
    public Article(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
                    Date dateFinEncheres, int prixInitial, int prixVente, int utilisateur,
                    int categorie) {
                
        setNoArticle(noArticle);
        setNomArticle(nomArticle);
        setDescription(description);
        setDateDebutEncheres(dateDebutEncheres);
        setDateFinEncheres(dateFinEncheres);
        setPrixInitial(prixInitial);
        setPrixVente(prixVente);
        setUtilisateur(utilisateur);
        setCategorie(categorie);

    }

    // Getters et setters
    /**
     * Returns the article number.
     * @return noArticle int
     */
    public int getNoArticle() {
        return noArticle;
    }

    /**
     * Sets the article number.
     * @param noArticle int
     */
    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    /**
     * Returns the article name.
     * @return nomArticle String
     */
    public String getNomArticle() {
        return nomArticle;
    }

    /**
     * Sets the article name.
     * @param nomArticle String
     */
    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    /**
     * Returns the article description.
     * @return description String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the article description.
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the article start date.
     * @return dateDebutEncheres Date
     */
    public Date getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    /**
     * Sets the article start date.
     * @param date Date
     */
    public void setDateDebutEncheres(Date date) {
        this.dateDebutEncheres = date;
    }

    /**
     * Returns the article end date.
     * @return dateFinEncheres Date
     */
    public Date getDateFinEncheres() {
        return dateFinEncheres;
    }

    /**
     * Sets the article end date.
     * @param dateFinEncheres Date
     */
    public void setDateFinEncheres(Date dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    /**
     * Returns the article initial price.
     * @return prixInitial int
     */
    public int getPrixInitial() {
        return prixInitial;
    }

    /**
     * Sets the article initial price.
     * @param prixInitial int
     */
    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    /**
     * Returns the article selling price.
     * @return prixVente int
     */
    public int getPrixVente() {
        return prixVente;
    }

    /**
     * Sets the article selling price.
     * @param prixVente int
     */
    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    /**
     * Returns the article owner.
     * @return utilisateur Utilisateur
     */
    public int getUtilisateur() {
        return utilisateur;
    }

    /**
     * Sets the article owner.
     * @param i Utilisateur
     */
    public void setUtilisateur(int i) {
        this.utilisateur = i;
    }

    /**
     * Returns the article category.
     * @return categorie Categorie
     */
    public int getCategorie() {
        return categorie;
    }

    /**
     * Sets the article category.
     * @param i Categorie
     */
    public void setCategorie(int i) {
        this.categorie = i;
    }

    /**
     * Returns the article as a string.
     */
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Article [getNoArticle()=");
		builder.append(getNoArticle());
		builder.append(", getNomArticle()=");
		builder.append(getNomArticle());
		builder.append(", getDescription()=");
		builder.append(getDescription());
		builder.append(", getDateDebutEncheres()=");
		builder.append(getDateDebutEncheres());
		builder.append(", getDateFinEncheres()=");
		builder.append(getDateFinEncheres());
		builder.append(", getPrixInitial()=");
		builder.append(getPrixInitial());
		builder.append(", getPrixVente()=");
		builder.append(getPrixVente());
		builder.append(", getUtilisateur()=");
		builder.append(getUtilisateur());
		builder.append(", getCategorie()=");
		builder.append(getCategorie());
		builder.append("]");
		return builder.toString();
	}
}