/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.category_freelance;
import Service.category_freelanceService;
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
public class home_CategoriefreelanceController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    private Button btn_formation;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TableView<category_freelance> tabview;
    @FXML
    private TableColumn<category_freelance, String> col_nom;
    @FXML
    private TableColumn<category_freelance, String> col_description;
    @FXML
    private TableColumn<category_freelance, Integer> col_nombre;
    @FXML
    private TableColumn<category_freelance, Integer> col_id;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_description;
    @FXML
    private Button btn_ajout;
    category_freelanceService service = new category_freelanceService();
    private TableColumn<category_freelance, String> col_btnDelet;
    @FXML
    private Button btn_freelance;
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
        javafx.util.Callback<TableColumn<category_freelance, String>, TableCell<category_freelance, String>> cellFactory
                = (final TableColumn<category_freelance, String> param) -> {
                    final TableCell<category_freelance, String> cell = new TableCell<category_freelance, String>() {
                        
                        final Button btn = new Button("supprimer");
                        
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction(event -> {
                                    category_freelance u = getTableView().getItems().get(getIndex());
                                    
                                    
                                    
                                    
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
         if (event.getSource() == btn_freelance) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Offre_Freelance.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_affichage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Categorie_freelance.fxml")));
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
                 AlertDialog.showNotification("Error !", "champ  v", AlertDialog.image_cross);

         } 
             else
        {
            category_freelance c = new category_freelance();
          
            c.setNbr_offre_fr(0);
            c.setDescription_cat_fr(txt_description.getText());
            c.setNom_cat_fr(txt_nom.getText());
            service.add(c);
            refreche();
        }
    }
    public void refreche() throws SQLException {

        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_cat_fr"));
        col_nom.setCellFactory(TextFieldTableCell.<category_freelance> forTableColumn());
      
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_cat_fr"));
        col_description.setCellFactory(TextFieldTableCell.<category_freelance> forTableColumn());
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nbr_offre_fr"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabview.getItems().clear();

        tabview.setItems(service.read());

    }
     public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_nom.setOnEditCommit((TableColumn.CellEditEvent<category_freelance, String> event) -> {
            TablePosition<category_freelance, String> pos = event.getTablePosition();
                
            String nom = event.getNewValue();
                 
            int row = pos.getRow();
            category_freelance ac = event.getTableView().getItems().get(row);
           
  
            ac.setNom_cat_fr(nom);
                    try {
                        service.update(ac,ac.getId());
                    } catch (SQLException ex) {
                    }
        });
                

              
              
              
                        col_description.setOnEditCommit((TableColumn.CellEditEvent<category_freelance, String> event) -> {
            TablePosition<category_freelance, String> pos = event.getTablePosition();
           
            String Description = event.getNewValue();
                  
            int row = pos.getRow();
            category_freelance ab = event.getTableView().getItems().get(row);
          
  
            ab.setDescription_cat_fr(Description);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });       
              
    
     
     
                
    }
}
