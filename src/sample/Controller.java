 package sample;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    @FXML
    private Label rootLbl;

    @FXML
    private ImageView logo;

    @FXML
    private Label pageLbl;

    @FXML
    private Button acceuilBtn;

    @FXML
    private Button freelaneBtn;

    @FXML
    private Button emploiBtn;

    @FXML
    private Button questionnaireBtn;

    @FXML
    private Button formationBtn;

    @FXML
    private Button reclamationBtn;

    @FXML
    private Button utilisateursBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button parametresBtn;

    @FXML
    private Pane statusPane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void handleClicks(ActionEvent event) {
        if(event.getSource() == acceuilBtn) {
            rootLbl.setText("/Accueil");
            pageLbl.setText("Accueil");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(5    , 172      ,250) , CornerRadii.EMPTY, Insets.EMPTY)) );


        }
        else if (event.getSource() == freelaneBtn){
            rootLbl.setText("/CategorieFreelance");
            pageLbl.setText("Freelance");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(250   , 5  ,5) , CornerRadii.EMPTY, Insets.EMPTY)) );

        }
        else if (event.getSource() == emploiBtn){
            rootLbl.setText("/CategorieEmploi");
            pageLbl.setText("Emploi");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(215   , 62  ,15) , CornerRadii.EMPTY, Insets.EMPTY)) );

        }
        else if (event.getSource() == questionnaireBtn){
            rootLbl.setText("/CategorieQuestionnaire");
            pageLbl.setText("Questionaires");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(215   , 189  ,15) , CornerRadii.EMPTY, Insets.EMPTY)) );

        }
        else if (event.getSource() == formationBtn){
            rootLbl.setText("/CategorieFormation");
            pageLbl.setText("Formations");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(67  , 206 ,47) , CornerRadii.EMPTY, Insets.EMPTY)) );
        }
        else if (event.getSource() == reclamationBtn){
            rootLbl.setText("/CategorieReclamation");
            pageLbl.setText("Reclamation");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(93,13,49) , CornerRadii.EMPTY, Insets.EMPTY)) );
        }
        else if (event.getSource() == utilisateursBtn){
            rootLbl.setText("/Users");
            pageLbl.setText("Users");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(68 ,97 ,255) , CornerRadii.EMPTY, Insets.EMPTY)) );
    }
        else if (event.getSource() == profileBtn){
            rootLbl.setText("/Profile");
            pageLbl.setText("Profile");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(43,60,99) , CornerRadii.EMPTY, Insets.EMPTY)) );

    }
        else if (event.getSource() == parametresBtn){
            rootLbl.setText("/Parametres");
            pageLbl.setText("Parametres");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99) , CornerRadii.EMPTY, Insets.EMPTY)) );     }


    }


}
