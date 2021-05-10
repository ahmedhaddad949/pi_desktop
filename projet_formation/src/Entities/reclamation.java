package Entities;

import java.util.Date;

public class reclamation {
    private int id;
    private int categorie_id;
    private int user_id;
    private String titre;
    private String text;
    private Date date;
    private int	etat;
    private int traitement;

    public reclamation() {
    }

    public reclamation(int id, int categorie_id, int user_id, String titre, String text, Date date, int etat, int traitement) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.user_id = user_id;
        this.titre = titre;
        this.text = text;
        this.date = date;
        this.etat = etat;
        this.traitement = traitement;
    }

    public reclamation(int categorie_id, int user_id, String titre, String text, Date date, int etat, int traitement) {
        this.categorie_id = categorie_id;
        this.user_id = user_id;
        this.titre = titre;
        this.text = text;
        this.date = date;
        this.etat = etat;
        this.traitement = traitement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getTraitement() {
        return traitement;
    }

    public void setTraitement(int traitement) {
        this.traitement = traitement;
    }

    @Override
    public String toString() {
        return "reclamation{" +
                "id=" + id +
                ", categorie_id=" + categorie_id +
                ", user_id=" + user_id +
                ", titre='" + titre + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", etat=" + etat +
                ", traitement=" + traitement +
                '}';
    }
}
