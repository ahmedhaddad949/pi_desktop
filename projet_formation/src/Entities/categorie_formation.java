package Entities;

public class categorie_formation {
    private int id;
    private String nom_cat_frt;
    private String description_cat_frt;
    private int nbr_cat_frt;

    public categorie_formation() {
    }

    public categorie_formation(int id, String nom_cat_frt, String description_cat_frt, int nbr_cat_frt) {
        this.id = id;
        this.nom_cat_frt = nom_cat_frt;
        this.description_cat_frt = description_cat_frt;
        this.nbr_cat_frt = nbr_cat_frt;
    }

    public categorie_formation(String nom_cat_frt, String description_cat_frt, int nbr_cat_frt) {
        this.nom_cat_frt = nom_cat_frt;
        this.description_cat_frt = description_cat_frt;
        this.nbr_cat_frt = nbr_cat_frt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cat_frt() {
        return nom_cat_frt;
    }

    public void setNom_cat_frt(String nom_cat_frt) {
        this.nom_cat_frt = nom_cat_frt;
    }

    public String getDescription_cat_frt() {
        return description_cat_frt;
    }

    public void setDescription_cat_frt(String description_cat_frt) {
        this.description_cat_frt = description_cat_frt;
    }

    public int getNbr_cat_frt() {
        return nbr_cat_frt;
    }

    public void setNbr_cat_frt(int nbr_cat_frt) {
        this.nbr_cat_frt = nbr_cat_frt;
    }

    @Override
    public String toString() {
        return "categorie_formation{" +
                "id=" + id +
                ", nom_cat_frt='" + nom_cat_frt + '\'' +
                ", description_cat_frt='" + description_cat_frt + '\'' +
                ", nbr_cat_frt=" + nbr_cat_frt +
                '}';
    }
}
