/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Entities.offre_freelance;
import Entities.postuler_freelance;
import Service.offre_freelanceService;
import Service.postuler_freelanceService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Item_Offre_freelanceController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Button btn_participer;
    @FXML
    private Label Titre;
    @FXML
    private Label description;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField txt_motivation;
    offre_freelance acc = null; 
    offre_freelanceService ser = new offre_freelanceService();
    postuler_freelanceService service = new postuler_freelanceService();
 private String nomImage = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      acc=ser.get_offre_freelance_affichage(Front_formationController.indiceFormation);
          description.setText(acc.getDescriptionfr());
      Titre.setText(acc.getTitre_offre_fr());
    }    

    @FXML
    private void participer(ActionEvent event) throws SQLException {
           InputStream inStream = null;
    OutputStream outStream = null;

        try{

            File afile =new File("./src/images/"+nomImage);
            File bfile =new File("C:/wamp64/www/images/"+nomImage);
            

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[]buffer = new byte[1024];

            int length;
           //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        }catch(IOException e){
            e.printStackTrace();
        }
        postuler_freelance p = new postuler_freelance();
        p.setPdfcv(nomImage);
        p.setUser_id(5);
        p.setMotivation(txt_motivation.getText());
        p.setOffre_freelance_id(acc.getId());
        p.setDate(new java.sql.Date(new java.util.Date().getTime()));
        service.Ajouter(p);

        AlertDialog.showNotification("postuler !", "postuler", AlertDialog.image_checked);
     
    }
    

     @FXML
    private void handleDragOver(DragEvent event) {
           if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
     
             List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageview.setImage(img);
        nomImage = files.get(0).getName();
    }

    @FXML
    private void Map(ActionEvent event) throws Exception {
        google_map g =  new google_map();
    g.start(new Stage());
    }
    
}
