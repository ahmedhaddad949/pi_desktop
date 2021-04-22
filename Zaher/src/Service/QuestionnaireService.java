package Service;

import Entities.formation;
import Entities.questionnaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;

public class QuestionnaireService {
    private static final  String INSERT_QUERY = "INSERT INTO questionnaire (users_id ,nom_qst,nom_cat_qst,description_cat_qst,nbr_qst,Categoriequestionnaire_id ) VALUES (?,?,?,?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE questionnaire set users_id = ? ,nom_qst= ? ,nom_cat_qst= ?,description_cat_qst=?,nbr_qst=?,Categoriequestionnaire_id =?  where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM questionnaire WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  questionnaire  ";
    private static final  String SEARCH_QUERY = "SELECT *  FROM  questionnaire WHERE UPPER(ID) LIKE ? OR UPPER(users_id) LIKE ? OR  UPPER(nom_qst) LIKE ? OR  UPPER(nom_cat_qst) LIKE ? OR  UPPER(description_cat_qst) LIKE ? OR UPPER(nbr_qst) LIKE ? OR  UPPER(Categoriequestionnaire_id) LIKE ? ";


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
    public boolean add(questionnaire u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt (1, u.getUsers_id());
            preparedStmt.setString (2, u.getNom_qst());
            preparedStmt.setString (3, u.getNom_cat_qst());
            preparedStmt.setString (4, u.getDescription_cat_qst());
            preparedStmt.setInt (5, u.getNbr_qst());
            preparedStmt.setInt (6, u.getCategoriequestionnaire_id ());



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
    public boolean update(questionnaire u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setInt (1, u.getUsers_id());
            preparedStmt.setString (2,u.getNom_cat_qst() );
            preparedStmt.setString (3, u.getNom_cat_qst());
            preparedStmt.setString (4, u.getDescription_cat_qst());
            preparedStmt.setInt (5, u.getNbr_qst());
            preparedStmt.setInt (6, u.getCategoriequestionnaire_id ());


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
    public ObservableList<questionnaire> read()

    {
        ObservableList<questionnaire> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                questionnaire c =new questionnaire();
                c.setId(res.getInt("ID"));
                c.setUsers_id(res.getInt("users_id"));
                c.setNom_qst(res.getString("nom_qst"));
                c.setNom_cat_qst(res.getString("nom_cat_qst"));
                c.setDescription_cat_qst(res.getString("description_cat_qst"));
                c.setNbr_qst(res.getInt("nbr_qst"));
                c.setCategoriequestionnaire_id(res.getInt("Categoriequestionnaire_id "));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public ObservableList<questionnaire> search(String index )

    {
        ObservableList<questionnaire> mylist= FXCollections.observableArrayList();

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
            ps.setString (7, "%" +index+ "%");

            ResultSet res=ps.executeQuery();

            while (res.next()){
                questionnaire c =new questionnaire();
                c.setId(res.getInt("ID"));
                c.setUsers_id(res.getInt("users_id"));
                c.setNom_qst(res.getString("nom_qst"));
                c.setNom_cat_qst(res.getString("nom_cat_qst"));
                c.setDescription_cat_qst(res.getString("description_cat_qst"));
                c.setNbr_qst(res.getInt("nbr_qst"));
                c.setCategoriequestionnaire_id(res.getInt("Categoriequestionnaire_id "));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
