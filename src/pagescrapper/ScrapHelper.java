/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagescrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author devStar
 */
public class ScrapHelper {

    private static final OkHttpClient client = new OkHttpClient();

    /**
     * @desc This method uses OkHttp library
     *
     * @param urlString URL String
     * @return HTML Content of WebSite specified with urlString
     */
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

    /**
     * @desc This method uses JDK method (original-version)
     *
     * @param urlString URL String
     * @return HTML Content of WebSite specified with urlString
     */
    public static String getHTML(String urlString) {
        try {
            URL oracle = new URL(urlString);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            StringBuilder builder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                builder.append(inputLine);
            }
            in.close();
            return builder.toString();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
