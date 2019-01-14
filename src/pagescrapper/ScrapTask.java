/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagescrapper;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.web.WebEngine;

/**
 *
 * @author devStar
 */
public class ScrapTask extends Task<String> {

    private final WebEngine webEngine;
    private State state;
    private String content;

    public ScrapTask(WebEngine webEngine, String url) {
        this.webEngine = webEngine;
        this.state = State.RUNNING;
        Platform.runLater(() -> {
            webEngine.load(url);
        });
    }

    @Override
    protected String call() throws InterruptedException {
        int i = 0;
        int timeout = 30000;
        int sleep = 500;

        while (i <= timeout / sleep && state == State.RUNNING) {
            Platform.runLater(() -> {
                state = webEngine.getLoadWorker().getState();
                content = (String) webEngine
                        .executeScript("document.documentElement.outerHTML");
            });
            Thread.sleep(sleep);
            i++;
        }

        return content;
    }
}
