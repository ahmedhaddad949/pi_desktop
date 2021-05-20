/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Models.offre_freelance;
import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hadda
 */
public class Serviceoffre_freelance  {
      public ArrayList<offre_freelance> offre_freelances;
    public static Serviceoffre_freelance instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Serviceoffre_freelance() {
        req = new ConnectionRequest();
    }

    public static Serviceoffre_freelance getInstance() {
        if (instance == null) {
            instance = new Serviceoffre_freelance();
        }
        return instance;
    }
            public ArrayList<offre_freelance> parseoffre_freelance(String jsonText) {
        try {
            offre_freelances = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                offre_freelance freelance = new offre_freelance();
  
                float id = Float.parseFloat(obj.get("id").toString());
                freelance.setId((int) id);

                float recomponse = Float.parseFloat(obj.get("recomponse").toString());
                freelance.setRecomponse((int) recomponse);

                freelance.setTitre_offre_fr(obj.get("titre_offre_fr").toString());
                freelance.setDescriptionfr(obj.get("descriptionfr").toString());
           
        freelance.setEntreprisefr(obj.get("entreprisefr").toString());
             
              freelance.setEtat_offre(obj.get("etat_offre").toString());   
                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                offre_freelances.add(freelance);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return offre_freelances;
    }

    public ArrayList<offre_freelance> findAll(int id) {
        String url = Statics.BASE_URL + "category/freelance/frontCategorieDetailfreelance_Mobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offre_freelances = parseoffre_freelance(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offre_freelances;
    }  
    
    public void postuler(int id_u,int id_o, String motivation) {
        String url = Statics.BASE_URL + "offre/freelance/PostulerFreelance_Mobile/"+id_u+"/"+id_o+"/"+motivation;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
    }  
}
