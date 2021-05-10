/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Aymen
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //-------------formation-----------
        // back admin   
        //Parent root = FXMLLoader.load(getClass().getResource("Home_Formation.fxml"));
          // user
      // Parent root = FXMLLoader.load(getClass().getResource("Front_formation.fxml"));
             //-------------reclamation-----------
        //user reclamation
        //Parent root = FXMLLoader.load(getClass().getResource("Front_Reclamaion.fxml"));
        //back reclamation
           //Parent root = FXMLLoader.load(getClass().getResource("Home_Categorie_reclamation.fxml"));
           //-------------Questionnaire-----------
           //back questionnaire
          // Parent root = FXMLLoader.load(getClass().getResource("Home_Questionnaire.fxml"));
         //back reponse
           //Parent root = FXMLLoader.load(getClass().getResource("Home_Reponse.fxml"));
        //front questionaire
        // Parent root = FXMLLoader.load(getClass().getResource("Front_Questionnaire.fxml"));
        /*--------------Offre---------------*/
        // admin offre emploi 
           //Parent root = FXMLLoader.load(getClass().getResource("Home_Offre_Emploi.fxml"));
           // admin offre freelance
            Parent root = FXMLLoader.load(getClass().getResource("Home_Offre_Freelance.fxml"));
            // Front offre emploi 
             //Parent root = FXMLLoader.load(getClass().getResource("Front_offre_emploi.fxml"));
           // Front offre freelance 
          // Parent root = FXMLLoader.load(getClass().getResource("Front_offre_freelance.fxml"));
           
           
           
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
