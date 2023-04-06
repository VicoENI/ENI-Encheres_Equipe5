package fr.eni.encheres.bo;

//import java.sql.Date;
import java.util.Date;

public class Enchere {

    private int id;
    private int idArticle;
    private int idUtilisateur;
    private Date dateEnchere;
    private int montantEnchere;
    private Utilisateur utilisateur;
    private Article articleVendu;
    
    // Constructeur par défaut
    public Enchere() {}
    
    // Constructeur avec paramètres
    public Enchere(Utilisateur utilisateur, Article articleVendu, Date dateEnchere, int montantEnchere) {
    	
        setDateEnchere(dateEnchere);
        setMontantEnchere(montantEnchere);
        setUtilisateur(utilisateur);
        setArticleVendu(articleVendu);
    }
   

	// Getters et setters
    public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Article getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(Article articleVendu) {
		this.articleVendu = articleVendu;
	}
	
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
    
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [getUtilisateur()=");
		builder.append(getUtilisateur());
		builder.append(", getArticleVendu()=");
		builder.append(getArticleVendu());
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getIdArticle()=");
		builder.append(getIdArticle());
		builder.append(", getIdUtilisateur()=");
		builder.append(getIdUtilisateur());
		builder.append(", getDateEnchere()=");
		builder.append(getDateEnchere());
		builder.append(", getMontantEnchere()=");
		builder.append(getMontantEnchere());
		builder.append("]");
		return builder.toString();
	}
}
