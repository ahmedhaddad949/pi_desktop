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
public class offre_freelance {
    private int id;
    private String titre_offre_fr;
    private String descriptionfr;
      private String entreprisefr;
         private int recomponse;
           private String etat_offre;

    public offre_freelance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_offre_fr() {
        return titre_offre_fr;
    }

    public void setTitre_offre_fr(String titre_offre_fr) {
        this.titre_offre_fr = titre_offre_fr;
    }

    public String getDescriptionfr() {
        return descriptionfr;
    }

    public void setDescriptionfr(String descriptionfr) {
        this.descriptionfr = descriptionfr;
    }

    public String getEntreprisefr() {
        return entreprisefr;
    }

    public void setEntreprisefr(String entreprisefr) {
        this.entreprisefr = entreprisefr;
    }

    public int getRecomponse() {
        return recomponse;
    }

    public void setRecomponse(int recomponse) {
        this.recomponse = recomponse;
    }

    public String getEtat_offre() {
        return etat_offre;
    }

    public void setEtat_offre(String etat_offre) {
        this.etat_offre = etat_offre;
    }
    
}
