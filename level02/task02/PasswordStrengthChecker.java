package task02;

import java.util.Scanner;

import static java.lang.Character.*;

public class PasswordStrengthChecker {
    static Scanner scanner = new Scanner(System.in);
    private boolean checkNum(String password){
        for(int i = 0; i < password.length(); i++){
            if (isDigit(password.charAt(i))){
                System.out.println("Digit check complete.");
                return true;
            }
        }
        return false;
    }
    private boolean checkLowerCase(String password){
        for(int i = 0; i < password.length(); i++){
            if (isLowerCase(password.charAt(i))){
                System.out.println("LowerCase check complete.");
                return true;
            }
        }
        return false;
    }
    private boolean checkUpperCase(String password){
        for(int i = 0; i < password.length(); i++){
            if (isUpperCase(password.charAt(i))){
                System.out.println("UpperCase check complete.");
                return true;
            }
        }
        return false;
    }
    private boolean checkSpecialChars(String password){
        for(int i = 0; i < password.length(); i++){
            if (!Character.isLetterOrDigit(password.charAt(i)) &&
                    !Character.isWhitespace(password.charAt(i))){
                System.out.println("SpecialChar check complete.");
                return true;
            }
        }
        return false;
    }
    public int strengthDeterminator(String password){
        int strength = 0, length = password.length();

        if(length > 8 && length < 12)
            strength += 2;
        else if(length  > 12)
            strength += 3;
        else
            return 0;

        if(checkNum(password))
            strength += 2;
        else if (checkLowerCase(password))
            strength += 2;
        else if(checkUpperCase(password))
            strength += 2;
        else if(checkSpecialChars(password))
            strength += 2;

        return strength;
    }
    public String feedback(int strength) {
        if (strength >= 8) {
            return "Excellent! Your password is very strong. Keep it up!";
        } else if (strength >= 5) {
            return "Good! Your password is strong, but you can make it even stronger.";
        } else if (strength >= 3) {
            return "Fair. Your password could be stronger. Consider adding more complexity.";
        } else {
            return "Weak. Please improve your password by making it longer and more complex.";
        }
    }

    public static void main(String[] args) {
        System.out.println("\nWelcome to Kartik's Password Strength Checker!");
        PasswordStrengthChecker check = new PasswordStrengthChecker();
        boolean flag = true;

        while (flag){
            System.out.println("\nEnter your password: ");
            String password = scanner.next();

            int passwordStrength = check.strengthDeterminator(password);
            String passwordFeedback = check.feedback(passwordStrength);

            System.out.println(passwordFeedback);

            System.out.println("\nDo you want to check the strength of another password?(y/n): ");
            String response = scanner.next();

            if(response.equals('n')) {
                System.out.println("Thank you for using Kartik's Password Strength Checker!");
                flag = false;
            }
        }
    }
}
