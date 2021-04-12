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
import entities.offre_emploi;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author hadda
 */
public class offre_emploi_crud {
    
      Connection cn2;
    Statement st;

    public offre_emploi_crud() {
        cn2 = MyConnection.getInstance().getCnx();
    }
    
        public List<offre_emploi> displayAllOffre() {
        ArrayList<offre_emploi> list = new ArrayList<>();

        try {

            String requete3 = "SELECT * FROM offre_emploi ";
            PreparedStatement pst2 = cn2.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                offre_emploi o = new offre_emploi();
                o.setId(rs.getInt("id"));
                o.setTitre_offre_emploi(rs.getString("titre_offre_emploi"));
                o.setNbr_offres(rs.getInt("nbr_offres"));
                o.setDescription_cat_em(rs.getString("description_cat_em"));
                o.setCategorieEmploi_id(rs.getInt("categorieEmploi_id"));
                list.add(o);

            }

        } catch (SQLException ex) {
            System.out.println("erreur displayAllOffre_emploi" +ex.getMessage());
        }
        return list;

    }
    
     public void add_offre_emploi(offre_emploi o) {
        try {
            PreparedStatement pst;
            String requete1;
            requete1 = "INSERT INTO offre_emploi ( titre_offre_emploi, nbr_offres, description_cat_em, categorieEmploi_id)VALUES (?,?,?,?)";
            pst = cn2.prepareStatement(requete1);

            pst.setString(1, o.getTitre_offre_emploi());
            pst.setInt(2, o.getNbr_offres());
            pst.setString(3, o.getDescription_cat_em());
            pst.setInt(4, o.getCategorieEmploi_id());
            

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("errreur dans l'ajout de l'offre_emploi "+ex.getMessage());
        }

    }
        public void updateOffre_emploi(offre_emploi o, int id) {
        try {
            PreparedStatement pst;
            String reqUpdate = "UPDATE offre_emploi SET titre_offre_emploi=? ,nbr_offres=? ,description_cat_em=? ,categorieEmploi_id=? where id=? ";

            PreparedStatement preparedStatement = cn2.prepareStatement(reqUpdate);
            pst = cn2.prepareStatement(reqUpdate);
            
            pst.setString(1, o.getTitre_offre_emploi());
            pst.setInt(2, o.getNbr_offres());
            pst.setString(3, o.getDescription_cat_em());
            pst.setInt(4, o.getCategorieEmploi_id());

            pst.setInt(5, id);
            pst.executeUpdate();
            
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
        
        
         public void deleteOffre_emploi(int idOffre_emploi) {
        try {

            String reqDel = "DELETE FROM offre_emploi WHERE id=? ";
            PreparedStatement pst = cn2.prepareStatement(reqDel);
            pst.setInt(1, idOffre_emploi);
            pst.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }

    }
             public offre_emploi findByIdOffre_emploi(int id) {


        try {
            offre_emploi o = new offre_emploi();
            String requete = "SELECT * FROM offre_emploi WHERE id=? ";
            PreparedStatement ps = cn2.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               o.setId(rs.getInt("id"));
                o.setTitre_offre_emploi(rs.getString("titre_offre_emploi"));
                o.setNbr_offres(rs.getInt("nbr_offres"));
                o.setDescription_cat_em(rs.getString("description_cat_em"));
                o.setCategorieEmploi_id(rs.getInt("categorieEmploi_id"));


            }
            return o;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche de l'offre emploi " + ex.getMessage());
            return null;
        }
    }
         

    
}
