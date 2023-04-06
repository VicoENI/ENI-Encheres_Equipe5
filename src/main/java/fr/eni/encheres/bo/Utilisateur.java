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
    public Utilisateur(String nom, String prenom, String email, String motDePasse, Date dateInscription, String pseudo, String telephone,int credit, boolean administrateur, String ville, String rue, String codePostal) {
        setNom(nom);
        setNom(prenom);
        setEmail(email);
        setMotDePasse(motDePasse);
        setDateInscription(dateInscription);
        setPseudo(pseudo);
        setTelephone(telephone);
        setCredit(credit);
        setAdministrateur(administrateur);
        setRue(rue);
        setVille(ville);
        setCodePostal(codePostal);
    }
    
    public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

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

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}


	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
    
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Utilisateur [getNoUtilisateur()=");
		builder.append(getNoUtilisateur());
		builder.append(", getTelephone()=");
		builder.append(getTelephone());
		builder.append(", getRue()=");
		builder.append(getRue());
		builder.append(", getCodePostal()=");
		builder.append(getCodePostal());
		builder.append(", getVille()=");
		builder.append(getVille());
		builder.append(", getCredit()=");
		builder.append(getCredit());
		builder.append(", isAdministrateur()=");
		builder.append(isAdministrateur());
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getNom()=");
		builder.append(getNom());
		builder.append(", getPrenom()=");
		builder.append(getPrenom());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append(", getMotDePasse()=");
		builder.append(getMotDePasse());
		builder.append(", getDateInscription()=");
		builder.append(getDateInscription());
		builder.append(", getPseudo()=");
		builder.append(getPseudo());
		builder.append("]");
		return builder.toString();
	}

 
}
