package fr.eni.encheres.bo;

import java.util.Date;

public class Article {

    private int noArticle;
    private String nomArticle;
    private String description;
    private Date dateDebutEncheres;
    private Date dateFinEncheres;
    private int prixInitial;
    private int prixVente;
    private Utilisateur utilisateur;
    private Categorie categorie;
    private Retrait retrait;

    public Article() {
        
    }
    
    public Article(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
                    Date dateFinEncheres, int prixInitial, int prixVente, Utilisateur utilisateur,
                    Categorie categorie, Retrait retrait) {
                
        setNoArticle(noArticle);
        setNomArticle(nomArticle);
        setDescription(description);
        setDateDebutEncheres(dateDebutEncheres);
        setDateFinEncheres(dateFinEncheres);
        setPrixInitial(prixInitial);
        setPrixVente(prixVente);
        setUtilisateur(utilisateur);
        setCategorie(categorie);
        setRetrait(retrait);

    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(Date dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public Date getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(Date dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Retrait getRetrait() {
        return retrait;
    }

    public void setRetrait(Retrait retrait) {
        this.retrait = retrait;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArticleVendu [noArticle=");
        sb.append(noArticle);
        sb.append(", nomArticle=");
        sb.append(nomArticle);
        sb.append(", description=");
        sb.append(description);
        sb.append(", dateDebutEncheres=");
        sb.append(dateDebutEncheres);
        sb.append(", dateFinEncheres=");
        sb.append(dateFinEncheres);
        return description;
       
    }
}