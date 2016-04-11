package ru.student_app.student;

import org.json.JSONArray;
import org.json.JSONObject;

import ru.student_app.student.JSONParser;

public class ReceiveSelfInfo {
    //URL страницы
    private String serverURL = "http://student-app.ru/json.php";

    //Ключи данных
    private static final String USERNAME = "name";
    private static final String USER_LASTNAME = "last_name";
    private static final String USER_SONAME = "soname";

    JSONArray user = null;
    JSONParser infoParser = new JSONParser();

    JSONObject info = infoParser.getJSONFromUrl(serverURL);


}
