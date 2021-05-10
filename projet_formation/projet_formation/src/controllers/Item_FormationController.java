/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.formation;
import Entities.particer_formation;
import Entities.rate;
import Service.formationService;
import Service.particer_formationService;
import Service.rateService;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class Item_FormationController implements Initializable {

    @FXML
    private HBox itemC;
    @FXML
    private Button btn_participer;
    @FXML
    private Label duree;
    @FXML
    private ImageView image_formation;
    @FXML
    private Label niveau;
    @FXML
    private Label description;
    formation acc = null; 
    formationService ser = new formationService();
    particer_formationService service = new particer_formationService();
    @FXML
    private Rating rating;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       acc=ser.get_formation_affichage(Front_formationController.indiceFormation);
         description.setText(acc.getDescription());
      niveau.setText(acc.getNiveau_frt());
   
      duree.setText(String.valueOf(acc.getDuree_fr()) );
   
        String ImageUrl = "http://localhost/images/";
        Image image = new Image(ImageUrl + acc.getImage());
      
        image_formation.setImage(image);
               rateService service = new rateService();
        int exist = 0;
        int stars = 0;
        try {
            for (rate rw : service.Affichertout()) {
            if (rw.getId_formation()==acc.getId() && rw.getId_user() == 5)
                {
                   exist=1;
                   stars=rw.getRate();
                }
                   
                
            }
        } catch (SQLException ex) {
        }
             if (exist ==1 )
        {
            rating.setRating(stars);
            rating.setDisable(true);
        
        }
    }    

    @FXML
    private void participer(ActionEvent event) throws SQLException {
           particer_formation p = new particer_formation();
           p.setFormarion_id(acc.getId());
           p.setDate(new java.sql.Date(new Date().getTime()));
           // 5 ttbdel b id user
           p.setUser_id(5);
                     service.Ajouter(p);
                     
                        rate r = new rate();
   
          int stars = (int) rating.getRating();
          r.setRate(stars);
           r.setId_user(5);
           r.setDescription_formation(acc.getDescription());
           r.setId_formation(acc.getId());
             rateService service = new rateService();
        int exist = 0;
        // 5 ttbdel b user
        System.out.println(acc.getId()+" ");
        for (rate rw : service.Affichertout()) {
            if (rw.getId_formation()==acc.getId() && rw.getId_user() == 5)
                exist=1;
            
        }
        if (exist ==0 )
        {
            service.Ajouter(r);
       
        }
     
                    
    }
    
}
