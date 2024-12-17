
package com.home;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static String apikey;
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

            try {
                
                if (apikey.equals("clear")) {

                    System.out.println("\nDo you want to clear the consol?(y/n)");
                    
                    String clearChoice = scanner.nextLine();
    
                    if (clearChoice.equals("y")) {
                        clearConsol();
                        
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
            
            if (validateKey(apikey)) {
                System.out.println("API KEY VALIDATED!");
                keyValid = true;
            } else {
                System.out.println("\nERROR! Failed to validate API key");
                keyValid = false;
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
}