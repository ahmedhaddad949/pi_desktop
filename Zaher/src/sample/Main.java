package sample;

import Entities.Users;
import Entities.UsersStats;
import Service.*;
import animatefx.animation.FadeIn;
import com.kosprov.jargon2.api.Jargon2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.security.crypto.bcrypt.BCrypt;

import util.DataSource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;

import static com.kosprov.jargon2.api.Jargon2.jargon2Hasher;
import static javafx.application.Application.launch;


public class Main extends Application {
    public static Stage stage;
    private Users loggedUser=new Users();





    DataSource ds = DataSource.getInstance();


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("INFIJOB");
        primaryStage.setScene(scene);
        primaryStage.show();
        new FadeIn(root).play();
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            logout(primaryStage);
        });
    }

    public void logout(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to exit");
        alert.setContentText("Are you sure ? ");
        if (alert.showAndWait().get() == ButtonType.OK) {

            System.out.println("You have exited");
            primaryStage.close();
        }
    }

    private static String USER_NAME = "username";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "password"; // GMail password

    private static String RECIPIENT = "xxxxx@gmail.com";
    
    public static void main(String[] args) throws SQLException, ParseException {
       /* UsersService t = new UsersService();
        t.read();*/
              /* Users user = new Users( "testt", "test", "jpg", "zaher.zaher@esprit.ttn", "hammadi", "test", "ROLE_ADMIN");

        t.search("zaher");
        boolean test = t.add(user);
        if (test){
            System.out.println("added");
        }
        else {
            System.out.println("not added");
        }
        Users updateUser = new Users("update", "update", "update", "zaher.zaher@esprit.ttn", "update", "update", "ROLE_ADMIN");
        boolean test2 = t.update(updateUser,574) ;
        if (test2){
            System.out.println("update done ;) ");
        }
        else{
            System.out.println("Update not done :( ");
        }

        boolean test3 = t.delete(574);
        if(test3){
            System.out.println("Deleted jawek behy  ;) ");
        }
        else{
            System.out.println("Jawek mosh behy ");
        }

        /*System.out.println("\n");
        BlogService b = new BlogService();
        b.search("hjf");
        System.out.println("\n");
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        formation c = new formation(579,date1,"this is a test ","image test ","hard",12);
        formationService fs = new formationService();
        boolean test69 = fs.add(c);
        if (test69){
            System.out.println("jawek behy formation");
        }
        else{
            System.out.println("oops test69 failed");
        }
        reclamation y = new reclamation( 6,579, "ez","ezee",date1,5,6);
        ReclamationService tt = new ReclamationService();
        tt.add(y);
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        formation fr = new formation(585,date1,"this is a description ", "imageurl","niveau",14);
        formationService sv = new formationService();
        sv.add(fr);
        sv.delete(62);*/

        // mailing
        /*String from = "zahertestamri@gmail.com";
        String pass = "swdzaher2050";
        String[] to = { "mohamed.mansour@esprit.tn" }; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = "test test   ";
        MailService serv = new MailService();
        serv.sendFromGMail(from, pass, to, subject, body);*/
        // end mailing
       //hash password
        /*HashPasswordService pw = new HashPasswordService();
        String Hashed =pw.Hashpwd("Ahla");
        String Input = "Ahla";
        String test = BCrypt.hashpw(Input,Hashed);
        System.out.println("Hashed test " +test);
        */
        // End hash password
       // UsersService t = new UsersService();
        /*Users user = new Users( "testt", "test", "76605236-2133901326904910-8052490271801212928-n-2-6061fe7531b5f340952460.jpg", "zaher.zaher@esprit.ttn", "hammadi", "test", "ROLE_ADMIN");
        t.ajouter(user);*/
        // 2nd hash pass
       /* Sha52 Sha52 = FOSJCrypt.crypt("123456");
        System.out.println(Sha52.getSalt());
        System.out.println(Sha52.getHash());
        */

        byte[] password = "123456".getBytes();
        Jargon2.Hasher hasher = jargon2Hasher()
                .type(Jargon2.Type.ARGON2id.ARGON2id)
                .memoryCost(65536)  // 64MB memory cost
                .timeCost(4)        // 3 passes through memory
                .parallelism(1)     // use 4 lanes and 4 threads
                .saltLength(8)     // 16 random bytes salt
                .hashLength(64);    // 16 bytes output hash

        String encodedHash = hasher.password(password).encodedHash();
        System.out.printf("Hash:  %s%n ", encodedHash);

        UsersDSService x = new UsersDSService();
        x.rolesStats();
        ProfileService p = new ProfileService();
        p.formationNumber(112);
        launch(args);



    }


}


