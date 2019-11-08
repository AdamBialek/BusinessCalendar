package com.businesscalendar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class Article {

        private String title;

        private String url;

        private String preview;

        private String picSmall;

        private String picBig;

        private String choice="";

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

    public void chooseNews(NewsType val){
        setChoice(val.toString());
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

        public Set<Article> parseJson(String response) {
            JSONObject rootObject = new JSONObject(response);
            
            JSONArray articlesArray = rootObject.getJSONArray("results");

            Set<Article> articleSet = new LinkedHashSet<>();

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


                articleSet.add(article);


            }

                return articleSet;
        }


}
