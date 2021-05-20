/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.io.ConnectionRequest;
import com.mycompany.Models.categorie_freelance;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hadda
 */
public class ServiceCategorie_freelance  {
        public ArrayList<categorie_freelance> categorie_freelances;
    public static ServiceCategorie_freelance instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public ServiceCategorie_freelance() {
        req = new ConnectionRequest();
    }

    public static ServiceCategorie_freelance getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie_freelance();
        }
        return instance;
    }
            public ArrayList<categorie_freelance> parsecategorie_freelance(String jsonText) {
        try {
            categorie_freelances = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                categorie_freelance categorie = new categorie_freelance();

                float id = Float.parseFloat(obj.get("id").toString());
                categorie.setId((int) id);

                float nbrOffres = Float.parseFloat(obj.get("nbr_offre_fr").toString());
                categorie.setNbrOffres_fr((int) nbrOffres);

                categorie.setNom_cat_fr(obj.get("nom_cat_fr").toString());
                categorie.setDescription_cat_fr(obj.get("description_cat_fr").toString());
           
        
             
              
                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                categorie_freelances.add(categorie);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return categorie_freelances;
    }

    public ArrayList<categorie_freelance> findAll() {
        String url = Statics.BASE_URL + "category/freelance/afficherCTF_Mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie_freelances = parsecategorie_freelance(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie_freelances;
    }
    
}
