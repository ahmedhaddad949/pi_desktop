/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.categorie_questionnaire;
import Service.categorie_qestionnaireService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class home_CategoriequestionaireController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Button btn_questionaire;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TableView<categorie_questionnaire> tabview;
    @FXML
    private TableColumn<categorie_questionnaire, String> col_nom;
    @FXML
    private TableColumn<categorie_questionnaire, String> col_description;
    @FXML
    private TableColumn<categorie_questionnaire, Integer> col_nombre;
    @FXML
    private TableColumn<categorie_questionnaire, Integer> col_id;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_description;
    @FXML
    private Button btn_ajout;
    categorie_qestionnaireService service = new categorie_qestionnaireService();
private TableColumn<categorie_questionnaire, String> col_btnDelet;
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
        javafx.util.Callback<TableColumn<categorie_questionnaire, String>, TableCell<categorie_questionnaire, String>> cellFactory
                = (final TableColumn<categorie_questionnaire, String> param) -> {
                    final TableCell<categorie_questionnaire, String> cell = new TableCell<categorie_questionnaire, String>() {
                        
                        final Button btn = new Button("supprimer");
                        
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    categorie_questionnaire u = getTableView().getItems().get(getIndex());
                                    
                                    
                                    
                                    
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
         if (event.getSource() == btn_questionaire) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Questionnaire.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_affichage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Categorie_questionaire.fxml")));
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
            categorie_questionnaire c = new categorie_questionnaire();
          
            c.setNbr_qst(0);
            c.setDescription_cat_qst(txt_description.getText());
            c.setNom_cat_qst(txt_nom.getText());
            service.add(c);
            refreche();
        }
    }
      public void refreche() throws SQLException {

        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_cat_qst"));
        col_nom.setCellFactory(TextFieldTableCell.<categorie_questionnaire> forTableColumn());
      
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_cat_qst"));
        col_description.setCellFactory(TextFieldTableCell.<categorie_questionnaire> forTableColumn());
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nbr_qst"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabview.getItems().clear();

        tabview.setItems(service.read());

    }
     public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_nom.setOnEditCommit((TableColumn.CellEditEvent<categorie_questionnaire, String> event) -> {
            TablePosition<categorie_questionnaire, String> pos = event.getTablePosition();
                
            String nom = event.getNewValue();
                 
            int row = pos.getRow();
            categorie_questionnaire ac = event.getTableView().getItems().get(row);
           
  
            ac.setNom_cat_qst(nom);
                    try {
                        service.update(ac,ac.getId());
                    } catch (SQLException ex) {
                    }
        });
                

              
              
              
                        col_description.setOnEditCommit((TableColumn.CellEditEvent<categorie_questionnaire, String> event) -> {
            TablePosition<categorie_questionnaire, String> pos = event.getTablePosition();
           
            String Description = event.getNewValue();
                  
            int row = pos.getRow();
            categorie_questionnaire ab = event.getTableView().getItems().get(row);
          
  
            ab.setDescription_cat_qst(Description);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });       
              
    
     
     
                
    }
}
