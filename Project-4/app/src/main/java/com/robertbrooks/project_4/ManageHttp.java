// Robert Brooks
// ManageHttp.java
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
        // Create buffered reader
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            // Create HttpURLConnection with url
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // Create String Builder
            StringBuilder sBuilder = new StringBuilder();
            // Set Buffered Reader to read input stream from HttpURLConnection
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String dataLine;
            // Loop through input Stream and append String Builder
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
            // close Buffered Reader
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
