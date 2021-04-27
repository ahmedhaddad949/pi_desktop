/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.offre_emploi;
import Service.Categorie_EmploiService;
import Service.offre_emploiService;
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
import javafx.util.converter.IntegerStringConverter;
/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_offre_emploiController implements Initializable {


    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_affichage;
    @FXML
    private Button btn_categorie_formation;
    @FXML
    private Pane pnl;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<offre_emploi> tabview;
    @FXML
    private TableColumn<offre_emploi, Integer> col_categorie;
    @FXML
    private TableColumn<offre_emploi, String> col_Titre;
    @FXML
    private TableColumn<offre_emploi, String> col_description;
    @FXML
    private TableColumn<offre_emploi, Integer> col_nbr_offres;
    @FXML
    private TableColumn<offre_emploi, Integer> col_id;
    @FXML
    private TextField txt_description;
    @FXML
    private ComboBox<Integer> combo_categorie;
    @FXML
    private Button btn_ajout;
    @FXML
    private TextField txt_Titre;
      private TableColumn<offre_emploi, String> col_btnDelet;
          Categorie_EmploiService cat_service= new Categorie_EmploiService();
          offre_emploiService service = new offre_emploiService();
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
               javafx.util.Callback<TableColumn<offre_emploi, String>, TableCell<offre_emploi, String>> cellFactory
                = new Callback<TableColumn<offre_emploi, String>, TableCell<offre_emploi, String>>() {
            public TableCell call(final TableColumn<offre_emploi, String> param) {
                final TableCell<offre_emploi, String> cell = new TableCell<offre_emploi, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                offre_emploi u = getTableView().getItems().get(getIndex());

                            
                          
                              
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
           if (event.getSource() == btn_categorie_formation) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
          
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Categorie_emploi.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_affichage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Offre_Emploi.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void ajouter_ofre_emploi(ActionEvent event) throws SQLException {
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
      
          else
             
             
         {
            cat_service.incrementer(combo_categorie.getSelectionModel().getSelectedItem());
             
             offre_emploi f = new offre_emploi();
             f.setCategorieEmploi_id(combo_categorie.getSelectionModel().getSelectedItem());
             f.setDescription_cat_em(txt_description.getText());
             f.setNbr_offres(0);
             f.setTitre_offre_emploi(txt_Titre.getText());
             
  
             service.add(f);
             refreche();
         }
        
        
    }
  public void refreche() throws SQLException {
 



        col_description.setCellValueFactory(new PropertyValueFactory<>("description_cat_em"));
        col_description.setCellFactory(TextFieldTableCell.<offre_emploi> forTableColumn());
           col_Titre.setCellValueFactory(new PropertyValueFactory<>("titre_offre_emploi"));
        col_Titre.setCellFactory(TextFieldTableCell.<offre_emploi> forTableColumn());
      col_nbr_offres.setCellValueFactory(new PropertyValueFactory<>("nbr_offres"));
        col_nbr_offres.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        

         
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("CategorieEmploi_id"));
        col_categorie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
  
  
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        

        tabview.getItems().clear();

        tabview.setItems(service.read());

    }
      
      
      
      
      
      
          public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_description.setOnEditCommit((TableColumn.CellEditEvent<offre_emploi, String> event) -> {
            TablePosition<offre_emploi, String> pos = event.getTablePosition();
                
            String des = event.getNewValue();
                 
            int row = pos.getRow();
            offre_emploi ac = event.getTableView().getItems().get(row);
           
  
            ac.setDescription_cat_em(des);
                    try {
                        service.update(ac,ac.getId());
                    } catch (SQLException ex) {
                    }
        });
                
                
            col_categorie.setOnEditCommit((TableColumn.CellEditEvent<offre_emploi, Integer> event) -> {
            TablePosition<offre_emploi, Integer> pos = event.getTablePosition();
           
            int Categorie = event.getNewValue();
                  
            int row = pos.getRow();
            offre_emploi ab = event.getTableView().getItems().get(row);
          
  
            ab.setCategorieEmploi_id(Categorie);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        }); 
            
            
              col_Titre.setOnEditCommit((TableColumn.CellEditEvent<offre_emploi, String> event) -> {
            TablePosition<offre_emploi, String> pos = event.getTablePosition();
           
            String tit = event.getNewValue();
                  
            int row = pos.getRow();
            offre_emploi ab = event.getTableView().getItems().get(row);
          
  
            ab.setTitre_offre_emploi(tit);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });       
              
                    col_nbr_offres.setOnEditCommit((TableColumn.CellEditEvent<offre_emploi, Integer> event) -> {
            TablePosition<offre_emploi, Integer> pos = event.getTablePosition();
           
            int nbr = event.getNewValue();
                  
            int row = pos.getRow();
            offre_emploi ab = event.getTableView().getItems().get(row);
          
  
            ab.setNbr_offres(nbr);
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
            col_description.setCellValueFactory(new PropertyValueFactory<>("description_cat_em"));
        col_description.setCellFactory(TextFieldTableCell.<offre_emploi> forTableColumn());
           col_Titre.setCellValueFactory(new PropertyValueFactory<>("titre_offre_emploi"));
        col_Titre.setCellFactory(TextFieldTableCell.<offre_emploi> forTableColumn());
      col_nbr_offres.setCellValueFactory(new PropertyValueFactory<>("nbr_offres"));
        col_nbr_offres.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        

         
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("CategorieEmploi_id"));
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
