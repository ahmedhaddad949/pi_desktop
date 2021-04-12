/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author hadda
 */
public class postuler_emploi {
    private int id ;
    private Date date_postulation ;
    private int userid ;
    private int OffrePostulation_Id ;

    public postuler_emploi(int id, Date date_postulation, int userid, int OffrePostulation_Id) {
        this.id = id;
        this.date_postulation = date_postulation;
        this.userid = userid;
        this.OffrePostulation_Id = OffrePostulation_Id;
    }

    public postuler_emploi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_postulation() {
        return date_postulation;
    }

    public void setDate_postulation(Date date_postulation) {
        this.date_postulation = date_postulation;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getOffrePostulation_Id() {
        return OffrePostulation_Id;
    }

    public void setOffrePostulation_Id(int OffrePostulation_Id) {
        this.OffrePostulation_Id = OffrePostulation_Id;
    }

    @Override
    public String toString() {
        return "postuler_emploi{" + "id=" + id + ", date_postulation=" + date_postulation + ", userid=" + userid + ", OffrePostulation_Id=" + OffrePostulation_Id + '}';
    }

  
    
    
}
