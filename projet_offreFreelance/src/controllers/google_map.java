/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Aymen
 */

public class google_map extends Application {

    public static void main(String[] args) {
        launch(args);
    }

 

    @Override
    public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(" Map");

        WebView webView = new WebView();

        webView.getEngine().load("https://www.google.com/maps/@33.8819669,9.560764,6z");

        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();  
    
    }
}
