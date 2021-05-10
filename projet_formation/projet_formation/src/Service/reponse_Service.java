/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.reponse;
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
public class reponse_Service {
      private Connection c = DataSource.getInstance().getCnx();

    
    
    public void Ajouter(reponse u) throws SQLException {
         PreparedStatement ps;
        
        
        String query = "INSERT INTO `reponse`( `questionnaire_id`, `user_id`, `text`, `date`) VALUES (?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getQuestionnaire_id());
             ps.setInt(2, u.getUser_id());
             
             ps.setString(3, u.getText());
ps.setDate(4, u.getDate());
         
            ps.execute();
         
       } catch (Exception e) {
              
       
            System.out.println(e);

        }  }

    public reponse get_reponse_affichage(int id_q) {
        reponse r = new reponse();
  
      String requete = "SELECT *  FROM  reponse where  questionnaire_id="+id_q  ;
                        Connection cnx = DataSource.getInstance().getCnx();;
         try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            
                    System.out.println("dqsdsq");
  
              r.setId(rs.getInt("id"));
       r.setQuestionnaire_id(rs.getInt("questionnaire_id"));
       r.setUser_id(rs.getInt("user_id"));
        r.setDate(rs.getDate("date"));
         r.setText(rs.getString("text"));
                
                         }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return r;

    }
 public void Supprimer(int t) throws SQLException {

        String requete = "DELETE FROM `reponse` WHERE `id`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
 

    public void Modifier(reponse t, int id) throws SQLException {

        PreparedStatement ps;

        String query = "UPDATE `reponse` SET `text`=? where id = ?";
        ps = c.prepareStatement(query);
        ps.setInt(2, id);
        ps.setString(1, t.getText());
        ps.execute();
    }
   


    public ObservableList<reponse> Affichertout() throws SQLException {
   ObservableList<reponse> list = FXCollections.observableArrayList();
        String requete = "select * from reponse ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
       reponse r = new reponse();
       r.setId(rs.getInt("id"));
       r.setQuestionnaire_id(rs.getInt("questionnaire_id"));
       r.setUser_id(rs.getInt("user_id"));
        r.setDate(rs.getDate("date"));
         r.setText(rs.getString("text"));

         list.add(r);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;  }
}
