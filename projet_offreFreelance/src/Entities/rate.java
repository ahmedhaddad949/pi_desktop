/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Aymen
 */
public class rate {
     private int id_rate;
            private int id_user;
            private int id_formation;
                  private int rate;
    private String description_formation;

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public rate() {
    }

    public int getId_rate() {
        return id_rate;
    }

    public void setId_rate(int id_rate) {
        this.id_rate = id_rate;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription_formation() {
        return description_formation;
    }

    public void setDescription_formation(String description_formation) {
        this.description_formation = description_formation;
    }

}
