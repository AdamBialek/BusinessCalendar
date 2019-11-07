package com.businesscalendar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodaysNews {
        public String buildUrl(){
            final String Api_Url = "https://api.nytimes.com/svc/topstories/v2/";

            String Api_WhatNews = "business";

            final String Api_Key = ".json?api-key=xiE45x0Ko9i4PoeHRqEU9rGDYWi4AGjI";

            String requestURL = new StringBuilder(Api_Url).append(Api_WhatNews).append(Api_Key).toString();

            return requestURL;
        }

        public String getResponse(String url) throws IOException {
            String response = "";

            response = new ApiConnectionService().connectionWithAPI(url);

            return response;
        }

        public void parseJson(String response) {
            JSONObject rootObject = new JSONObject(response);
            JSONArray articlesArray = rootObject.getJSONArray("results");
            List<JSONObject> articlesObjects = new ArrayList<JSONObject>();
            for (Object a: articlesArray
                 ) {
                JSONObject jsonObject = new JSONObject(a);
                String title = jsonObject.getString("title");
                String url = jsonObject.getString("url");
                String preview = jsonObject.getString("abstract");
                JSONArray multimedia = jsonObject.getJSONArray("multimedia");
                JSONObject multiSmall = multimedia.getJSONObject(0);
                String picSmall = multiSmall.getString("url");
                JSONObject multiBig = multimedia.getJSONObject(3);
                String picBig = multiBig.getString("url");
            }


        }



}
