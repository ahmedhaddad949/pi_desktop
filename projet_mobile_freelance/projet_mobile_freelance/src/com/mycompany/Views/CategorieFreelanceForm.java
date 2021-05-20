/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Views;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Models.categorie_freelance;
import com.mycompany.Models.offre_freelance;
import com.mycompany.MyApplication;
import com.mycompany.Services.ServiceCategorie_freelance;
import com.mycompany.Services.Serviceoffre_freelance;

/**
 *
 * @author hadda
 */
public class CategorieFreelanceForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    public CategorieFreelanceForm(Form previous)
    {
           super("Categories",BoxLayout.y());
             for (categorie_freelance c : new ServiceCategorie_freelance().findAll()) {

            this.add(addItem_Categorie(c));

        }
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
    }
    
     public Container addItem_Categorie(categorie_freelance c) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label nom = new Label(c.getNom_cat_fr());
             Label description = new Label(c.getDescription_cat_fr());
        Button btn = new Button("Detail");
      
        

        cn2.add(nom).add(description).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        btn.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
           
         
        
            Label nom_lab = new Label("Categorie Nom :");
            Label nom_etxt = new Label(c.getNom_cat_fr());
            Label des_lab = new Label("Description :");
            
            Label des_etxt = new Label(c.getDescription_cat_fr());
          


           
                
            f2.add(nom_lab).add(nom_etxt).add(des_lab).add(des_etxt);
                 for (offre_freelance q : new Serviceoffre_freelance().findAll(c.getId())) {

            f2.add(addItem_offre(q));

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
         public Container addItem_offre(offre_freelance q) {
             
             Button btn_map = new Button("Map");
             Button btn_postuller = new Button("postuller");

        Container cn1 = new Container(new BorderLayout());
       
        Container cn2 = new Container(BoxLayout.y());
        Label nom = new Label("Titre offre :"+q.getTitre_offre_fr());
        Label Description = new Label("Description offre :"+q.getDescriptionfr()); 
        Label nombre = new Label("Recompose :"+String.valueOf(q.getRecomponse()));
          Label entreprise = new Label("Entreprise :"+String.valueOf(q.getEntreprisefr()));
TextField motivation_text = new TextField("", "Motivation", 20, TextArea.ANY);


    btn_postuller.addActionListener(qqw->{
        
                                           if (motivation_text.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Motivation ", "OK", null);

                                }
                                           else
                                           {
                                                    Serviceoffre_freelance sf = new Serviceoffre_freelance();
        
        sf.postuler(12, q.getId(), motivation_text.getText()); 
          Dialog.show("Postuller", "Postuller", "OK", null);
          new MyApplication().start();
                                           }
        
 
        
          });
          


          btn_map.addActionListener(qqw->{
          
          new map().start(this);
          });
          
        cn2.add(nom).add(Description).add(entreprise).add(nombre).add(motivation_text).add(btn_postuller).add(btn_map);
        cn1.add(BorderLayout.WEST, cn2);
        return cn1;
         }
}
