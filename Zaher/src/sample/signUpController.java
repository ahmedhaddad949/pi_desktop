package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class signUpController implements Initializable {

    @FXML
    private TextField Name;

    @FXML
    private TextField Firstname;

    @FXML
    private Button broweImage;

    @FXML
    private TextField Email;

    @FXML
    private TextField usrName;

    @FXML
    private PasswordField pwdsighUp;

    @FXML
    private ComboBox<?> roleBox;

    @FXML
    private Button signUpOk;

    @FXML
    private VBox anchorpane;


    @FXML
    void signUp(ActionEvent event) {
        String name = Name.getText();
        String fName = Firstname.getText();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
