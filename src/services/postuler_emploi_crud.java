/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.offre_emploi;
import entities.postuler_emploi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author hadda
 */
public class postuler_emploi_crud {
    
     Connection cn2;
    Statement st;

    public postuler_emploi_crud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    
        public List<postuler_emploi> displayAllPostulation() {
        ArrayList<postuler_emploi> list = new ArrayList<>();

        try {

            String requete3 = "SELECT * FROM postuler_emploi ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                postuler_emploi p = new postuler_emploi();
                p.setId(rs.getInt("id"));
                p.setDate_postulation(rs.getDate("date_postulation"));
                p.setOffrePostulation_Id(rs.getInt("offrePostulation_Id"));
                p.setUserid(rs.getInt("userid"));
                list.add(p);

            }

        } catch (SQLException ex) {
            System.out.println("erreur displayAllPostulation" +ex.getMessage());
        }
        return list;

    }
    
     public void add_postuler_emploi(postuler_emploi p) {
        try {
            PreparedStatement pst;
            String requete1;
            requete1 = "INSERT INTO postuler_emploi ( date_postulation, offrePostulation_Id, userid)VALUES (?,?,?)";
            pst = cn2.prepareStatement(requete1);

            pst.setDate(1,p.getDate_postulation());
            pst.setInt(2, p.getOffrePostulation_Id());
            pst.setInt(3, p.getUserid());
            

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("errreur dans l'ajout de la postulation "+ex.getMessage());
        }

    }
        public void updatePostuler_emploi(postuler_emploi p, int id) {
        try {
            System.out.println(p);
            System.out.println(id);
            PreparedStatement pst;
            String reqUpdate = "UPDATE postuler_emploi SET date_postulation=? ,offrePostulation_Id=? ,userid=?  where id=? ";

            PreparedStatement preparedStatement = cn2.prepareStatement(reqUpdate);
            pst = cn2.prepareStatement(reqUpdate);
            
            pst.setDate(1, p.getDate_postulation());
            pst.setInt(2, p.getOffrePostulation_Id());
            pst.setInt(3, p.getUserid());
            

            pst.setInt(4, id);
            pst.executeUpdate();
            
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
        
        
         public void deletePostuler_emploi(int idPostuler_emploi) {
        try {

            String reqDel = "DELETE FROM postuler_emploi WHERE id=? ";
            PreparedStatement pst = cn2.prepareStatement(reqDel);
            pst.setInt(1, idPostuler_emploi);
            pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }
             public postuler_emploi findByIdPostuler_emploi(int id) {


        try {
            postuler_emploi p = new postuler_emploi();
            String requete = "SELECT * FROM postuler_emploi WHERE id=? ";
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               p.setId(rs.getInt("id"));
                p.setDate_postulation(rs.getDate("date_postulation"));
                p.setOffrePostulation_Id(rs.getInt("offrePostulation_Id"));
                p.setUserid(rs.getInt("userid"));
               


            }
            return p;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche de l'offre emploi " + ex.getMessage());
            return null;
        }
    }
         
}
