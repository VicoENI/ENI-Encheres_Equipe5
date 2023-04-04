package fr.eni.encheres.bo;

//import java.sql.Date;
import java.util.Date;

public class Enchere {

    private int id;
    private int idArticle;
    private int idUtilisateur;
    private Date dateEnchere;
    private int montantEnchere;
    
    // Constructeur par défaut
    public Enchere() {}
    
    // Constructeur avec paramètres
    public Enchere(int id, int idArticle, int idUtilisateur, Date dateEnchere, int montantEnchere) {
        setId(idUtilisateur);// problemes
        setIdUtilisateur(idUtilisateur);
        setIdArticle(idArticle);
        setDateEnchere(dateEnchere);
        setMontantEnchere(montantEnchere);
    }
    // Getters et setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdArticle() {
        return idArticle;
    }
    
    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }
    
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    public Date getDateEnchere() {
        return dateEnchere;
    }
    
    public void setDateEnchere(Date dateEnchere) {
        this.dateEnchere = dateEnchere;
    }
    
    public int getMontantEnchere() {
        return montantEnchere;
    }
    
    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }
    
    // Méthodes utilitaires
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encheres [noEnchere=");
        sb.append(dateEnchere);//verifier, mauvais 
        sb.append(", dateEnchere=");
        sb.append(dateEnchere);
        sb.append(", montantEnchere=");
        sb.append(montantEnchere);
        sb.append(", article=");
        sb.append(idArticle);// verifier
        sb.append(", utilisateur=");
        sb.append(idUtilisateur);//verifier
        sb.append("]");
        return sb.toString();
    }
}
