package Entities;

public class category_freelance {
    private int id ;
    private String nom_cat_fr;
    private String description_cat_fr;
    private int nbr_offre_fr;


    public category_freelance() {

    }


    public category_freelance(int id, String nom_cat_fr, String description_cat_fr, int nbr_offre_fr) {
        this.id = id;
        this.nom_cat_fr = nom_cat_fr;
        this.description_cat_fr = description_cat_fr;
        this.nbr_offre_fr = nbr_offre_fr;
    }

    public category_freelance(String nom_cat_fr, String description_cat_fr, int nbr_offre_fr) {
        this.nom_cat_fr = nom_cat_fr;
        this.description_cat_fr = description_cat_fr;
        this.nbr_offre_fr = nbr_offre_fr;
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

    public int getNbr_offre_fr() {
        return nbr_offre_fr;
    }

    public void setNbr_offre_fr(int nbr_offre_fr) {
        this.nbr_offre_fr = nbr_offre_fr;
    }

    @Override
    public String toString() {
        return "category_freelance{" +
                "id=" + id +
                ", nom_cat_fr='" + nom_cat_fr + '\'' +
                ", description_cat_fr='" + description_cat_fr + '\'' +
                ", nbr_offre_fr=" + nbr_offre_fr +
                '}';
    }
}
