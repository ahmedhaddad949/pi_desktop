/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.rate;
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
public class rateService {
      private Connection c = DataSource.getInstance().getCnx();

    
    
    public void Ajouter(rate u) throws SQLException {
         PreparedStatement ps;
        
        
        String query = "INSERT INTO `rate`( `id_user`, `id_formation`, `rate`, `description_formation`) VALUES (?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getId_user());
             ps.setInt(2, u.getId_formation());
             ps.setInt(3, u.getRate());
             ps.setString(4, u.getDescription_formation());

         
            ps.execute();
         
       } catch (Exception e) {
              
       
            System.out.println(e);

        }  }


   


    public ObservableList<rate> Affichertout() throws SQLException {
   ObservableList<rate> list = FXCollections.observableArrayList();
        String requete = "select * from rate ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {
       rate r = new rate();
       r.setId_rate(rs.getInt("id"));
       r.setId_user(rs.getInt("id_user"));
       r.setId_formation(rs.getInt("id_formation"));
        r.setRate(rs.getInt("rate"));
         r.setDescription_formation(rs.getString("description_formation"));

         list.add(r);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;  }
}
