/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.formation;
import Service.categorie_formationService;
import Service.formationService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.DatePicker;
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
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_FormationController implements Initializable {

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
    private TextField txt_Seach;
    @FXML
    private TableView<formation> tabview;
    @FXML
    private TableColumn<formation, Integer> col_categorie;
    @FXML
    private TableColumn<formation, String> col_niveau;
    @FXML
    private TableColumn<formation, String> col_description;
    @FXML
    private TableColumn<formation, Date> col_duree;
    @FXML
    private TableColumn<formation, Integer> col_user_id;
    @FXML
    private TableColumn<formation, Integer> col_id;
    @FXML
    private TextField txt_description;
    @FXML
    private ComboBox<Integer> combo_categorie;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button stat;
    @FXML
    private ImageView imageview;
    @FXML
    private ImageView Image_formation;
    @FXML
    private DatePicker duree_formation;
    @FXML
    private TextField txt_niveau;
    @FXML
    private Pane pnl;
          private TableColumn<formation, String> col_btnDelet;
          private String nomImage = "";
          categorie_formationService cat_service= new categorie_formationService();
          formationService service = new formationService();

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
               javafx.util.Callback<TableColumn<formation, String>, TableCell<formation, String>> cellFactory
                = new Callback<TableColumn<formation, String>, TableCell<formation, String>>() {
            public TableCell call(final TableColumn<formation, String> param) {
                final TableCell<formation, String> cell = new TableCell<formation, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                formation u = getTableView().getItems().get(getIndex());
 File f = new File("C:/wamp64/www/images/"+u.getImage());
                            
                                System.out.println(f.delete());                             

                          
                              
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
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Categorie_formation.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_affichage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Home_Formation.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }


    @FXML
    private void faire_stat(ActionEvent event) {
             Scene scene;
        Stage stage = new Stage();
        try {

            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Statistique.fxml")));
            stage.setScene(scene);
            stage.setTitle("Statistique Accessoire");
            stage.show();

        } catch (IOException ex) {
          }

    }

    @FXML
    private void handleDragOver(DragEvent event) {
           if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
     
             List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview.setImage(img);
        nomImage = files.get(0).getName();
    }
      public void refreche() throws SQLException {
 




        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_description.setCellFactory(TextFieldTableCell.<formation> forTableColumn());
        
        col_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau_frt"));
           col_niveau.setCellFactory(TextFieldTableCell.<formation> forTableColumn());
           
           col_duree.setCellValueFactory(new PropertyValueFactory<>("duree_fr"));
         col_duree.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
         
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorieformation_id"));
        col_categorie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
   col_user_id.setCellValueFactory(new PropertyValueFactory<>("users_id"));
  
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        

        tabview.getItems().clear();

        tabview.setItems(service.read());

    }
      
      
      
      
      
      
          public void Modifier()
    {
         
                   
                  
                   
            
        
               
                col_description.setOnEditCommit((TableColumn.CellEditEvent<formation, String> event) -> {
            TablePosition<formation, String> pos = event.getTablePosition();
                
            String des = event.getNewValue();
                 
            int row = pos.getRow();
            formation ac = event.getTableView().getItems().get(row);
           
  
            ac.setDescription(des);
                    try {
                        service.update(ac,ac.getId());
                    } catch (SQLException ex) {
                        Logger.getLogger(home_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
                
                
            col_categorie.setOnEditCommit((TableColumn.CellEditEvent<formation, Integer> event) -> {
            TablePosition<formation, Integer> pos = event.getTablePosition();
           
            int Categorie = event.getNewValue();
                  
            int row = pos.getRow();
            formation ab = event.getTableView().getItems().get(row);
          
  
            ab.setCategorieformation_id(Categorie);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        }); 
            
            
              col_niveau.setOnEditCommit((TableColumn.CellEditEvent<formation, String> event) -> {
            TablePosition<formation, String> pos = event.getTablePosition();
           
            String niveau = event.getNewValue();
                  
            int row = pos.getRow();
            formation ab = event.getTableView().getItems().get(row);
          
  
            ab.setNiveau_frt(niveau);
                    try {
                        service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });       
              
                 col_duree.setOnEditCommit((TableColumn.CellEditEvent<formation, Date> event) -> {
            TablePosition<formation, Date> pos = event.getTablePosition();
           
            Date dduree = event.getNewValue();
     
 
      
         java.sql.Date dd=  new java.sql.Date(  dduree.getTime());
            int row = pos.getRow();
            formation ab = event.getTableView().getItems().get(row);
          
  
            ab.setDuree_fr(dd);
                    try {
                       service.update(ab,ab.getId());
                    } catch (SQLException ex) {
                    }
        });       
              
                 
     
     
                
    }
      
      
      
      
      
      
      
      
      
      
      
      
       private void setCellfromtabletoImage() {
        tabview.setOnMouseClicked(e -> {

            formation ac = tabview.getItems().get(tabview.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/images/";
        

        Image image = new Image(ImageUrl + ac.getImage());
        Image_formation.setImage(image);
        }
        );
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
          col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_description.setCellFactory(TextFieldTableCell.<formation> forTableColumn());
        
        col_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau_frt"));
           col_niveau.setCellFactory(TextFieldTableCell.<formation> forTableColumn());
           
           col_duree.setCellValueFactory(new PropertyValueFactory<>("duree_fr"));
         col_duree.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
         
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("Categorieformation_id"));
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
    private void ajouter_formation(ActionEvent event) throws Exception {
          if (txt_description.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de txt_description", AlertDialog.image_cross);

         } else if (txt_description.getText().matches("^[0-9]+$")) {
                          AlertDialog.showNotification("Error !", "txt_description", AlertDialog.image_cross);

        } 
         
         else if (txt_niveau.getText().equals("")) {
                 AlertDialog.showNotification("Error !", "champ vide de txt_niveau", AlertDialog.image_cross);

         } else if (txt_niveau.getText().matches("^[0-9]+$")) {
                  AlertDialog.showNotification("Error !", "txt_niveau", AlertDialog.image_cross);

        }
         else if (duree_formation.getValue() == null ) {
                      AlertDialog.showNotification("Error !", "champ vide de duree_formation", AlertDialog.image_cross);


        }
          else
             
             
         {
                       InputStream inStream = null;
    OutputStream outStream = null;

        try{

            File afile =new File("./src/images/"+nomImage);
            File bfile =new File("C:/wamp64/www/images/"+nomImage);
            

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[]buffer = new byte[1024];

            int length;
           //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        }catch(IOException e){
            e.printStackTrace();
        }
             Date dd = new java.sql.Date(new Date(duree_formation.getEditor().getText()).getTime());
             formation f = new formation();
             f.setCategorieformation_id(combo_categorie.getSelectionModel().getSelectedItem());
             f.setDescription(txt_description.getText());
             f.setDuree_fr((java.sql.Date) dd);
             f.setImage(nomImage);
             
             cat_service.incrementer(combo_categorie.getSelectionModel().getSelectedItem());
             /// hjnee id ta admin el connect
             f.setUsers_id(5);
             f.setNiveau_frt(txt_niveau.getText());
             service.add(f);
             refreche();
         }
       
    }
 
}
