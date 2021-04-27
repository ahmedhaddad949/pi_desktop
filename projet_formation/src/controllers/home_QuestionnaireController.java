/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.categorie_questionnaire;
import Entities.questionnaire;
import Service.QuestionnaireService;
import Service.categorie_qestionnaireService;
import java.io.File;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_QuestionnaireController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    private Button btn_affichage;
    @FXML
    private Button btn_categorie_questionnaire;
    @FXML
    private Pane pnl;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<questionnaire> tabview;
    @FXML
    private TableColumn<questionnaire, Integer> col_categorie;
    @FXML
    private TableColumn<questionnaire, String> col_nom;
    @FXML
    private TableColumn<questionnaire, Integer> col_user_id;
    @FXML
    private TableColumn<questionnaire, Integer> col_id;
    @FXML
    private TextField txt_nom;
    @FXML
    private ComboBox<Integer> combo_categorie;
    @FXML
    private Button btn_ajout;
    @FXML
    private ImageView Image_qr;
    @FXML
    private TextField txt_nbr_ques;
    @FXML
    private TableColumn<questionnaire, Integer> col_nbr_ques;
    QuestionnaireService service = new QuestionnaireService();
     private TableColumn<questionnaire, String> col_btnDelet;
     categorie_qestionnaireService cat_service = new categorie_qestionnaireService();
    @FXML
    private Button btn_categorie_emploi;
    @FXML
    private Button btn_emploi_admin;
    @FXML
    private Button btn_categorie_formation;
    @FXML
    private Button btn_formation_admin;
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
           setCellfromtabletoImage();
        Modifier();
        tabview.setEditable(true);
        search();
        try {
            refreche();
        } catch (SQLException ex) {
        }
          col_btnDelet = new TableColumn("Supprimer");
         combo_categorie.setItems(cat_service.read_ids());
          combo_categorie.getSelectionModel().select(0);
               javafx.util.Callback<TableColumn<questionnaire, String>, TableCell<questionnaire, String>> cellFactory
                = new Callback<TableColumn<questionnaire, String>, TableCell<questionnaire, String>>() {
            public TableCell call(final TableColumn<questionnaire, String> param) {
                final TableCell<questionnaire, String> cell = new TableCell<questionnaire, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                questionnaire u = getTableView().getItems().get(getIndex());
 File f = new File("C:/wamp64/www/images/"+u.getQr());
                            
                                System.out.println(f.delete());                             

                          
                              
                                try {
                                    service.delete(u.getId());
                                } catch (SQLException ex) {
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
    private void ajouter_ques(ActionEvent event) throws SQLException {
           if (txt_nom.getText().equals("")) {
              AlertDialog.showNotification("Error !", "champ vide de txt_nom", AlertDialog.image_cross);

        
         } else if (txt_nom.getText().matches("^[0-9]+$")) {
                    AlertDialog.showNotification("Error !", "txt_nom", AlertDialog.image_cross);

        } 
           else if (txt_nbr_ques.getText().equals("")) {
                  AlertDialog.showNotification("Error !", "champ vide de txt_nbr_ques", AlertDialog.image_cross);

        } 
           else
           {
               questionnaire q =new questionnaire();
               q.setCategoriequestionnaire_id(combo_categorie.getSelectionModel().getSelectedItem());
               categorie_questionnaire catq = cat_service.get(combo_categorie.getSelectionModel().getSelectedItem());
               q.setDescription_cat_qst(catq.getDescription_cat_qst());
               q.setNom_cat_qst(catq.getNom_cat_qst());
               q.setNbr_qst(Integer.valueOf(txt_nbr_ques.getText()));
               q.setNom_qst(txt_nom.getText());
               // 1 admin connect
               q.setUsers_id(5);
               service.add(q);
               refreche();
               cat_service.incrementer(combo_categorie.getSelectionModel().getSelectedItem());
               
           }
         
    }
       public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_nom.setOnEditCommit((TableColumn.CellEditEvent<questionnaire, String> event) -> {
            TablePosition<questionnaire, String> pos = event.getTablePosition();
                
            String des = event.getNewValue();
                 
            int row = pos.getRow();
            questionnaire ac = event.getTableView().getItems().get(row);
           
  
            ac.setNom_qst(des);
                    try {
                        service.update(ac,ac.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
                
                
            col_categorie.setOnEditCommit((TableColumn.CellEditEvent<questionnaire, Integer> event) -> {
            TablePosition<questionnaire, Integer> pos = event.getTablePosition();
           
            int Categorie = event.getNewValue();
                  
            int row = pos.getRow();
            questionnaire ab = event.getTableView().getItems().get(row);
          
  
            ab.setCategoriequestionnaire_id(Categorie);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        }); 
            
            
              col_nbr_ques.setOnEditCommit((TableColumn.CellEditEvent<questionnaire, Integer> event) -> {
            TablePosition<questionnaire, Integer> pos = event.getTablePosition();
           
            int nbr = event.getNewValue();
                  
            int row = pos.getRow();
            questionnaire ab = event.getTableView().getItems().get(row);
          
  
            ab.setNbr_qst(nbr);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        }); 
               
              
                
              
                 
     
     
                
    }
      
      
      
      
      
      
      
      
      
      
      
      
       private void setCellfromtabletoImage() {
        tabview.setOnMouseClicked(e -> {

            questionnaire ac = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/images/";
        

        Image image = new Image(ImageUrl + ac.getQr());
        Image_qr.setImage(image);
        }
        );
       }
      public void refreche() throws SQLException {
 




        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_qst"));
        col_nom.setCellFactory(TextFieldTableCell.<questionnaire> forTableColumn());
        
 col_nbr_ques.setCellValueFactory(new PropertyValueFactory<>("nbr_qst"));
        col_nbr_ques.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("Categoriequestionnaire_id"));
        col_categorie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
   col_user_id.setCellValueFactory(new PropertyValueFactory<>("users_id"));
  
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        

        tabview.getItems().clear();

        tabview.setItems(service.read());

    }
       public void search() {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("") ) {

                try {
                    refreche();
                } catch (SQLException ex) {
                }

            } else {

                try {
     
       col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_qst"));
        col_nom.setCellFactory(TextFieldTableCell.<questionnaire> forTableColumn());
        
 col_nbr_ques.setCellValueFactory(new PropertyValueFactory<>("nbr_qst"));
        col_nbr_ques.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("Categoriequestionnaire_id"));
        col_categorie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
   col_user_id.setCellValueFactory(new PropertyValueFactory<>("users_id"));
  
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
                    tabview.getItems().clear();

                    tabview.setItems(service.search(txt_Seach.getText()));

                } catch (Exception ex) {
               }
        

            }
        }
        );

    }

    @FXML
    private void reponse_page(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Reponse.fxml")));
            stage.setScene(scene);
            stage.show();
    }
}
