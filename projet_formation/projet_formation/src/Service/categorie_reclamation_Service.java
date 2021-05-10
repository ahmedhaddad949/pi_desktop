/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.categorie_reclamation;
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
public class categorie_reclamation_Service {
     private static final  String INSERT_QUERY = "INSERT INTO `categorie_reclamation`( `nom`, `description`) VALUES (?,?)";
    private static final  String UPDATE_QUERY = "UPDATE categorie_reclamation set nom= ? ,description= ?  where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   categorie_reclamation WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  categorie_reclamation  ";

     public boolean add(categorie_reclamation u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString (1, u.getNom());
            preparedStmt.setString (2, u.getDescription());
 



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
    public boolean update(categorie_reclamation u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setString (1, u.getNom());
            preparedStmt.setString (2, u.getDescription());
            preparedStmt.setInt (3,id);
            preparedStmt.execute();
            return true;

        }
        catch (SQLException e)
        {
       
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
      
            return false ;
        }

    }
    public ObservableList<categorie_reclamation> read()

    {
        ObservableList<categorie_reclamation> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                categorie_reclamation c =new categorie_reclamation();
                c.setId(res.getInt("id"));
                c.setNom(res.getString("nom"));
                c.setDescription(res.getString("description"));
            

                mylist.add(c);
            }


        } catch (SQLException ex) {
       
        }
        System.out.print(mylist);
        return mylist;
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
         
        }
        System.out.print(mylist);
        return mylist;
    }
}
