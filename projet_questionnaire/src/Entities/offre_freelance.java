package Entities;

import java.util.Date;

public class offre_freelance {
    private int id;
    private String titre_offre_fr;
    private String descriptionfr;
    private String entreprisefr ;
    private Double recomponse;
    private Date date_expiration ;
    private int etat_offre ;
    private int categorie_freelance_id ;

    public offre_freelance() {
    }

    public offre_freelance(int id, String titre_offre_fr, String descriptionfr, String entreprisefr, Double recomponse, Date date_expiration, int etat_offre, int categorie_freelance_id) {
        this.id = id;
        this.titre_offre_fr = titre_offre_fr;
        this.descriptionfr = descriptionfr;
        this.entreprisefr = entreprisefr;
        this.recomponse = recomponse;
        this.date_expiration = date_expiration;
        this.etat_offre = etat_offre;
        this.categorie_freelance_id = categorie_freelance_id;
    }

    public offre_freelance(String titre_offre_fr, String descriptionfr, String entreprisefr, Double recomponse, Date date_expiration, int etat_offre, int categorie_freelance_id) {
        this.titre_offre_fr = titre_offre_fr;
        this.descriptionfr = descriptionfr;
        this.entreprisefr = entreprisefr;
        this.recomponse = recomponse;
        this.date_expiration = date_expiration;
        this.etat_offre = etat_offre;
        this.categorie_freelance_id = categorie_freelance_id;
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

    public Double getRecomponse() {
        return recomponse;
    }

    public void setRecomponse(Double recomponse) {
        this.recomponse = recomponse;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public int getEtat_offre() {
        return etat_offre;
    }

    public void setEtat_offre(int etat_offre) {
        this.etat_offre = etat_offre;
    }

    public int getCategorie_freelance_id() {
        return categorie_freelance_id;
    }

    public void setCategorie_freelance_id(int categorie_freelance_id) {
        this.categorie_freelance_id = categorie_freelance_id;
    }

    @Override
    public String toString() {
        return "offre_freelance{" +
                "id=" + id +
                ", titre_offre_fr='" + titre_offre_fr + '\'' +
                ", descriptionfr='" + descriptionfr + '\'' +
                ", entreprisefr='" + entreprisefr + '\'' +
                ", recomponse=" + recomponse +
                ", date_expiration=" + date_expiration +
                ", etat_offre=" + etat_offre +
                ", categorie_freelance_id=" + categorie_freelance_id +
                '}';
    }
}
