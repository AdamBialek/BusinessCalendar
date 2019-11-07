package com.businesscalendar;

import java.io.IOException;

public class TodaysNews {
        public final String Api_Url = "https://api.nytimes.com/svc/topstories/v2/";

        public String Api_WhatNews = "business";

        public final String Api_Key = ".json?api-key=xiE45x0Ko9i4PoeHRqEU9rGDYWi4AGjI";

        public String requestURL = new StringBuilder(Api_Url).append(Api_WhatNews).append(Api_Key).toString();

        public String getResponse(String url) throws IOException {
            String response = "";

            response = new ApiConnectionService().connectionWithAPI(requestURL);

            return response;
        }



}
