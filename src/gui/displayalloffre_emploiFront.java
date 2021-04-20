/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.offre_emploi;
import entities.categorie_emploi;
import entities.postuler_emploi;
import java.net.URL;
import java.sql.Date;
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

import services.postuler_emploi_crud;
import utils.Javamail;

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
    }
    
}
