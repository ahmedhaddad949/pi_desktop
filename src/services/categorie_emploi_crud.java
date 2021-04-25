/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import utils.MyConnection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import entities.categorie_emploi;
import entities.offre_emploi;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author hadda
 */
public class categorie_emploi_crud {
    
     Connection cn2;
    Statement st;

    public categorie_emploi_crud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    
        public ObservableList<categorie_emploi> displayAllCategorie() {
                     ObservableList<categorie_emploi> Listeopp=  FXCollections.observableArrayList();;

        ArrayList<categorie_emploi> list = new ArrayList<>();

        try {

            String requete3 = "SELECT * FROM categorie_emploi ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                categorie_emploi c = new categorie_emploi();
                c.setId(rs.getInt("id"));
                c.setNom_emploi(rs.getString("nom_emploi"));
                c.setDescription_emploi(rs.getString("description_emploi"));
                c.setNbr_offres(rs.getInt("nbr_offres"));
                Listeopp.add(c);

            }

        } catch (SQLException ex) {
            System.out.println("erreur displayAllCampement" +ex.getMessage());
        }
        return Listeopp;

    }
    
     public void add_categorie_emploi(categorie_emploi c ) {
        try {
            PreparedStatement pst;
            String requete1;
            requete1 = "INSERT INTO categorie_emploi ( nom_emploi, description_emploi, nbr_offres )VALUES (?,?,?)";
            pst = cn2.prepareStatement(requete1);

            pst.setString(1, c.getNom_emploi());
            pst.setString(2, c.getDescription_emploi());
            pst.setInt(3, c.getNbr_offres());
            

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("errreur dans l'ajout compement"+ex.getMessage());
        }

    }
        public void updateCategorie_emploi(categorie_emploi c, int id) {
        try {
            PreparedStatement pst;
            String reqUpdate = "UPDATE categorie_emploi SET nom_emploi=? ,description_emploi=? ,nbr_offres=? where id=? ";

            PreparedStatement preparedStatement = cn2.prepareStatement(reqUpdate);
            pst = cn2.prepareStatement(reqUpdate);
            
            pst.setString(1, c.getNom_emploi());
            pst.setString(2, c.getDescription_emploi());
            pst.setInt(3, c.getNbr_offres());
            pst.setInt(4, id);
            pst.executeUpdate();
            
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
        
        
         public void deleteCategorie_emploi(int idCategorie_emploi) {
        try {

            String reqDel = "DELETE FROM categorie_emploi WHERE id=? ";
            PreparedStatement pst = cn2.prepareStatement(reqDel);
            pst.setInt(1, idCategorie_emploi);
            pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }
             public categorie_emploi findByIdCategorie_emploi(int id) {


        try {
            categorie_emploi c = new categorie_emploi();
            String requete = "SELECT * FROM categorie_emploi WHERE id=? ";
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               c.setId(rs.getInt("id"));
                c.setNom_emploi(rs.getString("nom_emploi"));
                c.setDescription_emploi(rs.getString("description_emploi"));
                c.setNbr_offres(rs.getInt("nbr_offres"));

            }
            return c;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Campement " + ex.getMessage());
            return null;
        }
    }
         


}
