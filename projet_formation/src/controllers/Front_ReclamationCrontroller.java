/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.BadWords;
import Entities.categorie_formation;
import Entities.reclamation;
import Service.ReclamationService;
import Service.categorie_reclamation_Service;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Front_ReclamationCrontroller implements Initializable {

    @FXML
    private Pane pnl_Reclamation;
    @FXML
    private TableView<reclamation> tab_Reclamation;
    @FXML
    private TableColumn<reclamation, Integer> col_id_rec;
    @FXML
    private TableColumn<reclamation, String> col_titre_rec;
    @FXML
    private TableColumn<reclamation, String> col_text_rec;
    @FXML
    private TableColumn<reclamation, Date> col_date_rec;
    @FXML
    private TableColumn<reclamation, Integer> col_Etat_rec;
    @FXML
    private TableColumn<reclamation, Integer> col_Traite_rec;
    @FXML
    private Button btn_ajout_reclamation;
    @FXML
    private TextArea txt_contenu_reclamation;
    @FXML
    private ComboBox<Integer> combo_categorie_reclamation;
    @FXML
    private ComboBox<Integer> combo_etat_reclamation;
    @FXML
    private Label username;
    @FXML
    private TableColumn<reclamation, Integer> col_cat_rec;
    @FXML
    private TextField txt_titre;
  ReclamationService service = new ReclamationService();
    categorie_reclamation_Service cat_service= new categorie_reclamation_Service();
    @FXML
    private Button btn_question;
    @FXML
    private Button btn_Reclamation;
    @FXML
    private Button btn_Formation;
    @FXML
    private Button btn_Freelance;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> mylist= FXCollections.observableArrayList();
        mylist.add(1);
            mylist.add(0);
           combo_categorie_reclamation.setItems(cat_service.read_ids());
          combo_categorie_reclamation.getSelectionModel().select(0);
          combo_etat_reclamation.setItems(mylist);
           combo_etat_reclamation.getSelectionModel().select(0);
     try {
            refreche();
        } catch (SQLException ex) {
        }
    }    

    @FXML
    private void ajouter_reclamation(ActionEvent event) throws SQLException {
               BadWords.loadConfigs();

        {
          if (txt_contenu_reclamation.getText().equals("")) {
                          AlertDialog.showNotification("Error !", "champ vide de txt_contenu_reclamation", AlertDialog.image_cross);

         } else if (txt_contenu_reclamation.getText().matches("^[0-9]+$")) {
                     AlertDialog.showNotification("Error !", "txt_contenu_reclamation", AlertDialog.image_cross);

        } 
         else if (BadWords.filterText(txt_contenu_reclamation.getText())) {

                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            } 
         else if (txt_titre.getText().equals("")) {
                       AlertDialog.showNotification("Error !", "champ vide de txt_titre", AlertDialog.image_cross);

         } else if (BadWords.filterText(txt_titre.getText())) {

                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);

            } else if (txt_titre.getText().matches("^[0-9]+$")) {
                     AlertDialog.showNotification("Error !", "txt_titre", AlertDialog.image_cross);

        }
        else
         {
             reclamation r = new reclamation();
             r.setCategorie_id(combo_categorie_reclamation.getSelectionModel().getSelectedItem());
             r.setEtat(combo_etat_reclamation.getSelectionModel().getSelectedItem());
             r.setText(txt_contenu_reclamation.getText());
             r.setTitre(txt_titre.getText());
             r.setTraitement(0);
             r.setUser_id(5);
             service.add(r);
               AlertDialog.showNotification("Ajout !", "Ajout", AlertDialog.image_checked);

             refreche();
         }
        }
    }
      public void refreche() throws SQLException {




        col_Etat_rec.setCellValueFactory(new PropertyValueFactory<>("etat"));
      
        col_Traite_rec.setCellValueFactory(new PropertyValueFactory<>("traitement"));
           
           col_cat_rec.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
        
         
        col_date_rec.setCellValueFactory(new PropertyValueFactory<>("date"));
        
   col_id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
  
        col_text_rec.setCellValueFactory(new PropertyValueFactory<>("text"));
         col_titre_rec.setCellValueFactory(new PropertyValueFactory<>("titre"));


        tab_Reclamation.getItems().clear();

        tab_Reclamation.setItems(service.read());

    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
              if (event.getSource() == btn_question) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Front_Questionnaire.fxml")));
            stage.setScene(scene);
            stage.show();
        }
                    if (event.getSource() == btn_Reclamation) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Front_Reclamaion.fxml")));
            stage.setScene(scene);
            stage.show();
        }
                             if (event.getSource() == btn_Formation) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Front_formation.fxml")));
            stage.setScene(scene);
            stage.show();
        }
                                             if (event.getSource() == btn_Freelance) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Front_offre_freelance.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
      
}
