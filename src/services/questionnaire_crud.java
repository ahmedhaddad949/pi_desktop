/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.questionnaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author hadda
 */
public class questionnaire_crud {
    
    
     Connection cn2;
    Statement st;

    public questionnaire_crud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    
        public List<questionnaire> displayAllQuestionnaires() {
        ArrayList<questionnaire> list = new ArrayList<>();

        try {

            String requete3 = "SELECT * FROM questionnaire ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                questionnaire q = new questionnaire();
                q.setId(rs.getInt("id"));
                q.setNom_qst(rs.getString("nom_qst"));
                q.setNom_cat_qst(rs.getString("nom_cat_qst"));
                q.setDescription_cat_qst(rs.getString("description_cat_qst"));
                q.setNbr_qst(rs.getInt("nbr_qst"));
                q.setCategoriequestionnaire_id(rs.getInt("Categoriequestionnaire_id"));
                q.setUsers_id(rs.getInt("users_id"));
                list.add(q);

            }

        } catch (SQLException ex) {
            System.out.println("erreur displayAllCategorieQs" +ex.getMessage());
        }
        return list;

    }
    
     public void add_questionnaire(questionnaire q ) {
        try {
            PreparedStatement pst;
            String requete1;
            requete1 = "INSERT INTO questionnaire ( nom_qst, nom_cat_qst, description_cat_qst, nbr_qst, Categoriequestionnaire_id, users_id)VALUES (?,?,?,?,?,?)";
            pst = cn2.prepareStatement(requete1);

            pst.setString(1, q.getNom_qst());
            pst.setString(2, q.getNom_cat_qst());
            pst.setString(3, q.getDescription_cat_qst());
            pst.setInt(4, q.getNbr_qst());
            pst.setInt(5, q.getCategoriequestionnaire_id());
            pst.setInt(6, q.getUsers_id());
            

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("errreur dans l'ajout de la categorie"+ex.getMessage());
        }

    }
        public void updateQuestionnaire(questionnaire q, int id) {
        try {
            PreparedStatement pst;
            String reqUpdate = "UPDATE questionnaire SET nom_qst=? ,nom_cat_qst=? ,description_cat_qst=? ,nbr_qst=? ,Categoriequestionnaire_id=? ,users_id=? where id=? ";

            PreparedStatement preparedStatement = cn2.prepareStatement(reqUpdate);
            pst = cn2.prepareStatement(reqUpdate);
            
            pst.setString(1, q.getNom_qst());
            pst.setString(2, q.getNom_cat_qst());
            pst.setString(3, q.getDescription_cat_qst());
            pst.setInt(4, q.getNbr_qst());
            pst.setInt(5, q.getCategoriequestionnaire_id());
            pst.setInt(6, q.getUsers_id());
            pst.setInt(7, id);
            pst.executeUpdate();
            
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
        
        
         public void deleteQuestionnaire(int idQuestionnaire) {
        try {

            String reqDel = "DELETE FROM questionnaire WHERE id=? ";
            PreparedStatement pst = cn2.prepareStatement(reqDel);
            pst.setInt(1, idQuestionnaire);
            pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }
             public questionnaire findByIdQuestionnaire(int id) {


        try {
            questionnaire q = new questionnaire();
            String requete = "SELECT * FROM questionnaire WHERE id=? ";
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               q.setId(rs.getInt("id"));
               q.setNbr_qst(rs.getInt("nbr_qst"));
               q.setCategoriequestionnaire_id(rs.getInt("categoriequestionnaire_id"));
               q.setDescription_cat_qst(rs.getString("description_cat_qst"));
               q.setNom_cat_qst(rs.getString("nom_cat_qst"));
               q.setUsers_id(rs.getInt("users_id"));
               q.setNom_qst(rs.getString("nom_qst"));


            }
            return q;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du Campement " + ex.getMessage());
            return null;
        }
    }
         
    
}
