package Service;

import Entities.formation;
import Entities.offre_freelance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;

public class offre_freelanceService {
    private static final  String INSERT_QUERY = "INSERT INTO offre_freelance (titre_offre_fr ,descriptionfr,entreprisefr,recomponse,date_expiration,etat_offre,categorie_freelance_id  ) VALUES (?,?,?,?,?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE offre_freelance set titre_offre_fr = ? ,descriptionfr= ? ,entreprisefr= ?,recomponse=?,date_expiration=?,etat_offre =? ,categorie_freelance_id  =? where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM   offre_freelance WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  offre_freelance  ";
    private static final  String SEARCH_QUERY = "SELECT *  FROM  offre_freelance WHERE UPPER(ID) LIKE ? OR UPPER(users_id) LIKE ? OR  UPPER(titre_offre_fr) LIKE ? OR  UPPER(descriptionfr) LIKE ? OR  UPPER(entreprisefr) LIKE ? OR  UPPER(recomponse) LIKE ? OR  UPPER(date_expiration) LIKE ? OR  UPPER(etat_offre) LIKE ? OR  UPPER(categorie_freelance_id) LIKE ?";


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
    public boolean add(offre_freelance u) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setString (1, u.getTitre_offre_fr());
            preparedStmt.setString (2, u.getDescriptionfr() );
            preparedStmt.setString (3, u.getEntreprisefr());
            preparedStmt.setDouble (4, u.getRecomponse());
            preparedStmt.setDate (5, new java.sql.Date(u.getDate_expiration().getTime()));
            preparedStmt.setInt (6, u.getEtat_offre ());
            preparedStmt.setInt(7,u.getCategorie_freelance_id());



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
    public boolean update(offre_freelance u , int id) throws SQLException {
        try{
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
            preparedStmt.setString (1, u.getTitre_offre_fr());
            preparedStmt.setString (2, u.getDescriptionfr() );
            preparedStmt.setString (3, u.getEntreprisefr());
            preparedStmt.setDouble (4, u.getRecomponse());
            preparedStmt.setDate (5, new java.sql.Date(u.getDate_expiration().getTime()));
            preparedStmt.setInt (6, u.getEtat_offre ());
            preparedStmt.setInt(7,u.getCategorie_freelance_id());
            preparedStmt.setInt(8,u.getId());

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
    public ObservableList<offre_freelance> read()

    {
        ObservableList<offre_freelance> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
                offre_freelance c =new offre_freelance();
                c.setId(res.getInt("ID"));
                c.setTitre_offre_fr(res.getString("titre_offre_fr"));
                c.setDescriptionfr(res.getString("descriptionfr"));
                c.setEntreprisefr(res.getString("entreprisefr"));
                c.setRecomponse(res.getDouble("recomponse"));
                c.setDate_expiration(res.getDate("date_expiration"));
                c.setEtat_offre(res.getInt("etat_offre "));
                c.setCategorie_freelance_id(res.getInt("categorie_freelance_id "));


                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public ObservableList<offre_freelance> search(String index )
    {
        ObservableList<offre_freelance> mylist= FXCollections.observableArrayList();

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
            ps.setString (8, "%" +index+ "%");
            ResultSet res=ps.executeQuery();

            while (res.next()){
                offre_freelance c =new offre_freelance();
                c.setId(res.getInt("ID"));
                c.setTitre_offre_fr(res.getString("titre_offre_fr"));
                c.setDescriptionfr(res.getString("descriptionfr"));
                c.setEntreprisefr(res.getString("entreprisefr"));
                c.setRecomponse(res.getDouble("recomponse"));
                c.setDate_expiration(res.getDate("date_expiration"));
                c.setEtat_offre(res.getInt("etat_offre"));
                c.setCategorie_freelance_id(res.getInt("categorie_freelance_id"));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
