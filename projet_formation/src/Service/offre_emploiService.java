/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.offre_emploi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author Aymen
 */
public class offre_emploiService {
       private static final  String INSERT_QUERY = "INSERT INTO `offre_emploi`( `titre_offre_emploi`, `description_cat_em`, `nbr_offres`, `CategorieEmploi_id`) VALUES (?,?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE offre_emploi `offre_emploi` SET `titre_offre_emploi`=?,`description_cat_em`=?,`nbr_offres`=?,`CategorieEmploi_id`=?  where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   offre_emploi WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  offre_emploi  ";
    private static final  String SEARCH_QUERY = "SELECT *  FROM  offre_emploi WHERE UPPER(ID) LIKE ? OR UPPER(titre_offre_emploi) LIKE ? OR  UPPER(description_cat_em) LIKE ? OR  UPPER(nbr_offres) LIKE ? OR  UPPER(CategorieEmploi_id) LIKE ? ";
 
    
    
    
    public offre_emploi get_offre_emploi_affichage(int i) {
        offre_emploi c = new offre_emploi();
        int nombre = 0;
      String requete = "SELECT *  FROM  offre_emploi "  ;
                        Connection cnx = DataSource.getInstance().getCnx();;
         try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                if (i == nombre) {
                    System.out.println("dqsdsq");
  
                c.setId(res.getInt("ID"));
                c.setTitre_offre_emploi(res.getString("titre_offre_emploi"));
                c.setDescription_cat_em(res.getString("description_cat_em"));

                c.setNbr_offres(res.getInt("nbr_offres"));
                c.setCategorieEmploi_id(res.getInt("CategorieEmploi_id"));   
                }
                nombre++;
                         }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return c;

    }
public int nombre() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr from offre_emploi  ";

        try {
                Connection cnx = DataSource.getInstance().getCnx();;
             PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i = rs.getInt("nbr");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    public boolean add(offre_emploi u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString (1, u.getTitre_offre_emploi());
            preparedStmt.setString (2, u.getDescription_cat_em());

            preparedStmt.setInt (3, u.getNbr_offres());
            preparedStmt.setInt(4,u.getCategorieEmploi_id());



            preparedStmt.execute();
            return true;

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false ;
        }

    }
    public boolean update(offre_emploi u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
      preparedStmt.setString (1, u.getTitre_offre_emploi());
            preparedStmt.setString (2, u.getDescription_cat_em());

            preparedStmt.setInt (3, u.getNbr_offres());
            preparedStmt.setInt(4,u.getCategorieEmploi_id());
            preparedStmt.setInt(5,id);

            preparedStmt.execute();
            return true;

        }
        catch (SQLException e)
        {
            printSQLException(e);
            return false ;
        }

    }
    public boolean delete( int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_QUERY);

            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            return true;

        }
        catch (SQLException e)
        {
            printSQLException(e);
            return false ;
        }

    }
    public ObservableList<offre_emploi> read()

    {
        ObservableList<offre_emploi> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                offre_emploi c =new offre_emploi();
                c.setId(res.getInt("ID"));
                c.setTitre_offre_emploi(res.getString("titre_offre_emploi"));
                c.setDescription_cat_em(res.getString("description_cat_em"));

                c.setNbr_offres(res.getInt("nbr_offres"));
                c.setCategorieEmploi_id(res.getInt("CategorieEmploi_id"));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public ObservableList<offre_emploi> search(String index )
    {
        ObservableList<offre_emploi> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            index = index.toUpperCase();
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            PreparedStatement ps = cnx.prepareStatement(SEARCH_QUERY);
            ps.setString (1, "%" +index+ "%");
            ps.setString (2, "%" +index+ "%");
            ps.setString (3, "%" +index+ "%");
            ps.setString (4, "%" +index+ "%");
            ps.setString (5, "%" +index+ "%");
   
            ResultSet res=ps.executeQuery();

            while (res.next()){
                offre_emploi c =new offre_emploi();
     
                c.setId(res.getInt("ID"));
                c.setTitre_offre_emploi(res.getString("titre_offre_emploi"));
                c.setDescription_cat_em(res.getString("description_cat_em"));

                c.setNbr_offres(res.getInt("nbr_offres"));
                c.setCategorieEmploi_id(res.getInt("CategorieEmploi_id"));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
