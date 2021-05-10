package Service;

import Entities.Blog;
import Entities.categorie_reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;

public class Categorie_reclamationService {
    private static final  String INSERT_QUERY = "INSERT INTO categorie_reclamation (nom,description) VALUES (?,?)";
    private static final  String UPDATE_QUERY = "UPDATE categorie_reclamation set nom= ? ,description= ? where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   categorie_reclamation WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  categorie_reclamation  ";
    private static final  String SEARCH_QUERY = "SELECT *  FROM  categorie_reclamation WHERE UPPER(ID) LIKE ? OR UPPER(nom) LIKE ? OR  UPPER(description) LIKE ?  ";


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

            preparedStmt.setInt(3, id);
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
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
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
                c.setId(res.getInt("ID"));
                c.setNom(res.getString("nom"));
                c.setDescription(res.getString("description"));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public ObservableList<categorie_reclamation> search( String index )

    {
        ObservableList<categorie_reclamation> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            index = index.toUpperCase();
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            PreparedStatement ps = cnx.prepareStatement(SEARCH_QUERY);
            ps.setString (1, "%" +index+ "%");
            ps.setString (2, "%" +index+ "%");
            ps.setString (3, "%" +index+ "%");
            ResultSet res=ps.executeQuery();

            while (res.next()){
                categorie_reclamation c =new categorie_reclamation();
                c.setId(res.getInt("ID"));
                c.setNom(res.getString("nom"));
                c.setDescription(res.getString("DESCRIPTION"));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
