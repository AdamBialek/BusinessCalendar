package com.businesscalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConnectionService {

    public String connectionWithAPI(String url) throws IOException {

        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

        String line = "";
        StringBuilder stringBuilder=new StringBuilder();
        while ((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }
}