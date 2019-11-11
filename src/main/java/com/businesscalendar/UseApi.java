package com.businesscalendar;

import java.io.IOException;
import java.util.List;

public interface UseApi {

    String buildUrl() throws IOException;

    String getResponse(String string) throws IOException;

    List parseJson(String string);
}
