/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.QuestionnaireService;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import javazoom.jl.decoder.JavaLayerException;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Front_QuestionnaireController implements Initializable {

    @FXML
    private Pane pnl_Questions;
    @FXML
    private ScrollPane scrollpaneProduit;
    @FXML
    private HBox hboxQuestions;
    @FXML
    private Label username;
    @FXML
    private Button btn_question;
    QuestionnaireService service_Question = new QuestionnaireService();
     static int indiceQuestion = 0;
      private int tailleQuestion =0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tailleQuestion = service_Question.nombre();
       
          Node[] nodes_formations= new Node[tailleQuestion];
           scrollpaneProduit.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           
           
        for (indiceQuestion = 0; indiceQuestion < tailleQuestion; indiceQuestion++) {
            try {

                nodes_formations[indiceQuestion] = FXMLLoader.load(getClass().getResource("/sample/Item_Questionnaire.fxml"));

                hboxQuestions.getChildren().add(nodes_formations[indiceQuestion]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void capture(ActionEvent event) throws AWTException, IOException {
        Robot robot = new Robot();
        Rectangle rec = new Rectangle(0,0,1200,800);
        BufferedImage image = robot.createScreenCapture(rec);
        Image MyImage = SwingFXUtils.toFXImage(image,null);
        ImageIO.write(image, "jpg", new File("out.jpg"));
                     AlertDialog.showNotification("Capture !", "Capture", AlertDialog.image_checked);

        
    }
    
}
