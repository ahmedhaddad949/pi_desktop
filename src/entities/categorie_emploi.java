/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hadda
 */
public class categorie_emploi {
    private int id ;
    private String nom_emploi;
    private String description_emploi;
    private int nbr_offres;

    public categorie_emploi() {
    }

    public categorie_emploi(int id, String nom_emploi, String description_emploi, int nbr_offres) {
        this.id = id;
        this.nom_emploi = nom_emploi;
        this.description_emploi = description_emploi;
        this.nbr_offres = nbr_offres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_emploi() {
        return nom_emploi;
    }

    public void setNom_emploi(String nom_emploi) {
        this.nom_emploi = nom_emploi;
    }

    public String getDescription_emploi() {
        return description_emploi;
    }

    public void setDescription_emploi(String description_emploi) {
        this.description_emploi = description_emploi;
    }

    public int getNbr_offres() {
        return nbr_offres;
    }

    public void setNbr_offres(int nbr_offres) {
        this.nbr_offres = nbr_offres;
    }

    @Override
    public String toString() {
        return "categorie_emploi{" + "id=" + id + ", nom_emploi=" + nom_emploi + ", description_emploi=" + description_emploi + ", nbr_offres=" + nbr_offres + '}';
    }
    
    
    
}

