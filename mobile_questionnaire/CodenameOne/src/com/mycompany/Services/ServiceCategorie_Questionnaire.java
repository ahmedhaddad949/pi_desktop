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
import com.mycompany.Models.categorie_questionnaire;

import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author noux
 */
public class ServiceCategorie_Questionnaire {
        public ArrayList<categorie_questionnaire> categorie_questionnaires;
    public static ServiceCategorie_Questionnaire instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public ServiceCategorie_Questionnaire() {
        req = new ConnectionRequest();
    }

    public static ServiceCategorie_Questionnaire getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie_Questionnaire();
        }
        return instance;
    }
        public ArrayList<categorie_questionnaire> parsecategorie_questionnaire(String jsonText) {
        try {
            categorie_questionnaires = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                categorie_questionnaire categorie = new categorie_questionnaire();

                float id = Float.parseFloat(obj.get("id").toString());
                categorie.setId((int) id);

                float nbr_qst = Float.parseFloat(obj.get("nbr_qst").toString());
                categorie.setNbr_qst((int) nbr_qst);

                categorie.setDescription_cat_qst(obj.get("description_cat_qst").toString());
                categorie.setNom_cat_qst(obj.get("nom_cat_qst").toString());
           
        
             
              
                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                categorie_questionnaires.add(categorie);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return categorie_questionnaires;
    }

    public ArrayList<categorie_questionnaire> findAll() {
        String url = Statics.BASE_URL + "categorie/questionnaire/mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie_questionnaires = parsecategorie_questionnaire(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie_questionnaires;
    }


}
