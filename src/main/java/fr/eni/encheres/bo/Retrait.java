/**
This class represents a Retrait object, which contains information about a withdrawal for an auction.
*/
package fr.eni.encheres.bo;

import java.util.Date;

public class Retrait {
    
    private int id;
    private int noArticle;
    private String rue;
    private String codePostal;
    private String ville;
    private Article article;
    /**
    * Default constructor for Retrait objects.
    */
    // Constructeur par défaut
    public Retrait() {}
    
    /**
    * Parameterized constructor for Retrait objects.
    * @param id id int 
    * @param idUtilisateur id utilisateur int 
    * @param idEnchere id enchere int
    * @param rue rue string
    * @param codePostal postal code string
    * @param ville ville string
    * @param dateRetrait dateRetrait date
    * @param article article object 
    */
    // Constructeur avec paramètres
    public Retrait(String rue, String codePostal, String ville, Article article) {
        //setId(idEnchere);
        setRue(rue);
        setCodePostal(codePostal);
        setVille(ville);
    }

    

	/**
    * Getter method for the id attribute.
    * @return The id of the Retrait object.
    */
    // Getters et setters
    
    public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
    public int getId() {
        return id;
    }

    /**
    * Setter method for the id attribute.
    * @param id The new id of the Retrait object.
    */
    public void setId(int id) {
        this.id = id;
    }
    
   
   

    /**
    * Getter method for the rue attribute.
    * @return The street name of the address where the item was withdrawn.
    */
    public String getRue() {
        return rue;
    }
     
    /**
    * Setter method for the rue attribute.
    * @param rue The new street name of the address where the item was withdrawn.
    */
    public void setRue(String rue) {
        this.rue = rue;
    }
    
    /**
    * Getter method for the CodePostal attribute.
    * @return The  name of the address where the item was withdrawn.
    */
    public String getCodePostal() {
        return codePostal;
    }
    
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    public String getVille() {
        return ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Retrait [getNoArticle()=");
		builder.append(getNoArticle());
		builder.append(", getArticle()=");
		builder.append(getArticle());
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getRue()=");
		builder.append(getRue());
		builder.append(", getCodePostal()=");
		builder.append(getCodePostal());
		builder.append(", getVille()=");
		builder.append(getVille());
		builder.append("]");
		return builder.toString();
	}
    
}
