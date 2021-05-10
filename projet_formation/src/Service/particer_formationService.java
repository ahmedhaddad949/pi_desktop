/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.particer_formation;
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
public class particer_formationService {
          private Connection c = DataSource.getInstance().getCnx();
           public void Ajouter(particer_formation u) throws SQLException {
         PreparedStatement ps;
        
        
        String query = "INSERT INTO `particer_formation`( `user_id`, `formation_id`, `date`) VALUES (?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getUser_id());
             ps.setInt(2, u.getFormarion_id());
             ps.setDate(3, u.getDate());
           

         
            ps.execute();
         
       } catch (Exception e) {
              
       
            System.out.println(e);

        }  }
              public ObservableList<particer_formation> Affichertout() throws SQLException {
   ObservableList<particer_formation> list = FXCollections.observableArrayList();
        String requete = "select * from particer_formation ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
       particer_formation r = new particer_formation();
       r.setId(rs.getInt("id"));
       r.setUser_id(rs.getInt("user_id"));
       r.setFormarion_id(rs.getInt("formation_id"));
        r.setDate(rs.getDate("date"));
       
         list.add(r);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return list;   
 }
              public ObservableList<particer_formation> Affichertout_user (int id) {
   ObservableList<particer_formation> list = FXCollections.observableArrayList();
        String requete = "select * from particer_formation where user_id="+id;
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
       particer_formation r = new particer_formation();
       r.setId(rs.getInt("id"));
       r.setUser_id(rs.getInt("user_id"));
       r.setFormarion_id(rs.getInt("formation_id"));
        r.setDate(rs.getDate("date"));
       
         list.add(r);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list; 
              }
   public void Supprimer(int id) throws SQLException {
        PreparedStatement ps;

        String query = "DELETE FROM `particer_formation` WHERE `id`=?  ";
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
