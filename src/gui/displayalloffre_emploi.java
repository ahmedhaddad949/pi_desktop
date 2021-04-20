/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.categorie_emploi;
import entities.offre_emploi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import services.categorie_emploi_crud;
import services.offre_emploi_crud;

/**
 * FXML Controller class
 *
 * @author hadda
 */
public class displayalloffre_emploi implements Initializable {

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
    private TableView<offre_emploi> table;

    @FXML
    private TableColumn<offre_emploi, String> titre;
    @FXML
    private TableColumn<offre_emploi, Integer> nb_offre;
    @FXML
    private TableColumn<offre_emploi, String> description;
    @FXML
    private TableColumn<offre_emploi, Integer> categorie;
    
    
        
    private ObservableList<offre_emploi> data;
    
    offre_emploi_crud oe=new offre_emploi_crud();
    @FXML
    private FontAwesomeIconView homeIco;
    @FXML
    private Button ajouter;
    @FXML
    private Button modiferBtn;


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
        if(event.getSource() == acceuilBtn) {
            rootLbl.setText("/Accueil");
            pageLbl.setText("Accueil");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(5    , 172      ,250) , CornerRadii.EMPTY, Insets.EMPTY)) );


        }
        else if (event.getSource() == freelaneBtn){
            rootLbl.setText("/CategorieFreelance");
            pageLbl.setText("Freelance");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(250   , 5  ,5) , CornerRadii.EMPTY, Insets.EMPTY)) );

        }
        else if (event.getSource() == emploiBtn){
            rootLbl.setText("/CategorieEmploi");
            pageLbl.setText("Emploi");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(215   , 62  ,15) , CornerRadii.EMPTY, Insets.EMPTY)) );
            
            
            

        }
        else if (event.getSource() == questionnaireBtn){
            rootLbl.setText("/CategorieQuestionnaire");
            pageLbl.setText("Questionaires");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(215   , 189  ,15) , CornerRadii.EMPTY, Insets.EMPTY)) );

        }
        else if (event.getSource() == formationBtn){
            rootLbl.setText("/CategorieFormation");
            pageLbl.setText("Formations");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(67  , 206 ,47) , CornerRadii.EMPTY, Insets.EMPTY)) );
        }
        else if (event.getSource() == reclamationBtn){
            rootLbl.setText("/CategorieReclamation");
            pageLbl.setText("Reclamation");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(93,13,49) , CornerRadii.EMPTY, Insets.EMPTY)) );
        }
        else if (event.getSource() == utilisateursBtn){
            rootLbl.setText("/Users");
            pageLbl.setText("Users");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(68 ,97 ,255) , CornerRadii.EMPTY, Insets.EMPTY)) );
    }
        else if (event.getSource() == profileBtn){
            rootLbl.setText("/Profile");
            pageLbl.setText("Profile");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(43,60,99) , CornerRadii.EMPTY, Insets.EMPTY)) );

    }
        else if (event.getSource() == parametresBtn){
            rootLbl.setText("/Parametres");
            pageLbl.setText("Parametres");
            statusPane.setBackground(new Background(new BackgroundFill(Color.rgb(63,43,99) , CornerRadii.EMPTY, Insets.EMPTY)) );     }


    }

    @FXML
    private void logout(ActionEvent event) {
    }

    private void sayhello(ActionEvent event) {
        try{
            
  FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/sample_1.fxml"));
            Parent root = loader.load();
              

            sample_1 dpc = loader.getController();

            homeBtn.getScene().setRoot(root );       } catch (IOException ex) {
                
                    System.out.println(ex.getMessage());;
        } }

    @FXML
    private void ajouter(ActionEvent event) {
        
         try{
            
  FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/ajoutOffreEmploi.fxml"));
            Parent root = loader.load();
              

            ajouterOffreEmploi dpc = loader.getController();

            homeBtn.getScene().setRoot(root );       } catch (IOException ex) {
                
                    System.out.println(ex.getMessage());
        } 
        
    }

    @FXML
    private void modiferBtn(ActionEvent event) {
        
          ObservableList<offre_emploi> row ;
            row = table.getSelectionModel().getSelectedItems();  
            System.out.println(row.get(0).getId());
               try{
            
  FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/modfier.fxml"));
            Parent root = loader.load();
              

            modifierOffreEmploi dpc = loader.getController();
            dpc.idOffre=row.get(0).getId();
            dpc.offre =row.get(0);

            homeBtn.getScene().setRoot(root );   
               } catch (IOException ex) {
                
                    System.out.println(ex.getMessage());
        } 
        

          

    }
    
}
