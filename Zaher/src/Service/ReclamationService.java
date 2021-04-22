package Service;

import Entities.formation;
import Entities.reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;

public class ReclamationService {

        private static final  String INSERT_QUERY = "INSERT INTO reclamation (categorie_id  ,user_id ,titre,text,date,etat,traitement ) VALUES (?,?,?,?,?,?,?)";
        private static final  String UPDATE_QUERY = "UPDATE reclamation set categorie_id = ? ,user_id= ? ,titre= ?,text=?,date=?,etat =?  traitement=?  where id= ? ";
        private static final  String DELETE_QUERY = "DELETE FROM   reclamation WHERE ID = ? ";
        private static final  String SELECT_QUERY = "SELECT *  FROM  reclamation  ";


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
        public boolean add(reclamation u) throws SQLException {
            try{
                Connection cnx = DataSource.getInstance().getCnx();
                PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
                preparedStmt.setInt (1, u.getCategorie_id());
                preparedStmt.setInt(2, u.getUser_id() );
                preparedStmt.setString (3, u.getTitre());
                preparedStmt.setString (4, u.getText());
                preparedStmt.setDate (5, new java.sql.Date(u.getDate().getTime()));
                preparedStmt.setInt (6, u.getEtat());
                preparedStmt.setInt (7, u.getTraitement());



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
        public boolean update(reclamation u , int id) throws SQLException {
            try{
                Connection cnx = DataSource.getInstance().getCnx();
                PreparedStatement preparedStmt = cnx.prepareStatement(UPDATE_QUERY);
                preparedStmt.setInt (1, u.getCategorie_id());
                preparedStmt.setInt(2, u.getUser_id() );
                preparedStmt.setString (3, u.getTitre());
                preparedStmt.setString (4, u.getText());
                preparedStmt.setDate (5, new java.sql.Date(u.getDate().getTime()));
                preparedStmt.setInt (6, u.getEtat());
                preparedStmt.setInt (7, u.getTraitement());

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
            catch (SQLException e)
            {
                printSQLException(e);
                return false ;
            }

        }
        public ObservableList<reclamation> read()

        {
            ObservableList<reclamation> mylist= FXCollections.observableArrayList();

            Statement st;
            try {
                Connection cnx = DataSource.getInstance().getCnx();
                st=cnx.createStatement();
                ResultSet res=st.executeQuery(SELECT_QUERY);
                while (res.next()){
                    reclamation c =new reclamation();
                    c.setId(res.getInt("ID"));
                    c.setCategorie_id(res.getInt("categorie_id "));
                    c.setUser_id(res.getInt("user_id "));
                    c.setTitre(res.getString("titre"));
                    c.setText(res.getString("text"));
                    c.setDate(res.getDate("date"));
                    c.setEtat(res.getInt("etat "));
                    c.setTraitement(res.getInt("traitement "));


                    mylist.add(c);
                }


            } catch (SQLException ex) {
                printSQLException(ex);
            }
            System.out.print(mylist);
            return mylist;

        }
}



