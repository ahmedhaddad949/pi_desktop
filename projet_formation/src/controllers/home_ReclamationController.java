/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.formation;
import Entities.reclamation;
import Service.ReclamationService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;


/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class home_ReclamationController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    private Button btn_reclamations;
    private Button btn_affichage;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private Button btn_Traiter;
    @FXML
    private TableView<reclamation> tab_Reclamation;
    @FXML
    private TableColumn<reclamation, Integer> col_id_rec;
    @FXML
    private TableColumn<reclamation, String> col_titre_rec;
    @FXML
    private TableColumn<reclamation, String> col_text_rec;
    @FXML
    private TableColumn<reclamation, Date> col_date_rec;
    @FXML
    private TableColumn<reclamation, Integer> col_Etat_rec;
    @FXML
    private TableColumn<reclamation, Integer> col_Traite_rec;
    @FXML
    private TableColumn<reclamation, Integer> col_cat_rec;
    @FXML
    private TableColumn<reclamation, Integer> col_id_user;
  ReclamationService service = new ReclamationService();
      reclamation reclamation;
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
        setCellfromtabletorec();
     try {
            refreche();
        } catch (SQLException ex) {
        }
     search() ;
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
    private void Traiter_Reclamation(ActionEvent event) throws SQLException {
        
        reclamation.setTraitement(1);
        service.update(reclamation, reclamation.getId());
        refreche();
    }
        private void setCellfromtabletorec() {
        tab_Reclamation.setOnMouseClicked(e -> {

            reclamation rec = tab_Reclamation.getItems().get(tab_Reclamation.getSelectionModel().getSelectedIndex());
           
            reclamation = tab_Reclamation.getItems().get(tab_Reclamation.getSelectionModel().getSelectedIndex());
     
         
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
        
        col_Etat_rec.setCellValueFactory(new PropertyValueFactory<>("etat"));
      
        col_Traite_rec.setCellValueFactory(new PropertyValueFactory<>("traitement"));
           
           col_cat_rec.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
        
         
        col_date_rec.setCellValueFactory(new PropertyValueFactory<>("date"));
        
   col_id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
  
        col_text_rec.setCellValueFactory(new PropertyValueFactory<>("text"));
         col_titre_rec.setCellValueFactory(new PropertyValueFactory<>("titre"));
   col_id_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        tab_Reclamation.getItems().clear();

                    tab_Reclamation.setItems(service.Searsh(txt_Seach.getText()));

                } catch (Exception ex) {
               }
        

            }
        }
        );

    }
      public void refreche() throws SQLException {




        col_Etat_rec.setCellValueFactory(new PropertyValueFactory<>("etat"));
      
        col_Traite_rec.setCellValueFactory(new PropertyValueFactory<>("traitement"));
           
           col_cat_rec.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
        
         
        col_date_rec.setCellValueFactory(new PropertyValueFactory<>("date"));
        
   col_id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
  
        col_text_rec.setCellValueFactory(new PropertyValueFactory<>("text"));
         col_titre_rec.setCellValueFactory(new PropertyValueFactory<>("titre"));
   col_id_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));

        tab_Reclamation.getItems().clear();

        tab_Reclamation.setItems(service.read());

    }

    @FXML
    private void faire_pdf(ActionEvent event) {
           Document document = new Document() {};
        try {

            PdfWriter.getInstance(document, new FileOutputStream("rec.pdf"));
            document.open();
            Paragraph ph1 = new Paragraph("Bienvenue a projet !");
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(5);
         
  
   
            //On créer l'objet cellule.
            PdfPCell cell;
           
            //contenu du tableau.
            table.addCell("Titre : ");
            table.addCell("Text : ");
            table.addCell("Date : ");
            table.addCell("etat :");
            table.addCell("Traite : ");
          
             
            service.read().forEach(e
                    -> {
                 table.addCell(e.getTitre());
             
		table.setHorizontalAlignment(Element.ALIGN_CENTER);

               
                table.addCell(String.valueOf(e.getText()));
                table.addCell(String.valueOf(e.getDate()));
                table.addCell(String.valueOf(e.getEtat()));
                table.addCell(String.valueOf(e.getTraitement()));
     
   
    //Scale to new height and new width of image

    //Add to document

             
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            document.addAuthor("projet");
            AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();
    }
      
}
