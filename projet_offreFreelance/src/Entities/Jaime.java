
package Entites;

import java.sql.Date;


public class Jaime {
    private int id ;
    private int idUser ;
    private int idEexperience;
    private Date date ;

    public Jaime(int id, int idUser, int idEexperience, Date date) {
        this.id = id;
        this.idUser = idUser;
        this.idEexperience = idEexperience;
        this.date = date;
    }

    public Jaime(int id, int idUser, int idEexperience) {
        this.id = id;
        this.idUser = idUser;
        this.idEexperience = idEexperience;
    }

    public Jaime(int idUser, int idEexperience) {
        this.idUser = idUser;
        this.idEexperience = idEexperience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEexperience() {
        return idEexperience;
    }

    public void setIdEexperience(int idEexperience) {
        this.idEexperience = idEexperience;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
