/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.offre_emploi;
import entities.categorie_emploi;
import entities.postuler_emploi;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.offre_emploi_crud;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

import services.postuler_emploi_crud;
import utils.Javamail;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author hadda
 */
public class displayalloffre_emploiFront implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private Pane statusPane;
    @FXML
    private Label rootLbl;
    @FXML
    private Label pageLbl;
    @FXML
    private Button homeBtn;
    @FXML
    private Button acceuilBtn;
    @FXML
    private FontAwesomeIconView homeIco;
    @FXML
    private Button freelaneBtn;
    @FXML
    private Button emploiBtn;
    @FXML
    private Button questionnaireBtn;
    @FXML
    private Button formationBtn;
    @FXML
    private Button reclamationBtn;
    @FXML
    private Button utilisateursBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Button parametresBtn;
    @FXML
    private Button closeBtn;
   
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> nb_offre;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> categorie;
    @FXML
    private Button postuler;
    private ObservableList<offre_emploi> data;
    
    offre_emploi_crud oe=new offre_emploi_crud();
    @FXML
    private TableView<offre_emploi> table;
    @FXML
    private Button postuler1;
    
    

    /**
     * Initializes the controller class.
     */
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
                System.out.println(data);
                data=oe.displayAllOffre();
                
                titre.setCellValueFactory(new PropertyValueFactory<>("titre_offre_emploi"));
                nb_offre.setCellValueFactory(new PropertyValueFactory<>("nbr_offres"));
                description.setCellValueFactory(new PropertyValueFactory<>("description_cat_em"));
                categorie.setCellValueFactory(new PropertyValueFactory<>("CategorieEmploi_id"));
                
                titre.setSortType(TableColumn.SortType.ASCENDING);
                nb_offre.setSortType(TableColumn.SortType.ASCENDING);
                description.setSortType(TableColumn.SortType.ASCENDING);
                categorie.setSortType(TableColumn.SortType.ASCENDING);
                     
                 System.out.println(data);
                
                 table.setItems(data);
        
        
        
    }    

   
    @FXML
    private void handleClicks(ActionEvent event) {
        
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void postuler(ActionEvent event) {
          ObservableList<offre_emploi> row ;
            row = table.getSelectionModel().getSelectedItems();  
            
            if (row.get(0)==null) {
                        alert("erreur", "Vous devez selectionnez une offre");
                       

            return;
            }
            System.out.println(row.get(0).getId());
            
            
            postuler_emploi_crud pe = new postuler_emploi_crud();
            Date sqlDate  = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            postuler_emploi p =new postuler_emploi(0, sqlDate, 17, row.get(0).getId());
            pe.add_postuler_emploi(p);
            
            Javamail mail =new Javamail();
        try {
            mail.sendMail("ahmed.haddad@esprit.tn", "demande d'emploi"+row.get(0).getTitre_offre_emploi());
        } catch (Exception ex) {
            Logger.getLogger(displayalloffre_emploiFront.class.getName()).log(Level.SEVERE, null, ex);
        }
        String title = "Succes! ";
        String message = "Le fichier PDF est generé";

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
    }
    
     void alert (String title , String content){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setContentText(content);
            alert.showAndWait();
            
    }

    @FXML
    void pdfs (ActionEvent event) throws  SQLException{
        try {
         Connection cn2 = MyConnection.getInstance().getCnx();
        ResultSet rs ;
        rs = cn2.createStatement().executeQuery("SELECT * FROM postuler_emploi ");
                    /* Step-2: Initialize PDF documents - logical objects */
                    Document my_pdf_report = new Document();
                    PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Liste Reservation.pdf"));
                    my_pdf_report.open();            
                    //we have four columns in our table
                    PdfPTable my_report_table = new PdfPTable(3);
                    //create a cell object
                    PdfPCell table_cell;

                    while (rs.next()) {                
                                    String dept_id = rs.getString("OffrePostulation_Id");
                                    table_cell=new PdfPCell(new Phrase(dept_id));
                                    my_report_table.addCell(table_cell);
                                    
                                    String dept_name=rs.getString("date_postulation");
                                    table_cell=new PdfPCell(new Phrase(dept_name));
                                    my_report_table.addCell(table_cell);
                                    
                                    String manager_id=rs.getString("userid");
                                    table_cell=new PdfPCell(new Phrase(manager_id));
                                    my_report_table.addCell(table_cell);
                                    
                                   
                                    }
                    /* Attach report table to PDF */
                    my_pdf_report.add(my_report_table);                       
                    my_pdf_report.close();

                    /* Close all DB related objects */
                    rs.close();
                    
                           



    } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (DocumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
        
    }
         String title = "Succes! ";
        String message = "Le fichier PDF est generé";

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
       // tray.showAndDismiss(Duration.seconds(5));

    }
    
}
