package com.shehrozwali.travelpartner1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    private HashMap<String,String>parseJsonObject(JSONObject obj){
        HashMap<String, String> dataList=new HashMap<>();
        try {
            String name=obj.getString("name");
            String latitude=obj.getJSONObject("geometry")
                    .getJSONObject("location").getString("lat");
            String longitude=obj.getJSONObject("geometry")
                    .getJSONObject("location").getString("lng");

            dataList.put("name",name);
            dataList.put("lat",latitude);
            dataList.put("lng",longitude);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private List<HashMap<String,String>> parseJsonArray(JSONArray jsonArray)  {
        List <HashMap<String,String>> dataList=new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            HashMap<String,String> data= null;
            try {
                data = parseJsonObject((JSONObject)jsonArray.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dataList.add(data);
        }
        return dataList;
    }
    List<HashMap<String, String>>parseResult(JSONObject object){
        JSONArray jsonArray=null;

        try {

            jsonArray=object.getJSONArray("results");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parseJsonArray(jsonArray);
    }
}
