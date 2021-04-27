/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.offre_freelance;
import Service.category_freelanceService;
import Service.offre_freelanceService;
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
import javafx.scene.control.DatePicker;
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
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_offre_freelanceController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Button btn_categorie_freelance;
    @FXML
    private Pane pnl;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<offre_freelance> tabview;
    @FXML
    private TableColumn<offre_freelance, Integer> col_categorie;
    @FXML
    private TableColumn<offre_freelance, String> col_Titre;
    @FXML
    private TableColumn<offre_freelance, String> col_description;
    @FXML
    private TableColumn<offre_freelance, String> col_entreprisefr;
    @FXML
    private TableColumn<offre_freelance, Double> col_recomponse;
    @FXML
    private TableColumn<offre_freelance, Date> col_date_expiration;
    @FXML
    private TableColumn<offre_freelance, Integer> col_etat_offre;
    @FXML
    private TableColumn<offre_freelance, Integer> col_id;
    @FXML
    private TextField txt_description;
    @FXML
    private ComboBox<Integer> combo_categorie;
    @FXML
    private Button btn_ajout;
    @FXML
    private TextField txt_Titre;
    @FXML
    private TextField txt_entreprisefr;
    @FXML
    private TextField txt_recomponse;
    @FXML
    private ComboBox<Integer> combo_etat_offre;
    @FXML
    private DatePicker date_expiration;
          private TableColumn<offre_freelance, String> col_btnDelet;
          category_freelanceService cat_service= new category_freelanceService();
          offre_freelanceService service = new offre_freelanceService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
               javafx.util.Callback<TableColumn<offre_freelance, String>, TableCell<offre_freelance, String>> cellFactory
                = new Callback<TableColumn<offre_freelance, String>, TableCell<offre_freelance, String>>() {
            public TableCell call(final TableColumn<offre_freelance, String> param) {
                final TableCell<offre_freelance, String> cell = new TableCell<offre_freelance, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                offre_freelance u = getTableView().getItems().get(getIndex());

                            
                          
                              
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
            }
        };
        col_btnDelet.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnDelet);
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
          if (event.getSource() == btn_categorie_freelance) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
          
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Categorie_freelance.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_affichage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Offre_Freelance.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void ajouter_ofre_freelance(ActionEvent event) throws SQLException {
            if (txt_description.getText().equals("")) {
                   AlertDialog.showNotification("Error !", "champ vide de txt_description", AlertDialog.image_cross);

         } else if (txt_description.getText().matches("^[0-9]+$")) {
                     AlertDialog.showNotification("Error !", "txt_description", AlertDialog.image_cross);

        } 
         
         else if (txt_Titre.getText().equals("")) {
                  AlertDialog.showNotification("Error !", "champ vide de txt_Titre", AlertDialog.image_cross);

         } else if (txt_Titre.getText().matches("^[0-9]+$")) {
                AlertDialog.showNotification("Error !", "txt_Titre", AlertDialog.image_cross);

        }
            else if (txt_recomponse.getText().equals("")) {
                  AlertDialog.showNotification("Error !", "champ vide de txt_recomponse", AlertDialog.image_cross);

         } 
               else if (txt_entreprisefr.getText().equals("")) {
                  AlertDialog.showNotification("Error !", "champ vide de txt_entreprisefr", AlertDialog.image_cross);

         } 
              else if (date_expiration.getValue() == null ) {
                      AlertDialog.showNotification("Error !", "champ vide de date_expiration", AlertDialog.image_cross);


        }

          else
             
             
         {
            cat_service.incrementer(combo_categorie.getSelectionModel().getSelectedItem());
              Date dd = new java.sql.Date(new Date(date_expiration.getEditor().getText()).getTime()); 
             offre_freelance f = new offre_freelance();
             f.setCategorie_freelance_id(combo_categorie.getSelectionModel().getSelectedItem());
             f.setDescriptionfr(txt_description.getText());
             f.setEtat_offre(0);
             f.setTitre_offre_fr(txt_Titre.getText());
                f.setEntreprisefr(txt_entreprisefr.getText());
               f.setRecomponse(Double.valueOf(txt_recomponse.getText()));
               f.setDate_expiration((java.sql.Date) dd);
               
  
             service.add(f);
             refreche();
         }
        
    }
     public void refreche() throws SQLException {
 



        col_description.setCellValueFactory(new PropertyValueFactory<>("descriptionfr"));
        col_description.setCellFactory(TextFieldTableCell.<offre_freelance> forTableColumn());
           col_Titre.setCellValueFactory(new PropertyValueFactory<>("titre_offre_fr"));
        col_Titre.setCellFactory(TextFieldTableCell.<offre_freelance> forTableColumn());
      
        col_entreprisefr.setCellValueFactory(new PropertyValueFactory<>("entreprisefr"));
        col_entreprisefr.setCellFactory(TextFieldTableCell.<offre_freelance> forTableColumn());
        
        col_etat_offre.setCellValueFactory(new PropertyValueFactory<>("etat_offre"));
        col_etat_offre.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
          col_recomponse.setCellValueFactory(new PropertyValueFactory<>("recomponse"));
            col_date_expiration.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
  
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie_freelance_id"));
        col_categorie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
  
  
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        

        tabview.getItems().clear();

        tabview.setItems(service.read());

    
    }
      
      
      
      
      
      
          public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_description.setOnEditCommit((TableColumn.CellEditEvent<offre_freelance, String> event) -> {
            TablePosition<offre_freelance, String> pos = event.getTablePosition();
                
            String des = event.getNewValue();
                 
            int row = pos.getRow();
            offre_freelance ac = event.getTableView().getItems().get(row);
           
  
            ac.setDescriptionfr(des);
                    try {
                        service.update(ac,ac.getId());
                    } catch (SQLException ex) {
                    }
        });
               
     
            col_categorie.setOnEditCommit((TableColumn.CellEditEvent<offre_freelance, Integer> event) -> {
            TablePosition<offre_freelance, Integer> pos = event.getTablePosition();
           
            int Categorie = event.getNewValue();
                  
            int row = pos.getRow();
            offre_freelance ab = event.getTableView().getItems().get(row);
          
  
            ab.setCategorie_freelance_id(Categorie);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        }); 
                     col_etat_offre.setOnEditCommit((TableColumn.CellEditEvent<offre_freelance, Integer> event) -> {
            TablePosition<offre_freelance, Integer> pos = event.getTablePosition();
           
            int etat = event.getNewValue();
                  
            int row = pos.getRow();
            offre_freelance ab = event.getTableView().getItems().get(row);
          
  
            ab.setEtat_offre(etat);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        }); 
            
              col_Titre.setOnEditCommit((TableColumn.CellEditEvent<offre_freelance, String> event) -> {
            TablePosition<offre_freelance, String> pos = event.getTablePosition();
           
            String tit = event.getNewValue();
                  
            int row = pos.getRow();
            offre_freelance ab = event.getTableView().getItems().get(row);
          
  
            ab.setTitre_offre_fr(tit);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });       
          col_entreprisefr.setOnEditCommit((TableColumn.CellEditEvent<offre_freelance, String> event) -> {
            TablePosition<offre_freelance, String> pos = event.getTablePosition();
           
            String entreprise = event.getNewValue();
                  
            int row = pos.getRow();
            offre_freelance ab = event.getTableView().getItems().get(row);
          
  
            ab.setEntreprisefr(entreprise);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });           
         
              
                 
     
     
                
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
     
        col_description.setCellValueFactory(new PropertyValueFactory<>("descriptionfr"));
        col_description.setCellFactory(TextFieldTableCell.<offre_freelance> forTableColumn());
           col_Titre.setCellValueFactory(new PropertyValueFactory<>("titre_offre_fr"));
        col_Titre.setCellFactory(TextFieldTableCell.<offre_freelance> forTableColumn());
      
        col_entreprisefr.setCellValueFactory(new PropertyValueFactory<>("entreprisefr"));
        col_entreprisefr.setCellFactory(TextFieldTableCell.<offre_freelance> forTableColumn());
        
        col_etat_offre.setCellValueFactory(new PropertyValueFactory<>("etat_offre"));
        col_etat_offre.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
          col_recomponse.setCellValueFactory(new PropertyValueFactory<>("recomponse"));
            col_date_expiration.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
  
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie_freelance_id"));
        col_categorie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
  
  
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tabview.getItems().clear();

                    tabview.setItems(service.search(txt_Seach.getText()));

                } catch (Exception ex) {
               }
        

            }
        }
        );

    }
      
}
