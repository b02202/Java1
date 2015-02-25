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
            JSONObject data = new JSONObject(content).getJSONObject("data");
            JSONArray children = data.getJSONArray("children");
            List<MLB> mlbList = new ArrayList<>();

            for (int i = 0; i < children.length() ; i++) {
                JSONObject obj = children.getJSONObject(i);
                MLB mlb = new MLB();

                // Populate MLB
                mlb.setTitle("title");
                mlb.setAuthor("author");
                mlb.setDomain("permalink");

                // add to mlbList
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
