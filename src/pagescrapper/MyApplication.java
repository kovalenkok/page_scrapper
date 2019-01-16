/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagescrapper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author devStar
 */
public class MyApplication extends Application {

    private static final String URL = "http://www.sia.ch/fr/affiliation/liste-des-membres/membres-etudiants/m/236019/";

    private WebView browser;
    private WebEngine webEngine;

    @Override
    public void start(Stage primaryStage) {
        browser = new WebView();
        webEngine = browser.getEngine();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);

        Scene scene = new Scene(scrollPane, 800, 600);

        primaryStage.setTitle("PageScrapper");
        primaryStage.setScene(scene);
        primaryStage.hide();

        // Example usage of the method.
        Thread thread = new Thread(() -> {
            String html = getHTMLFrom(URL);
            System.out.println(html);
        });
        thread.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private String getHTMLFrom(String url) {
        ScrapTask task = new ScrapTask(webEngine, url);
        try {
            return task.call();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
