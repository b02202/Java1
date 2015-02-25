package com.robertbrooks.project_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Bob on 2/25/2015.
 */
public class ManageHttp {

    public static String getData(String urlString)
    {

        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder sBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String dataLine;
            while ((dataLine = reader.readLine()) != null)
            {
                sBuilder.append(dataLine + "\n");
            }

            return sBuilder.toString();

        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
