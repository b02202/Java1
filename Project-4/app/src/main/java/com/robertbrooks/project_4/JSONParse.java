// Robert Brooks
// JSONParse.java
package com.robertbrooks.project_4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bob on 2/25/2015.
 */
public class JSONParse {
    public static List<MLB> parse(String content)
    {
        try{
            // get complete JSON object from Reddit api
            JSONObject data = new JSONObject(content).getJSONObject("data");
            // create JSON Array from data JSON object
            JSONArray children = data.getJSONArray("children");
            // Create MLB class arrayList
            List<MLB> mlbList = new ArrayList<>();
            // Loop through JSON Array
            for (int i = 0; i < children.length() ; i++) {
                // Create JSON object from "data" object in children JSON Array
                JSONObject obj = children.getJSONObject(i).getJSONObject("data");
                // create MLB instance
                MLB mlb = new MLB();

                // Populate MLB data
                mlb.setTitle(obj.getString("title"));
                mlb.setAuthor(obj.getString("author"));
                mlb.setDomain(obj.getString("domain"));

                // add to mlbList ArrayList
                mlbList.add(mlb);
            }
            return mlbList;
        } catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
