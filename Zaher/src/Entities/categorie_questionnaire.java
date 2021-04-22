package Entities;

public class categorie_questionnaire {
    private int id ;
    private String nom_cat_qst;
    private String description_cat_qst;
    private int nbr_qst;

    public categorie_questionnaire() {
    }

    public categorie_questionnaire(int id, String nom_cat_qst, String description_cat_qst, int nbr_qst) {
        this.id = id;
        this.nom_cat_qst = nom_cat_qst;
        this.description_cat_qst = description_cat_qst;
        this.nbr_qst = nbr_qst;
    }

    public categorie_questionnaire(String nom_cat_qst, String description_cat_qst, int nbr_qst) {
        this.nom_cat_qst = nom_cat_qst;
        this.description_cat_qst = description_cat_qst;
        this.nbr_qst = nbr_qst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cat_qst() {
        return nom_cat_qst;
    }

    public void setNom_cat_qst(String nom_cat_qst) {
        this.nom_cat_qst = nom_cat_qst;
    }

    public String getDescription_cat_qst() {
        return description_cat_qst;
    }

    public void setDescription_cat_qst(String description_cat_qst) {
        this.description_cat_qst = description_cat_qst;
    }

    public int getNbr_qst() {
        return nbr_qst;
    }

    public void setNbr_qst(int nbr_qst) {
        this.nbr_qst = nbr_qst;
    }

    @Override
    public String toString() {
        return "categorie_questionnaire{" +
                "id=" + id +
                ", nom_cat_qst='" + nom_cat_qst + '\'' +
                ", description_cat_qst='" + description_cat_qst + '\'' +
                ", nbr_qst=" + nbr_qst +
                '}';
    }
}
