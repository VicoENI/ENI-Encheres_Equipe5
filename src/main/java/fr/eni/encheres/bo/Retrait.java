/**
This class represents a Retrait object, which contains information about a withdrawal for an auction.
*/
package fr.eni.encheres.bo;

import java.util.Date;

public class Retrait {
    
    private int id;
    private int idUtilisateur;
    private int idEnchere;
    private String rue;
    private String codePostal;
    private String ville;
    private Date dateRetrait;
    private Object article;
    private Object noRetrait;
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
    public Retrait(int id, int idUtilisateur, int idEnchere, String rue, String codePostal, String ville, Date dateRetrait, Article article) {
        //setId(idEnchere);
    
        setIdUtilisateur(idUtilisateur);
        setIdEnchere(idEnchere);
        setRue(rue);
        setCodePostal(codePostal);
        setVille(ville);
        setDateRetrait(dateRetrait);
    }

    /**
    * Getter method for the id attribute.
    * @return The id of the Retrait object.
    */
    // Getters et setters
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
    * Getter method for the idUtilisateur attribute.
    * @return The id of the user who withdrew the auction item.
    */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    
    /**
    * Setter method for the idUtilisateur attribute.
    * @param idUtilisateur The new id of the user who withdrew the auction item.
    */
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    /**
    * Getter method for the idEnchere attribute.
    * @return The id of the auction from which the item was withdrawn.
    */
    public int getIdEnchere() {
        return idEnchere;
    }
    
    /**
    * Setter method for the idEnchere attribute.
    * @param idEnchere The new id of the auction from which the item was withdrawn.
    */
    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
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
    
    public Date getDateRetrait() {
        return dateRetrait;
    }
    
    public void setDateRetrait(Date dateRetrait) {
        this.dateRetrait = dateRetrait;
    }
    
    // Méthodes utilitaires
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Retraits [noRetrait=");
        sb.append(noRetrait);//verifier si cela est bon 
        sb.append(", rue=");
        sb.append(rue);
        sb.append(", codePostal=");
        sb.append(codePostal);
        sb.append(", ville=");
        sb.append(ville);
        sb.append(", article=");
        sb.append(article);// verofoer si cela est bon 
        sb.append("]");
        return sb.toString();
    }
    
}
