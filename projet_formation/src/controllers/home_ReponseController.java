/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.reponse;
import Service.QuestionnaireService;
import Service.reponse_Service;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_ReponseController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Pane pnl;
    @FXML
    private TextField txt_reponse;
    @FXML
    private Button btn_ajout;
    @FXML
    private TableView<reponse> tabview_Reponse;
    @FXML
    private TableColumn<reponse, String> col_text_rep;
    @FXML
    private TableColumn<reponse, Integer> col_question_id_rep;
    @FXML
    private TableColumn<reponse, Date> col_date_rep;
    @FXML
    private TableColumn<reponse, Integer> col_id_rep;
    @FXML
    private ComboBox<Integer> combo_Question_rep;
    private reponse_Service rep_service = new reponse_Service();
    private QuestionnaireService ques_service = new QuestionnaireService();
 private TableColumn<reponse, String> col_btnDelet_reponse;
    @FXML
    private Button btn_categorie_emploi;
    @FXML
    private Button btn_emploi_admin;
    @FXML
    private Button btn_categorie_formation;
    @FXML
    private Button btn_formation_admin;
    @FXML
    private Button btn_categorie_questionnaire;
    @FXML
    private Button btn_Questionnaire_admin;
    @FXML
    private Button btn_categorie_reclamation;
    @FXML
    private Button btn_Reclamation_admin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        
        Modifier();
        tabview_Reponse.setEditable(true);
   
        try {
            refreche();
        } catch (SQLException ex) {
        }
          col_btnDelet_reponse = new TableColumn("Supprimer");
         combo_Question_rep.setItems(ques_service.read_ids());
          combo_Question_rep.getSelectionModel().select(0);
               javafx.util.Callback<TableColumn<reponse, String>, TableCell<reponse, String>> cellFactory
                = new Callback<TableColumn<reponse, String>, TableCell<reponse, String>>() {
            public TableCell call(final TableColumn<reponse, String> param) {
                final TableCell<reponse, String> cell = new TableCell<reponse, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                reponse u = getTableView().getItems().get(getIndex());

                            
                                                           

                          
                              
                                try {
                                    rep_service.Supprimer(u.getId());
                                } catch (Exception ex) {
                                }
                               
                               AlertDialog.showNotification("Supprimer !", "Supprimer", AlertDialog.image_checked);

                                try {
                                    refreche();
                                } catch (SQLException ex) {
                                }

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btnDelet_reponse.setCellFactory(cellFactory);
        tabview_Reponse.getColumns().add(col_btnDelet_reponse);
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
                  if (event.getSource() == btn_categorie_questionnaire) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Categorie_questionaire.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_Questionnaire_admin) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Questionnaire.fxml")));
            stage.setScene(scene);
            stage.show();
        }
        if (event.getSource() == btn_categorie_emploi) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Categorie_emploi.fxml")));
            stage.setScene(scene);
            stage.show();
        }
          if (event.getSource() == btn_emploi_admin) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Offre_Emploi.fxml")));
            stage.setScene(scene);
            stage.show();
        }
        
  if (event.getSource() == btn_categorie_formation) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Categorie_formation.fxml")));
            stage.setScene(scene);
            stage.show();
        }
          if (event.getSource() == btn_formation_admin) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Formation.fxml")));
            stage.setScene(scene);
            stage.show();
        }
            if (event.getSource() == btn_categorie_reclamation) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/btn_categorie_reclamation.fxml")));
            stage.setScene(scene);
            stage.show();
        }
          if (event.getSource() == btn_Reclamation_admin) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Reclamation.fxml")));
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    private void ajouter_Reponse(ActionEvent event) throws SQLException {
          if (txt_reponse.getText().equals("")) {
              AlertDialog.showNotification("Error !", "champ vide de txt_reponse", AlertDialog.image_cross);

        
         } else if (txt_reponse.getText().matches("^[0-9]+$")) {
                    AlertDialog.showNotification("Error !", "txt_reponse", AlertDialog.image_cross);

        } 
           
           else
           {
               reponse q =new reponse();
               q.setQuestionnaire_id(combo_Question_rep.getSelectionModel().getSelectedItem());
             q.setText(txt_reponse.getText());
       
             q.setDate(new java.sql.Date(new java.util.Date().getTime()));
               // 1 admin connect
                 q.setUser_id(5);
               rep_service.Ajouter(q);
               refreche();
           }
    }
      public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_text_rep.setOnEditCommit((TableColumn.CellEditEvent<reponse, String> event) -> {
            TablePosition<reponse, String> pos = event.getTablePosition();
                
            String text = event.getNewValue();
                 
            int row = pos.getRow();
            reponse ac = event.getTableView().getItems().get(row);
           
  
            ac.setText(text);
                    try {
                        rep_service.Modifier(ac,ac.getId());
                    } catch (SQLException ex) {
                }
        });
                
                
         
               
              
                
              
                 
     
     
                
    }
      
      
      
      
      
      
      
      
      
      
      

      public void refreche() throws SQLException {
 
        col_text_rep.setCellValueFactory(new PropertyValueFactory<>("text"));
        col_text_rep.setCellFactory(TextFieldTableCell.<reponse> forTableColumn());
 col_question_id_rep.setCellValueFactory(new PropertyValueFactory<>("questionnaire_id"));
         
        col_date_rep.setCellValueFactory(new PropertyValueFactory<>("date"));
        
   col_id_rep.setCellValueFactory(new PropertyValueFactory<>("id"));
  

        tabview_Reponse.getItems().clear();

        tabview_Reponse.setItems(rep_service.Affichertout());

    } 
}
