/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.postuler_emploi;
import Service.offre_emploiService;
import Service.postuler_emploiService;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Front_offre_emploiController implements Initializable {

    @FXML
    private Pane pnl_postuler;
    @FXML
    private TableView<postuler_emploi> tabparticipations;
    @FXML
    private TableColumn<postuler_emploi, Date> col_date;
    @FXML
    private TableColumn<postuler_emploi, Integer> col_id_emploi;
    @FXML
    private TableColumn<postuler_emploi, String> col_motivation;
    @FXML
    private TableColumn<postuler_emploi, Integer> col_id;
    @FXML
    private Pane pnl_emplois;
    @FXML
    private ScrollPane scrollpaneProduit;
    @FXML
    private HBox hboxemploi;
    @FXML
    private Label username;
    @FXML
    private Button btn_formation;
    @FXML
    private Button btn_mes_postuler;
     postuler_emploiService service = new postuler_emploiService();
    offre_emploiService emploiService = new offre_emploiService();
     static int indiceEmploi = 0;
      private int tailleEmploi =0;
              private TableColumn<postuler_emploi, String> col_btnDelet;
    @FXML
    private ImageView image_cv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
setCellfromtabletoImage();
         col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_id_emploi.setCellValueFactory(new PropertyValueFactory<>("offre_emploi_id"));
        col_motivation.setCellValueFactory(new PropertyValueFactory<>("motivation"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            // 5 ttbadel hasb user connect
            tabparticipations.setItems(service.Affichertout_user(5));
        } catch (Exception ex) {
         }
          /*-----------------*/
                
     
            tailleEmploi = emploiService.nombre();
     
          Node[] nodes_formations= new Node[tailleEmploi];
           scrollpaneProduit.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           
           
        for (indiceEmploi = 0; indiceEmploi < tailleEmploi; indiceEmploi++) {
            try {

                nodes_formations[indiceEmploi] = FXMLLoader.load(getClass().getResource("/sample/Item_offre_emploi.fxml"));

                hboxemploi.getChildren().add(nodes_formations[indiceEmploi]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
           col_btnDelet = new TableColumn("Supprimer");
                javafx.util.Callback<TableColumn<postuler_emploi, String>, TableCell<postuler_emploi, String>> cellFactory
                = new Callback<TableColumn<postuler_emploi, String>, TableCell<postuler_emploi, String>>() {
            public TableCell call(final TableColumn<postuler_emploi, String> param) {
                final TableCell<postuler_emploi, String> cell = new TableCell<postuler_emploi, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                postuler_emploi u = getTableView().getItems().get(getIndex());

                          
                              
                                try {
                                    service.Supprimer(u.getId());
                                } catch (SQLException ex) {
                                 }
                               
                              
                                col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_id_emploi.setCellValueFactory(new PropertyValueFactory<>("offre_emploi_id"));
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
    private void handleClicks(ActionEvent event) {
           if (event.getSource() == btn_mes_postuler) {
            pnl_postuler.toFront();
            tabparticipations.setItems(service.Affichertout_user(5));
  
         }
            if (event.getSource() == btn_formation) {
            pnl_emplois.toFront();
        }
    }
      private void setCellfromtabletoImage() {
        tabparticipations.setOnMouseClicked(e -> {

            postuler_emploi ac = tabparticipations.getItems().get(tabparticipations.getSelectionModel().getSelectedIndex());
            String ImageUrl ="http://localhost/images/";
        

        Image image = new Image(ImageUrl + ac.getPdfcv());
        image_cv.setImage(image);
        }
        );

    }
    
}
