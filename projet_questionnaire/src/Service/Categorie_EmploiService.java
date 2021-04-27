/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;



import Entities.Categorie_emploi;
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
public class Categorie_EmploiService {
     private static final  String INSERT_QUERY = "INSERT INTO categorie_emploi (nom_emploi,description_emploi,nbr_offres) VALUES (?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE categorie_emploi set nom_emploi= ? ,description_emploi= ? ,nbr_offres= ? where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   categorie_emploi WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  categorie_emploi  ";
 public boolean incrementer( int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement("UPDATE categorie_emploi set nbr_offre_fr= nbr_offre_fr+1  where id = ? ");
        

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
     public ObservableList<Integer> read_ids()

    {
        ObservableList<Integer> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
        
                mylist.add(res.getInt("id"));
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
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
    public boolean add(Categorie_emploi u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString (1, u.getNom_cat_emp());
            preparedStmt.setString (2, u.getDescription_cat_emp());
            preparedStmt.setInt (3, u.getNbr_offre_emp());



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
    public boolean update(Categorie_emploi u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setString (1, u.getNom_cat_emp());
            preparedStmt.setString (2, u.getDescription_cat_emp());
            preparedStmt.setInt (3, u.getNbr_offre_emp());

            preparedStmt.setInt(4, id);
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
    public ObservableList<Categorie_emploi> read()

    {
        ObservableList<Categorie_emploi> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                Categorie_emploi c =new Categorie_emploi();
                c.setId(res.getInt("id"));
                c.setNom_cat_emp(res.getString("nom_emploi"));
                c.setDescription_cat_emp(res.getString("description_emploi"));
                c.setNbr_offre_emp(res.getInt("nbr_offres"));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
