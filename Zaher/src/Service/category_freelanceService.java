package Service;

import Entities.categorie_formation;
import Entities.category_freelance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;

public class category_freelanceService {
    private static final  String INSERT_QUERY = "INSERT INTO category_freelance (nom_cat_fr,description_cat_fr,nbr_offre_fr) VALUES (?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE category_freelance set nom_cat_fr= ? ,description_cat_fr= ? ,nbr_cat_fr= ? where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   category_freelance WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  category_freelance  ";


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
    public boolean add(category_freelance u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString (1, u.getNom_cat_fr());
            preparedStmt.setString (2, u.getDescription_cat_fr());
            preparedStmt.setInt (3, u.getNbr_offre_fr());



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
    public boolean update(category_freelance u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setString (1, u.getNom_cat_fr());
            preparedStmt.setString (2, u.getDescription_cat_fr());
            preparedStmt.setInt (3, u.getNbr_offre_fr());

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
    public ObservableList<category_freelance> read()

    {
        ObservableList<category_freelance> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                category_freelance c =new category_freelance();
                c.setId(res.getInt("ID"));
                c.setNom_cat_fr(res.getString("nom_cat_fr"));
                c.setDescription_cat_fr(res.getString("description_cat_fr"));
                c.setNbr_offre_fr(res.getInt("nbr_offre_fr"));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
