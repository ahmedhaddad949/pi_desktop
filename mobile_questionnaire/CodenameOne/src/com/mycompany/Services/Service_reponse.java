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
import com.mycompany.Models.reponse;
import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author noux
 */
public class Service_reponse  {
        public ArrayList<reponse> reponses;
    public static Service_reponse instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Service_reponse() {
        req = new ConnectionRequest();
    }

    public static Service_reponse getInstance() {
        if (instance == null) {
            instance = new Service_reponse();
        }
        return instance;
    }
        public ArrayList<reponse> parsecategorie_questionnaire(String jsonText) {
        try {
            reponses = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                reponse r = new reponse();

               
                r.setText(obj.get("text").toString());
               
              
                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                reponses.add(r);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return reponses;
    }

    public ArrayList<reponse> findAll(int id) {
        String url = Statics.BASE_URL + "questionnaire/ReponseMobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reponses = parsecategorie_questionnaire(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reponses;
    }


}
