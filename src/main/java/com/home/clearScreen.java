
package com.home;

import java.util.Scanner;

public class clearScreen {

    public static String apiKey;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to the news app!");
        System.out.println("\nTo access the app, you must subscribe to a free API key from newsapi.org");

        boolean KeyValid = false;

        while (!KeyValid) {

            System.out.println("\nPlease enter your API key");

            apiKey = scanner.nextLine();

            if (apiKey.equals("exit")) {
                System.out.println("\nExiting Program\n");
                System.exit(0);
            }

            if (apiKey.isBlank()) {
                System.out.println("\nERROR! API Key cannot be blank!");
                continue;
            }

            try {

                
            if (apiKey.equals("clear")) {
                System.out.println("\nDo you want to clear the consol? (y/n)");
                String confirmation = scanner.nextLine();

                if (confirmation.equals("y")) {
                    clearConsol();
                    
                } else if (confirmation.equalsIgnoreCase("y")) {
                    System.out.println("Screen Clear Aborted");
                    
                } else{
                    throw new IllegalArgumentException("Invalid Choice");
                    
                }
                
            } 
                

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            
        }
    }

    public static void clearConsol(){

        try {
            
            if (System.getProperty("os.name").contains("Windows")) {

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

            System.out.println("\nConsol Cleared");

            
        } catch (Exception e) {
            System.out.println("ERROR Clearing Consol");
        }
    }
}