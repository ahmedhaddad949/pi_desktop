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
   //back admin
         Parent root = FXMLLoader.load(getClass().getResource("Home_Questionnaire.fxml"));
      
        //front  user
        //Parent root = FXMLLoader.load(getClass().getResource("Front_formation.fxml"));
    
           
           
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
