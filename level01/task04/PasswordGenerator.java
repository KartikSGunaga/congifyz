package task04;

import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    static Scanner scanner = new Scanner(System.in);
    static Random rd = new Random();

//    public static char getNumber(){
//        return (char)rd.nextInt(10);
//    }

    public char getRandom(String source){
        int index = rd.nextInt(source.length());
        return source.charAt(index);
    }

    public String generatePassword(int nums, int upperCase, int lowerCase, int spChars){
        int length = nums + upperCase + lowerCase + spChars;
        int passwordLength = 0;
        StringBuilder password = new StringBuilder();

        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String spchars = "~!@#$%^&*()_+:\\?>;'<}{][|=-`/\"";

        while (passwordLength != length) {

            int randomChoice = rd.nextInt(4);
            if (randomChoice == 0 && nums > 0) {
                password.append(getRandom(numbers));
                nums -= 1;
                passwordLength ++;
            }
            else if (randomChoice == 1 && upperCase > 0) {
                password.append(getRandom(upperAlphabet));
                upperCase --;
                passwordLength ++;
            }
            else if (randomChoice == 2 && lowerCase > 0) {
                password.append(getRandom(lowerAlphabet));
                lowerCase --;
                passwordLength ++;
            }
            else {
                password.append(getRandom(spchars));
                spChars --;
                passwordLength ++;
            }
        }
        return password.toString();
    }

    public static void main(String[] args) {
        PasswordGenerator passGen = new PasswordGenerator();
        String generatedPassword, cont = null;
        System.out.println("\nWelcome to Kartik's Password Generator!");

        do {
            try {
                System.out.println("Do you wish to specify password requirements?(y/n): ");
                String response = scanner.next().toLowerCase();

                if (response.equals("n")) {
                    int length = 17;
                    int nums = rd.nextInt(1, length - 5);
                    int upperCase = rd.nextInt(1, length - nums - 5);
                    int lowerCase = rd.nextInt(1, length - upperCase - 5);
                    int spChars = rd.nextInt(1, length - lowerCase - 5);

                    generatedPassword = passGen.generatePassword(nums, upperCase, lowerCase, spChars);
                } else {
                    System.out.println("\nEnter the number of digits you want in your password: ");
                    int nums = scanner.nextInt();
                    System.out.println("\nEnter the number of uppercase you want in your password: ");
                    int upperCase = scanner.nextInt();
                    System.out.println("\nEnter the number of lowercase you want in your password: ");
                    int lowerCase = scanner.nextInt();
                    System.out.println("\nEnter the number of special characters you want in your password: ");
                    int spChars = scanner.nextInt();

                    generatedPassword = passGen.generatePassword(nums, upperCase, lowerCase, spChars);
                }
                System.out.println("Generated password: " + generatedPassword);
            } catch (Exception e) {
                System.out.println("Exception => " + e.getMessage());
            }
            try{
                System.out.println("Do you want to generate another password? (y/n): ");
                cont = scanner.next().toLowerCase();
            }catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        } while (cont.equals("y"));
        System.out.println("\nThank you for using Kartik's Password Generator!");
    }
}
