/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagescrapper;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author devStar
 */
public class ScrapHelper {

    private static final OkHttpClient client = new OkHttpClient();

    public static String getHTMLFrom(String urlString) {
        try {
            Request request = new Request.Builder()
                    .url(urlString)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    return response.body().string();
                }
                return null;
            }
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
}
