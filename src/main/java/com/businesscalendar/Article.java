package com.businesscalendar;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.*;

public class Article {

        private String title;

        private String url;

        private String preview;

        private String picSmall;

        private String picBig;

        private String choice="";

        private Image image;




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "TodaysNews{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", preview='" + preview + '\'' +
                ", picSmall='" + picSmall + '\'' +
                ", picBig='" + picBig + '\'' +
                '}';
    }

    public void chooseNews(String string){
        setChoice(string);
    }

    public String buildUrl(){
            final String Api_Url = "https://api.nytimes.com/svc/topstories/v2/";
            String Api_WhatNews="";

            if(choice.equals("")){
                Api_WhatNews = "business";
            } else {
                Api_WhatNews=choice;
            }


            final String Api_Key = ".json?api-key=xiE45x0Ko9i4PoeHRqEU9rGDYWi4AGjI";

            String requestURL = new StringBuilder(Api_Url).append(Api_WhatNews).append(Api_Key).toString();

            return requestURL;
        }

        public String getResponse(String url) throws IOException {
            String response = "";

            response = new ApiConnectionService().connectionWithAPI(url);

            return response;
        }

//    public Image getImage(String url) throws IOException {
//        BufferedImage image = null;
//
//        URL link = new URL(url);
//
//        InputStream inputStream = new BufferedInputStream(link.openStream());
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int n=0;
//
//        while (-1!=(n=inputStream.read(buffer))){
//            out.write(buffer,0,n);
//        }
//        out.close();
//        inputStream.close();
//        byte[] response = out.toByteArray();
//
//        int index = url.lastIndexOf("/");
//        String filename = url.substring(index+1);
//
//        FileOutputStream fileOutputStream = new FileOutputStream("C:/Images/businessCalendar/src/main/resources/images/"+filename);
//
//        Image imageToDisplay = new Image("file")
//
//        return response;
//    }

        public List<Article> parseJson(String response) throws IOException {
            JSONObject rootObject = new JSONObject(response);
            
            JSONArray articlesArray = rootObject.getJSONArray("results");

            List<Article> articleLinkedList = new LinkedList<>();

            int counter=0;

            for (Object a: articlesArray
                 ) {
                Article article = new Article();
                JSONObject jsonObject = articlesArray.getJSONObject(counter);
                counter++;

                String title = jsonObject.getString("title");
                String url = jsonObject.getString("url");
                String preview = jsonObject.getString("abstract");
                JSONArray multimedia = jsonObject.getJSONArray("multimedia");

                if(multimedia.length()!=0){
                    JSONObject multiSmall = multimedia.getJSONObject(0);
                    String picSmall = multiSmall.getString("url");
                    JSONObject multiBig = multimedia.getJSONObject(3);
                    String picBig = multiBig.getString("url");
                    article.setPicBig(picBig);
                    article.setPicSmall(picSmall);
                }


                article.setTitle(title);
                article.setUrl(url);
                article.setPreview(preview);


                articleLinkedList.add(article);


            }

                return articleLinkedList;
        }



}
