package Service;

import Entities.Blog;
import Entities.Users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.io.IOException;
import java.sql.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsersService  {

    private Statement ste=null;
    private Connection connection = DataSource.getInstance().getCnx();

    private static final String LOGIN_QUERY = "SELECT * FROM users WHERE username = ? and password = ? ";
    private static final String INSERT_QUERY = "INSERT INTO USERS (nom,prenom,image,email,username,password,roles) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE USERS set nom= ? ,prenom= ? ,image= ? ,email = ? ,username = ? ,password = ?,roles = ? where id= ? ";
    private static final String DELETE_QUERY = "DELETE FROM   USERS WHERE ID = ? ";
    private static final String SELECT_QUERY = "SELECT *  FROM  USERS  ";
    private static final String SEARCH_QUERY = "SELECT *  FROM  USERS WHERE UPPER(ID) LIKE ? OR UPPER(nom) LIKE ? OR  UPPER(prenom) LIKE ? OR  UPPER(email) LIKE ?OR  UPPER(username) LIKE ?OR  UPPER(roles) LIKE ? ";
    private boolean test = false;


    public boolean validateLogin(String username, String password) throws SQLException {

        try {
            Connection cnx = DataSource.getInstance().getCnx();

            PreparedStatement preparedStatement = cnx.prepareStatement(LOGIN_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                test = true;

            }


        } catch (SQLException e) {
            // print SQL exception information

            test = false;
            printSQLException(e);

        }
        return test;
    }

    public ArrayList<Users> selectAllEnabled() {
        ArrayList<Users> users = new ArrayList<>();
        ResultSet rs;
        try {

            rs = ste.executeQuery("SELECT * FROM Users where enabled=1");
            users = new ArrayList<>();
            while (rs.next()){
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  users;
    }

    public void ajouter(Users user) {
        String req = "INSERT INTO users (photo_profil,langitude,latitude,username,username_canonical,email,email_canonical,enabled,salt,password,roles,prenom) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" ;
        PreparedStatement pre;
        try {
            pre = connection.prepareStatement(INSERT_QUERY);

            pre.setString(1, user.getNom());
            pre.setString(2, user.getPrenom());
            pre.setString(3, user.getImage());
            pre.setString(4, user.getEmail());
            pre.setString(5, user.getUsername());
            pre.setString(6, user.getPassword());
            pre.setString(7, user.getRoles());

            if(user.getRoles().equals("ROLE_FREELANCER"))
                pre.setString(8,"a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
            else
            if(user.getRoles().equals("ROLE_ADMIN"))
                pre.setString(8,"a:1:{i:0;s:18:\"ROLE_ETABLISSEMENT\";}");

            else
            if(user.getRoles().equals("ROLE_USER"))
                pre.setString(8,"a:1:{i:0;s:18:\"ROLE_ETABLISSEMENT\";}");
            else
            if(user.getRoles().equals("ROLE_EMPLOYEUR"))
                pre.setString(8,"a:1:{i:0;s:18:\"ROLE_ETABLISSEMENT\";}");
            else
            if(user.getRoles().equals("ROLE_EMPLOYE"))
                pre.setString(8,"a:1:{i:0;s:18:\"ROLE_ETABLISSEMENT\";}");



            pre.executeUpdate();
            System.out.println("Utilisateur ajouter avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public boolean add(Users u) throws SQLException {
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString(1, u.getNom());
            preparedStmt.setString(2, u.getPrenom());
            preparedStmt.setString(3, u.getImage());
            preparedStmt.setString(4, u.getEmail());
            preparedStmt.setString(5, u.getUsername());
            preparedStmt.setString(6, u.getPassword());
            preparedStmt.setString(7, u.getRoles());


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
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setString(1, u.getNom());
            preparedStmt.setString(2, u.getPrenom());
            preparedStmt.setString(3, u.getImage());
            preparedStmt.setString(4, u.getEmail());
            preparedStmt.setString(5, u.getUsername());
            preparedStmt.setString(6, u.getPassword());
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
