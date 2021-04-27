package Service;


import Entities.formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;

public class formationService {
    private static final  String INSERT_QUERY = "INSERT INTO formation (users_id ,duree_fr,description,image,niveau_frt,Categorieformation_id ) VALUES (?,?,?,?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE formation set users_id = ? ,duree_fr= ? ,description= ?,image=?,niveau_frt=?,Categorieformation_id =?  where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   formation WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  formation  ";
    private static final  String SEARCH_QUERY = "SELECT *  FROM  formation WHERE UPPER(ID) LIKE ? OR UPPER(users_id) LIKE ? OR  UPPER(duree_fr) LIKE ? OR  UPPER(description) LIKE ?OR  UPPER(niveau_frt) LIKE ?OR  UPPER(Categorieformation_id) LIKE ? ";
public int nombre() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr from formation  ";

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

 public formation get_formation_affichage(int i) {
        formation c = new formation();
        int nombre = 0;
      String requete = "SELECT *  FROM  formation "  ;
                        Connection cnx = DataSource.getInstance().getCnx();;
         try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                if (i == nombre) {
                    System.out.println("dqsdsq");
  
               c.setId(res.getInt("ID"));
                c.setUsers_id(res.getInt("users_id"));
                c.setDuree_fr(res.getDate("duree_fr"));
                c.setDescription(res.getString("description"));
                c.setImage(res.getString("image"));
                c.setNiveau_frt(res.getString("niveau_frt"));
                c.setCategorieformation_id(res.getInt("Categorieformation_id"));       
                }
                nombre++;
                         }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return c;

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
    public boolean add(formation u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt (1, u.getUsers_id());
            preparedStmt.setDate (2, new java.sql.Date(u.getDuree_fr().getTime()) );
            preparedStmt.setString (3, u.getDescription());
            preparedStmt.setString (4, u.getImage());
            preparedStmt.setString (5, u.getNiveau_frt());
            preparedStmt.setInt (6, u.getCategorieformation_id ());



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
    public boolean update(formation u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setInt (1, u.getUsers_id());
            preparedStmt.setDate (2, new java.sql.Date(u.getDuree_fr().getTime()) );
            preparedStmt.setString (3, u.getDescription());
            preparedStmt.setString (4, u.getImage());
            preparedStmt.setString (5, u.getNiveau_frt());
            preparedStmt.setInt (6, u.getCategorieformation_id ());


            preparedStmt.setInt(7, id);
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
    public ObservableList<formation> read()

    {
        ObservableList<formation> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                formation c =new formation();
                c.setId(res.getInt("ID"));
                c.setUsers_id(res.getInt("users_id"));
                c.setDuree_fr(res.getDate("duree_fr"));
                c.setDescription(res.getString("description"));
                c.setImage(res.getString("image"));
                c.setNiveau_frt(res.getString("niveau_frt"));
                c.setCategorieformation_id(res.getInt("Categorieformation_id"));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    
    public ObservableList<String> read_Des()

    {
        ObservableList<String> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
    

                mylist.add(res.getString("description"));
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public ObservableList<formation> Searsh(String cas)

    {
        ObservableList<formation> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery("SELECT * FROM `formation`  where  ( users_id LIKE '%" + cas + "%'or  duree_fr LIKE '%" + cas + "%' or  description LIKE '%" + cas + "%' or  niveau_frt LIKE '%" +  cas + "%' or  Categorieformation_id LIKE '%" + cas + "%' )");
            while (res.next()){
                formation c =new formation();
                c.setId(res.getInt("ID"));
                c.setUsers_id(res.getInt("users_id"));
                c.setDuree_fr(res.getDate("duree_fr"));
                c.setDescription(res.getString("description"));
                c.setImage(res.getString("image"));
                c.setNiveau_frt(res.getString("niveau_frt"));
                c.setCategorieformation_id(res.getInt("Categorieformation_id"));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public ObservableList<formation> search(String index )

    {
        ObservableList<formation> mylist= FXCollections.observableArrayList();

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
            ps.setString (6, "%" +index+ "%");
            ResultSet res=ps.executeQuery();

            while (res.next()){
                formation c =new formation();
                c.setId(res.getInt("ID"));
                c.setUsers_id(res.getInt("users_id"));
                c.setDuree_fr(res.getDate("duree_fr"));
                c.setDescription(res.getString("description"));
                c.setImage(res.getString("image"));
                c.setNiveau_frt(res.getString("niveau_frt"));
                c.setCategorieformation_id(res.getInt("Categorieformation_id"));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
