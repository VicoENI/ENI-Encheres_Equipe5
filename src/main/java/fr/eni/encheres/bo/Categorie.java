package fr.eni.encheres.bo;

//import java.sql.Date;
//import java.util.Date;

public class Categorie {

    private int noCategorie;
    private String libelle;
 
        
    // Constructeur par défaut
    public Categorie() {}

    // Constructeur avec paramètres
    public Categorie(String libelle) {
        setLibelle(libelle);
    }
    
    // Getters et setters
    public int getNoCategorie() {
        return noCategorie;
    }
    
    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    // Méthodes utilitaires
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Categories [noCategorie=");
        sb.append(noCategorie);
        sb.append(", libelle=");
        sb.append(libelle);
        sb.append("]");
        return sb.toString();
    }

    public void setCategorieId(int noCategorie2) {
    }
    
}
