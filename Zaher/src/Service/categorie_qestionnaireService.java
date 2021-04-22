package Service;

import Entities.categorie_questionnaire;
import Entities.categorie_reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;

public class categorie_qestionnaireService {
    private static final  String INSERT_QUERY = "INSERT INTO categorie_questionnaire (nom_cat_qst,description_cat_qst,nbr_qst) VALUES (?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE categorie_questionnaire set nom_cat_qst= ? ,description_cat_qst= ? ,nbr_qst= ?  where id = ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   categorie_questionnaire WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  categorie_questionnaire  ";
    private static final  String SEARCH_QUERY = "SELECT *  FROM  categorie_questionnaire WHERE UPPER(ID) LIKE ? OR UPPER(nom_cat_qst) LIKE ? OR  UPPER(description_cat_qst) LIKE ?  ";


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
    public boolean add(categorie_questionnaire u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString (1, u.getNom_cat_qst());
            preparedStmt.setString (2, u.getDescription_cat_qst());
            preparedStmt.setInt (3, u.getNbr_qst());


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
    public boolean update(categorie_questionnaire u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setString (1, u.getNom_cat_qst());
            preparedStmt.setString (2, u.getDescription_cat_qst());
            preparedStmt.setInt (3, u.getNbr_qst());

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
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false ;
        }

    }
    public ObservableList<categorie_questionnaire> read()

    {
        ObservableList<categorie_questionnaire> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                categorie_questionnaire c =new categorie_questionnaire();
                c.setId(res.getInt("ID"));
                c.setNom_cat_qst(res.getString("nom"));
                c.setDescription_cat_qst(res.getString("description"));
                c.setNbr_qst(res.getInt("nbr_qst"));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public ObservableList<categorie_questionnaire> search( String index )

    {
        ObservableList<categorie_questionnaire> mylist= FXCollections.observableArrayList();

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

            ResultSet res=ps.executeQuery();

            while (res.next()){
                categorie_questionnaire c =new categorie_questionnaire();
                c.setId(res.getInt("ID"));
                c.setNom_cat_qst(res.getString("nom_cat_qst"));
                c.setNom_cat_qst(res.getString("description_cat_qst"));
                c.setNbr_qst(res.getInt("nbr_qst"));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
