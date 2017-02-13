package com.haiying.p2papp.view;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/6.
 */
public class ObjectToJson {


    /**
     * javabean to json
     *
     * @param person
     * @return
     */
    public static String javabeanToJson(Const person) {
        Gson gson = new Gson();
        String json = gson.toJson(person);
        return json;
    }

    /**
     * list to json
     *
     * @param list
     * @return
     */
    public static String listToJson(List<Const> list) {

        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * map to json
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, Const> map) {

        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }

}
