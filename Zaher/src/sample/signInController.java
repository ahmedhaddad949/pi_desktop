package sample;

import Entities.Users;
import Service.UserSession;
import Service.UsersService;
import Service.resetPwdService;
import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import util.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

public class signInController implements Initializable {
    private static final String LOGIN_QUERY = "SELECT * FROM users WHERE username = '";

    private Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private TextField usrNamesignIn;
    @FXML
    private PasswordField pwdsignIn;
    @FXML
    private AnchorPane mainPane;


   @FXML
    public void loginUser(ActionEvent actionEvent) throws SQLException, IOException {


       UsersService sv = new UsersService();
        boolean test =sv.validateLogin(usrNamesignIn.getText(),pwdsignIn.getText());

        if (!test) {
            System.out.println("Please enter correct Email and Password");

        } else {


            ResultSet rs = cnx.createStatement().executeQuery("Select * from users where username = '"+usrNamesignIn.getText()+"' ");
            if (rs.next()){
                int s = rs.getInt("id");
                System.out.println(s);
                String role = rs.getString("roles");
                System.out.println(role);
                String username =usrNamesignIn.getText();
                UserSession.getInstance(usrNamesignIn.getText(), role);

                System.out.println("Login Successful ! ");

                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                Stage primaryStage = new Stage();
                primaryStage.initStyle(StageStyle.TRANSPARENT);
                primaryStage.setTitle("INFIJOB");
                primaryStage.setScene(scene);
                primaryStage.show();
                new FadeIn(root).play();
            }




        }
    }

    @FXML
    private Button forgotPassword;
    @FXML
    void forgotPwd(ActionEvent event) {
        resetPwdService c = new resetPwdService();
        if(c.resetPwd(usrNamesignIn.getText())){
            System.out.println("jawek behy mail send");
            Notifications not = Notifications.create()
                    .title("PASSWORD RESET")
                    .text("NEW VALUE OF PASSWORD SENT TO YOUR MAIL MR "+usrNamesignIn.getText())
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            not.showConfirm();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
