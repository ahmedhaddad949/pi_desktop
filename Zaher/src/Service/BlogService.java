package Service;

import Entities.Blog;
import Entities.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlogService {

    private static final  String INSERT_QUERY = "INSERT INTO BLOG (nom,prenom,image,email,username,password,roles) VALUES (?,?,?,?,?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE BLOG set nom= ? ,prenom= ? ,image= ? ,email = ? ,username = ? ,password = ?,roles = ? where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   BLOG WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  BLOG  ";
    private static final  String SEARCH_QUERY = "SELECT *  FROM  BLOG WHERE UPPER(ID) LIKE ? OR UPPER(TITLE) LIKE ? OR  UPPER(DESCRIPTION) LIKE ?  ";


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
    public boolean add(Blog u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString (1, u.getTitle());
            preparedStmt.setString (2, u.getDescription());
            preparedStmt.setString (3, u.getImage());



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
    public boolean update(Blog u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setString (1, u.getTitle());
            preparedStmt.setString (2, u.getDescription());
            preparedStmt.setString (3, u.getImage());
            preparedStmt.setInt(8, id);
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
    public ObservableList<Blog> read()

    {
        ObservableList<Blog> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                Blog c =new Blog();
                c.setId(res.getInt("ID"));
                c.setTitle(res.getString("TITLE"));
                c.setDescription(res.getString("DESCRIPTION"));
                c.setImage(res.getString("IMAGE"));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public ObservableList<Blog> search( String index )

    {
        ObservableList<Blog> mylist= FXCollections.observableArrayList();

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
                Blog c =new Blog();
                c.setId(res.getInt("ID"));
                c.setTitle(res.getString("TITLE"));
                c.setDescription(res.getString("DESCRIPTION"));
                c.setImage(res.getString("IMAGE"));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
