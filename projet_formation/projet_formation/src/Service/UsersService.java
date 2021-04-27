package Service;

import Entities.Blog;
import Entities.Users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.security.crypto.bcrypt.BCrypt;
import util.DataSource;

import java.io.IOException;
import java.sql.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsersService  {

    private Statement ste=null;
    private Connection cnx = DataSource.getInstance().getCnx();

    private static final String INSERT_QUERY = "INSERT INTO USERS (nom,prenom,email,username,password,roles) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE USERS set nom= ? ,prenom= ? ,image= ? ,email = ? ,username = ? ,password = ?,roles = ? where id= ? ";
    private static final String DELETE_QUERY = "DELETE FROM   USERS WHERE ID = ? ";
    private static final String SELECT_QUERY = "SELECT *  FROM  USERS  ";
    private static final String SEARCH_QUERY = "SELECT *  FROM  USERS WHERE UPPER(ID) LIKE ? OR UPPER(nom) LIKE ? OR  UPPER(prenom) LIKE ? OR  UPPER(email) LIKE ?OR  UPPER(username) LIKE ?OR  UPPER(roles) LIKE ? ";
    private boolean test = false;


    public boolean validateLogin(String username, String password) throws SQLException {
        String LOGIN_QUERY = "SELECT * FROM users WHERE username = '"+username+"' ";


            ResultSet rs  = cnx.createStatement().executeQuery(LOGIN_QUERY);
            if (rs.next()) {

                String pwd = rs.getString("password");
                HashPasswordService pw = new HashPasswordService();


                // CHECKS
                System.out.println(password);

                System.out.println(username);
                // END-CHECKS
                if (pw.checkPassword( password,pw.Hashpwd(password)))
                {
                    test = true;
                }
                else{
                    System.out.println("Failed to login");
                    test = false;
                }


            }
        return test;
    }

    // SQL EXCEPTION WITH STYLE BABY ;)
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

    public boolean add(Users u) throws SQLException {
        try {
            HashPasswordService pw = new HashPasswordService();

            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString(1, u.getNom());
            preparedStmt.setString(2,  u.getPrenom());
           // preparedStmt.setString(3, u.getImage());
            preparedStmt.setString(3, u.getEmail());
            preparedStmt.setString(4, u.getUsername());
            preparedStmt.setString(5, pw.Hashpwd(u.getPassword()));
            preparedStmt.setString(6, u.getRoles());


            preparedStmt.execute();
            return true;

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public boolean update(Users u, int id) throws SQLException {
        try {
            HashPasswordService pw = new HashPasswordService();
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setString(1, u.getNom());
            preparedStmt.setString(2, u.getPrenom());
            preparedStmt.setString(3, u.getImage());
            preparedStmt.setString(4, u.getEmail());
            preparedStmt.setString(5, u.getUsername());
            preparedStmt.setString(6,  pw.Hashpwd(u.getPassword()));
            preparedStmt.setString(7, u.getRoles());
            preparedStmt.setInt(8, id);
            preparedStmt.execute();
            return true;

        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }

    }

    public boolean delete(int id) throws SQLException {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(DELETE_QUERY);

            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            return true;

        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }

    }

    public ObservableList<Users> read() {
        ObservableList<Users> mylist = FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(SELECT_QUERY);
            while (res.next()) {
                Users c = new Users();
                c.setId(res.getInt("ID"));
                c.setNom(res.getString("NOM"));
                c.setPrenom(res.getString("PRENOM"));
                c.setImage(res.getString("IMAGE"));
                c.setEmail(res.getString("EMAIL"));
                c.setUsername(res.getString("USERNAME"));
                c.setPassword(res.getString("PASSWORD"));
                c.setRoles(res.getString("ROLES"));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }

    public ObservableList<Users> search(String index) {
        ObservableList<Users> mylist = FXCollections.observableArrayList();

        Statement st;
        try {
            index = index.toUpperCase();
            Connection cnx = DataSource.getInstance().getCnx();
            st = cnx.createStatement();
            PreparedStatement ps = cnx.prepareStatement(SEARCH_QUERY);
            ps.setString(1, "%" + index + "%");
            ps.setString(2, "%" + index + "%");
            ps.setString(3, "%" + index + "%");
            ps.setString(4, "%" + index + "%");
            ps.setString(5, "%" + index + "%");
            ps.setString(6, "%" + index + "%");
            ResultSet res = ps.executeQuery();

            while (res.next())
            {
                Users c = new Users();
                c.setId(res.getInt("ID"));
                c.setNom(res.getString("nom"));
                c.setPrenom(res.getString("prenom"));
                c.setImage(res.getString("IMAGE"));
                c.setEmail(res.getString("email"));
                c.setUsername(res.getString("username"));
                c.setRoles(res.getString("roles"));
                mylist.add(c);
            }
        }
        catch (SQLException ex)
        {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }



}
