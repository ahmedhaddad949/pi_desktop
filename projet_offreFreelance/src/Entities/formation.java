package Entities;

import java.util.Date;

public class formation {
    private int id;
    private int users_id;
    private Date duree_fr;
    private String description;
    private String image;
    private String niveau_frt;
    private int Categorieformation_id;

    public formation() {
    }

    public formation(int id, int users_id, Date duree_fr, String description, String image, String niveau_frt, int categorieformation_id) {
        this.id = id;
        this.users_id = users_id;
        this.duree_fr = duree_fr;
        this.description = description;
        this.image = image;
        this.niveau_frt = niveau_frt;
        Categorieformation_id = categorieformation_id;
    }

    public formation(int users_id, Date duree_fr, String description, String image, String niveau_frt, int categorieformation_id) {
        this.users_id = users_id;
        this.duree_fr = duree_fr;
        this.description = description;
        this.image = image;
        this.niveau_frt = niveau_frt;
        Categorieformation_id = categorieformation_id;
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

    public Date getDuree_fr() {
        return duree_fr;
    }

    public void setDuree_fr(Date duree_fr) {
        this.duree_fr = duree_fr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNiveau_frt() {
        return niveau_frt;
    }

    public void setNiveau_frt(String niveau_frt) {
        this.niveau_frt = niveau_frt;
    }

    public int getCategorieformation_id() {
        return Categorieformation_id;
    }

    public void setCategorieformation_id(int categorieformation_id) {
        Categorieformation_id = categorieformation_id;
    }

    @Override
    public String toString() {
        return "formation{" +
                "id=" + id +
                ", users_id=" + users_id +
                ", duree_fr=" + duree_fr +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", niveau_frt='" + niveau_frt + '\'' +
                ", Categorieformation_id=" + Categorieformation_id +
                '}';
    }
}
