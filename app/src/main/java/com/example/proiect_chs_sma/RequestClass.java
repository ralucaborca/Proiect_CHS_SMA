package com.example.proiect_chs_sma;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RequestClass {
    private static HttpsURLConnection connection;
    public static void method (String[] args){
        try {
            URL url = new URL("https://regmed.cmr.ro/api/v2/public/medic/cautare/pop");
            connection = (HttpsURLConnection) url.openConnection();

            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
