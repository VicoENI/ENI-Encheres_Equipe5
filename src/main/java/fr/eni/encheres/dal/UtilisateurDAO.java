package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

    Utilisateur getUtilisateurByPseudo(String pseudo) throws SQLException;

    List<Utilisateur> getUtilisateurs() throws SQLException;

    void addUtilisateur(Utilisateur utilisateur) throws SQLException;

    void updateUtilisateur(Utilisateur utilisateur) throws SQLException;

    void deleteUtilisateur(int id) throws SQLException;
}
