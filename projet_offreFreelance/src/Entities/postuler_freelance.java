/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Aymen
 */
public class postuler_freelance {
        private int id;
    private Date date;
    private int offre_freelance_id ;
    private int user_id ;
    private String motivation ;
    private String pdfcv ;

    public postuler_freelance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOffre_freelance_id() {
        return offre_freelance_id;
    }

    public void setOffre_freelance_id(int offre_freelance_id) {
        this.offre_freelance_id = offre_freelance_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getPdfcv() {
        return pdfcv;
    }

    public void setPdfcv(String pdfcv) {
        this.pdfcv = pdfcv;
    }
    
}
