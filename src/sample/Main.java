package sample;
import entities.categorie_emploi;
import entities.categorie_questionnaire;
import entities.questionnaire;

import services.categorie_questionnaire_crud;
import services.questionnaire_crud;
import entities.offre_emploi;
import entities.postuler_emploi;
import java.sql.Date;
import java.util.Calendar;
import services.categorie_emploi_crud;
import java.util.List;
import services.offre_emploi_crud;
import services.postuler_emploi_crud;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

       //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("front.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        
       
        launch(args);
    }
}
/*  
public class Main{

public static void main(String[] args) {
//categorie_emploi c =new categorie_emploi(54, "ahmed", " this is a description ", 22);

//categorie_emploi_crud crud = new categorie_emploi_crud();
    
//test add

  // crud.add_categorie_emploi(c);
    
 //test affichage   
   
 /*List<categorie_emploi> lc =crud.displayAllCategorie();
    System.out.println(lc); */
 //crud.updateCategorie_emploi(new categorie_emploi(0, "ahmed", "hello", 50), 2);
 
 
 //crud.deleteCategorie_emploi(6);
 
 
 /*categorie_emploi c1= crud.findByIdCategorie_emploi(3);
    System.out.println(c1); */
 
 //offre_emploi o =new offre_emploi(2, "informatique", 2, "hhhhhhh", 2);

// offre_emploi_crud crudEmploi = new offre_emploi_crud();
    
//test add

 // crudEmploi.add_offre_emploi(o);
    
 //test affichage   
   
/* List<offre_emploi> lc =crudEmploi.displayAllOffre();
    System.out.println(lc); */
 //crudEmploi.updateOffre_emploi(new offre_emploi(0, "math"  , 3, "hahah", 3),6);
 
 //crudEmploi.deleteOffre_emploi(6);
 
 
 //offre_emploi c1= crudEmploi.findByIdOffre_emploi(4);
  //  System.out.println(c1); 
//Date sqlDate  = new java.sql.Date(Calendar.getInstance().getTime().getTime());

  
  //postuler_emploi p =new postuler_emploi(0, sqlDate, 16, 4);
    

//postuler_emploi_crud crudpsotuler = new postuler_emploi_crud();
//crudpsotuler.add_postuler_emploi(p);

/*List<postuler_emploi> lc =crudpsotuler.displayAllPostulation();
    System.out.println(lc);*/
//crudpsotuler.updatePostuler_emploi(new postuler_emploi(0, sqlDate, 17, 5),1);

//postuler_emploi c1= crudpsotuler.findByIdPostuler_emploi(1);
    //System.out.println(c1);
    
   // crudpsotuler.deletePostuler_emploi(1);

 
 
// categorie_questionnaire cq =new categorie_questionnaire(0, "html", "info", 5);

//categorie_questionnaire_crud crudcq = new categorie_questionnaire_crud(); 
 //crudcq.add_categorie_questionnaire(cq);
 
/* List<categorie_questionnaire> lc =crudcq.displayAllCategorie_questionnaire();
    System.out.println(lc); */

//crudcq.updateCategorie_questionnaire(new categorie_questionnaire(0, "css", "math", 3),4);

//categorie_questionnaire c1= crudcq.findByIdCategorie_questionnaire(4);
   // System.out.println(c1);

 // crudcq.deletecategorie_questionnaire(4);
  
    /*questionnaire q =new questionnaire(0, "top", "fgh", "dfghj,", 10, 2, 5);

questionnaire_crud crudq = new questionnaire_crud(); */
// crudq.add_questionnaire(q); 
 /*List<questionnaire> lc =crudq.displayAllQuestionnaires();
    System.out.println(lc); */
 
 //crudq.updateQuestionnaire(new questionnaire(0, "up", "fgh", "dfghj,", 7, 2, 5),2);
 
 /*questionnaire c1= crudq.findByIdQuestionnaire(2);
   System.out.println(c1); */
 
 //crudq.deleteQuestionnaire(2);
//}
//}