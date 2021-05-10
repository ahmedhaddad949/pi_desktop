package com.mycompany.Models;

public class questionnaire {
    private int id;
    private int users_id;
    private String nom_qst;
    private String nom_cat_qst;
    private String description_cat_qst;
    private int nbr_qst;
    private int Categoriequestionnaire_id;
    
 private String qr;

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
 
    public questionnaire() {
    }

    public questionnaire(int id, int users_id, String nom_qst, String nom_cat_qst, String description_cat_qst, int nbr_qst, int categoriequestionnaire_id) {
        this.id = id;
        this.users_id = users_id;
        this.nom_qst = nom_qst;
        this.nom_cat_qst = nom_cat_qst;
        this.description_cat_qst = description_cat_qst;
        this.nbr_qst = nbr_qst;
        Categoriequestionnaire_id = categoriequestionnaire_id;
    }

    public questionnaire(int users_id, String nom_qst, String nom_cat_qst, String description_cat_qst, int nbr_qst, int categoriequestionnaire_id) {
        this.users_id = users_id;
        this.nom_qst = nom_qst;
        this.nom_cat_qst = nom_cat_qst;
        this.description_cat_qst = description_cat_qst;
        this.nbr_qst = nbr_qst;
        Categoriequestionnaire_id = categoriequestionnaire_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getNom_qst() {
        return nom_qst;
    }

    public void setNom_qst(String nom_qst) {
        this.nom_qst = nom_qst;
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

    public int getCategoriequestionnaire_id() {
        return Categoriequestionnaire_id;
    }

    public void setCategoriequestionnaire_id(int categoriequestionnaire_id) {
        Categoriequestionnaire_id = categoriequestionnaire_id;
    }

    @Override
    public String toString() {
        return "questionnaire{" +
                "id=" + id +
                ", users_id=" + users_id +
                ", nom_qst='" + nom_qst + '\'' +
                ", nom_cat_qst='" + nom_cat_qst + '\'' +
                ", description_cat_qst='" + description_cat_qst + '\'' +
                ", nbr_qst=" + nbr_qst +
                ", Categoriequestionnaire_id=" + Categoriequestionnaire_id +
                '}';
    }

  
}
