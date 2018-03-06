package ru.atc.uss.app.util;

import com.google.gson.Gson;

import java.util.TreeMap;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class NapiErrorHandler {

    public static TreeMap<String, String> errorsMap;

    static {
        Gson gson = new Gson();
        errorsMap = gson.fromJson(Config.NAPI_ERRORS, TreeMap.class);
    }
}
