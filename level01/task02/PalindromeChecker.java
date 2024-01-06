package task02;

import java.util.Scanner;

public class PalindromeChecker {
    static Scanner scanner =  new Scanner(System.in);

    public boolean isPalindrome(String word){
        word = word.toLowerCase().replaceAll("\\s", "");
        int length = word.length();
        int start = 0, end = length - 1;

        while(start < end){
            if(word.charAt(start) != word.charAt(end))
                return false;
            start ++;
            end --;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Kartik's Palindrome Checker!");

        System.out.println("\nEnter your word: ");
        String word = scanner.nextLine();

        PalindromeChecker check = new PalindromeChecker();
        if(check.isPalindrome(word))
            System.out.println("\nThe word \"" + word + "\" is a palindrome.");
        else
            System.out.println("\nThe word \"" + word + "\" is not a palindrome.");

        scanner.close();
    }

}
