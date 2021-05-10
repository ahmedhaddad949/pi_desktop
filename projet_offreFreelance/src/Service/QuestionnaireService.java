package Service;

import Entities.formation;
import Entities.questionnaire;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

import java.sql.*;
import javax.imageio.ImageIO;

public class QuestionnaireService {
    private static final  String INSERT_QUERY = "INSERT INTO questionnaire (users_id ,nom_qst,nom_cat_qst,description_cat_qst,nbr_qst,Categoriequestionnaire_id,qr ) VALUES (?,?,?,?,?,?,?)";
    private static final  String UPDATE_QUERY = "UPDATE questionnaire set users_id = ? ,nom_qst= ? ,nom_cat_qst= ?,description_cat_qst=?,nbr_qst=?,Categoriequestionnaire_id =?  where id= ? ";
    private static final  String DELETE_QUERY = "DELETE FROM questionnaire WHERE ID = ? ";
    private static final  String SELECT_QUERY = "SELECT *  FROM  questionnaire  ";
    private static final  String SEARCH_QUERY = "SELECT *  FROM  questionnaire WHERE UPPER(ID) LIKE ? OR UPPER(users_id) LIKE ? OR  UPPER(nom_qst) LIKE ? OR  UPPER(nom_cat_qst) LIKE ? OR  UPPER(description_cat_qst) LIKE ? OR UPPER(nbr_qst) LIKE ? OR  UPPER(Categoriequestionnaire_id) LIKE ? ";

public int nombre() {

        int i = 0;
        String requete = "SELECT COUNT(*) as nbr from questionnaire  ";

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
 public questionnaire get_question_affichage(int i) {
        questionnaire c = new questionnaire();
        int nombre = 0;
      String requete = "SELECT *  FROM  questionnaire "  ;
                        Connection cnx = DataSource.getInstance().getCnx();;
         try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                if (i == nombre) {
                    System.out.println("dqsdsq");
  
              c.setId(res.getInt("ID"));
                c.setUsers_id(res.getInt("users_id"));
                c.setNom_qst(res.getString("nom_qst"));
                c.setNom_cat_qst(res.getString("nom_cat_qst"));
                c.setDescription_cat_qst(res.getString("description_cat_qst"));
                c.setNbr_qst(res.getInt("nbr_qst"));
                c.setCategoriequestionnaire_id(res.getInt("Categoriequestionnaire_id"));
                c.setQr(res.getString("qr"));  
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
     public ObservableList<Integer> read_ids()

    {
        ObservableList<Integer> mylist= FXCollections.observableArrayList();

        Statement st;
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            st=cnx.createStatement();
            ResultSet res=st.executeQuery(SELECT_QUERY);
            while (res.next()){
        
                mylist.add(res.getInt("id"));
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
    public boolean add(questionnaire u) throws SQLException {
        try{
            
            
            
            
            
            
            
            String code = "image";

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
     
        String data = "desc : "+ u.getDescription_cat_qst()+"nom ques : "+u.getNom_qst();
        int width = 300;
        int height = 300;

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D image = (Graphics2D) bufferedImage.getGraphics();
            image.setColor(Color.WHITE);
            image.fillRect(0, 0, width, height);
            image.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        image.fillRect(i, j, 1, 1);
                    }
                }
            }
            if (ImageIO.write(bufferedImage, "png", new File("C:/wamp64/www/images/" + code + ".png"))) {
                System.out.println("-- saved");
            }
            System.out.println("QR created successfully....");

        } catch (WriterException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
            
            
            
            
            
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement preparedStmt = cnx.prepareStatement(INSERT_QUERY);
            preparedStmt.setInt (1, u.getUsers_id());
            preparedStmt.setString (2, u.getNom_qst());
            preparedStmt.setString (3, u.getNom_cat_qst());
            preparedStmt.setString (4, u.getDescription_cat_qst());
            preparedStmt.setInt (5, u.getNbr_qst());
            preparedStmt.setInt (6, u.getCategoriequestionnaire_id ());
                   preparedStmt.setString (7, code+".png");



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
                c.setCategoriequestionnaire_id(res.getInt("Categoriequestionnaire_id"));
                c.setQr(res.getString("qr"));


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
                c.setCategoriequestionnaire_id(res.getInt("Categoriequestionnaire_id"));

                mylist.add(c);
            }


        } catch (SQLException ex) {
            printSQLException(ex);
        }
        System.out.print(mylist);
        return mylist;
    }
}
