
package com.home;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Scanner;

import javax.print.DocFlavor.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static String mainURL = "https://newsapi.org";

    public static String apiKey;
    public static String newsSearch;

    public static void main(String[] args) {

        System.out.println("\n**Welcome to the news app");
        System.out.println("\nTo begin, you must subscribe to an API key from https://newsapi.org/");
        
        Scanner scanner = new Scanner(System.in);

        boolean keyIsValid = false;

        while (!keyIsValid) {

            System.out.println("\nPlease Enter your API Key:");

            apiKey = scanner.nextLine();

            if (apiKey.equals("exit")) {
                System.out.println("\nExiting Program");
                break; 
            }

            if (apiKey.isBlank()) {
                System.out.println("\nERROR: API Key is required to access the application");
                break;
            }

            if (testVerify(apiKey)) {
                System.out.println("\nAPI KEY VALIDATED!");
                keyIsValid = true;
                
            } else {
                System.out.println("ERROR! API Key is not valid!");
            }
            
        }

        while (true) {

            System.out.println("Enter a keyword to search: ");
            newsSearch = scanner.nextLine();

            try {
                
                URI newsURI = new URI(buildAPIUrl(newsSearch));
                java.net.URL newsURL = newsURI.toURL();

                HttpURLConnection newsConnection = (HttpURLConnection) newsURL.openConnection();

                if (newsConnection.getResponseCode() == (HttpURLConnection.HTTP_OK)) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(newsConnection.getInputStream()));

                    String line;

                    StringBuffer response = new StringBuffer();

                    while ((line = reader.readLine())!=null) {
                        response.append(line);
                        
                    }
                    
                    parseNewsData(response.toString());
                    
                } else {
                    System.out.println("\nERROR! Unable to connect to API");
                }

            } catch (Exception e) {
                // TODO: handle exception
            }

            
        }
    }

    public static boolean testVerify(String apiKey){

        try {
            
            URI testURI = new URI(mainURL + "/v2/everything?q=bitcoin&apiKey=" + apiKey);
            java.net.URL testURL = testURI.toURL();

            HttpURLConnection testConnect = (HttpURLConnection) testURL.openConnection();

            testConnect.setRequestMethod("GET");

            int testRequest = testConnect.getResponseCode();

            return testRequest == 200;

            

        } catch (Exception e) {
            // TODO: handle exception
            return false;

        }
    }


    public static String buildAPIUrl(String newsSearch) {
        return String.format(mainURL + "/v2/everything?q="+ newsSearch +"&apiKey=" + apiKey);
    }

    public static void parseNewsData(String  newsData){
        
    JSONObject json = new JSONObject(newsData);

    JSONArray articleArr = json.getJSONArray("articles");

    JSONObject zeroObject = articleArr.getJSONObject(0);
        
    String title_0 = zeroObject.getString("title");

    System.out.println("Title: " + title_0);

    }
}