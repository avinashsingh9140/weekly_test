package org.geekster;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;
        System.out.println(responseCode);
        String urlString = "https://api.nationalize.io/?name=nathaniel";


        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("problem in URL");
        }

        //connection

        try {
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("connection problem");
        }

//        extract information from the connection object:

        if(responseCode == 200)
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apiData = new StringBuilder();
            String readLine = null;

            while((readLine = in.readLine())!= null)
            {
                apiData.append(readLine);
            }


            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(apiData.toString());
            try{
                JSONObject jsonAPIResponse = new JSONObject(apiData.toString());


//             System.out.println(jsonAPIResponse.get("count"));
//             System.out.println(jsonAPIResponse.get("name"));
//             System.out.println(jsonAPIResponse.get("country"));
//             System.out.println(jsonAPIResponse.get("utc_offset_seconds"));

                JSONObject jsonAPIResponse1 = new JSONObject(jsonAPIResponse.get("hourly").toString());
                // System.out.println(jsonAPIResponse1.get("temperature_2m"));

                Object o1 = jsonAPIResponse1.get("temperature_2m");
                String s1 = o1.toString();
                s1 = s1.substring(1,s1.length()-1);

                String[] strArray = null;
                strArray = s1.split(",");

                for(String s : strArray)
                {
                    System.out.println(s);
                }
                //System.out.println(jsonAPIResponse.toString());
            }
            catch(JSONException e){
                System.out.print(e);
            }

        }
//        else
//            System.out.println("API call could not be made!!!");



    }
}