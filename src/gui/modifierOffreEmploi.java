/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.offre_emploi;
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

/**
 * FXML Controller class
 *
 * @author hadda
 */
public class modifierOffreEmploi implements Initializable {
    
    int idOffre;
    offre_emploi offre ;

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
    private TextField categorie;
    @FXML
    private TextField description;
    @FXML
    private TextField nombre_offre;
    @FXML
    private Button retourBtn;

    offre_emploi_crud offrecrud =new offre_emploi_crud();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //titre.setText(offre.getTitre_offre_emploi());
        
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
        String  categoriet=categorie.getText();
        String descriptiont = description.getText();
        String nombre_offret =nombre_offre.getText();
        System.out.println(titret+categoriet+descriptiont+nombre_offret);
        int intCategorie =Integer.parseInt(categoriet);
                int intNombreOffre=Integer.parseInt(nombre_offret);

        
        offre_emploi offre =new offre_emploi(0, titret, intNombreOffre  , descriptiont, intCategorie);
        
        offrecrud.updateOffre_emploi(offre, idOffre);
    }

    @FXML
    private void retourBtn(ActionEvent event) {
    }
    
}
