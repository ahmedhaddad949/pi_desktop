/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.particer_formation;
import Service.formationService;
import Service.particer_formationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Front_formationController implements Initializable {

    @FXML
    private Pane pnl_formations;
    @FXML
    private ScrollPane scrollpaneProduit;
    @FXML
    private HBox hboxProduit;
    @FXML
    private TableView<particer_formation> tabparticipations;
    @FXML
    private TableColumn<particer_formation, Date> col_date;
    @FXML
    private TableColumn<particer_formation, Integer> col_id_formation;
    @FXML
    private Label username;
    @FXML
    private Button btn_formation;
    @FXML
    private Button btn_mes_participations;
    @FXML
    private Pane pnl_participations;
    @FXML
    private TableColumn<particer_formation, Integer> col_id;
    particer_formationService service = new particer_formationService();
    formationService service_for = new formationService();
     static int indiceFormation = 0;
      private int tailleFormation =0;
              private TableColumn<particer_formation, String> col_btnDelet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_id_formation.setCellValueFactory(new PropertyValueFactory<>("formarion_id"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            // 5 ttbadel hasb user connect
            tabparticipations.setItems(service.Affichertout_user(5));
        } catch (Exception ex) {
         }
          /*-----------------*/
                
     
            tailleFormation = service_for.nombre();
        System.out.println("taa"+tailleFormation);
          Node[] nodes_formations= new Node[tailleFormation];
           scrollpaneProduit.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           
           
        for (indiceFormation = 0; indiceFormation < tailleFormation; indiceFormation++) {
            try {

                nodes_formations[indiceFormation] = FXMLLoader.load(getClass().getResource("/sample/Item_formation.fxml"));

                hboxProduit.getChildren().add(nodes_formations[indiceFormation]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
           col_btnDelet = new TableColumn("Supprimer");
                javafx.util.Callback<TableColumn<particer_formation, String>, TableCell<particer_formation, String>> cellFactory
                = new Callback<TableColumn<particer_formation, String>, TableCell<particer_formation, String>>() {
            public TableCell call(final TableColumn<particer_formation, String> param) {
                final TableCell<particer_formation, String> cell = new TableCell<particer_formation, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                particer_formation u = getTableView().getItems().get(getIndex());

                          
                              
                                try {
                                    service.Supprimer(u.getId());
                                } catch (SQLException ex) {
                                 }
                               
                              
                                 col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_id_formation.setCellValueFactory(new PropertyValueFactory<>("formarion_id"));
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
           if (event.getSource() == btn_mes_participations) {
            pnl_participations.toFront();
            tabparticipations.setItems(service.Affichertout_user(5));
  
         }
            if (event.getSource() == btn_formation) {
            pnl_formations.toFront();
        }
    }
    
}
