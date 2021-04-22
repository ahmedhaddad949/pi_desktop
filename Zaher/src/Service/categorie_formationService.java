package Service;

import Entities.Users;
import Entities.categorie_formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;

public class categorie_formationService {
    private static final  String INSERT_QUERY = "INSERT INTO categorie_formation (nom_cat_frt,description_cat_frt,nbr_cat_frt) VALUES (?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE categorie_formation set nom_cat_frt= ? ,description_cat_frt= ? ,nbr_cat_frt= ? where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   categorie_formation WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  categorie_formation  ";


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
    public boolean add(categorie_formation u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString (1, u.getNom_cat_frt());
            preparedStmt.setString (2, u.getDescription_cat_frt());
            preparedStmt.setInt (3, u.getNbr_cat_frt());



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
    public boolean update(categorie_formation u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setString (1, u.getNom_cat_frt());
            preparedStmt.setString (2, u.getDescription_cat_frt());
            preparedStmt.setInt (3, u.getNbr_cat_frt());

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
    public ObservableList<categorie_formation> read()

    {
        ObservableList<categorie_formation> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                categorie_formation c =new categorie_formation();
                c.setId(res.getInt("ID"));
                c.setNom_cat_frt(res.getString("NOM"));
                c.setDescription_cat_frt(res.getString("PRENOM"));
                c.setNbr_cat_frt(res.getInt("IMAGE"));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
