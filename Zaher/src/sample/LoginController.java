package sample;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.5), vbox);
        t.setToX(vbox.getLayoutX() * 20 );
        t.play();
        t.setOnFinished(e ->{
            try {

                fxml = FXMLLoader.load(getClass().getResource("signIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);


            }catch(IOException ex) {

            }
        });
    }
    @FXML
    private VBox vbox;
    private Parent fxml;

    @FXML
    public void open_signIn(javafx.event.ActionEvent actionEvent) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.3), vbox);
        t.setToX(vbox.getLayoutX() * 20 );
        t.play();
        t.setOnFinished(e ->{
            try {

                fxml = FXMLLoader.load(getClass().getResource("signIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);

            }catch(IOException ex) {

            }
        });
    }

    @FXML
    public void open_signUp(javafx.event.ActionEvent actionEvent) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.3), vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished(e ->{
            try {

                fxml = FXMLLoader.load(getClass().getResource("signUp.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);

            }catch(IOException ex) {

            }
        });
    }


}
