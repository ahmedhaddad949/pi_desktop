/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.categorie_formation;
import Service.categorie_formationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_CategorieController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    private Button btn_affichage;
    private Button btn_formation;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TableView<categorie_formation> tabview;
    @FXML
    private TableColumn<categorie_formation, String> col_description;
    @FXML
    private TableColumn<categorie_formation, Integer> col_id;
    @FXML
    private TableColumn<categorie_formation, String> col_nom;
    @FXML
    private TableColumn<categorie_formation, Integer> col_nombre;
    @FXML
    private TextField txt_description;
    @FXML
    private Button btn_ajout;
    categorie_formationService service = new categorie_formationService();
    @FXML
    private TextField txt_nom;
       private TableColumn<categorie_formation, String> col_btnDelet;
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
        tabview.setEditable(true);
   
        try {
            refreche();
        } catch (SQLException ex) {
       }
        
           col_btnDelet = new TableColumn("Supprimer");
        javafx.util.Callback<TableColumn<categorie_formation, String>, TableCell<categorie_formation, String>> cellFactory
                = (final TableColumn<categorie_formation, String> param) -> {
                    final TableCell<categorie_formation, String> cell = new TableCell<categorie_formation, String>() {
                        
                        final Button btn = new Button("supprimer");
                        
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    categorie_formation u = getTableView().getItems().get(getIndex());
                                    
                                    
                                    
                                    
                                    try {
                                        service.delete(u.getId());
                                    } catch (SQLException ex) {
                                    }
                                    
                                   
                                    
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
           };
        col_btnDelet.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnDelet);
        
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
    private void ajouter_categorie(ActionEvent event) throws SQLException {
            if (txt_nom.getText().equals("")) {
                  AlertDialog.showNotification("Error !", "champ vide de txt_nom", AlertDialog.image_cross);

           } else if (txt_nom.getText().matches("^[0-9]+$")) {
               AlertDialog.showNotification("Error !", "champ  txt_nom", AlertDialog.image_cross);

           } 
              else    if (txt_description.getText().equals("")) {
                    AlertDialog.showNotification("Error !", "champ vide de txt_description", AlertDialog.image_cross);

        } else if (txt_description.getText().matches("^[0-9]+$")) {
                AlertDialog.showNotification("Error !", "champ  txt_description", AlertDialog.image_cross);

         } 
             else
        {
            categorie_formation c = new categorie_formation();
          
            c.setNbr_cat_frt(0);
            c.setDescription_cat_frt(txt_description.getText());
            c.setNom_cat_frt(txt_nom.getText());
            service.add(c);
            refreche();
        }
    }
    public void refreche() throws SQLException {

        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_cat_frt"));
        col_nom.setCellFactory(TextFieldTableCell.<categorie_formation> forTableColumn());
      
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_cat_frt"));
        col_description.setCellFactory(TextFieldTableCell.<categorie_formation> forTableColumn());
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nbr_cat_frt"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabview.getItems().clear();

        tabview.setItems(service.read());

    }
     public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_nom.setOnEditCommit((TableColumn.CellEditEvent<categorie_formation, String> event) -> {
            TablePosition<categorie_formation, String> pos = event.getTablePosition();
                
            String nom = event.getNewValue();
                 
            int row = pos.getRow();
            categorie_formation ac = event.getTableView().getItems().get(row);
           
  
            ac.setNom_cat_frt(nom);
                    try {
                        service.update(ac,ac.getId());
                    } catch (SQLException ex) {
                    }
        });
                

              
              
              
                        col_description.setOnEditCommit((TableColumn.CellEditEvent<categorie_formation, String> event) -> {
            TablePosition<categorie_formation, String> pos = event.getTablePosition();
           
            String Description = event.getNewValue();
                  
            int row = pos.getRow();
            categorie_formation ab = event.getTableView().getItems().get(row);
          
  
            ab.setDescription_cat_frt(Description);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });       
              
    
     
     
                
    }
}
