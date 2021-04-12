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
public class offre_emploi {
    	private int id ;
        private String titre_offre_emploi ;
        private int nbr_offres ;
        private String description_cat_em;
        private int CategorieEmploi_id  ;

    public offre_emploi(int id, String titre_offre_emploi, int nbr_offres, String description_cat_em, int CategorieEmploi_id) {
        this.id = id;
        this.titre_offre_emploi = titre_offre_emploi;
        this.nbr_offres = nbr_offres;
        this.description_cat_em = description_cat_em;
        this.CategorieEmploi_id = CategorieEmploi_id;
    }

    public offre_emploi() {
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_offre_emploi() {
        return titre_offre_emploi;
    }

    public void setTitre_offre_emploi(String titre_offre_emploi) {
        this.titre_offre_emploi = titre_offre_emploi;
    }

    public int getNbr_offres() {
        return nbr_offres;
    }

    public void setNbr_offres(int nbr_offres) {
        this.nbr_offres = nbr_offres;
    }

    public String getDescription_cat_em() {
        return description_cat_em;
    }

    public void setDescription_cat_em(String description_cat_em) {
        this.description_cat_em = description_cat_em;
    }

    public int getCategorieEmploi_id() {
        return CategorieEmploi_id;
    }

    public void setCategorieEmploi_id(int CategorieEmploi_id) {
        this.CategorieEmploi_id = CategorieEmploi_id;
    }

    @Override
    public String toString() {
        return "offre_emploi{" + "id=" + id + ", titre_offre_emploi=" + titre_offre_emploi + ", nbr_offres=" + nbr_offres + ", description_cat_em=" + description_cat_em + ", CategorieEmploi_id=" + CategorieEmploi_id + '}';
    }

    
        
        
    
}
