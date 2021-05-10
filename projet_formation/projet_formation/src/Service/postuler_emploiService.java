/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.postuler_emploi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author Aymen
 */
public class postuler_emploiService {
     private Connection c = DataSource.getInstance().getCnx();
           public void Ajouter(postuler_emploi u) throws SQLException {
         PreparedStatement ps;
        
        
        String query = "INSERT INTO `postuler_emploi`( `offre_emploi_id`, `user_id`, `date_postulation`, `motivation`, `pdfcv`) VALUES (?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getOffre_emploi_id());
             ps.setInt(2, u.getUser_id());
             ps.setDate(3, u.getDate());
            ps.setString(4, u.getMotivation());
             ps.setString(5, u.getPdfcv());

         
            ps.execute();
         
       } catch (Exception e) {
              
       
            System.out.println(e);

        }  }
              public ObservableList<postuler_emploi> Affichertout() throws SQLException {
   ObservableList<postuler_emploi> list = FXCollections.observableArrayList();
        String requete = "select * from postuler_emploi ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
       postuler_emploi r = new postuler_emploi();
       r.setId(rs.getInt("id"));
             r.setOffre_emploi_id(rs.getInt("offre_emploi_id"));
       r.setUser_id(rs.getInt("user_id"));
       r.setDate(rs.getDate("date_postulation"));
        r.setMotivation(rs.getString("motivation"));
          r.setPdfcv(rs.getString("pdfcv"));
         list.add(r);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return list;   
 }
              public ObservableList<postuler_emploi> Affichertout_user (int id) {
   ObservableList<postuler_emploi> list = FXCollections.observableArrayList();
        String requete = "select * from postuler_emploi where user_id="+id;
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
 postuler_emploi r = new postuler_emploi();
       r.setId(rs.getInt("id"));
             r.setOffre_emploi_id(rs.getInt("offre_emploi_id"));
       r.setUser_id(rs.getInt("user_id"));
       r.setDate(rs.getDate("date_postulation"));
        r.setMotivation(rs.getString("motivation"));
          r.setPdfcv(rs.getString("pdfcv"));
         list.add(r);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list; 
              }
   public void Supprimer(int id) throws SQLException {
        PreparedStatement ps;

        String query = "DELETE FROM `postuler_emploi` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, id);

            ps.execute();

            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
