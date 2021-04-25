/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.categorie_emploi;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.offre_emploi_crud;
import entities.offre_emploi;
import java.io.IOException;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import services.categorie_emploi_crud;

/**
 * FXML Controller class
 *
 * @author hadda
 */
public class ajouterOffreEmploi implements Initializable {

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
    private Button ajouterBtn;
    @FXML
    private TextField titre;
    @FXML
    private ComboBox<categorie_emploi> categorie;
    @FXML
    private TextField description;
    @FXML
    private TextField nombre_offre;
    
    int categorieint ;

    offre_emploi_crud of =new offre_emploi_crud();
    @FXML
    private Button retourBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                categorie_emploi_crud cem=new  categorie_emploi_crud();
         ObservableList<categorie_emploi> items =cem.displayAllCategorie();
         
         System.out.println("hiiiiiiiii"+items);
                 categorie.setItems(items);

         
        StringConverter<categorie_emploi> converter = new StringConverter<categorie_emploi>() {
            @Override
            public String toString(categorie_emploi ce) {
                return ce.getNom_emploi();
            }

            @Override
            public categorie_emploi fromString(String id) {
                return items.stream()
                        .filter(item -> item.getNom_emploi().equals(id))
                        .collect(Collectors.toList()).get(0);
            }
        };
        categorie.setConverter(converter);
        // Print the name of the Bank that is selected
        categorie.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {
            System.out.println(categorie.getValue());
            categorieint =categorie.getValue().getId();
            
        });

        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void ajouterBtn(ActionEvent event) {
        


        String titret=titre.getText();
        String descriptiont = description.getText();
        String nombre_offret =nombre_offre.getText();
        System.out.println(titret+descriptiont+nombre_offret);
//        int intCategorie =Integer.parseInt(categoriet);
                int intNombreOffre=Integer.parseInt(nombre_offret);

        
        offre_emploi offre =new offre_emploi(0, titret, intNombreOffre  , descriptiont, categorieint);
        of.add_offre_emploi(offre);
        
        
    }

    @FXML
    private void retourBtn(ActionEvent event) {
         
         try{
            
  FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
            Parent root = loader.load();
              

            displayalloffre_emploi dpc = loader.getController();

            homeBtn.getScene().setRoot(root );       } catch (IOException ex) {
                
                    System.out.println(ex.getMessage());;
        } 
        
    }
    
    
}
