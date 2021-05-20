/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Models;

/**
 *
 * @author hadda
 */
public class categorie_freelance {
        private int id ;
    private String nom_cat_fr;
    private String description_cat_fr;
    private int nbrOffres_fr;

    public categorie_freelance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cat_fr() {
        return nom_cat_fr;
    }

    public void setNom_cat_fr(String nom_cat_fr) {
        this.nom_cat_fr = nom_cat_fr;
    }

    public String getDescription_cat_fr() {
        return description_cat_fr;
    }

    public void setDescription_cat_fr(String description_cat_fr) {
        this.description_cat_fr = description_cat_fr;
    }

  

    public int getNbrOffres_fr() {
        return nbrOffres_fr;
    }

    public void setNbrOffres_fr(int nbrOffres_fr) {
        this.nbrOffres_fr = nbrOffres_fr;
    }
    
    
}
