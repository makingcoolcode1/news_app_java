
package com.home;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static String apikey;
    public static String newsSearch;
    public static String apiURL = "https://newsapi.org/v2/everything";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nWelcome to the news app!");
        System.out.println("To access the app, you must have an API key from newsapi.org");

        boolean keyValid = false;

        while (!keyValid) {

            System.out.println("\nPlease enter your API key: ");

            apikey = scanner.nextLine();

            if (apikey.equals("exit")) {
                System.out.println("Exiting Program....");
                System.exit(0);
            }

            if (apikey.isBlank()) {
                System.out.println("ERROR! API key cannot be blank....");
                continue;
            } 

            boolean consolCleared = false;

            try {
                
                if (apikey.equals("clear")) {

                    System.out.println("\nDo you want to clear the consol?(y/n)");
                    
                    String clearChoice = scanner.nextLine();
    
                    if (clearChoice.equals("y")) {
                        clearConsol();
                        consolCleared = true;
                        
                        System.out.println("\nConsol Cleared!");
                    } else if (clearChoice.equals("n")) {
                        System.out.println("\nConsol Clear Aborted!");
                        
                    } else{
                        throw new IllegalArgumentException("\nERROR! Invalid Choice!");
                    }

                    
                }


            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            if (!consolCleared) {

                // Attempt to validate API kay

                if (validateKey(apikey)) {
                    System.out.println("API KEY VALIDATED!");
                    keyValid = true;
                } else {
                    System.out.println("\nERROR! Failed to validate API key");
                    keyValid = false;

                }
                
            }
            

        }

        while (true) {
            
            System.out.println("Enter a news query: ");

            newsSearch = scanner.nextLine();

            if (newsSearch.equals("exit")) {
                System.out.println("Exiting Program....");
                System.exit(0);
            }

            if (newsSearch.isBlank()) {
                System.out.println("ERROR! News query cannot be blank!");
                continue;
                
            }

            // Search News Query

            try {
                
                URI newsURI = new URI(apiURL + "?q=" + newsSearch + "&apiKey=" + apikey);
                URL newsURL = newsURI.toURL();

                HttpURLConnection mainConnection = (HttpURLConnection) newsURL.openConnection();

                if (mainConnection.getResponseCode() == (HttpURLConnection.HTTP_OK)) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(mainConnection.getInputStream()));

                    String line;

                    StringBuffer response = new StringBuffer();
                    
                    while ((line = reader.readLine())!=null) {
                        response.toString();
                        
                    }

                    parseNewsData(response.toString());
                    
                }



            } catch (Exception e) {
                // TODO: handle exception
            }

            
        }
        
    }

    public static void clearConsol() {

        try {
            
            if (System.getProperty("os.name").contains("Windows")) {

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                
            } else {
                System.out.print("033[H\033[2J");
                System.out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean validateKey(String apiKey) {

        try {
            
            URI testURI = new URI(apiURL + "?q=ravens&apiKey=" + apiKey);
            URL testURL = testURI.toURL();

            HttpURLConnection testConnection = (HttpURLConnection) testURL.openConnection();

            testConnection.setRequestMethod("GET");

            int testReturn = testConnection.getResponseCode();

            return testReturn == 200;


        } catch (Exception e) {
            e.printStackTrace();
        }
                return false;
    }

    public static void parseNewsData(String getData) {

    }
    
}