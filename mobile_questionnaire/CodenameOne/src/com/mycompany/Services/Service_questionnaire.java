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
import com.mycompany.Models.questionnaire;
import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author noux
 */
public class Service_questionnaire {
       public ArrayList<questionnaire> questionnaires;
    public static Service_questionnaire instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Service_questionnaire() {
        req = new ConnectionRequest();
    }

    public static Service_questionnaire getInstance() {
        if (instance == null) {
            instance = new Service_questionnaire();
        }
        return instance;
    }
     public ArrayList<questionnaire> parsecategorie_questionnaire(String jsonText) {
        try {
            questionnaires = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                questionnaire q = new questionnaire();

                float id = Float.parseFloat(obj.get("id").toString());
                q.setId((int) id);

                float nbr_qst = Float.parseFloat(obj.get("nbr_qst").toString());
                q.setNbr_qst((int) nbr_qst);

                q.setDescription_cat_qst(obj.get("description_cat_qst").toString());
                q.setNom_qst(obj.get("nom_qst").toString());
        
             
              
                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                questionnaires.add(q);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return questionnaires;
    }

    public ArrayList<questionnaire> findAll(int id) {
        String url = Statics.BASE_URL + "categorie/questionnaire/ques_mobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                questionnaires = parsecategorie_questionnaire(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return questionnaires;
    }
 public void Ajouter_reponse(int id_q, int id_c,int id_u,String reponse) {
        String url = Statics.BASE_URL + "questionnaire/reponseQuestionnaire_Mobile/"+id_q+"/"+id_c+"/"+id_u+"/"+reponse;
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
