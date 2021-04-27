/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.questionnaire;
import Entities.reponse;
import Service.QuestionnaireService;
import Service.reponse_Service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Item_QuestionnaireController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Button btn_reponse;
    @FXML
    private Label des_cat;
    @FXML
    private ImageView image_qr;
    @FXML
    private Label nom;
    @FXML
    private Label nom_cat;
    @FXML
    private TextField txt_reponse;
      questionnaire acc = null; 
    QuestionnaireService ser = new QuestionnaireService();
    reponse_Service service = new reponse_Service();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       acc=ser.get_question_affichage(Front_QuestionnaireController.indiceQuestion);
          String ImageUrl = "http://localhost/images/";
        Image image = new Image(ImageUrl + acc.getQr());
             des_cat.setText(acc.getDescription_cat_qst());
       nom.setText(acc.getNom_qst());
        nom_cat.setText(acc.getNom_cat_qst());
        image_qr.setImage(image);
    }    

    @FXML
    private void reponse(ActionEvent event) {
             if (txt_reponse.getText().equals("")) {
                          AlertDialog.showNotification("Error !", "champ vide de txt_reponse", AlertDialog.image_cross);

         } else if (txt_reponse.getText().matches("^[0-9]+$")) {
                     AlertDialog.showNotification("Error !", "txt_reponse", AlertDialog.image_cross);

        } 
             else
         {
         
             reponse rep_correect = service.get_reponse_affichage(acc.getId());
             
             if (rep_correect.getText().equals(txt_reponse.getText()))
             {
                      AlertDialog.showNotification("Valide !", "reponse correct", AlertDialog.image_checked);

             }
             else
             {
                      AlertDialog.showNotification("Error !", "Reponse fause", AlertDialog.image_cross);

             }
             
         }
    }
    
}
