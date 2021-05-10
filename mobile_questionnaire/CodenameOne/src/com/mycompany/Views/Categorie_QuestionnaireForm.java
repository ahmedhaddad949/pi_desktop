/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Views;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Models.categorie_questionnaire;
import com.mycompany.Models.questionnaire;
import com.mycompany.Models.reponse;
import com.mycompany.Services.ServiceCategorie_Questionnaire;
import com.mycompany.Services.Service_questionnaire;
import com.mycompany.Services.Service_reponse;
import com.mycompany.myapp.MyApplication;
import java.util.ArrayList;


/**
 *
 * @author noux
 */
public class Categorie_QuestionnaireForm  extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    public Categorie_QuestionnaireForm(Form previous)
    {
           super("Categories",BoxLayout.y());
             for (categorie_questionnaire c : new ServiceCategorie_Questionnaire().findAll()) {

            this.add(addItem_Categorie(c));

        }
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
    }
    
     public Container addItem_Categorie(categorie_questionnaire c) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label nom = new Label(c.getNom_cat_qst());
             Label description = new Label(c.getDescription_cat_qst());
        Button btn = new Button("Detail");
      
        

        cn2.add(nom).add(description).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        btn.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
           
         
        
            Label nom_lab = new Label("Categorie Nom");
            Label nom_etxt = new Label(c.getNom_cat_qst());
            Label des_lab = new Label("Description ");
            Label des_etxt = new Label(c.getDescription_cat_qst());
          


           
                
            f2.add(nom_lab).add(nom_etxt).add(des_lab).add(des_etxt);
                 for (questionnaire q : new Service_questionnaire().findAll(c.getId())) {

            f2.add(addItem_Question(q,c.getId()));

        }
                        f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
 f2.show();
        });
      
         /*for (int i = 0; i < c.getQuestionnaires().size(); i++) {
              Container cnq = new Container(new BorderLayout());
        Container cnq2 = new Container(BoxLayout.y());
       
   
      
        

        cnq2.add(c.getQuestionnaires().get(i));
        cnq.add(BorderLayout.WEST, cn2);
      
       
         }*/
        //(String) c.getQuestionnaires().get("username");

        cn1.setLeadComponent(btn);
        return cn1;

    }
     String image_name;
         public Container addItem_Question(questionnaire q,int id_c) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label nom = new Label("Nom question :"+q.getNom_qst());
         Button btn_detail = new Button("Repondre");
        
        TextField Reponse = new TextField("", "Reponse", 20, TextArea.ANY);
        
        btn_detail.addActionListener(lw->{
        Service_questionnaire sq = new Service_questionnaire();
        // 5 heya l id user
        if (Reponse.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Reponse ", "OK", null);

                                }
        else
        {
            Service_reponse sr = new Service_reponse();
                sq.Ajouter_reponse(q.getId(), id_c, 5,Reponse.getText());
                Dialog.show("Ajout", "Ajout", "OK", null);
            ArrayList<reponse> reps = sr.findAll(q.getId());
            reponse r = new reponse();
            r.setText(Reponse.getText());
            reps.add(r);
            
            
            
                  String urlab = "http://localhost/packandgo-omarBranch/public/uploads/mobile/qrcode.php";

                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);
                                 String data="";
                                for (reponse rep : reps) {
                   data += rep.getText()+"\n";

            }
                                System.out.println(data);
                                
                              
                                cnreq.addArgument("data", data);
                                cnreq.addArgument("id_q", String.valueOf(q.getId()));
                                cnreq.setUrl(urlab);

                                cnreq.addResponseListener(evx
                                        -> {
                                   image_name= new String(cnreq.getResponseData());
                                    System.out.println(image_name);
                                

                                }
                                );
                                NetworkManager.getInstance().addToQueueAndWait(cnreq);
            
            
           
           
        }
       
        
        });
        Button btn_voir_reponse = new Button("voir reponse");
        btn_voir_reponse.addActionListener(qwww ->{
            
            
            
            
            
            Service_reponse sr = new Service_reponse();
       
            ArrayList<reponse> reps = sr.findAll(q.getId());
        
            
            
            
                  String urlab = "http://localhost/packandgo-omarBranch/public/uploads/mobile/qrcode.php";

                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);
                                 String data="";
                                for (reponse rep : reps) {
                   data += rep.getText()+"\n";

            }
                       
                                
                              
                                cnreq.addArgument("data", data);
                                
                                cnreq.setUrl(urlab);

                                cnreq.addResponseListener(evx
                                        -> {
                                   image_name= new String(cnreq.getResponseData());
                                    System.out.println(image_name);
                                

                                }
                                );
                                NetworkManager.getInstance().addToQueueAndWait(cnreq);
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        
             Form f3 =  new Form("reponse",BoxLayout.y());
                  String url = "http://localhost/packandgo-omarBranch/public/uploads/images/"+image_name;
                  System.out.println(url);
                        ImageViewer imgv;
                        Image imge;
                        EncodedImage enc;
                        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                        imge = URLImage.createToStorage(enc, url, url);
                        imgv = new ImageViewer(imge);
                        
                        f3.add(imgv);
                             f3.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
                f3.show();
        });
        
        

        cn2.add(nom).add(Reponse).add(btn_detail).add(btn_voir_reponse);
        cn1.add(BorderLayout.WEST, cn2);
        return cn1;
         }
}
