/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.offre_freelance;
import Entities.postuler_freelance;
import Service.offre_freelanceService;
import Service.postuler_freelanceService;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Front_offre_freelanceController implements Initializable {

    @FXML
    private Pane pnl_postuler;
    @FXML
    private TableView<postuler_freelance> tabparticipations;
    @FXML
    private TableColumn<postuler_freelance, Date> col_date;
    @FXML
    private TableColumn<postuler_freelance, Integer> col_id_emploi;
    @FXML
    private TableColumn<postuler_freelance, String>col_motivation;
    @FXML
    private TableColumn<postuler_freelance, Integer> col_id;
    @FXML
    private ImageView image_cv;
    @FXML
    private Pane pnl_emplois;
    @FXML
    private ScrollPane scrollpanefreelance;
    @FXML
    private HBox hboxfreeplance;
    @FXML
    private Label username;
    @FXML
    private Button btn_freelance;
    @FXML
    private Button btn_mes_postuler;
      postuler_freelanceService service = new postuler_freelanceService();
    offre_freelanceService freelanceService = new offre_freelanceService();
     static int indicefreelance = 0;
      private int taillefreelance =0;
              private TableColumn<postuler_freelance, String> col_btnDelet;
    @FXML
    private Button btn_question;
    @FXML
    private Button btn_Reclamation;
    @FXML
    private Button btn_Formation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 setCellfromtabletoImage();
         col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_id_emploi.setCellValueFactory(new PropertyValueFactory<>("offre_freelance_id"));
        col_motivation.setCellValueFactory(new PropertyValueFactory<>("motivation"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            // 5 ttbadel hasb user connect
            tabparticipations.setItems(service.Affichertout_user(5));
        } catch (Exception ex) {
         }
          /*-----------------*/
                
     
            taillefreelance = freelanceService.nombre();
     
          Node[] nodes_formations= new Node[taillefreelance];
           scrollpanefreelance.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           
           
        for (indicefreelance = 0; indicefreelance < taillefreelance; indicefreelance++) {
            try {

                nodes_formations[indicefreelance] = FXMLLoader.load(getClass().getResource("/sample/Item_offre_freelance.fxml"));

                hboxfreeplance.getChildren().add(nodes_formations[indicefreelance]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
           col_btnDelet = new TableColumn("Supprimer");
                javafx.util.Callback<TableColumn<postuler_freelance, String>, TableCell<postuler_freelance, String>> cellFactory
                = new Callback<TableColumn<postuler_freelance, String>, TableCell<postuler_freelance, String>>() {
            public TableCell call(final TableColumn<postuler_freelance, String> param) {
                final TableCell<postuler_freelance, String> cell = new TableCell<postuler_freelance, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                postuler_freelance u = getTableView().getItems().get(getIndex());

                          
                              
                                try {
                                    service.Supprimer(u.getId());
                                } catch (SQLException ex) {
                                 }
                               
                              
                                col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_id_emploi.setCellValueFactory(new PropertyValueFactory<>("offre_freelance_id"));
        col_motivation.setCellValueFactory(new PropertyValueFactory<>("motivation"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            // 5 ttbadel hasb user connect
            
            tabparticipations.setItems(service.Affichertout_user(5));
        } catch (Exception ex) {
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
        tabparticipations.getColumns().add(col_btnDelet);
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
           if (event.getSource() == btn_mes_postuler) {
            pnl_postuler.toFront();
            tabparticipations.setItems(service.Affichertout_user(5));
  
         }
            if (event.getSource() == btn_freelance) {
            pnl_emplois.toFront();
        }
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
    }
      private void setCellfromtabletoImage() {
        tabparticipations.setOnMouseClicked(e -> {

            postuler_freelance ac = tabparticipations.getItems().get(tabparticipations.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/images/";
        

        Image image = new Image(ImageUrl + ac.getPdfcv());
        image_cv.setImage(image);
        }
        );

    }
    
    
}
