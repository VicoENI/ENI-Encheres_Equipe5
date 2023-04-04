package fr.eni.encheres.bo;

import java.util.Date;

public class Utilisateur {
    
    private int noUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Date dateInscription; // la date n'est pas demander
    private String pseudo;
    private String telephone;
    private String rue;
    private String codePostal;
    private String ville;
    private int credit;
    private boolean administrateur;
    
    // Constructeur par défaut
    public Utilisateur() {}
    
    // Constructeur avec paramètres
    public Utilisateur(String nom, String prenom, String email, String motDePasse, Date dateInscription) {
        setNom(nom);
        setNom(prenom);
        setEmail(email);
        setMotDePasse(motDePasse);
        setDateInscription(dateInscription);
    }
    
    // Getters et setters
    public int getId() {
        return noUtilisateur;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public Date getDateInscription() {
        return dateInscription;
    }
    
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
    
    // Méthodes utilitaires
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilisateurs [pseudo=");
        sb.append(pseudo);
        sb.append(", nom=");
        sb.append(nom);
        sb.append(", prenom=");
        sb.append(prenom);
        sb.append(", email=");
        sb.append(email);
        sb.append(", telephone=");
        sb.append(telephone);
        sb.append(", rue=");
        sb.append(rue);
        sb.append(", codePostal=");
        sb.append(codePostal);
        sb.append(", ville=");
        sb.append(ville);
        sb.append(", motDePasse=");
        sb.append(motDePasse);
        sb.append(", credit=");
        sb.append(credit);
        sb.append(", administrateur=");
        sb.append(administrateur);
        sb.append("]");
        return sb.toString();
    }

    public Object getPseudo() {
        return null;
    }
}
