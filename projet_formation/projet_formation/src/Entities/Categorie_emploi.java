/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Aymen
 */
public class Categorie_emploi {
    private int id ;
    private String nom_cat_emp;
    private String description_cat_emp;
    private int nbr_offre_emp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cat_emp() {
        return nom_cat_emp;
    }

    public void setNom_cat_emp(String nom_cat_emp) {
        this.nom_cat_emp = nom_cat_emp;
    }

    public String getDescription_cat_emp() {
        return description_cat_emp;
    }

    public void setDescription_cat_emp(String description_cat_emp) {
        this.description_cat_emp = description_cat_emp;
    }

    public int getNbr_offre_emp() {
        return nbr_offre_emp;
    }

    public void setNbr_offre_emp(int nbr_offre_emp) {
        this.nbr_offre_emp = nbr_offre_emp;
    }

    public Categorie_emploi() {
    }
    
}
