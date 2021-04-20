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
import entities.categorie_questionnaire;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author hadda
 */
public class categorie_questionnaire_crud {
    
    Connection cn2;
    Statement st;

    public categorie_questionnaire_crud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    
        public List<categorie_questionnaire> displayAllCategorie_questionnaire() {
        ArrayList<categorie_questionnaire> list = new ArrayList<>();

        try {

            String requete3 = "SELECT * FROM categorie_questionnaire ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                categorie_questionnaire cq = new categorie_questionnaire();
                cq.setId(rs.getInt("id"));
                cq.setNom_cat_qst(rs.getString("nom_cat_qst"));
                cq.setDescription_cat_qst(rs.getString("description_cat_qst"));
                cq.setNbr_qst(rs.getInt("nbr_qst"));
                list.add(cq);

            }

        } catch (SQLException ex) {
            System.out.println("erreur displayAllCategorie_questionnaire" +ex.getMessage());
        }
        return list;

    }
    
     public void add_categorie_questionnaire(categorie_questionnaire cq ) {
        try {
            PreparedStatement pst;
            String requete1;
            requete1 = "INSERT INTO categorie_questionnaire ( nom_cat_qst, description_cat_qst, nbr_qst )VALUES (?,?,?)";
            pst = cn2.prepareStatement(requete1);

            pst.setString(1, cq.getNom_cat_qst());
            pst.setString(2, cq.getDescription_cat_qst());
            pst.setInt(3, cq.getNbr_qst());
            

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("errreur dans l'ajout du categorie questionnaire"+ex.getMessage());
        }

    }
        public void updateCategorie_questionnaire(categorie_questionnaire cq, int id) {
        try {
            PreparedStatement pst;
            String reqUpdate = "UPDATE categorie_questionnaire SET nom_cat_qst=? ,description_cat_qst=? ,nbr_qst=? where id=? ";

            PreparedStatement preparedStatement = cn2.prepareStatement(reqUpdate);
            pst = cn2.prepareStatement(reqUpdate);
            
            pst.setString(1, cq.getNom_cat_qst());
            pst.setString(2, cq.getDescription_cat_qst());
            pst.setInt(3, cq.getNbr_qst());
            pst.setInt(4, id);
            pst.executeUpdate();
            
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
        
        
         public void deletecategorie_questionnaire(int idCategorie_questionnaire) {
        try {

            String reqDel = "DELETE FROM categorie_questionnaire WHERE id=? ";
            PreparedStatement pst = cn2.prepareStatement(reqDel);
            pst.setInt(1, idCategorie_questionnaire);
            pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }
             public categorie_questionnaire findByIdCategorie_questionnaire(int id) {


        try {
            categorie_questionnaire cq = new categorie_questionnaire();
            String requete = "SELECT * FROM categorie_questionnaire WHERE id=? ";
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               cq.setId(rs.getInt("id"));
                cq.setNom_cat_qst(rs.getString("nom_cat_qst"));
                cq.setDescription_cat_qst(rs.getString("description_cat_qst"));
                cq.setNbr_qst(rs.getInt("nbr_qst"));

            }
            return cq;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Campement " + ex.getMessage());
            return null;
        }
    }
         
    
    
}
