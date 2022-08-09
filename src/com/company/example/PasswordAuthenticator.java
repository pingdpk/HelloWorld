package com.company.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Formatter;

public class PasswordAuthenticator {

    public static void main(String[] args) {
        System.out.println(24 + 5 * 3 % (1+1));

        String password;

        try {
            File myObj = new File("Passwords.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        while(true){
            System.out.println("Please enter password: ");
            Scanner sc = new Scanner(System.in);
            String firstPassword = sc.nextLine();

            System.out.println("Please confirm password: ");
            Scanner sc1 = new Scanner(System.in);
            String secondPassword = sc.nextLine();

            if(firstPassword.equals(secondPassword)) {
                System.out.println("Password confirmed.");
                password = secondPassword;
                break;
            } else if(firstPassword != secondPassword) {
                try {
                    FileWriter myWriter = new FileWriter("Passwords.txt", true);
                    myWriter.write(secondPassword);
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                System.out.println("\nPasswords are not a match. Retry!\n");
            }

        }

    }
}
